package com.travel.personaltravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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
                String pwdStr = userPwd.getText().toString();
                if (pwdStr.length() < 6) {
                    Toast.makeText(UserRegisterLastActivity.this, "密码长度小于6位", Toast.LENGTH_SHORT).show();
                } else {
                    sendRegister(pwdStr);
                }
                break;
            case R.id.l_register_title_bar_back_tv:
                finish();
                break;
        }
    }

    public void pwdInput(final EditText mEditText) {
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                name = userPwd.getText().toString();
            }
        });
    }

    private void sendRegister(String pwd) {
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
