package com.travel.personaltravel.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.travel.personaltravel.R;
import com.travel.personaltravel.cache.ACache;
import com.travel.personaltravel.widget.CircleImageView;


public class PersonalCenterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView uNameTv;
    private CircleImageView avatarIv;
    private ACache aCache;
    private String avatarImgCacheDir = "avatar-cache";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_center_activity);
        ImageView backIv = (ImageView) findViewById(R.id.personal_center_title_bar_back_iv);
        avatarIv = (CircleImageView) findViewById(R.id.personal_center_avatar_iv);
        ImageView setIv = (ImageView) findViewById(R.id.personal_center_setting_iv);
        uNameTv = (TextView) findViewById(R.id.personal_center_title_bar_uname_tv);
        LinearLayout balanceLl = (LinearLayout) findViewById(R.id.personal_center_my_balance_ll);
        balanceLl.setOnClickListener(this);
        LinearLayout uOrderLl = (LinearLayout) findViewById(R.id.personal_center_my_order_ll);
        LinearLayout uCollectLl = (LinearLayout) findViewById(R.id.personal_center_my_collect_ll);
        setIv.setOnClickListener(this);
        backIv.setOnClickListener(this);
        avatarIv.setOnClickListener(this);
        uOrderLl.setOnClickListener(this);
        uCollectLl.setOnClickListener(this);
        aCache = ACache.get(this, avatarImgCacheDir);
        Bitmap cAvatar = aCache.getAsBitmap("cAvatar");
        if (cAvatar != null) {
            avatarIv.setImageBitmap(cAvatar);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Bitmap cAvatar = aCache.getAsBitmap("cAvatar");
        if (cAvatar != null) {
            avatarIv.setImageBitmap(cAvatar);
        }
    }

    private void isUpdateAvatar() {
        startActivity(new Intent(this, AvatarPickerActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_center_title_bar_back_iv:
                finish();
                break;
            case R.id.personal_center_my_balance_ll:
                break;
            case R.id.personal_center_avatar_iv:
                //TODO 选择或拍照完成之后 上传头像,取消则不上传
                isUpdateAvatar();
                break;
            case R.id.personal_center_my_order_ll:
                break;
            case R.id.personal_center_my_collect_ll:
                break;
            case R.id.personal_center_setting_iv:
                break;
        }
    }
}
