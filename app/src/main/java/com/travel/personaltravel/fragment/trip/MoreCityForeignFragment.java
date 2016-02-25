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
import com.travel.personaltravel.model.morecity.MoreCityForeign;
import com.travel.personaltravel.util.ParseUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreCityForeignFragment extends Fragment{
    @ViewInject(R.id.fragment_more_city_foreign_country)
    private ListView listViewCountry;
    @ViewInject(R.id.fragment_more_city_foreign_country_detail)
    private ListView listViewCountryDetail;

    private MultiItemCommonAdapter<MoreCityForeign> adapterCountry;
    private List<MoreCityForeign> listCountry;

    private MultiItemCommonAdapter<Destination> adapterCountryDetail;
    private List<Destination> listDestination;


    public MoreCityForeignFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listCountry = new LinkedList<>();
        listDestination = new LinkedList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more_city_foreign, container, false);
        ViewUtils.inject(this,view);

        adapterCountry=new MultiItemCommonAdapter<MoreCityForeign>(getActivity(), listCountry, new MultiItemTypeSupport<MoreCityForeign>() {
            @Override
            public int getLayoutId(int position, MoreCityForeign moreCityForeign) {
                return R.layout.item_fragment_more_city_foreign_country;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public int getItemViewType(int postion, MoreCityForeign moreCityForeign) {
                return 0;
            }
        }) {
            @Override
            public void convert(ViewHolder holder, MoreCityForeign moreCityForeign) {
                holder.setText(R.id.item_fragment_more_city_foreign_country_name, moreCityForeign.getZhName());
            }
        };
        listViewCountry.setAdapter(adapterCountry);
        adapterCountryDetail=new MultiItemCommonAdapter<Destination>(getActivity(), listDestination, new MultiItemTypeSupport<Destination>() {
            @Override
            public int getLayoutId(int position, Destination destination) {
                return R.layout.item_fragment_more_city_foreign_country_detail;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public int getItemViewType(int postion, Destination destination) {
                return 0;
            }
        }) {
            @Override
            public void convert(ViewHolder holder, Destination destination) {
                holder.setText(R.id.item_fragment_more_city_foreign_country_detail_textView, destination.getZhName());
                holder.setText(R.id.item_fragment_more_city_foreign_country_detail_pinyin, destination.getEnName());
                BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
                bitmapUtils.display(holder.getView(R.id.item_fragment_more_city_foreign_country_detail_imageView), destination.getImages().get(0).getThumb());
            }
        };
        listViewCountryDetail.setAdapter(adapterCountryDetail);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, SieConstant.pathForeignAll, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                List<MoreCityForeign> moreCityForeign = ParseUtils.getMoreCityForeign(result);
                listCountry.addAll(moreCityForeign);
                adapterCountry.notifyDataSetChanged();
                listDestination.addAll(moreCityForeign.get(0).getDestinations());
                adapterCountryDetail.notifyDataSetChanged();

            }

            @Override
            public void onFailure(HttpException error, String msg) {
                if (BuildConfig.DEBUG) Log.d("MoreCityForeignFragment", msg);
            }
        });
    }
    @OnItemClick(value = {R.id.fragment_more_city_foreign_country})
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        listDestination.clear();
        listDestination.addAll(listCountry.get(position).getDestinations());
        adapterCountryDetail.notifyDataSetChanged();
    }

    @OnItemClick(value = {R.id.fragment_more_city_foreign_country_detail})
    public void onDetailItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), CityDetailActivity.class);
        intent.putExtra(SieConstant.ACTION_EXTRA, listDestination.get(position).getId());
        startActivity(intent);
    }

}
