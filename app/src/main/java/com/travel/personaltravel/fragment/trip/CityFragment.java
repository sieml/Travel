package com.travel.personaltravel.fragment.trip;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.travel.personaltravel.BuildConfig;
import com.travel.personaltravel.R;
import com.travel.personaltravel.activity.CityDetailActivity;
import com.travel.personaltravel.adapter.MultiItemCommonAdapter;
import com.travel.personaltravel.adapter.MultiItemTypeSupport;
import com.travel.personaltravel.adapter.ViewHolder;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.model.City;
import com.travel.personaltravel.util.ParseUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CityFragment extends Fragment implements MultiItemTypeSupport<City> {
    @ViewInject(R.id.fragment_city_listView)
    private ListView listView;

    private MultiItemCommonAdapter<City> adapter;
    private List<City> citys;
    private Button loadMore;
    private Handler handler;
    //是否到达ListView底部
    boolean isLastRow = false;


    public CityFragment() {
    }

    public static CityFragment newInstance() {
        CityFragment ret = new CityFragment();
        return ret;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        citys = new LinkedList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city, container, false);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        handler = new Handler();
        //添加底部按钮
        LinearLayout bottomView = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.main_list_view_bottom, null);
        loadMore = (Button) bottomView.findViewById(R.id.load);
        loadMore.setOnClickListener(new ButtonClickListener());
        listView.addFooterView(bottomView);
        listView.setOnScrollListener(new OnScrollListener());
        adapter = new MultiItemCommonAdapter<City>(getActivity(), citys, this) {
            @Override
            public void convert(ViewHolder holder, City city) {
                holder.setText(R.id.item_fragment_trip_listView_cityName, city.getZhName());
                holder.setText(R.id.item_fragment_trip_listView_cityNamePinYin, city.getEnName());
                BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
                bitmapUtils.display(holder.getView(R.id.item_fragment_trip_listView_imageView), city.getImages().get(0).getUrl());
                //listView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
            }
        };
        listView.setAdapter(adapter);
        String fUrl = SieConstant.pathForeignRecommend + "true";
        loadData(fUrl);
    }


    @Override
    public int getLayoutId(int position, City city) {
        return R.layout.item_fragment_trip_listview;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int postion, City city) {
        return 0;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @OnItemClick(value = R.id.fragment_city_listView)
    public void onListViewItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), CityDetailActivity.class);
        intent.putExtra(SieConstant.ACTION_EXTRA, citys.get(position).getId());
        intent.putExtra("cName", citys.get(position).getZhName());
        startActivity(intent);
    }

    class OnScrollListener implements android.widget.AbsListView.OnScrollListener {

        //滚动的时候一直回调，直到停止滚动时才停止回调，单击时回调一次
        //firstVisibleItem:当前嫩看见的第一个列表项ID(从0开始,小半个也算)
        //visibleItemCount：当前能看见的列表项个数(小半个也算)
        //totalItemCount：列表项总共数
        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            //判断是否滚动到最后一行
            if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                System.out.println("已经到最后一行了");
                isLastRow = true;
            }
        }

        //正在滚动时回调，回调2-3次，手指没抛则回调2次，scrollState=2的这次不回调
        //回调顺序如下：
        //第一次：scrollState=SCROLL_STATE_TOUCH_SCROLL(1)正在滚动
        //第二次：scrollState = SCROLL_STATE_FLING(2)手指做了抛的动作（手指离开屏幕前，用力滑了一下）
        //第三次：scrollState = SCROLL_STATE_IDLE(0) 停止滚动

        //当屏幕停止滚动时为0；当屏幕滚动且用户使用的触碰或手指还在屏幕上时为1；
        //由于用户的操作，屏幕产生惯性滑动时为2
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            //当滚动到最后一行并且停止滚动时，执行加载
            if (isLastRow && scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
                //执行加载代码
                isLastRow = false;
            }
        }

    }

    String moreUrl = SieConstant.pathForeignRecommend + "false";

    class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            loadMore.setText("数据加载中");
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadData(moreUrl);
                    adapter.notifyDataSetChanged();
                    //listView.setSelection(5);
                    //重置为加载更多字样
                    loadMore.setVisibility(View.GONE);
                    loadMore.setText("加载更多");
                }
            }, 2000);
        }
    }

    private boolean onlyOneMore = true;

    private void loadData(String citysUrl) {
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET, citysUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                List<City> listCitys = ParseUtils.getCitys(result);
                citys.addAll(listCitys);
                adapter.notifyDataSetChanged();
                if (onlyOneMore == true) {
                    loadMore.setVisibility(View.VISIBLE);
                    onlyOneMore = false;
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                if (BuildConfig.DEBUG) Log.d("CityFragment", "下载失败:" + msg);
            }
        });
    }
}
