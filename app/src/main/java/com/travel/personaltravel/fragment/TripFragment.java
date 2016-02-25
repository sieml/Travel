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
public class TripFragment extends Fragment {
    @ViewInject(value = R.id.fragment_trip_container)
    FragmentManager manager;

    public TripFragment() {
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
        manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        CityFragment cityFragmentInland = (CityFragment) manager.findFragmentByTag("citysFragment");
        if (cityFragmentInland == null) {
            cityFragmentInland = CityFragment.newInstance();
        }
        transaction.replace(R.id.fragment_trip_container, cityFragmentInland);
        transaction.commit();
    }

}


