package com.travel.personaltravel.fragment.search;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.travel.personaltravel.BuildConfig;
import com.travel.personaltravel.R;
import com.travel.personaltravel.activity.SearchCityDetailActivity;
import com.travel.personaltravel.adapter.Search.SearchResultAdapter;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.model.SearchResult;
import com.travel.personaltravel.model.morecity.SearchFirstCity;
import com.travel.personaltravel.util.ParseUtils;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends Fragment implements View.OnClickListener {


    private int CONTENT = 0;
    private int TITLE = 1;
    private String[] typeName = {"景点", "美食", "购物"};
    private int i = 0;
    private static String path;

    private List<SearchResult> data;
    private SearchResultAdapter adapter;
    private ListView listView;
    private SearchFirstCity firstCity;
    private TextView cityName;
    private TextView cityAddress;
    private ImageView imgIcon;
    private EditText txtSo;
    private EditText editInput;

    public SearchResultFragment() {
        // Required empty public constructor
    }


    public static SearchResultFragment newInstance(String url) {
        SearchResultFragment ret = new SearchResultFragment();
        path = url;
        return ret;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        txtSo = (EditText) view.findViewById(R.id.search_keyword_edit_et);
        listView = (ListView) view.findViewById(R.id.search_result_list_view);
        View v = LayoutInflater.from(getContext()).inflate(R.layout.search_result_header_view_item, null);
        cityName = (TextView) v.findViewById(R.id.search_result_name_item1);
        cityAddress = (TextView) v.findViewById(R.id.search_result_address_item1);
        imgIcon = (ImageView) v.findViewById(R.id.search_result_icon_item1);
        editInput = (EditText) v.findViewById (R.id.search_keyword_edit_et);


        data = new LinkedList<>();
        listView.addHeaderView(v);
        adapter = new SearchResultAdapter(getActivity(), data);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //达人链接: Constracts.CITY_LOCAL_EXPERT
                Intent intent = new Intent(getActivity(), SearchCityDetailActivity.class);
                Bundle bundle = new Bundle();
                String CityId = firstCity.getId();
                String CityName = firstCity.getZhName();

                bundle.putString(SieConstant.INTENT_CITY_ID, CityId);
                bundle.putString(SieConstant.INTENT_CITY_NAME, CityName);
                intent.putExtras(bundle);

                //跳转到 城市详情Activity
                startActivity(intent);
            }
        });
        downData();
    }

    private void downData() {
        HttpUtils http = new HttpUtils();
        if (path != null) {

            http.send(HttpRequest.HttpMethod.GET, path, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    String results = responseInfo.result;
                    if (results != null) {

                        try {
                            firstCity = SearchFirstCity.parseJson(results);
                            if (firstCity != null) {
                                cityName.setText(firstCity.getZhName());
                                cityAddress.setText(firstCity.getZhName());
                                txtSo.setText(firstCity.getZhName());
                                BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
                                bitmapUtils.display(imgIcon, firstCity.getImages().get(0).getUrl());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        List<SearchResult> lists = ParseUtils.getSearchResult(results);

                        data.addAll(lists);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(HttpException error, String msg) {
                    if (BuildConfig.DEBUG) Log.d("SearchResultFragment", "下载失败:" + msg);
                }
            });
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() ==  R.id.search_history_title_tv){

            if (editInput.getText().length()>0&&editInput!=null) {
                try {
                    String key = URLEncoder.encode(editInput.getText().toString().trim(), "utf-8");
                    path = String.format(SieConstant.SEARCH_CITY_LIST, key);
                    data.clear();
                    downData();
                    adapter.notifyDataSetChanged();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if(editInput.getText().toString() ==""){
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        }
    }
}
