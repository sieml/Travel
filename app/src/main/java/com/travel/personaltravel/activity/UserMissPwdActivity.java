package com.travel.personaltravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.personaltravel.R;
import com.travel.personaltravel.cache.ACache;
import com.travel.personaltravel.widget.ClearEditText;


public class UserMissPwdActivity extends AppCompatActivity implements View.OnClickListener {

    private ClearEditText newPwd;
    private ClearEditText newRePwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_miss_pwd_activity);
        newPwd = (ClearEditText) findViewById(R.id.f_user_new_pwd_dt);
        newRePwd = (ClearEditText) findViewById(R.id.f_user_new_repwd_dt);
        TextView confirmTv = (TextView) findViewById(R.id.f_user_press_confirm_tv);
        confirmTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (R.id.f_user_press_confirm_tv == v.getId()) {
            int npLen = newPwd.getText().length();
            if (npLen < 6) {
                Toast.makeText(UserMissPwdActivity.this, "密码长度小于6", Toast.LENGTH_SHORT).show();
            } else {
                String npStr = newPwd.getText().toString();
                String npRStr = newRePwd.getText().toString();
                if (!npStr.equals(npRStr)) {
                    Toast.makeText(UserMissPwdActivity.this, "密码前后输入不一致", Toast.LENGTH_SHORT).show();
                } else {
                    ACache.get(UserMissPwdActivity.this).put("upwd", npStr);
                    startActivity(new Intent(UserMissPwdActivity.this, UserLoginActivity.class));
                    UserMissPwdActivity.this.finish();
                }
            }
        }
    }
}
