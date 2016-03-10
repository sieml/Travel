package com.travel.personaltravel.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.travel.personaltravel.R;
import com.travel.personaltravel.widget.SwitchView;

public class PersonalSettingActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_setting_activity);
        //TODO token, api-key, username
        ImageView bcakIv = (ImageView) findViewById(R.id.personal_setting_title_bar_back_iv);
        RelativeLayout fbRl = (RelativeLayout) findViewById(R.id.personal_setting_feedback_rl);
        fbRl.setOnClickListener(this);
        RelativeLayout faqRl = (RelativeLayout) findViewById(R.id.personal_setting_faq_rl);
        faqRl.setOnClickListener(this);
        RelativeLayout aboutRl = (RelativeLayout) findViewById(R.id.personal_setting_about_us_rl);
        aboutRl.setOnClickListener(this);

        RelativeLayout checkVerRl = (RelativeLayout) findViewById(R.id.personal_setting_check_version_rl);
        checkVerRl.setOnClickListener(this);
        bcakIv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //TODO 设置
            case R.id.personal_setting_about_us_rl:
                startActivity(new Intent(PersonalSettingActivity.this, AppAboutActivity.class));
                break;
            case R.id.personal_setting_faq_rl:
                //startActivity(new Intent(PersonalSettingActivity.this, AppFAQActivity.class));
                break;
            case R.id.personal_setting_feedback_rl:
                //startActivity(new Intent(PersonalSettingActivity.this, AppFeedbackActivity.class));
                break;
            case R.id.personal_setting_check_version_rl:
                AlertDialog.Builder builder = new AlertDialog.Builder(PersonalSettingActivity.this);
                builder.setMessage("检查应用是否有新版?");
                builder.setTitle("提示");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.create().show();

                break;
            //TODO 其他
            case R.id.personal_setting_title_bar_back_iv:
                finish();
                break;
        }
    }
}
