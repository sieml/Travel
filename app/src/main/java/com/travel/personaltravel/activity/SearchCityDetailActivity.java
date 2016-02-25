package com.travel.personaltravel.activity;

import android.content.Intent;
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

    /**
     * 与好友聊天的消息
     * @param savedInstanceState
     */
    @ViewInject(R.id.search_detail_city_talk)
    private ImageView talkImg;

    /**
     * 添加行程
     * @param savedInstanceState
     */
    @ViewInject(R.id.search_detail_city_add_travel)
    private ImageView addTravel;

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

        fragment = SearchCityDetailFragment.newInstance(cityId);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.search_detail_city_fragment,fragment,"srcf");
        transaction.commit() ;
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        talkImg.setOnClickListener(this);
        addTravel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_detail_city_back://返回上一个页面
                finish();
                break;

            case R.id.search_detail_city_add_travel://添加旅游计划
                startActivity(new Intent(this, AddSearchTravelPlanActivity.class));
                break;

            case R.id.search_detail_city_talk://创建聊天
                startActivity(new Intent(this, CreateSearchTalkActivity.class));
                break;
        }
    }
}
