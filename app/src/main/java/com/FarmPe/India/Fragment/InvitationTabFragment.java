package com.FarmPe.India.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.FarmPe.India.R;


public class InvitationTabFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    private ViewPager viewPager;
    public static TabLayout tabLayout;
    LinearLayout back_profile_detail;
    Fragment selectedFragment;
/*
    private int[] tabIcons = {
            R.drawable.ic_tomato,
            R.drawable.ic_orange,
            R.drawable.ic_tomato,
            R.drawable.ic_tomato,
            R.drawable.ic_tomato
    };
*/

    public static InvitationTabFragment newInstance() {
        InvitationTabFragment fragment = new InvitationTabFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.invitation_tab_layout, container, false);
        back_profile_detail=view.findViewById(R.id.back_feed);

        back_profile_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                /*selectedFragment =HomeFragment.newInstance();
                FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();*/
            }
        });
        tabLayout = view. findViewById(R.id.simpleTabLayout_prof);
        //tabLayout.addTab(tabLayout.newTab().setText("New"));
        tabLayout.addTab(tabLayout.newTab().setText("Received"));
        tabLayout.addTab(tabLayout.newTab().setText("Sent"));
       tabLayout.setOnTabSelectedListener(this);

        viewPager = view.findViewById(R.id.simpleViewPager_order_prof);

        PagerTabFragment adapter=new PagerTabFragment(getActivity().getSupportFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        return view;
    }



    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        System.out.println("getttttposss"+tab.getPosition());
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }



}
