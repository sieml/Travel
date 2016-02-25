package com.travel.personaltravel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.travel.personaltravel.application.AiLYApplication;
import com.travel.personaltravel.R;
import com.travel.personaltravel.adapter.CommonAdapter;
import com.travel.personaltravel.adapter.ViewHolder;
import com.travel.personaltravel.model.PlanRoute;

import java.util.ArrayList;
import java.util.List;

public class PlanRouteActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @ViewInject(R.id.plan_route_city)
    private TextView citytxt;

    @ViewInject(R.id.plan_route_day)
    private TextView daytxt;

    @ViewInject(R.id.plan_route_backimg)
    private ImageView backimg;

    @ViewInject(R.id.plan_route_list)
    private ListView listview;
    private ArrayList<PlanRoute.ResultEntity.ItineraryEntity> routes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_route);
        ViewUtils.inject(this);


        routes = getIntent().getParcelableArrayListExtra("routes");
        int day = getIntent().getIntExtra("day", 1);
        String name = getIntent().getStringExtra("name");
        if (day<10){
            daytxt.setText("0"+day + ".Day详情");
        }else{
            daytxt.setText(day + ".Day详情");
        }

        citytxt.setText(name);


        CommonAdapter<PlanRoute.ResultEntity.ItineraryEntity> adapter=new CommonAdapter<PlanRoute.ResultEntity.ItineraryEntity>(this,routes,R.layout.plan_route_item) {
            @Override
            public int getCount() {
                return routes.size();
            }

            @Override
            public void convert(ViewHolder holder, PlanRoute.ResultEntity.ItineraryEntity itineraryEntity) {

                holder.setText(R.id.route_item_land,itineraryEntity.getPoi().getZhName());
                holder.setText(R.id.route_item_recommend, "建议游玩" + itineraryEntity.getPoi().getTimeCostDesc());
                List<PlanRoute.ResultEntity.ItineraryEntity.PoiEntity.ImagesEntity> images = itineraryEntity.getPoi().getImages();

                if (images!=null && images.size()>0){
                    PlanRoute.ResultEntity.ItineraryEntity.PoiEntity.ImagesEntity imagesEntity = images.get(0);
                    if (imagesEntity != null) {
                        if (imagesEntity.getThumb() != null) {
                            ImageView view = holder.getView(R.id.route_item_img);
                            if (view != null) {
                                String thumb = images.get(0).getThumb();
                                if (thumb != null) {
                                    AiLYApplication.bitmapUtils.display(view, thumb);
                                }
                            }
                        }
                    }
                }



            }


        };

            listview.setAdapter(adapter);
        //listview点击事件
        listview.setOnItemClickListener(this);



        //点击backimg返回
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlanRouteActivity.this.finish();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
