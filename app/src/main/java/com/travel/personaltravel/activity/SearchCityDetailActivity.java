package com.travel.personaltravel.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.travel.personaltravel.R;
import com.travel.personaltravel.cache.ACache;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.fragment.search.SearchCityDetailFragment;

public class SearchCityDetailActivity extends AppCompatActivity {


    private SearchCityDetailFragment fragment;
    private ACache aCache;

    @ViewInject(R.id.detail_city_like)
    private CheckBox isLikeTv;

    @ViewInject(R.id.detail_city_hasgone)
    private CheckBox isGoneTv;

    @ViewInject(R.id.search_detail_city_name)
    private TextView cityName;
    private String cityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_city_detail_activity);
        aCache = ACache.get(this);

        ViewUtils.inject(this);
        //加载Fragment
        initFragment();
    }

    private void initFragment() {
        cityId = getIntent().getStringExtra(SieConstant.INTENT_CITY_ID);
        String isLike = aCache.getAsString("isLike" + cityId);
        Log.d("sie", "islike = " + isLike);
        if (isLike != null && isLike.equals("true")) {
            isLikeTv.setChecked(true);
        }
        String isGone = aCache.getAsString("isGone" + cityId);
        if (isGone != null && isGone.equals("true")) {
            isGoneTv.setChecked(true);
        }
        String searchCityName = getIntent().getStringExtra(SieConstant.INTENT_CITY_NAME);
        cityName.setText(searchCityName);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        fragment = (SearchCityDetailFragment) manager.findFragmentByTag("srcf");
        if (fragment == null) {
            fragment = SearchCityDetailFragment.newInstance(cityId);
        }
        transaction.add(R.id.search_detail_city_fragment, fragment, "srcf");
        transaction.commit();
    }

    private boolean noLChecked = false;
    private boolean noHChecked = false;

    @OnClick(value = R.id.search_detail_city_like)
    public void likeClick(View view) {
        Log.d("sie", "gggg");
        if (!noLChecked) {
            aCache.put("isLike" + cityId, "true");
            noLChecked = true;
        } else {
            aCache.put("isLike" + cityId, "false");
            noLChecked = false;
        }
    }

    @OnClick(value = R.id.search_detail_city_hasgone)
    public void goneClick(View view) {
        if (!noHChecked) {
            aCache.put("isGone" + cityId, "true");
            noHChecked = true;
        } else {
            aCache.put("isGone" + cityId, "false");
            noHChecked = false;
        }
    }

    @OnClick(value = R.id.search_detail_city_back)
    public void finishClick(View view) {
        finish();
    }
}
