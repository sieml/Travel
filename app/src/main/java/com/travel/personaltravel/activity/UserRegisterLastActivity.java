package com.travel.personaltravel.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.travel.personaltravel.R;
import com.travel.personaltravel.widget.ClearEditText;


public class UserRegisterLastActivity extends AppCompatActivity implements View.OnClickListener {

    private ClearEditText smsCode;
    private ClearEditText userPwd;
    private String tel;
    private TextView sendRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register_last_activity);
        userPwd = (ClearEditText) findViewById(R.id.l_user_create_pwd_dt);
        sendRegister = (TextView) findViewById(R.id.l_user_done_register_tv);
        ImageView backIv = (ImageView) findViewById(R.id.l_register_title_bar_back_tv);
        backIv.setOnClickListener(this);
        sendRegister.setOnClickListener(this);
        tel = getIntent().getStringExtra("uTel");
        countDownThread();
    }

    private void countDownThread() {
        //60s, 间隔时间1s
        new DecreaseCount(60000, 1000).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.l_user_done_register_tv:
                //TODO 监听注册请求
                sendRegister();
                break;
            case R.id.l_register_title_bar_back_tv:
                finish();
                break;
        }
    }

    /* TODO 定义一个倒计时的内部类 */
    class DecreaseCount extends CountDownTimer {
        public DecreaseCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }
    }

    private void sendRegister() {
    }

}
