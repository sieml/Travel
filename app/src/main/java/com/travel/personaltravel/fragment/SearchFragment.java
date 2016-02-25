package com.travel.personaltravel.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.travel.personaltravel.R;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.model.SearchHot;
import com.travel.personaltravel.fragment.search.SearchResultFragment;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {
    private TextView search;
    private List<String> historyList;
    private EditText keyWord;
    private LinearLayout linearAdd;
    private GridView gridViewHistory;
    private GridView gridViewHot;
    private HistoryGridAdapter historyGridAdapter;
    private List<SearchHot> datas;
    private HotGridAdapter hotGridAdapter;

    public SearchFragment() {
        datas = new LinkedList<>();
        loadHotSOSO();
    }

    public static SearchFragment newInstance() {
        SearchFragment ret = new SearchFragment();
        return ret;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        gridViewHistory = (GridView) view.findViewById(R.id.search_history_grid_view);
        gridViewHot = (GridView) view.findViewById(R.id.search_hot_grid_view);

        search = (TextView) view.findViewById(R.id.search_history_title_tv);
        keyWord = (EditText) view.findViewById(R.id.search_keyword_edit_et);

        search.setOnClickListener(this);

        //显示历史
        historyList = new LinkedList<>();
        getHistory();
        historyGridAdapter = new HistoryGridAdapter(historyList);
        gridViewHistory.setAdapter(historyGridAdapter);
        gridViewHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                keyWord.setText(((TextView) view).getText());
            }
        });

        hotGridAdapter = new HotGridAdapter(datas);
        gridViewHot.setAdapter(hotGridAdapter);
        gridViewHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                keyWord.setText(((TextView) view).getText());
            }
        });

        return view;
    }

    private class HistoryGridAdapter extends BaseAdapter {

        private List<String> list;

        public HistoryGridAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            int ret = 0;
            if (list != null) {
                ret = list.size();
            }
            return ret;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View ret = null;
            if (convertView != null)
                ret = convertView;
            else {
                ret = LayoutInflater.from(getActivity()).inflate(R.layout.search_tag_grid_view_item, parent, false);
                ViewHolder holder = new ViewHolder();
                holder.textView = (TextView) ret.findViewById(R.id.search_tag_show);
                ret.setTag(holder);
            }
            ViewHolder holder = (ViewHolder) ret.getTag();
            holder.textView.setText(list.get(position));
            return ret;
        }
    }

    private class HotGridAdapter extends BaseAdapter {

        private List<SearchHot> list;

        public HotGridAdapter(List<SearchHot> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            int ret = 0;
            if (list != null) {
                ret = list.size();
            }
            return ret;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View ret = null;
            if (convertView != null)
                ret = convertView;
            else {
                ret = LayoutInflater.from(getActivity()).inflate(R.layout.search_tag_grid_view_item, parent, false);
                ViewHolder holder = new ViewHolder();
                holder.textView = (TextView) ret.findViewById(R.id.search_tag_show);
                ret.setTag(holder);
            }
            ViewHolder holder = (ViewHolder) ret.getTag();
            holder.textView.setText(list.get(position).getZhName());
            return ret;
        }
    }

    class ViewHolder {
        TextView textView;
    }

    private void loadHotSOSO() {
        HttpUtils httpUtils = new HttpUtils();

        httpUtils.send(HttpRequest.HttpMethod.GET, SieConstant.SEARCH_CIRT_HOT_LIST, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    datas.addAll(SearchHot.parseJSON(responseInfo.result));
                    hotGridAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });
    }

    private void getHistory() {
        SharedPreferences sp = getActivity().getSharedPreferences("searchHistory", 0);
        String history = sp.getString("history", "冲绳,");

        String[] split = history.split(",");
        for (int i = 0; i < split.length; i++) {
            historyList.add(split[i]);
        }
    }


    @Override
    public void onClick(View v) {

        ////清空历史
        if (v.getId() == R.id.search_history_clear_history_tv) {
            SharedPreferences sp = getActivity()
                    .getSharedPreferences("searchHistory", 0);
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.commit();
            historyGridAdapter.notifyDataSetChanged();
        }
        //保存搜索 城市 历史记录; 跳转到 搜索列表 页面
        if (v.getId() == R.id.search_history_title_tv) {
            String cityTxt = keyWord.getText().toString().trim();
            SharedPreferences mysp = getActivity().getSharedPreferences("searchHistory", 0);

            //读取 共享参数的 历史
            String old_history = mysp.getString("history", "冲绳,");//默认是冲绳
            StringBuilder builder = new StringBuilder(old_history);
            //保存记录时 加一个","分割
            builder.append(cityTxt + ",");
            Log.d("bb", "builder = " + builder.toString());
            //判断是否已经有相同的历史记录
            if (!old_history.contains(cityTxt + ",")) {
                SharedPreferences.Editor editor = mysp.edit();
                editor.putString("history", builder.toString());
                editor.commit();///提交  保存
            } else {
                //已保存过
            }

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            try {
                String key = URLEncoder.encode(cityTxt, "utf-8");
                String url = String.format(SieConstant.SEARCH_CITY_LIST, key);

                SearchResultFragment searchResultFragment = SearchResultFragment.newInstance(url);
                transaction.replace(R.id.fragment_trip_container, searchResultFragment, "searchResultFragment");
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.addToBackStack(null);
                transaction.commit();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
