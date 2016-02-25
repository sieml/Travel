package com.travel.personaltravel.webview;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Zern on 2015/10/30.
 */

/**
 * 自定义网址加载的WebViewClient
 */
public class MyWebViewClient extends WebViewClient {

    private Context context;

    private BrowserSupport browserSupport ;

    public MyWebViewClient(Context context, BrowserSupport browserSupport) {
        this.context = context;
        this.browserSupport = browserSupport;
    }

    /**
     * * 当WebView需要加载GET请求的网址的时候,
     * !!!!并且不是loadUrl加载网址才会进入这个方法;
     * WebView会回调这个方法,如果当前的方法,返回true,代表由程序自己来完成
     * 就像事件分发中,返回true一样的效果,false由WebView内部的引擎来加载网页
     *
     * @param view
     * @param url
     * @return
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.i("debug123", url);
        boolean ret = false;
        if (url.startsWith("abc://")) {
            //需要自定义的情况,内部处理网址
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "Hello World");
            String address = url.substring("abc://".length()).replaceAll("/", "");
            intent.putExtra(Intent.EXTRA_EMAIL, address);
            context.startActivity(intent);
            ret = true;
        }
        return ret;
    }

}


















