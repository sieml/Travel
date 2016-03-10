package com.travel.personaltravel.activity;

import com.travel.personaltravel.cache.ACache;
import com.travel.personaltravel.constant.SieConstant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.travel.personaltravel.R;
import com.travel.personaltravel.fragment.search.SearchCityDetailFragment;

public class CityDetailActivity extends FragmentActivity {

    @ViewInject(R.id.city_detail_title)
    private TextView city_detail_title;
    private SearchCityDetailFragment fragment;
    private String title;

    private String CityId = "5473cce2b8ce043a64108e12";
    @ViewInject(R.id.detail_city_like)
    private CheckBox isLikeTv;

    @ViewInject(R.id.detail_city_hasgone)
    private CheckBox isGoneTv;
    private ACache aCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);
        aCache = ACache.get(this);

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
        String isLike = aCache.getAsString("isLike" + CityId);
        Log.d("sie", "islike = " + isLike);
        if (isLike != null && isLike.equals("true")) {
            isLikeTv.setChecked(true);
        }
        String isGone = aCache.getAsString("isGone" + CityId);
        if (isGone != null && isGone.equals("true")) {
            isGoneTv.setChecked(true);
        }

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        fragment = (SearchCityDetailFragment) manager.findFragmentByTag("scdFragment");
        if (fragment == null) {
            fragment = SearchCityDetailFragment.newInstance(CityId);
        }
        transaction.replace(R.id.city_detail_fragment, fragment, "scdFragment");
        transaction.commit();
    }

    private boolean noLChecked = false;
    private boolean noHChecked = false;

    @OnClick(value = R.id.detail_city_like)
    public void likeClick(View view) {
        Log.d("sie", "gggg");
        if (!noLChecked) {
            aCache.put("isLike" + CityId, "true");
            noLChecked = true;
        } else {
            aCache.put("isLike" + CityId, "false");
            noLChecked = false;
        }
    }

    @OnClick(value = R.id.detail_city_hasgone)
    public void goneClick(View view) {
        if (!noHChecked) {
            aCache.put("isGone" + CityId, "true");
            noHChecked = true;
        } else {
            aCache.put("isGone" + CityId, "false");
            noHChecked = false;
        }
    }

    @OnClick(R.id.city_detail_back)
    public void back(View view) {
        this.finish();
    }

}
