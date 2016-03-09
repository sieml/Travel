package com.travel.personaltravel.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.travel.personaltravel.R;
import com.travel.personaltravel.webview.BrowserSupport;
import com.travel.personaltravel.webview.MyWebChromeClient;
import com.travel.personaltravel.webview.MyWebViewClient;
import com.travel.personaltravel.constant.SieConstant;

public class FoodGuideActivity extends FragmentActivity implements BrowserSupport {

    @ViewInject(R.id.food_progressbar)
    private ProgressBar progressBar;

    @ViewInject(R.id.food_webView)
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_food_guide);
        TextView titleTxt = (TextView) findViewById(R.id.food_title);
        ViewUtils.inject(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new MyWebViewClient(this, this));
        webView.setWebChromeClient(new MyWebChromeClient(this));

        String cityId = getIntent().getStringExtra("cityId");
        String tag = getIntent().getStringExtra(SieConstant.WebView_Tag);
        //加载网络服务器方式
        if (cityId != null) {
            if ("food".equals(tag)) {
                titleTxt.setText("美食详细攻略");
                webView.loadUrl(String.format(SieConstant.Url_Food_Guide, cityId));
            } else if ("shop".equals(tag)) {
                titleTxt.setText("购物详细攻略");
                webView.loadUrl(String.format(SieConstant.Url_Shopping_Guide, cityId));
            }
        } else {
            Toast.makeText(this, "不好意思,数据解析错误", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onProgressUpdate(int progress) {
        progressBar.setProgress(progress);
    }

    @OnClick(R.id.foodguide_back)
    public void foodguideback(View view) {
        finish();
    }
}
