package com.travel.personaltravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.travel.personaltravel.R;
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
    private String apiKey;
    private String nickName = "已登录";
    private String headImg;

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
    }

    //TODO 登录请求, 获取用户API-Key
    private void loginPost() {
        /**
         * 登录请求: 是 OkHttp会直接判断"成功" 或 "失败", 对应2个方法
         * 请求成功返回的头部信息: Auth-Response,1代表登录成功,0用户名错误或者密码错误; API-Key,用户的Key
         */
        Map<String, String> params = new HashMap<>();
        params.put("username", userName);
        params.put("password", userPwd);
    }

    //手机号码的格式
    public void phoneNumAddSpace(final EditText mEditText) {
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 1) {
                    int len = s.toString().length();
                    if (len == 3 || len == 8) {
                        uname.setText(s + " ");
                        uname.setSelection(uname.getText().toString().length());
                    }
                }
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
                if (userName.equals("") && uname.length() == 11) {
                    userName = uname.getText().toString();
                }
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
