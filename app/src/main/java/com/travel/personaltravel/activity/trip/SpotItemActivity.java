package com.travel.personaltravel.activity.trip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.travel.personaltravel.R;
import com.travel.personaltravel.constant.SieConstant;

public class SpotItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_item);
        String cityId = getIntent().getStringExtra(SieConstant.ACTION_EXTRA);
    }
}
