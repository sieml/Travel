package com.travel.personaltravel.activity;

import com.travel.personaltravel.cache.ACache;
import com.travel.personaltravel.constant.SieConstant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.travel.personaltravel.R;
import com.travel.personaltravel.fragment.search.SearchCityDetailFragment;

public class CityDetailActivity extends FragmentActivity{

    @ViewInject(R.id.city_detail_title)
    private TextView city_detail_title;
    private SearchCityDetailFragment fragment;
    private String title;

    private String CityId = "5473cce2b8ce043a64108e12";
    @ViewInject(R.id.search_detail_city_like)
    private TextView isLikeTv;

    @ViewInject(R.id.search_detail_city_hasgone)
    private TextView isGoneTv;
    private ACache aCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);
        aCache = ACache.get(this);
        String isLike = aCache.getAsString("isLike");
        if (isLike != null && isLike.equals(CityId)) {
            isLikeTv.setSelected(true);
        }
        String isGone = aCache.getAsString("isGone");
        if (isGone != null && isGone.equals(CityId)) {
            isGoneTv.setSelected(true);
        }
        ViewUtils.inject(this);
        initFragment();
    }

    private void initFragment() {
        Intent intent = getIntent();
        title = intent.getStringExtra("cName");
        if (title != null) {
            city_detail_title.setText(title);
        }
        CityId = intent.getStringExtra(SieConstant.ACTION_EXTRA);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        fragment = (SearchCityDetailFragment) manager.findFragmentByTag("scdFragment");
        if (fragment == null) {
            fragment = SearchCityDetailFragment.newInstance(CityId);
        }
        transaction.replace(R.id.city_detail_fragment, fragment, "scdFragment");
        transaction.commit();
    }

    @OnClick(value = R.id.search_detail_city_like)
    public void likeClick(View view) {
        if (view.isSelected()) {
            aCache.put("isLike", CityId);
        } else {
            aCache.put("isLike", "false");
        }
    }

    @OnClick(value = R.id.search_detail_city_hasgone)
    public void goneClick(View view) {
        if (view.isSelected()) {
            aCache.put("isGone", CityId);
        } else {
            aCache.put("isGone", "false");
        }
    }

    @OnClick(R.id.city_detail_back)
    public void back(View view) {
        this.finish();
    }

}
