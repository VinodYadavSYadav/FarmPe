package com.renewin.FarmPe.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.renewin.FarmPe.R;

public class DashboardFragment extends Fragment {
    boolean doubleBackToExitPressedOnce = false;
    LinearLayout looking_for,farms,farmer;
    View looking_view,farms_view,farmer_view;
    Fragment selectedFragment;
  public static NestedScrollView scrollView;


    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_main, container, false);
        looking_for=view.findViewById(R.id.looking_for);
        farms=view.findViewById(R.id.farms);
        farmer=view.findViewById(R.id.farmer);
        looking_view=view.findViewById(R.id.looking_view);
        farms_view=view.findViewById(R.id.farms_view);
        farmer_view=view.findViewById(R.id.farmer_view);
       scrollView=view.findViewById(R.id.scroll);

        farms_view.setVisibility(View.INVISIBLE);
        farmer_view.setVisibility(View.INVISIBLE);


     /*  scrollView.post(new Runnable() {

            @Override
            public void run() {
               scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });*/

        selectedFragment = LookingForFragment.newInstance();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_dashboard, selectedFragment);
        transaction.commit();


        looking_for.setOnClickListener(new View.OnClickListener() {
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
        });

        farms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                looking_view.setVisibility(View.INVISIBLE);
                farmer_view.setVisibility(View.INVISIBLE);
                farms_view.setVisibility(View.VISIBLE);
                selectedFragment = FarmsHomePageFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_dashboard, selectedFragment);
                transaction.commit();
            }
        });

        farmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                farms_view.setVisibility(View.INVISIBLE);
                looking_view.setVisibility(View.INVISIBLE);
                farmer_view.setVisibility(View.VISIBLE);
                selectedFragment = FarmerFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_dashboard, selectedFragment);
                transaction.commit();
            }
        });


        return view;
    }



}
