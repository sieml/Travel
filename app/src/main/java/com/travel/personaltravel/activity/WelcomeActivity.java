package com.travel.personaltravel.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.ImageView;

import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.travel.personaltravel.BuildConfig;
import com.travel.personaltravel.MainActivity;
import com.travel.personaltravel.application.AiLYApplication;
import com.travel.personaltravel.R;
import com.travel.personaltravel.constant.SieConstant;

public class WelcomeActivity extends FragmentActivity implements Runnable {

    private ImageView splash_img;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        initView();
    }

    private void initView() {
        splash_img = (ImageView) findViewById(R.id.splash_img);
        thread = new Thread(this);
        setImage();
    }

    public void setImage() {
        // TODO 模拟 网络请求 加载图片
        AiLYApplication.bitmapUtils.display(splash_img, SieConstant.Url_Splash_Pic, new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView container, String uri, Bitmap bitmap, BitmapDisplayConfig config, BitmapLoadFrom from) {
                if (bitmap != null) {
                    splash_img.setImageBitmap(bitmap);
                }
                thread.start();
            }

            @Override
            public void onLoadFailed(ImageView container, String uri, Drawable drawable) {
                splash_img.setImageResource(R.mipmap.ic_launcher);
                JumpActivity();
            }
        });
    }

    public void JumpActivity() {
        //Acitivity实现接口implements Runnable
        // 跳转 Activity
        Intent intent = new Intent();
        // TODO 查看 共享参数,和当前的版本的对比  不一致就显示欢迎页
        SharedPreferences sp =
                getSharedPreferences(SieConstant.Splash_Share, MODE_PRIVATE);
        // 获取上次保存的的版本号,
        int version = sp.getInt(SieConstant.App_Version, -1);
        //更新之后,版本升级,
        if (BuildConfig.VERSION_CODE != version) {
            // TODO 显示欢迎页
            intent.setClass(this, SplashActivity.class);
        } else {
            // TODO 显示主界面
            intent.setClass(this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }

    @Override
    public void run() {
        try {
            thread.sleep(650);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JumpActivity();
    }
}

















