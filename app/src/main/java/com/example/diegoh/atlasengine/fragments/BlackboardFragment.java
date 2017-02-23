package com.example.diegoh.atlasengine.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.diegoh.atlasengine.R;
import com.example.diegoh.atlasengine.class_general.Animation_general;
import com.example.diegoh.atlasengine.class_general.General;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlackboardFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView[] btnImageViews;

    private Animation_general appAnimation;
    public BlackboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_blackboard, container, false);
        loadView();
        return view;
    }

    private void loadView() {
        appAnimation=new Animation_general();
        btnImageViews = new ImageView[2];
        btnImageViews[0] = (ImageView) view.findViewById(R.id.btn_paletteColor);
        btnImageViews[1] = (ImageView) view.findViewById(R.id.btn_lineBlackboard);
        for (int i = 0; i < btnImageViews.length; i++) {
            btnImageViews[i].setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_paletteColor:
                btnImageViews[0].setBackgroundResource(R.drawable.ic_palette_black_select);
                break;
            case R.id.btn_lineBlackboard:
                btnImageViews[1].setBackgroundResource(R.drawable.ic_gesture_select);
                break;
        }
    }

}
