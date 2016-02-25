package com.travel.personaltravel.fragment.trip;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
public class CityFragment extends Fragment implements MultiItemTypeSupport<City>{
    @ViewInject(R.id.fragment_city_listView)
    private ListView listView;
    private MultiItemCommonAdapter<City> adapter;
    private List<City> citys;
    private String cityPath;
    public CityFragment() {
        // Required empty public constructor
    }

    public static  CityFragment newInstance(String cityPath) {
        CityFragment ret=new CityFragment();
        Bundle arguments = new Bundle();
        arguments.putString("cityPath", cityPath);
        ret.setArguments(arguments);
        return ret;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        citys = new LinkedList<>();
        cityPath = getArguments().getString("cityPath");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_city, container, false);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter=new MultiItemCommonAdapter<City>(getActivity(),citys,this) {
            @Override
            public void convert(ViewHolder holder, City city) {
                holder.setText(R.id.item_fragment_trip_listView_cityName, city.getZhName());
                holder.setText(R.id.item_fragment_trip_listView_cityNamePinYin, city.getEnName());
                BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
                bitmapUtils.display(holder.getView(R.id.item_fragment_trip_listView_imageView), city.getImages().get(0).getUrl());
//                listView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
            }
        };
        listView.setAdapter(adapter);


        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET, cityPath, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                List<City> listCitys = ParseUtils.getCitys(result);
                citys.addAll(listCitys);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                if (BuildConfig.DEBUG) Log.d("CityFragment", "下载失败:" + msg);
            }
        });
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
        startActivity(intent);
    }

}
