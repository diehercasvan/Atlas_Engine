package com.example.diegoh.atlasengine.class_general;

import android.support.v4.app.Fragment;

import com.example.diegoh.atlasengine.fragments.ImageFragment;
import com.example.diegoh.atlasengine.fragments.VideoFragment;

/**
 * Created by DIEGO H CASALLAS on 2/22/2017.
 */

public class MenuFragment {
    Fragment fragment;

    public Fragment selectedFragment(int iSelect) {

        switch (iSelect) {
            case 0:
                fragment = new ImageFragment();
                break;
            case 1:
                fragment = new VideoFragment();
                break;
        }

        return fragment;
    }
}
