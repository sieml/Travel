package com.travel.personaltravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mileslife.R;
import com.android.mileslife.application.InitApplication;
import com.android.mileslife.constant.SieConstant;
import com.android.mileslife.widget.ClearEditText;
import com.lee.okhttplib.OkHttpTool;
import com.lee.okhttplib.callback.MapCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Headers;

public class UserRegisterLastActivity extends BaseActivity {

    private ClearEditText smsCode;
    private ClearEditText userPwd;
    private String tel;
    private TextView reGainSmsCode;
    private String registerUrl = SieConstant.USER_REGISTER_URL;
    private TextView sendRegister;

    @Override
    public void initView(Bundle state) {
        setContentView(R.layout.user_register_last_activity);
        smsCode = (ClearEditText) findViewById(R.id.l_user_sms_code_register_dt);
        userPwd = (ClearEditText) findViewById(R.id.l_user_create_pwd_dt);
        reGainSmsCode = (TextView) findViewById(R.id.l_user_re_gain_sms_code_tv);
        sendRegister = (TextView) findViewById(R.id.l_user_done_register_tv);
        ImageView backIv = (ImageView) findViewById(R.id.l_register_title_bar_back_tv);
        backIv.setOnClickListener(this);
        reGainSmsCode.setOnClickListener(this);
        sendRegister.setOnClickListener(this);
        tel = getIntent().getStringExtra("uTel");
        gainSmsCode(tel);
        reGainSmsCode.setEnabled(false);
        countDownThread();
    }


    @Override
    public void viewOnClick(View v) {
        switch (v.getId()) {
            case R.id.l_user_re_gain_sms_code_tv:
                //TODO 重新发送验证码
                gainSmsCode(tel);
                reGainSmsCode.setEnabled(false);
                countDownThread();
                break;
            case R.id.l_user_done_register_tv:
                //TODO 监听注册请求
                sendRegister();
                break;
            case R.id.l_register_title_bar_back_tv:
                finish();
                break;
        }
    }

    private void countDownThread() {
        //60s, 间隔时间1s
        new DecreaseCount(60000, 1000).start();
    }

    /* TODO 定义一个倒计时的内部类 */
    class DecreaseCount extends CountDownTimer {
        public DecreaseCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            reGainSmsCode.setSelected(true);
            reGainSmsCode.setText("重新获取");
            reGainSmsCode.setEnabled(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            reGainSmsCode.setText("重新获取(" + millisUntilFinished / 1000 + ")");
            reGainSmsCode.setSelected(false);
        }
    }

    private void sendRegister() {
        String uSMSCode = smsCode.getText().toString();
        String uPassword = userPwd.getText().toString();
        if (uSMSCode.length() != 6) {
            showToast("验证码错误!");
        } else if (uPassword.length() < 6) {
            showToast("密码小于6位");
        } else {
            Map<String, String> params = new HashMap<>();
            params.put("username", tel);
            params.put("password", uPassword);
            params.put("code", uSMSCode);
            registerPost(params);
        }
    }

    private String smsCodeUrl = SieConstant.USER_REGISTER_SMS_CODE_URL;

    private void gainSmsCode(String tel) {
        //之前页面已经判断过是否合法
        String url = String.format(smsCodeUrl, tel);
        OkHttpTool.getInstance().get().url(url).build()
                .asyncExecute(new MapCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        showToast("网络不给力,请重试!");
                    }

                    @Override
                    public void onResponse(HashMap<String, Object> response) {
                        //TODO 接收验证码
                        String hint = (String) response.get("body");
                        String failReason = null;
                        try {
                            JSONObject obj = new JSONObject(hint);
                            failReason = obj.getString("error");
                        } catch (JSONException e) {
                            InitApplication.reportException(this, e);
                        }
                        if (hint != null && !hint.contains("null")) {
                            showToast("验证码接收失败: " + failReason);
                            sendRegister.setEnabled(false);
                            UserRegisterLastActivity.this.finish();
                        } else if (hint.contains("null")) {
                            showToast("请查看短信验证码");
                        }
                    }
                });
    }

    private void registerPost(Map<String, String> params) {
        /**
         * 注册请求: 是 OkHttp会直接判断"成功" 或 "失败", 对应2个方法
         * status_code200 时，表示发送成功 400 表示失败，返回json格式数据
         */
        OkHttpTool.getInstance().postForm().params(params).url(registerUrl).build()
                .asyncExecute(new MapCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        showToast("网络不给力,请检查网络!");
                    }

                    @Override
                    public void onResponse(HashMap<String, Object> response) {
                        Headers headers = (Headers) response.get("header");
                        String msgInfo = (String) response.get("body");
                        //头部信息, 判断是否注册成功
                        String resp = headers.get("Auth-Response");
                        if (resp.equals("0")) {
                            showToast("注册失败!\n" + msgInfo);
                        } else if (resp.equals("1")) {
                            showToast("恭喜您注册成功");
                            InitApplication.spUtil.put(SieConstant.SPKEY_USER_LOGIN_FLAG, false);
                            InitApplication.isLogged = false;
                            InitApplication.spUtil.remove(SieConstant.SPKEY_USER_API_KEY);
                            JSONObject obj = null;
                            try {
                                obj = new JSONObject(msgInfo);
                                String apiKey = obj.getString("apikey");
                                String sname = obj.getString("username");
                                InitApplication.spUtil.put(SieConstant.SPKEY_USER_NAME, sname);
                                InitApplication.spUtil.put(SieConstant.SPKEY_USER_API_KEY, apiKey);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Intent intent = new Intent(UserRegisterLastActivity.this, UserLoginActivity.class);
                            intent.putExtra("yrName", tel);
                            startActivity(intent);
                            UserRegisterLastActivity.this.finish();
                        }
                    }
                });
    }
}
