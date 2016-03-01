package com.travel.personaltravel.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.mileslife.R;
import com.android.mileslife.constant.SieConstant;

public class UserMissPwdActivity extends BaseActivity {

    private String missPwdUrl;

    @Override
    public void initView(Bundle state) {
        setContentView(R.layout.user_miss_pwd_activity);
        isWebPage = SieConstant.WEB_PAGE_TYPE;
        setTitleBarBack(DEFAULT_RES_ID);
        setTitleBarTxt("重设密码");
        missPwdUrl = SieConstant.USER_RESET_PASSWORD;
        setWebView(this);
        loadWebUrl(missPwdUrl);
        initWebViewClient();
    }

    @Override
    public void viewOnClick(View v) {
    }


    private void initWebViewClient() {
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        webView.setWebViewClient(new WebViewClient() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                boolean ret = false;
                if (url.contains("/useraccount/login/")) {
                    showToast("完成...");
                    UserMissPwdActivity.this.finish();
                    ret = true;
                }
                return ret;
            }

            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //这里进行无网络或错误处理，具体可以根据errorCode的值进行判断，做跟详细的处理。
                view.stopLoading();
                view.loadUrl("about:blank");
                noNet.setVisibility(View.VISIBLE);
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
    }
}
