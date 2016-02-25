package com.travel.personaltravel.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnRadioGroupCheckedChange;
import com.travel.personaltravel.R;
import com.travel.personaltravel.fragment.CityCollectionFragment;
import com.travel.personaltravel.fragment.CityCommentTripFragment;


public class CityCommentTripActivity extends FragmentActivity {
    private CityCommentTripFragment cityCommentTripFragment ;
    private FragmentManager manager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        setContentView(R.layout.activity_city_comment_trip);
        ViewUtils.inject(this);

        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        cityCommentTripFragment= (CityCommentTripFragment) manager.findFragmentByTag("citytripcomment");
        if (cityCommentTripFragment == null) {
            cityCommentTripFragment = new CityCommentTripFragment();
        }
        transaction.replace(R.id.city_commenttrip_container,cityCommentTripFragment) ;
        transaction.commit() ;
    }


    @OnRadioGroupCheckedChange(value={R.id.city_commenttrip_radiogroup})
    public void onIndexFragmentChange(RadioGroup group,int checkId ){
        FragmentTransaction transaction = manager.beginTransaction();
        switch (checkId){
            case R.id.city_commenttrip_r1:
                cityCommentTripFragment = (CityCommentTripFragment) manager.findFragmentByTag("citytripcomment");
                if (cityCommentTripFragment == null) {
                    cityCommentTripFragment = new CityCommentTripFragment() ;
                }
                transaction.replace(R.id.city_commenttrip_container, cityCommentTripFragment, "citytripcomment");
                break ;
            case R.id.city_commenttrip_r2:
                CityCollectionFragment cityCollectionFragment = (CityCollectionFragment) manager.findFragmentByTag("cityCollectionFragment");
                if (cityCollectionFragment == null) {
                    cityCollectionFragment = new CityCollectionFragment() ;
                }
                transaction.replace(R.id.city_commenttrip_container, cityCollectionFragment,"cityCollectionFragment");
                break ;
        }
        transaction.commit();

    }

}
