package com.travel.personaltravel.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.travel.personaltravel.R;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.fragment.search.SearchCityDetailFragment;

public class SearchCityDetailActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 返回上一个页面
     */
    @ViewInject(R.id.search_detail_city_back)
    private ImageView backImg;

    @ViewInject(R.id.search_detail_city_name)
    private TextView cityName;


    private SearchCityDetailFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_city_detail_activity);

        ViewUtils.inject(this);

        //设置事件监听
        initEvent();

        //加载Fragment
        initFragment();
    }

    private void initFragment() {
        String cityId = getIntent().getStringExtra(SieConstant.INTENT_CITY_ID);
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

    private void initEvent() {
        backImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_detail_city_back://返回上一个页面
                finish();
                break;
        }
    }
}
