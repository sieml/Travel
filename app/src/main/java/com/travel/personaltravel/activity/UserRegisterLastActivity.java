package com.travel.personaltravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.travel.personaltravel.R;
import com.travel.personaltravel.cache.ACache;
import com.travel.personaltravel.widget.ClearEditText;


public class UserRegisterLastActivity extends AppCompatActivity implements View.OnClickListener {

    private ClearEditText userPwd;
    private String name;
    private TextView sendRegister;
    private ACache aCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register_last_activity);
        userPwd = (ClearEditText) findViewById(R.id.l_user_create_pwd_dt);
        sendRegister = (TextView) findViewById(R.id.l_user_done_register_tv);
        ImageView backIv = (ImageView) findViewById(R.id.l_register_title_bar_back_tv);
        backIv.setOnClickListener(this);
        sendRegister.setOnClickListener(this);
        name = getIntent().getStringExtra("uName");
        aCache = ACache.get(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.l_user_done_register_tv:
                //TODO 监听注册
                sendRegister();
                break;
            case R.id.l_register_title_bar_back_tv:
                finish();
                break;
        }
    }

    private void sendRegister() {
        String pwd = userPwd.getText().toString();
        if (pwd == null || pwd == "") {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            aCache.put("uname", name);
            aCache.put("upwd", pwd);
            startActivity(new Intent(this, UserLoginActivity.class));
            finish();
        }
    }

}
