package com.travel.personaltravel.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.travel.personaltravel.application.AiLYApplication;
import com.travel.personaltravel.R;
import com.travel.personaltravel.activity.*;
import com.travel.personaltravel.activity.trip.GridPicActivity;
import com.travel.personaltravel.activity.trip.PersonDetailInfoActivity;
import com.travel.personaltravel.activity.trip.TravelNoteActivity;
import com.travel.personaltravel.adapter.MultiItemCommonAdapter;
import com.travel.personaltravel.adapter.MultiItemTypeSupport;
import com.travel.personaltravel.adapter.ViewHolder;
import com.travel.personaltravel.model.CityDetail;
import com.travel.personaltravel.model.CityDetailComment;
import com.travel.personaltravel.model.CityDetailImg;
import com.travel.personaltravel.widget.MyListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.travel.personaltravel.constant.SieConstant;
/**
 * A simple {@link Fragment} subclass.
 */
public class CityDetailFragment extends Fragment implements View.OnClickListener, MultiItemTypeSupport<CityDetailComment> {
    @ViewInject(R.id.fragment_city_detail_relativeLayout)
    private RelativeLayout relativeLayout;
    private View view;
    private MultiItemCommonAdapter adapter;
    private List<CityDetailComment> datas;
    //找到控件
    @ViewInject(R.id.fcd_img0)
    private ImageView fcd_img0;
    @ViewInject(R.id.fcd_img1)
    private ImageView fcd_img1;
    @ViewInject(R.id.fcd_img2)
    private ImageView fcd_img2;
    @ViewInject(R.id.fcd_img3)
    private ImageView fcd_img3;
    @ViewInject(R.id.fcd_img4)
    private ImageView fcd_img4;
    @ViewInject(R.id.fcd_img5)
    private ImageView fcd_img5;
    //中文名字
    @ViewInject(R.id.fcd_zhName)
    private TextView fcd_zhName;
    //适宜时间
    @ViewInject(R.id.fcd_timeCostDesc)
    private TextView fcd_timeCostDesc;
    //最佳季节
    @ViewInject(R.id.fcd_travelMonth)
    private TextView fcd_travelMonth;
    @ViewInject(R.id.fcd_desc)
    private TextView fcd_desc;
    @ViewInject(R.id.fcd_guide)
    private ImageView fcd_guide;
    @ViewInject(R.id.fcd_spot)
    private ImageView fcd_spot;
    @ViewInject(R.id.fcd_food)
    private ImageView fcd_food;
    @ViewInject(R.id.fcd_shopping)
    private ImageView fcd_shopping;
    @ViewInject(R.id.fcd_commtrip)
    private Button fcd_commtrip;
    @ViewInject(R.id.fcd_commnote)
    private Button fcd_commnote;
    @ViewInject(R.id.fcd_all)
    private TextView fcd_all;
    @ViewInject(R.id.fcd_listView)
    private MyListView fcd_listView;

    private CityDetail cityDetail;

    private String cityDetailUrl;
    private String cityId;
    private BitmapUtils bitmapUtils = AiLYApplication.bitmapUtils;

