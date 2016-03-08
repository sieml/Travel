package com.travel.personaltravel.activity;

import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Window;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

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
import com.travel.personaltravel.BuildConfig;
import com.travel.personaltravel.R;
import com.travel.personaltravel.activity.trip.SpotItemActivity;
import com.travel.personaltravel.adapter.MultiItemCommonAdapter;
import com.travel.personaltravel.adapter.MultiItemTypeSupport;
import com.travel.personaltravel.adapter.ViewHolder;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.model.Trip.SpotItem;
import com.travel.personaltravel.util.ParseUtils;

import java.util.LinkedList;
import java.util.List;

public class SpotActivity extends FragmentActivity {
    @ViewInject(R.id.spot_lv)
    private ListView listView;
    List<SpotItem> datas = null;
    @ViewInject(value = R.id.spot_title)
    private TextView textViewTitle;
    private MultiItemCommonAdapter<SpotItem> adapter;
    private View view;
    private int page = 1;
    private boolean isLoadingData = false;
    private boolean isBottom = false;
    private String cityId;
    private AnimationDrawable animationDrawable;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot);
        ViewUtils.inject(this);
        String cityName = getIntent().getStringExtra(SieConstant.ACTION_EXTRA_CITY_NAME);
        if (cityName != null) {
            textViewTitle.setText(cityName + "景点");
            setTitle(cityName + "景点");
        } else {
            textViewTitle.setText("城市景点");
            setTitle("城市景点");
        }
        datas = new LinkedList<>();
        adapter = new MultiItemCommonAdapter<SpotItem>(this, datas, new MultiItemTypeSupport<SpotItem>() {
            @Override
            public int getLayoutId(int position, SpotItem spotItem) {
                return R.layout.item_spot_activity_listview;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public int getItemViewType(int position, SpotItem spotItem) {
                return 0;
            }
        }) {
            @Override
            public void convert(ViewHolder holder, SpotItem spotItem) {
                holder.setText(R.id.item_spot_activity_listview_position, holder.getPosition() + "");
                holder.setText(R.id.item_spot_activity_listview_spot_name, spotItem.getZhName());
                BitmapUtils bitmapUtils = new BitmapUtils(SpotActivity.this);
                if (spotItem.getImages().size() > 0) {
                    bitmapUtils.display(holder.getView(R.id.item_spot_activity_listview_icon), spotItem.getImages().get(0).getThumb());
                }
            }
        };
        listView.setAdapter(adapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        view = LayoutInflater.from(this).inflate(R.layout.item_anim_buttom, null);
        imageView = (ImageView) view.findViewById(R.id.item_anim_buttom_imageView);
        imageView.setBackgroundResource(R.drawable.load_anim);
        animationDrawable = (AnimationDrawable) imageView.getBackground();
        view.setVisibility(View.GONE);
        listView.addFooterView(view);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE && isBottom && !isLoadingData) {
                    addData(cityId);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    isBottom = true;
                } else {
                    isBottom = false;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cityId = getIntent().getStringExtra(SieConstant.ACTION_EXTRA);
        addData(cityId);

    }

    private void addData(String cityId) {
        isLoadingData = true;
        imageView.setVisibility(View.VISIBLE);
        animationDrawable.start();
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, SieConstant.getSpotDataPath(cityId, page), new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;

                List<SpotItem> spotItem = ParseUtils.getSpotItem(result);
                datas.addAll(spotItem);
                imageView.setVisibility(View.GONE);
                animationDrawable.stop();
                adapter.notifyDataSetChanged();

                isLoadingData = false;
                page++;
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                if (BuildConfig.DEBUG) Log.d("SpotActivity", msg);
            }
        });
    }

    @OnClick(value = R.id.spot_back)
    public void backOnClick(View view) {
        finish();
    }

    @OnItemClick(value = {R.id.spot_lv})
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, SpotItemActivity.class);
        intent.putExtra(SieConstant.ACTION_EXTRA, cityId);
        startActivity(intent);

    }
}
