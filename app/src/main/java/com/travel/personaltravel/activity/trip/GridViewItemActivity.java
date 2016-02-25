package com.travel.personaltravel.activity.trip;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.travel.personaltravel.R;
import com.travel.personaltravel.adapter.trip.ViewPagerAdapter;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.model.Trip.Album;
import com.travel.personaltravel.util.ParseUtils;

import java.util.LinkedList;
import java.util.List;

public class GridViewItemActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    @ViewInject(value = R.id.activity_grid_view_item_viewPager)
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    @ViewInject(value = R.id.activity_grid_view_item_back)
    private TextView textViewBack;
    private List<String> imagesPath;
    @ViewInject(value = R.id.activity_grid_view_item_page)
    private TextView page;
    private int pageImage;
    private int sizeImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_item);
        ViewUtils.inject(this);
        textViewBack.setOnClickListener(this);
        imagesPath = new LinkedList<>();
        adapter = new ViewPagerAdapter(imagesPath);
        viewPager.setAdapter(adapter);
        String cityId = getIntent().getStringExtra(SieConstant.ACTION_EXTRA);
        pageImage = getIntent().getIntExtra(SieConstant.ACTION_EXTRA_PAGE, 1);
        final HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, SieConstant.getalbumPath(cityId), new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                LinkedList<Album> listAlbums = ParseUtils.getAlbums(result);
                for (int i = 0; i < listAlbums.size(); i++) {
                    imagesPath.add(listAlbums.get(i).getOriginUrl());
                }
                sizeImages = listAlbums.size();
                page.setText(pageImage +1+ "/" + sizeImages);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        page.setText(position + 1 + "/" + sizeImages);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
