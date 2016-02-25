package com.travel.personaltravel.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnRadioGroupCheckedChange;
import com.travel.personaltravel.R;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.fragment.user.CollectFragment;
import com.travel.personaltravel.fragment.user.PlanFragment;

public class PlanDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private String id;
    String name;
    private String userId;
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    private PlanFragment planFragment;
    private String url;

    @ViewInject(R.id.plan_detail_title)
    private TextView titletxt;

    @ViewInject(R.id.plan_detail_backimg)
    private ImageView backimg;

    @ViewInject(R.id.plan_detail_setimg)
    private ImageView setimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);
        ViewUtils.inject(this);

        id=getIntent().getStringExtra("id");
        name=getIntent().getStringExtra("name");
        userId=getIntent().getStringExtra("userid");
        titletxt.setText(name);

        //设置点击事件
        backimg.setOnClickListener(this);
        setimg.setOnClickListener(this);



        url = SieConstant.baseUrl+String.format(SieConstant.travelPlan,userId,id);

        //默认显示
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        planFragment= (PlanFragment) fragmentManager.findFragmentByTag("planFragment");
    if (planFragment==null)
        planFragment = PlanFragment.newInstance(url);

        transaction.replace(R.id.plan_detaile_container, planFragment,"planFragment");
        transaction.commit();
    }




    @OnRadioGroupCheckedChange(value ={R.id.plan_detail_radiogroup})
    public void onIndexRadioChange(RadioGroup group,int checkedId){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (checkedId){
            case R.id.plan_detail_route:
                planFragment= (PlanFragment) fragmentManager.findFragmentByTag("planFragment");
                if (planFragment==null)
                    planFragment = PlanFragment.newInstance(url);
                transaction.replace(R.id.plan_detaile_container, planFragment,"planFragment");
                break;
            case R.id.plan_detail_collect:
                CollectFragment collectFragment= (CollectFragment) fragmentManager.findFragmentByTag("CollectFragment");
                if (collectFragment==null)
                    collectFragment=CollectFragment.newInstance(id);
                transaction.replace(R.id.plan_detaile_container, collectFragment,"CollectFragment");
                break;
        }
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.plan_detail_setimg:
                //TODO slidemenu的侧滑
                break;
            case R.id.plan_detail_backimg:
                this.finish();
                break;
        }
    }
}
