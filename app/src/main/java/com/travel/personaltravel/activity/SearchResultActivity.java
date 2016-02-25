package com.travel.personaltravel.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.travel.personaltravel.R;
import com.travel.personaltravel.fragment.search.SearchResultFragment;

public class SearchResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_activity);
        String url = getIntent().getStringExtra("srUrl");

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        SearchResultFragment srFragment = (SearchResultFragment) manager.findFragmentByTag("srFragment");
        if (srFragment == null) {
            srFragment = SearchResultFragment.newInstance(url);
        }
        transaction.replace(R.id.fragment_search_container, srFragment, "srFragment");
        transaction.commit();
    }
}
