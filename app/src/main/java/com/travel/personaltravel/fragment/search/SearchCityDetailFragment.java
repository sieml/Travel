package com.travel.personaltravel.fragment.search;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.travel.personaltravel.activity.CityCommentTripActivity;
import com.travel.personaltravel.activity.FoodActivity;
import com.travel.personaltravel.activity.SearchTestActivity;
import com.travel.personaltravel.activity.ShoppingActivity;
import com.travel.personaltravel.activity.SpotActivity;
import com.travel.personaltravel.activity.Travelguide;
import com.travel.personaltravel.activity.trip.GridPicActivity;
import com.travel.personaltravel.activity.trip.PersonDetailInfoActivity;
import com.travel.personaltravel.activity.trip.TravelNoteActivity;
import com.travel.personaltravel.adapter.MultiItemCommonAdapter;
import com.travel.personaltravel.adapter.MultiItemTypeSupport;
import com.travel.personaltravel.adapter.ViewHolder;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.model.searchresult.SearchCityDetail;
import com.travel.personaltravel.model.searchresult.SearchLocalExpert;
import com.travel.personaltravel.widget.MyListView;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchCityDetailFragment#newInstance} factory method to
 * create an instance of this fragment.S
 */
public class SearchCityDetailFragment extends Fragment implements View.OnClickListener, MultiItemTypeSupport<SearchLocalExpert> {

    //参数 key
    private static final String ARG_ID = "cityID";

    //接收的参数值
    private String mCityId;
    private String cityDetailUrl;
    private SearchCityDetail data;

    //6张图片网址集合
    private List<String> urlList;
    private List<SearchLocalExpert> expertsList;

    //当地达人的列表
    private MultiItemCommonAdapter adapter;

    //zern的 达人listView
    @ViewInject(R.id.fcd_listView)
    private MyListView expertListView;

