package com.travel.personaltravel.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.travel.personaltravel.BuildConfig;
import com.travel.personaltravel.MainActivity;
import com.travel.personaltravel.R;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.widget.transformer.CubeOutTransformer;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends FragmentActivity {

    private ViewPager viewPager;

    private List<ImageView> imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        viewPager = (ViewPager) findViewById(R.id.splash_viewpager);
        imgs = new ArrayList<>();
        TextView jumpTv = (TextView) findViewById(R.id.jump_tv);
        //下划线
        jumpTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        //抗锯齿
        jumpTv.getPaint().setAntiAlias(true);

        ImageView imageView = new ImageView(this);
        ImageView imageView1 = new ImageView(this);
        ImageView imageView2 = new ImageView(this);
        imageView.setImageResource(R.mipmap.guide_1);
        imageView1.setImageResource(R.mipmap.guide_2);
        imageView2.setImageResource(R.mipmap.guide_3);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);

        imgs.add(imageView);
        imgs.add(imageView1);
        imgs.add(imageView2);

        WelVPAdapter adapter = new WelVPAdapter(imgs);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new CubeOutTransformer());
        //获取共享参数 扉页的共享参数的信息
        SharedPreferences sp =
                getSharedPreferences(SieConstant.Splash_Share, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        //保存当前版本号
        edit.putInt(SieConstant.App_Version, BuildConfig.VERSION_CODE);
        edit.apply();
    }

    // 在进入欢迎页的时候 用户点击 回退按钮,默认进入 MainActivity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    public void welJump(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * ViewPager的适配器
     */
    class WelVPAdapter extends PagerAdapter {
        private List<ImageView> lists;

        public WelVPAdapter(List<ImageView> imgs) {
            lists = imgs;
        }

        @Override
        public int getCount() {
            int ret = 0;
            if (lists != null) {
                ret = lists.size();
            }
            return ret;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(lists.get(position));
            return lists.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(lists.get(position));
        }

    }
}
