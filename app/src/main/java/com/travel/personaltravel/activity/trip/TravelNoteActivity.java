package com.travel.personaltravel.activity.trip;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
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
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.travel.personaltravel.R;
import com.travel.personaltravel.adapter.CommonAdapter;
import com.travel.personaltravel.adapter.ViewHolder;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.model.Trip.TravelNote;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 游记Activity
 * Url: SieConstants.CITY_TRAVEL_NOTE
 */
public class TravelNoteActivity extends FragmentActivity {

    @ViewInject(R.id.city_all_travel_note_list_view)
    private ListView listView;

    private List<TravelNote> data;
    private CommonAdapter<TravelNote> adapter;

    private String cityName;
    private String tNoteUrl;
    private int pageNum = 0;
    private boolean isLoadingData = false;
    private boolean isBottom = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_note);

        ViewUtils.inject(this);

        cityName = getIntent().getStringExtra(SieConstant.ACTION_EXTRA_CITY_NAME);

//        tNoteUrl = String.format(SieConstants.CITY_TRAVEL_NOTE, cityName,pageNum);

        data = new LinkedList<>();

        //构建适配器
        newAdapter();

        //下载数据, 刷新适配器
        downData();
    }

    private void downData() {
        HttpUtils httpUtils = new HttpUtils();
        tNoteUrl = String.format(SieConstant.CITY_TRAVEL_NOTE, cityName, pageNum);
        httpUtils.send(HttpRequest.HttpMethod.GET, tNoteUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String netData = responseInfo.result;
                try {
                    List<TravelNote> notes = TravelNote.parseJSON(netData);
                    if (notes != null) {
                        data.addAll(notes);
                        adapter.notifyDataSetChanged();
                        isLoadingData = false;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pageNum++;
            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });
    }

    //listView的item时间文本控件
    private Date date;
    //listView的底部结束 view
    private View view;

    private void newAdapter() {
        view = LayoutInflater.from(this).inflate(R.layout.item_anim_buttom, null);
        view.setVisibility(View.GONE);
        listView.addFooterView(view);
        adapter = new CommonAdapter<TravelNote>(this, data, R.layout.city_travel_note_list_item) {
            @Override
            public void convert(ViewHolder holder, TravelNote travelNote) {
                holder.setText(R.id.travel_note_item_big_title, travelNote.getTitle());
                holder.setText(R.id.travel_note_item_user_nikename, travelNote.getAuthorName());
                //TODO 发表时间
                long time = travelNote.getPublishTime();
                date = new Date(time);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String timeStr = sdf.format(date);
                holder.setText(R.id.travel_note_item_user_publish_tiem, timeStr);

                holder.setText(R.id.travel_note_item_content, travelNote.getSummary());

                BitmapUtils bitmapUtils = new BitmapUtils(TravelNoteActivity.this);
                if (travelNote.getImages() != null) {
                    if (travelNote.getImages().size() > 0) {
                        bitmapUtils.display(holder.getView(R.id.travel_note_item_one_icon), travelNote.getAuthorAvatar());
                    }
                }
            }
        };

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TravelNoteActivity.this, TravelNoteItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("noteId", data.get(position).getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE && isBottom && !isLoadingData) {
                    downData();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    isBottom = true;
                    view.setVisibility(View.VISIBLE);
                } else {
                    isBottom = false;
                }
            }
        });
    }


    @OnClick(value = R.id.city_travel_note_top_back)
    public void backOnClick(View view) {
        finish();
    }

    @OnItemClick(value = {R.id.city_all_travel_note_list_view})
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, TravelNoteItemActivity.class);
//TODO        intent.putExtra(Constracts.ACTION_EXTRA, cityId);
        startActivity(intent);

    }
}