    public static CityDetailFragment newInstance(String cityId) {
        CityDetailFragment ret = new CityDetailFragment();
        Bundle args = new Bundle();
        args.putString("cityId", cityId);
        ret.setArguments(args);
        return ret;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cityDetail = new CityDetail();
        cityId = getArguments().getString("cityId");
        cityDetailUrl = String.format(SieConstant.Url_City_Detail, cityId);
        datas = new ArrayList<CityDetailComment>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_city_detail, container, false);
        ViewUtils.inject(this, view);
        adapter = new MultiItemCommonAdapter<CityDetailComment>(getActivity(), datas, this) {

            @Override
            public void convert(ViewHolder holder, CityDetailComment cityDetailComment) {
                holder.setText(R.id.item_comment_nickname, cityDetailComment.getNickName());
                holder.setText(R.id.item_comment_address, cityDetailComment.getResidence());
                holder.setText(R.id.item_comment_profile, cityDetailComment.getExpertInfo().getProfile());
                holder.setText(R.id.item_comment_level, cityDetailComment.getLevel() + "");
                bitmapUtils.display(holder.getView(R.id.item_comment_avatarSmall), cityDetailComment.getAvatarSmall());
            }
        };
        fcd_listView.setAdapter(adapter) ;
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //批量设置 需要的监听事件
        setEvent();
        //去网络请求,首先得到Json数据,然后解析出来,再然后展示
        getJsonAndSetData();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent() ;
        switch (id) {
            case R.id.fcd_guide:
                intent.setClass(getActivity(), Travelguide.class) ;
                break;
            case R.id.fcd_spot:
                intent.setClass(getActivity(), SpotActivity.class);
                intent.putExtra(SieConstant.ACTION_EXTRA, cityId);
                intent.putExtra(SieConstant.ACTION_EXTRA_CITY_NAME, cityDetail.getZhName());
                break;
            case R.id.fcd_food:
                intent.setClass(getActivity(), FoodActivity.class) ;
                intent.putExtra("food_title",cityDetail.getZhName());
                intent.putExtra("city_id",cityId);
                break;
            case R.id.fcd_shopping:
                intent.setClass(getActivity(), ShoppingActivity.class) ;
                intent.putExtra("shopping_title",cityDetail.getZhName());
                intent.putExtra("city_id",cityId);
                break;
            case R.id.fcd_commtrip:
                //打開旅行計劃詳細窗口
                intent.setClass(getContext(), CityCommentTripActivity.class);
                intent.putExtra("userid", AiLYApplication.userId);
                intent.putExtra("name",cityDetail.getZhName());
                intent.putExtra("id", cityId);
                break;
            case R.id.fcd_commnote:
                intent.setClass(getActivity(), TravelNoteActivity.class);
                intent.putExtra(SieConstant.ACTION_EXTRA_CITY_NAME, cityDetail.getZhName());
                break;
            case R.id.fcd_all:
                break;


        }
        startActivity(intent);
    }


    @Override
    public int getLayoutId(int position, CityDetailComment cityDetailComment) {
        return R.layout.item_comment;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position, CityDetailComment cityDetailComment) {
        return 0;
    }


    //访问服务器,得到json数据
    private void getJsonAndSetData() {
        HttpUtils httpUtilsDetail = new HttpUtils();
        // 获取城市详情的网址
        if (cityDetailUrl != null) {
            httpUtilsDetail.send(HttpRequest.HttpMethod.GET, cityDetailUrl, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    //解析到JSON数据
                    String jsonStr = responseInfo.result;
                    try {
                        JSONObject jsonObject = new JSONObject(jsonStr);
                        JSONObject result = jsonObject.getJSONObject("result");
                        Gson gson = new Gson();
                        cityDetail = gson.fromJson(result.toString(), CityDetail.class);
                        if (cityDetail.getImages() != null) {
                            setAboveData();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onFailure(HttpException error, String msg) {
                }
            });
        } else {
        }

        // TODO 加载是ListView里面的网络JSON数据请求
        HttpUtils httpUtilsComm = new HttpUtils();
        String cityDetailComm = SieConstant.Url_City_CommentList;
        //得到 评价列表的 JSON数据
        httpUtilsComm.send(HttpRequest.HttpMethod.GET, cityDetailComm, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String jsonStr = responseInfo.result;
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONArray array = jsonObject.getJSONArray("result");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jb = array.getJSONObject(i);
                        Gson gson = new Gson();
                        CityDetailComment cdc = new CityDetailComment();
                        cdc = gson.fromJson(jb.toString(), CityDetailComment.class);
                        datas.add(cdc);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });
    }

    //设置 数据非ListView的数据 就是 处理  listView 的那些图片 标题啊.等等
    private void setAboveData() {

        List<CityDetailImg> images = cityDetail.getImages();
        //添加图片 TODO 用最笨的方法 后期再改善吧,用反射
        LinkedList<ImageView> ivList = new LinkedList<>();
        ivList.add(fcd_img0);
        ivList.add(fcd_img1);
        ivList.add(fcd_img2);
        ivList.add(fcd_img3);
        ivList.add(fcd_img4);
        ivList.add(fcd_img5);
        for (int i=0;i<images.size();i++){
            bitmapUtils.display(ivList.get(i), images.get(i).getThumb());
        }
        //添加文本信息
        fcd_zhName.setText(cityDetail.getZhName());
        fcd_timeCostDesc.setText("~推荐旅行-" + cityDetail.getTimeCostDesc());
        fcd_travelMonth.setText("~最佳季节: " + cityDetail.getTravelMonth());
        fcd_desc.setText(cityDetail.getDesc());
    }

    private void setEvent() {
        fcd_guide.setOnClickListener(this);
        fcd_spot.setOnClickListener(this);
        fcd_food.setOnClickListener(this);
        fcd_shopping.setOnClickListener(this);
        fcd_commtrip.setOnClickListener(this);
        fcd_commnote.setOnClickListener(this);
        fcd_all.setOnClickListener(this);
    }

    @OnClick(value = {R.id.fragment_city_detail_relativeLayout})
    public void relativieLayoutOnClick(View view) {
        Intent intent = new Intent(getActivity(), GridPicActivity.class);
        intent.putExtra(SieConstant.ACTION_EXTRA, cityId);
        startActivity(intent);
    }

    @OnItemClick(value = R.id.fcd_listView)
    public void onListViewItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), PersonDetailInfoActivity.class);
        startActivity(intent);
    }
}

