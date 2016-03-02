package com.travel.personaltravel.application;

import android.app.Application;
import android.os.Environment;

import com.lidroid.xutils.BitmapUtils;
import com.travel.personaltravel.cache.ACache;

import java.io.File;

/**
 * Created by on
 * Author: Zern
 * DATE:2015/11/3
 * Time: 13:03
 * email:AndroidZern@163.com
 */
public final class AiLYApplication extends Application {
    public static BitmapUtils bitmapUtils;
    // 唯一登录设置
    public static boolean isLogged;
    private ACache aCache;

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO 加载一些全局的变量
        bitmapUtils = new BitmapUtils(this);
        aCache = ACache.get(this);
        String isLogin = aCache.getAsString("isLogin");
        if (isLogin != null && isLogin.equals("true")) {
            isLogged = true;
        }
    }
}
