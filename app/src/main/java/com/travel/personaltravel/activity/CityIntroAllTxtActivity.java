package com.travel.personaltravel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.travel.personaltravel.R;

public class CityIntroAllTxtActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_intro_all_txt);
        TextView txt = (TextView) findViewById(R.id.city_intro_txt_tv);
        String txtStr = getIntent().getStringExtra("cityTxt");
        if (txtStr != null) {
            txt.setText("  " + txtStr);
        }
    }
}
