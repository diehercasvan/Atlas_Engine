package com.example.diegoh.atlasengine.class_general;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.example.diegoh.atlasengine.*;

/**
 * Created by DIEGO H CASALLAS on 2/22/2017.
 * 0=fade_in
 * 1=fade_out
 * 2=Slide down
 * 3=Slide up
 */

public class Animation_general {


    public Animation selectAnimation(int iSelected) {
        int iAni=0;
        switch (iSelected) {
            case 0:
                iAni=R.anim.fade_in;
                break;
            case 1:
                iAni=R.anim.fade_out;
                break;
            case 2:
                iAni=R.anim.slide_down;
                break;
            case 3:
                iAni=R.anim.slide_up;
                break;
            case 4:
                iAni=R.anim.slide_left;
                break;
            case 5:
                iAni=R.anim.slide_right;
                break;
            case 6:
                iAni=R.anim.start;
                break;
        }

        return  AnimationUtils.loadAnimation(General.CONTEXT,iAni);
    }
}
