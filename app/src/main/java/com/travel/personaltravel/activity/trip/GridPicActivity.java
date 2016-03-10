package com.travel.personaltravel.activity.trip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.travel.personaltravel.R;
import com.travel.personaltravel.adapter.MultiItemCommonAdapter;
import com.travel.personaltravel.adapter.MultiItemTypeSupport;
import com.travel.personaltravel.adapter.ViewHolder;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.model.Trip.Album;
import com.travel.personaltravel.util.ParseUtils;

import java.util.LinkedList;
import java.util.List;

public class GridPicActivity extends FragmentActivity {

    //图集 gridView展示
    @ViewInject(value = R.id.activity_grid_pic_gridview)
    private GridView gridView;

    //城市图集的标题
    @ViewInject(R.id.tuji_city_name_tv)
    private TextView cityTitle;

    private String cityId;
    private MultiItemCommonAdapter<Album> adapter;
    private List<Album> albums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_pic);
        ViewUtils.inject(this);
        albums = new LinkedList<>();
        adapter = new MultiItemCommonAdapter<Album>(this, albums, new MultiItemTypeSupport<Album>() {
            @Override
            public int getLayoutId(int position, Album album) {
                return R.layout.item_gridview;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public int getItemViewType(int position, Album album) {
                return 0;
            }
        }) {
            @Override
            public void convert(ViewHolder holder, Album album) {
                BitmapUtils bitmapUtils = new BitmapUtils(GridPicActivity.this);
                bitmapUtils.display(holder.getView(R.id.item_gridView_imageView), album.getUrl());
            }
        };
        gridView.setAdapter(adapter);


        cityId = getIntent().getStringExtra(SieConstant.ACTION_EXTRA);
        String cityName = getIntent().getStringExtra(SieConstant.ACTION_EXTRA_CITY_NAME);
        if (cityName != null) {
            cityTitle.setText(cityName+"图集");
        }
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, SieConstant.getalbumPath(cityId), new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                //if (BuildConfig.DEBUG) Log.d("GridPicActivity", result);
                LinkedList<Album> listAlbums = ParseUtils.getAlbums(result);
                albums.addAll(listAlbums);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GridPicActivity.this, GridViewItemActivity.class);
                intent.putExtra(SieConstant.ACTION_EXTRA, cityId);
                intent.putExtra(SieConstant.ACTION_EXTRA_PAGE, position);
                startActivity(intent);
            }
        });
    }

    @OnClick(value = {R.id.activity_grid_pic_back})
    public void onBackClick(View view) {
        finish();
    }

}
