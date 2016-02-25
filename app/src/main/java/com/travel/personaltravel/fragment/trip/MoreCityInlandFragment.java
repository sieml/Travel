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
import com.travel.personaltravel.model.morecity.Destination;
import com.travel.personaltravel.model.morecity.MoreCityInland;
import com.travel.personaltravel.util.ParseUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreCityInlandFragment extends Fragment {
    private MultiItemCommonAdapter<Destination> adapter;
    private List<Destination> listDestinations;
    @ViewInject(R.id.fragment_more_city_inland_listView)
    private ListView listView;

    public MoreCityInlandFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listDestinations = new LinkedList<Destination>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more_city_inland, container, false);
        ViewUtils.inject(this, view);
        adapter = new MultiItemCommonAdapter<Destination>(getActivity(), listDestinations, new MultiItemTypeSupport<Destination>() {
            @Override
            public int getLayoutId(int position, Destination destination) {
                int ret = -1;
                String id = destination.getId();
                if (id.equals("")) {
                    ret = R.layout.item_fragment_trip_morecity_inland_text;
                } else {
                    ret = R.layout.item_fragment_trip_morecity_inland;
                }
                return ret;
            }

            @Override
            public int getViewTypeCount() {
                return 2;
            }

            @Override
            public int getItemViewType(int postion, Destination destination) {
                int ret = -1;
                if (destination.getId().equals("")) {
                    ret = 0;
                } else {
                    ret = 1;
                }
                return ret;
            }
        }) {
            @Override
            public void convert(ViewHolder holder, Destination destination) {
                if (destination.getId().equals("")) {
                    holder.setText(R.id.item_fragment_trip_morecity_inland_text_address, "-" + destination.getZhName() + "-");
                } else {
                    holder.setText(R.id.item_fragment_trip__morecity_inland2_listView_cityName, destination.getZhName());
                    holder.setText(R.id.item_fragment_trip___morecity_inland2_listView_cityNamePinYin, destination.getEnName());
                    BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
                    bitmapUtils.display(holder.getView(R.id.item_fragment_trip_morecity_inland2_listView_imageView), destination.getImages().get(0).getUrl());
                }
            }
        };
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, SieConstant.pathInlandAll, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                if (BuildConfig.DEBUG) Log.d("MoreCityInlandFragment", result);
                List<MoreCityInland> cityInlands = ParseUtils.getMoreCityInland(result);
                for (int i = 0; i < cityInlands.size(); i++) {
                    MoreCityInland moreCityInland = cityInlands.get(i);
                    Destination destination = new Destination("", "", moreCityInland.getZhName(), null, null);
                    listDestinations.add(destination);
                    List<Destination> destinations = moreCityInland.getDestinations();
                    listDestinations.addAll(destinations);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                if (BuildConfig.DEBUG) Log.d("MoreCityInlandFragment", msg);
            }
        });
    }

    @OnItemClick(value = {R.id.fragment_more_city_inland_listView})
    public void onInlandListViewItemClick(AdapterView<?> parent, View view, int position, long id) {
        String cityId = listDestinations.get(position).getId();
        if (!cityId.equals("")) {
            Intent intent = new Intent(getActivity(), CityDetailActivity.class);
            intent.putExtra(SieConstant.ACTION_EXTRA, cityId);
            startActivity(intent);
        }
    }
}
