package com.renewin.Xohri.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renewin.Xohri.R;


public class Aboutusdetail_fragment extends Fragment {



    boolean doubleBackToExitPressedOnce = false;

    public static Aboutusdetail_fragment newInstance() {
        Aboutusdetail_fragment fragment = new Aboutusdetail_fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.aboutusdetail_layout, container, false);




        return view;


    }



}