    public static SearchCityDetailFragment newInstance(String searchCityId) {
        SearchCityDetailFragment fragment = new SearchCityDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ID, searchCityId);
        fragment.setArguments(args);
        return fragment;
    }

    public SearchCityDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCityId = getArguments().getString(ARG_ID);
            cityDetailUrl = String.format(SieConstant.SEARCH_CITY_DETAIL, mCityId);
        }

        data = new SearchCityDetail();
        urlList = new LinkedList<>();
        expertsList = new LinkedList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.search_city_detail_fragment, container, false);
        ViewUtils.inject(this, ret);

        newExpertListAdapter();

        return ret;
    }

    private void newExpertListAdapter() {
        adapter = new MultiItemCommonAdapter<SearchLocalExpert>(getActivity(), expertsList, this) {

            @Override
            public void convert(ViewHolder holder, SearchLocalExpert searchLocalExpert) {
                holder.setText(R.id.item_comment_nickname, searchLocalExpert.getNickName());
                holder.setText(R.id.item_comment_address, searchLocalExpert.getResidence());
                holder.setText(R.id.item_comment_profile, searchLocalExpert.getExpertInfo().getProfile());
                holder.setText(R.id.item_comment_level, searchLocalExpert.getLevel() + "");
                bitmapUtils.display(holder.getView(R.id.item_comment_avatarSmall), searchLocalExpert.getAvatarSmall());
            }

        };
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //达人列表的适配器Adapter设置
        expertListView.setAdapter(adapter);

        //TODO 设置一些控件的点击监听
        setOnClickEvent();
        //链接网络,加载6张大图片
        getSixViewPortPhoto();


    }

    //事件 监听
    private void setOnClickEvent() {

        search_scdf_guide.setOnClickListener(this);
        search_scdf_spot.setOnClickListener(this);
        search_scdf_food.setOnClickListener(this);
        search_scdf_shopping.setOnClickListener(this);
        search_scdf_recommend_trip.setOnClickListener(this);
        search_scdf_relative_note.setOnClickListener(this);
        fcd_all.setOnClickListener(this);
    }


    private void getLocalExpert() {
        // TODO 加载是ListView里面的网络JSON数据请求
        HttpUtils httpUtilsComm = new HttpUtils();
        try {

            if (data.getZhName() != null) {

                String cityName = URLEncoder.encode(data.getZhName(), "utf-8");
                String localExpertUrl = String.format(SieConstant.CITY_LOCAL_EXPERT, cityName);
//                Log.d("expertUrl", "expertUrl = " + localExpertUrl);
                //得到 评价列表的 JSON数据
                httpUtilsComm.send(HttpRequest.HttpMethod.GET, localExpertUrl, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String jsonStr = responseInfo.result;
                        try {
                            List<SearchLocalExpert> localExperts = SearchLocalExpert.parseJSON(jsonStr);
                            Log.d("", "");
                            if (localExperts != null && localExperts.size() > 0) {
                                expertsList.addAll(localExperts);
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {

                    }
                });
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void getSixViewPortPhoto() {
        //判断网址
        if (cityDetailUrl != null && cityDetailUrl.length() > 0) {
//            Log.d("cityDetailUrl = ",cityDetailUrl);
            HttpUtils httpUtils = new HttpUtils();

            httpUtils.send(HttpRequest.HttpMethod.GET, cityDetailUrl, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {

                    try {
                        data = SearchCityDetail.parseJSON(responseInfo.result);

                        if (data.getImages() != null) {

                            for (int i = 0; i < data.getImages().size(); i++) {
                                urlList.add(data.getImages().get(i).getThumb());
                            }
                            //链接网络, 加载当地达人
                            getLocalExpert();
                            downPhoto();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(HttpException error, String msg) {

                }
            });
        }
    }

    private BitmapUtils bitmapUtils = AiLYApplication.bitmapUtils;

    @ViewInject(R.id.search_scdf_img0)
    private ImageView viewportImg0;

    @ViewInject(R.id.search_scdf_img1)
    private ImageView viewportImg1;

    @ViewInject(R.id.search_scdf_img2)
    private ImageView viewportImg2;

    @ViewInject(R.id.search_scdf_img3)
    private ImageView viewportImg3;

    @ViewInject(R.id.search_scdf_img4)
    private ImageView viewportImg4;

    @ViewInject(R.id.search_scdf_img5)
    private ImageView viewportImg5;

    //----------------------------------------
    //TODO 反射 文本信息控件
    //城市
    @ViewInject(R.id.search_scdf_zhName)
    private TextView vpZhName;

    //天数
    @ViewInject(R.id.search_scdf_timeCostDesc)
    private TextView vpTravelTime;

    //最佳季节
    @ViewInject(R.id.search_scdf_BestSeason)
    private TextView vpBestSeason;

    //地理位置
    @ViewInject(R.id.search_scdf_desc)
    private TextView vpDesc;

    private void downPhoto() {
        LinkedList<ImageView> ivList = new LinkedList<>();
        ivList.add(viewportImg0);
        ivList.add(viewportImg1);
        ivList.add(viewportImg2);
        ivList.add(viewportImg3);
        ivList.add(viewportImg4);
        ivList.add(viewportImg5);
        for (int i = 0; i < urlList.size(); i++) {
            bitmapUtils.display(ivList.get(i), urlList.get(i));
        }

        //文字信息
        vpZhName.setText(data.getZhName());
        vpTravelTime.setText("~推荐旅行-" + data.getTimeCostDesc());
        vpBestSeason.setText("~最佳季节: " + data.getTravelMonth());
        vpDesc.setText(data.getDesc());
    }

    @Override
    public int getLayoutId(int position, SearchLocalExpert searchLocalExpert) {
        return R.layout.search_city_expert_list_item;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position, SearchLocalExpert searchLocalExpert) {
        return 0;
    }

    @ViewInject(R.id.search_scdf_guide)
    private ImageView search_scdf_guide;

    @ViewInject(R.id.search_scdf_spot)
    private ImageView search_scdf_spot;

    @ViewInject(R.id.search_scdf_shopping)
    private ImageView search_scdf_shopping;

    @ViewInject(R.id.search_scdf_food)
    private ImageView search_scdf_food;

    @ViewInject(R.id.search_scdf_recommend_trip)
    private TextView search_scdf_recommend_trip;

    @ViewInject(R.id.search_scdf_relative_note)
    private TextView search_scdf_relative_note;

    @ViewInject(R.id.fcd_all)
    private TextView fcd_all;

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent();

        switch (id) {
            case R.id.search_scdf_guide:
                intent.setClass(getActivity(), Travelguide.class);
                break;
            case R.id.search_scdf_spot:
                intent.setClass(getActivity(), SpotActivity.class);
                intent.putExtra(SieConstant.ACTION_EXTRA, mCityId);
                if (data != null) {
                    Log.d("cityName", "cName = " + data.getZhName());
                }
                intent.putExtra(SieConstant.ACTION_EXTRA_CITY_NAME, data.getZhName());
                break;
            case R.id.search_scdf_food:
                intent.setClass(getActivity(), FoodActivity.class);
                intent.putExtra("food_title", data.getZhName());
                intent.putExtra("city_id", mCityId);
                break;
            case R.id.search_scdf_shopping:
                intent.setClass(getActivity(), ShoppingActivity.class);
                intent.putExtra("shopping_title", data.getZhName());
                intent.putExtra("city_id", mCityId);
                break;
            case R.id.search_scdf_recommend_trip:
                intent.setClass(getContext(), CityCommentTripActivity.class);
                intent.putExtra("city_id", mCityId);
                break;
            case R.id.search_scdf_relative_note:
                intent.setClass(getActivity(), TravelNoteActivity.class);
                intent.putExtra(SieConstant.ACTION_EXTRA_CITY_NAME, data.getZhName());
                break;
            case R.id.fcd_all:
                intent.setClass(getActivity(), SearchTestActivity.class);
                break;

        }

        startActivity(intent);
    }

    //6张 图片父控件的点击事件
    @OnClick(value = {R.id.search_city_detail_cardview_pic})
    public void cardViewOnClick(View view) {
        Intent intent = new Intent(getActivity(), GridPicActivity.class);
//        Log.d("id", "mCityId = " + mCityId);
        intent.putExtra(SieConstant.ACTION_EXTRA, mCityId);
        intent.putExtra(SieConstant.ACTION_EXTRA_CITY_NAME, data.getZhName());
        startActivity(intent);
    }

    //listView的item点击事件
    @OnItemClick(value = R.id.fcd_listView)
    public void onListViewItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), PersonDetailInfoActivity.class);
        startActivity(intent);
    }
}
