package com.travel.personaltravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.travel.personaltravel.MainActivity;
import com.travel.personaltravel.R;
import com.travel.personaltravel.application.AiLYApplication;
import com.travel.personaltravel.cache.ACache;
import com.travel.personaltravel.widget.ClearEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class UserLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private String userName = "";
    private String userPwd;
    private ClearEditText uname;
    private ClearEditText upassword;
    private ImageView cancelIb;
    private String nickName = "已登录";
    private ACache aCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_activity);
        uname = (ClearEditText) findViewById(R.id.user_phone_number_edit_text);
        upassword = (ClearEditText) findViewById(R.id.user_password_edit_text);
        TextView wxLoginTv = (TextView) findViewById(R.id.user_wx_third_party_login_tv);
        wxLoginTv.setOnClickListener(this);
        cancelIb = (ImageView) findViewById(R.id.login_title_bar_cancel_iv);
        cancelIb.setOnClickListener(this);
        TextView loginTv = (TextView) findViewById(R.id.user_click_login_btn);
        loginTv.setOnClickListener(this);
        Button regbtn = (Button) findViewById(R.id.user_register_account_btn);
        regbtn.setOnClickListener(this);
        Button missBtn = (Button) findViewById(R.id.user_miss_pwd_btn);
        missBtn.setOnClickListener(this);
        aCache = ACache.get(this);
    }

    //TODO 登录请求
    private void loginPost() {
        String locName = aCache.getAsString("uname");
        String locPwd = aCache.getAsString("upwd");
        Log.d("sie", "name = " + locName + ", pwd = " + locPwd);
        if (locName != null && !locName.equals(userName)) {
            Toast.makeText(this, "用户名出错", Toast.LENGTH_SHORT).show();
        } else if (locPwd != null && !locPwd.equals(userPwd)) {
            Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
        } else if (locName.equals(userName) && locPwd.equals(userPwd)) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            AiLYApplication.isLogged = true;
            aCache.put("isLogin", "true");
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "无法验证", Toast.LENGTH_SHORT).show();
        }
    }

    public void nameInput(final EditText mEditText) {
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userName = uname.getText().toString();
                if (s.length() == 13) {
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_click_login_btn:
                userPwd = upassword.getText().toString();
                userName = uname.getText().toString();
                loginPost();
                break;
            case R.id.login_title_bar_cancel_iv:
                finish();
                break;
            case R.id.user_register_account_btn:
                //注册
                startActivity(new Intent(this, UserRegisterFirstActivity.class));
                break;
            case R.id.user_miss_pwd_btn:
                //忘记密码
                //startActivity(new Intent(this, UserResetPwdActivity.class));
                startActivity(new Intent(this, UserMissPwdActivity.class));
                break;
            case R.id.user_wx_third_party_login_tv:
                //startActivityForResult(new Intent(UserLoginActivity.this, WXEntryActivity.class), REQUEST_WX_ISLOGGED_CODE);
                break;
        }
    }
}
