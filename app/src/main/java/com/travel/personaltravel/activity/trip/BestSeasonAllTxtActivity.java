package com.travel.personaltravel.activity.trip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.travel.personaltravel.R;

public class BestSeasonAllTxtActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_season_all_txt);
        TextView txt = (TextView) findViewById(R.id.bs_txt_tv);
        String txtStr = getIntent().getStringExtra("bsTxt");
        if (txtStr != null) {
            txt.setText("  " + txtStr);
        }
    }
}
