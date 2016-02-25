package com.travel.personaltravel.fragment.user;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travel.personaltravel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends Fragment {

public static final String ID="id";
    private  String id;
    public CollectFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CollectFragment newInstance(String param) {
        CollectFragment fragment = new CollectFragment();
        Bundle args = new Bundle();
        args.putString(ID, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collect, container, false);
    }


}
