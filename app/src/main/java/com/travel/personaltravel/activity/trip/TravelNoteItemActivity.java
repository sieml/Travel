package com.travel.personaltravel.activity.trip;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.travel.personaltravel.R;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.webview.BrowserSupport;
import com.travel.personaltravel.webview.MyWebChromeClient;
import com.travel.personaltravel.webview.MyWebViewClient;

public class TravelNoteItemActivity extends FragmentActivity implements BrowserSupport {

    @ViewInject(R.id.one_city_travel_note_web_view)
    private WebView webView;

    @ViewInject(R.id.one_city_travel_note_progress_bar)
    private ProgressBar progressBar;

    private MyWebViewClient webViewClient;

    private String url;
    private String noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_note_item);
        ViewUtils.inject(this);
        noteId = getIntent().getStringExtra("noteId");
        if (noteId != null) {
            url = String.format(SieConstant.CITY_TRAVEL_NOTE_WEBVIEW, noteId);

            // 1. WebView的loadUrl会检查自身包含WebViewClient对象
            // 包含就由WebView自身来加载,否则就会打开(隐式意图)游览器,
            webViewClient = new MyWebViewClient(this, this);
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setBuiltInZoomControls(true);
            settings.setDisplayZoomControls(true);
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            webView.setWebViewClient(webViewClient);
            webView.setWebChromeClient(new MyWebChromeClient(this));

            //加载网络服务器方式
            webView.loadUrl(url);
        }
    }

    public void refresh(View view) {
        if (webView != null) {
            webView.reload();
        }
    }

    public void back(View view) {
        if (webView.canGoBack()) {
            webView.goBack();
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) { //如果可以后退,那么直接后退,否则直接退出Activity
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onProgressUpdate(int progress) {
        progressBar.setProgress(progress);
    }

    @OnClick(R.id.one_city_travel_note_top_back)
    public void ImageBack() {
        onBackPressed();
    }
}
