package com.travel.personaltravel.activity;
import com.travel.personaltravel.constant.SieConstant;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
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
import com.travel.personaltravel.adapter.MultiItemCommonAdapter;
import com.travel.personaltravel.model.Trip.SpotItem;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends FragmentActivity {
    @ViewInject(R.id.food_content)
    private TextView food_content ;
    @ViewInject(R.id.food_title)
    private TextView food_title ;
    @ViewInject(R.id.food_lv)
    private ListView food_lv ;

    private String title;
    private String cityId;

    private List datas ;
    private MultiItemCommonAdapter<SpotItem> adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        setContentView(R.layout.activity_food);
        ViewUtils.inject(this) ;

        Intent intent = getIntent();
        title = intent.getStringExtra("food_title");
        cityId = intent.getStringExtra("city_id");
        initView() ;


    }



    private void initView() {
        if (title != null) {
            food_title.setText(title+"美食");
        }
        if (cityId != null) {
            String foodUrl = String.format(SieConstant.Url_Food_Content, cityId);
            HttpUtils fchttpUtils = new HttpUtils() ;
            fchttpUtils.send(HttpRequest.HttpMethod.GET, foodUrl, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    //解析到JSON数据
                    String jsonStr = responseInfo.result;
                    try {
                        JSONObject jsonObject = new JSONObject(jsonStr);
                        JSONObject result = jsonObject.getJSONObject("result");
                        String desc = result.getString("desc");
                        if (desc != null) {
                            food_content.setText(desc);
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
        datas = new ArrayList() ;





    }

    @OnClick(R.id.food_content)
    public void foodguide(View view){
        Intent intent = new Intent(this,FoodGuideActivity.class) ;
        intent.putExtra("cityId",cityId) ;
        intent.putExtra(SieConstant.WebView_Tag,"food") ;
        startActivity(intent);
    }

    @OnClick(R.id.food_back)
    public void food_back(View view){
        this.finish();
    }

}
