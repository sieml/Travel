package com.travel.personaltravel.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travel.personaltravel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CityCollectionFragment extends Fragment {


    public CityCollectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_collection, container, false);
    }


}
