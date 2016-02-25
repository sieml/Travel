package com.travel.personaltravel.application;

import android.app.Application;
import android.os.Environment;
import com.lidroid.xutils.BitmapUtils;

import java.io.File;

/**
 * Created by on
 * Author: Zern
 * DATE:2015/11/3
 * Time: 13:03
 * email:AndroidZern@163.com
 */
public final class AiLYApplication extends Application {
    public static BitmapUtils bitmapUtils ;
    public static String userId = "206502" ;

    @Override
    public void onCreate() {
        // TODO 加载一些全局的变量
        File file = Environment.getDownloadCacheDirectory();
        bitmapUtils = new BitmapUtils(this) ;
        super.onCreate();

    }
}
