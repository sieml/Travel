package com.travel.personaltravel;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.lidroid.xutils.ViewUtils;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.fragment.trip.CityFragment;

public class MainActivity extends AppCompatActivity {
    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);

        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        CityFragment cityFragmentInland = (CityFragment) manager.findFragmentByTag("inlandFragment");
        if (cityFragmentInland == null) {
            cityFragmentInland = CityFragment.newInstance(SieConstant.pathInlandRecommend);
        }
        transaction.replace(R.id.fragment_trip_container, cityFragmentInland);
        transaction.commit();
    }
}
