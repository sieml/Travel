package com.travel.personaltravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.travel.personaltravel.activity.SearchActivity;
import com.travel.personaltravel.fragment.trip.CityFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewUtils.inject(this);

        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        CityFragment cityFragmentInland = (CityFragment) manager.findFragmentByTag("citysFragment");
        if (cityFragmentInland == null) {
            cityFragmentInland = CityFragment.newInstance();
        }
        transaction.replace(R.id.fragment_trip_container, cityFragmentInland,"citysFragment");
        transaction.commit();
    }

    @OnClick(value = R.id.main_title_bar_search_iv)
    public void onClickSearch(View view) {
        startActivity(new Intent(MainActivity.this, SearchActivity.class));
    }

}
