package com.travel.personaltravel.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.travel.personaltravel.R;
import com.travel.personaltravel.cache.ACache;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.model.SearchHot;
import com.travel.personaltravel.widget.ClearEditText;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView search;
    private ArrayList<String> historyList;
    private ClearEditText keyWord;
    private LinearLayout linearAdd;
    private GridView gridViewHistory;
    private GridView gridViewHot;
    private HistoryGridAdapter historyGridAdapter;
    private ArrayList<SearchHot> datas;
    private HotGridAdapter hotGridAdapter;
    private TextView historyTv;
    private boolean updateHistory;
    private String cityTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        aCache = ACache.get(this);
        datas = new ArrayList<>();
        hotGridAdapter = new HotGridAdapter(this, datas);
        ArrayList<SearchHot> value = (ArrayList<SearchHot>) aCache.getAsObject("citys");
        if (value != null && value.size() > 0) {
            datas.addAll(value);
            hotGridAdapter.notifyDataSetChanged();
        } else {
            loadHotSOSO();
        }

        gridViewHistory = (GridView) findViewById(R.id.search_history_grid_view);
        gridViewHot = (GridView) findViewById(R.id.search_hot_grid_view);

        search = (TextView) findViewById(R.id.search_history_title_tv);
        historyTv = (TextView) findViewById(R.id.search_history_clear_history_tv);
        ImageView backIv = (ImageView) findViewById(R.id.s_title_bar_back_iv);
        backIv.setOnClickListener(this);
        historyTv.setOnClickListener(this);
        keyWord = (ClearEditText) findViewById(R.id.search_keyword_edit_et);

        search.setOnClickListener(this);

        //显示历史
        historyList = new ArrayList<>();
        getHistory();
        historyGridAdapter = new HistoryGridAdapter(this, historyList);
        gridViewHistory.setAdapter(historyGridAdapter);
        gridViewHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                keyWord.setText(((TextView) view).getText());
            }
        });

        gridViewHot.setAdapter(hotGridAdapter);
        gridViewHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                keyWord.setText(((TextView) view).getText());
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (updateHistory) {
            historyList.add(cityTxt);
            historyGridAdapter.notifyDataSetChanged();
            updateHistory = false;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.s_title_bar_back_iv) {
            finish();
        } else if (v.getId() == R.id.search_history_clear_history_tv) {
            //清空历史
            SharedPreferences sp = getSharedPreferences("searchHistory", 0);
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.commit();
            historyList.clear();
            historyGridAdapter.notifyDataSetChanged();
        } else if (v.getId() == R.id.search_history_title_tv) {
            //保存搜索 城市 历史记录; 跳转到 搜索列表 页面
            cityTxt = keyWord.getText().toString().trim();

            if (cityTxt == null || cityTxt.equals("")) {
                Toast.makeText(SearchActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    //读取共享参数中的历史
                    SharedPreferences mysp = getSharedPreferences("searchHistory", 0);
                    String old_history = mysp.getString("history", "#");//默认是冲绳
                    //判断旧历史记录是否包含当前城市
                    if (!old_history.contains(cityTxt)) {
                        updateHistory = true;
                        //搜索后新加了1个的长串
                        if (old_history.equals("#")) old_history = "";
                        StringBuilder newStr = new StringBuilder(old_history);
                        newStr.append(cityTxt + ",");
                        SharedPreferences.Editor editor = mysp.edit();
                        editor.putString("history", newStr.toString());
                        editor.commit();///提交  保存
                    } else {
                        //历史已记录了该城市
                    }

                    String cityKey = URLEncoder.encode(cityTxt, "utf-8");
                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    intent.putExtra("srCityKey", cityKey);
                    startActivity(intent);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    private class HistoryGridAdapter extends BaseAdapter {

        private List<String> list;
        private Context context;

        public HistoryGridAdapter(Context context, List<String> list) {
            this.list = list;
            this.context = context;
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
                ret = LayoutInflater.from(context).inflate(R.layout.search_tag_grid_view_item, parent, false);
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

        private Context context;
        private List<SearchHot> list;

        public HotGridAdapter(Context context, List<SearchHot> list) {
            this.list = list;
            this.context = context;
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
                ret = LayoutInflater.from(context).inflate(R.layout.search_tag_grid_view_item, parent, false);
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

    private ACache aCache;

    private void loadHotSOSO() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, SieConstant.SEARCH_CIRT_HOT_LIST, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    datas.addAll(SearchHot.parseJSON(responseInfo.result));
                    //缓存一天
                    aCache.put("citys", datas, 1 * ACache.TIME_DAY);
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
        SharedPreferences sp = getSharedPreferences("searchHistory", 0);
        String history = sp.getString("history", "#");
        if (!history.equals("#")) {
            String[] split = history.split(",");
            for (int i = 0; i < split.length; i++) {
                historyList.add(split[i]);
            }
        }
    }
}
