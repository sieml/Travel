package com.travel.personaltravel.webview;

/**
 * Created by Zern on 2015/10/30.
 */

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * 用于处理WebView加载时的进度,标题等信息
 */
public class MyWebChromeClient extends WebChromeClient{

    private BrowserSupport browserSupport ;

    public MyWebChromeClient(BrowserSupport browserSupport) {
        this.browserSupport = browserSupport;
    }


    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if(browserSupport!=null){
            browserSupport.onProgressUpdate(newProgress);
        }
    }

}
