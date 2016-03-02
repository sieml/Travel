package com.travel.personaltravel.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.travel.personaltravel.R;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.fragment.search.SearchResultFragment;


public class SearchResultActivity extends AppCompatActivity {

    private String srUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_activity);

        String cityKey = getIntent().getStringExtra("srCityKey");
        srUrl = String.format(SieConstant.SEARCH_CITY_LIST, cityKey);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        SearchResultFragment srFragment = (SearchResultFragment) manager.findFragmentByTag("srFragment");
        if (srFragment == null) {
            srFragment = SearchResultFragment.newInstance(srUrl);
        }
        transaction.replace(R.id.fragment_search_container, srFragment, "srFragment");
        transaction.commit();
    }

    public void backIv(View view) {
        finish();
    }
}
