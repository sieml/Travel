package com.travel.personaltravel.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.travel.personaltravel.R;
import com.travel.personaltravel.constant.SieConstant;


public class UserMissPwdActivity extends AppCompatActivity {

    private String missPwdUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_miss_pwd_activity);
    }

}
