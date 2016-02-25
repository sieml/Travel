package com.travel.personaltravel.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.travel.personaltravel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CityCommentTripFragment extends Fragment {
    @ViewInject(R.id.fragment_city_comment_trip_lv)
    private ListView lv ;

    public CityCommentTripFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_city_comment_trip, container, false);
        ViewUtils.inject(this,view);
        return view;
    }


}
