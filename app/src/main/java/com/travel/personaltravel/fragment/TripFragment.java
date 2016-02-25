package com.travel.personaltravel.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnRadioGroupCheckedChange;
import com.travel.personaltravel.R;
import com.travel.personaltravel.constant.SieConstant;
import com.travel.personaltravel.fragment.trip.CityFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripFragment extends Fragment{
    @ViewInject(value = R.id.fragment_trip_container)
    private LinearLayout container;
    FragmentManager manager;

    public TripFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip, container, false);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        manager = getChildFragmentManager() ;
        FragmentTransaction transaction = manager.beginTransaction();
        CityFragment cityFragmentInland = (CityFragment) manager.findFragmentByTag("inlandFragment");
        if (cityFragmentInland == null) {
            cityFragmentInland = CityFragment.newInstance(SieConstant.pathInlandRecommend);
        }
        transaction.replace(R.id.fragment_trip_container, cityFragmentInland);
        transaction.commit();
    }

    /**
     * RadioGroup中的焦点变化后的fragment的切换
     *
     * @param group
     * @param checkedId
     */
    @OnRadioGroupCheckedChange(value = {R.id.fragment_trip_radioGroup})
    public void onRadioGroupCheckedChanged(RadioGroup group, int checkedId) {
        if (manager == null) {
            manager = getChildFragmentManager() ;
        }
        FragmentTransaction transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.fragment_trip_radioButton_inland:
                CityFragment cityFragmentInland = (CityFragment) manager.findFragmentByTag("inlandFragment");
                if (cityFragmentInland == null) {
                    cityFragmentInland = CityFragment.newInstance(SieConstant.pathInlandRecommend);
                }
                transaction.replace(R.id.fragment_trip_container, cityFragmentInland);
                break;
            case R.id.fragment_trip_radioButton_foreign:
                CityFragment cityFragmentForeign = (CityFragment) manager.findFragmentByTag("foreignFragment");
                if (cityFragmentForeign == null) {
                    cityFragmentForeign = CityFragment.newInstance(SieConstant.pathForeignRecommend);
                }
                transaction.replace(R.id.fragment_trip_container, cityFragmentForeign);
        }
        transaction.commit();
    }
}


