package com.FarmPe.India.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.FarmPe.India.R;

public class NearByFragment extends Fragment {
    boolean doubleBackToExitPressedOnce = false;
    LinearLayout looking_for,farms,farmer;
    View looking_view,farms_view,farmer_view;
    Fragment selectedFragment;
  public static NestedScrollView scrollView;


    public static NearByFragment newInstance() {
        NearByFragment fragment = new NearByFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.near_by_layout, container, false);
      //  looking_for=view.findViewById(R.id.looking_for);
        farms=view.findViewById(R.id.farms);
        farmer=view.findViewById(R.id.farmer);
       // looking_view=view.findViewById(R.id.looking_view);
        farms_view=view.findViewById(R.id.farms_view);
        farmer_view=view.findViewById(R.id.farmer_view);
       scrollView=view.findViewById(R.id.scroll);


        farmer_view.setVisibility(View.INVISIBLE);


       scrollView.post(new Runnable() {

            @Override
            public void run() {
               scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });

        selectedFragment = FarmsHomePageFragment.newInstance();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_nearby, selectedFragment);
        transaction.commit();

       /* view.setFocusableInTouchMode(true);
        view.requestFocus(View.FOCUS_UP);
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    selectedFragment = HomeMenuFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();
                }
                return false;
            }
        });*/



       /* looking_for.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                farms_view.setVisibility(View.INVISIBLE);
                farmer_view.setVisibility(View.INVISIBLE);
                looking_view.setVisibility(View.VISIBLE);
                selectedFragment = LookingForFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_dashboard, selectedFragment);
                transaction.commit();
            }
        });*/

        farms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // looking_view.setVisibility(View.INVISIBLE);
                farmer_view.setVisibility(View.INVISIBLE);
                farms_view.setVisibility(View.VISIBLE);
                selectedFragment = FarmsHomePageFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_nearby, selectedFragment);
                transaction.commit();
            }
        });

        farmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                farms_view.setVisibility(View.INVISIBLE);
               // looking_view.setVisibility(View.INVISIBLE);
                farmer_view.setVisibility(View.VISIBLE);
                selectedFragment = FarmerFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_nearby, selectedFragment);
                transaction.commit();
            }
        });


        return view;
    }



}
