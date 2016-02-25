package com.travel.personaltravel.activity;

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
import com.travel.personaltravel.fragment.CityDetailFragment;
import com.travel.personaltravel.fragment.search.SearchCityDetailFragment;

public class CityDetailActivity extends FragmentActivity implements View.OnClickListener {

    @ViewInject(R.id.city_detail_like)
    private Button like;
    @ViewInject(R.id.city_detail_hasgone)
    private Button hasgone;
    @ViewInject(R.id.city_detail_title)
    private TextView city_detail_title;
    private boolean flaghasgone = true;
    private boolean flaglike = true;
    //private CityDetailFragment fragment;
    private SearchCityDetailFragment fragment;
    private String title;

    private String CityId = "5473cce2b8ce043a64108e12";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);
        ViewUtils.inject(this);
        initEvent();
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

    private void initEvent() {
        like.setOnClickListener(this);
        hasgone.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.city_detail_hasgone:
                break;
            case R.id.city_detail_like:
                break;
        }
    }

    @OnClick(R.id.city_detail_back)
    public void back(View view) {
        this.finish();
    }
}
