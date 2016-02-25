package com.travel.personaltravel.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.travel.personaltravel.R;
import com.travel.personaltravel.constant.SieConstant;

import org.json.JSONException;
import org.json.JSONObject;

public class ShoppingActivity extends FragmentActivity {
    @ViewInject(R.id.shop_title)
    private TextView shop_title ;
    @ViewInject(R.id.shop_content)
    private TextView shop_content ;

    private String title;
    private String cityId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        setContentView(R.layout.activity_shopping);
        ViewUtils.inject(this);
        Intent intent = getIntent();
        title = intent.getStringExtra("shopping_title");
        cityId = intent.getStringExtra("city_id");
        initView() ;

    }

    private void initView() {
        if (title != null) {
            shop_title.setText(title+"购物");
        }
        if (cityId != null) {
            String foodUrl = String.format(SieConstant.Url_Shop_Content, cityId);
            HttpUtils schttpUtils = new HttpUtils() ;
            schttpUtils.send(HttpRequest.HttpMethod.GET, foodUrl, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    //解析到JSON数据
                    String jsonStr = responseInfo.result;
                    try {
                        JSONObject jsonObject = new JSONObject(jsonStr);
                        JSONObject result = jsonObject.getJSONObject("result");
                        String desc = result.getString("desc");
                        if (desc != null) {
                            shop_content.setText(desc);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(HttpException error, String msg) {
                }
            });
        }

    }

    @OnClick(R.id.shop_content)
    public void foodguide(View view){
        Intent intent = new Intent(this,FoodGuideActivity.class) ;
        intent.putExtra("cityId",cityId) ;
        intent.putExtra(SieConstant.WebView_Tag,"shop") ;
        startActivity(intent);
    }

    @OnClick(R.id.shop_back)
    public void shop_back(View view){
        this.finish();
    }
}
