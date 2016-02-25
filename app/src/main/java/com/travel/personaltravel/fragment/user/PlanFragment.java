package com.travel.personaltravel.fragment.user;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.travel.personaltravel.R;
import com.travel.personaltravel.activity.PlanRouteActivity;
import com.travel.personaltravel.adapter.CommonAdapter;
import com.travel.personaltravel.adapter.ViewHolder;
import com.travel.personaltravel.model.PlanRoute;
import com.travel.personaltravel.model.Planitem;

import java.util.ArrayList;
import java.util.List;


public class PlanFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String URL = "url";


    // TODO: Rename and change types of parameters
    private String url;
    private ListView lists;
    private List<Planitem> data;
    private PlanRoute planRoute;
    private int day;
    private String name;

    // TODO: Rename and change types and number of parameters
    public static PlanFragment newInstance(String param) {
        PlanFragment fragment = new PlanFragment();
        Bundle args = new Bundle();
        args.putString(URL, param);
        fragment.setArguments(args);
        return fragment;
    }

    public PlanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_plan, container, false);
        lists = (ListView) inflate.findViewById(R.id.fragment_plan_list);
        ViewUtils.inject(this, inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        data = new ArrayList<>();
        //获取网络数据
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                planRoute = PlanRoute.Parse(result);

                data = Planitem.getRoutes(planRoute.getResult());
                CommonAdapter adapter = new CommonAdapter<Planitem>(getActivity(), data, R.layout.activity_plan_item) {
                    @Override
                    public void convert(ViewHolder holder, Planitem iter) {
                        day = iter.getDayindex() + 1;
                        holder.setText(R.id.plan_item_day, day + "\nday");
                        name = iter.getName();
                        holder.setText(R.id.plan_item_name, name);
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < iter.getRoutes().size(); i++) {
                            stringBuilder.append(iter.getRoutes().get(i).getPoi().getZhName()).append("->");
                        }
                        if (stringBuilder.length() >= 2) {
                            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                        }
                        holder.setText(R.id.plan_item_route, stringBuilder.toString());

                    }


                };
                lists.setAdapter(adapter);
            }


            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });

    }

    @OnItemClick(value = R.id.fragment_plan_list)
    public void onListViewItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), PlanRouteActivity.class);
        intent.putParcelableArrayListExtra("routes", data.get(position).getRoutes());

        intent.putExtra("day", day);
        intent.putExtra("name", data.get(position).getName());

        startActivity(intent);
    }

}
