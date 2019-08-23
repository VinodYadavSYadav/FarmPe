package com.FarmPe.India.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.India.Adapter.InvitationLeadAdapter;
import com.FarmPe.India.Adapter.InvitationLeadSentAdapter;
import com.FarmPe.India.Bean.ConnectionBean;
import com.FarmPe.India.R;

import java.util.ArrayList;
import java.util.List;

public class InvitationsLeadsSentFragment extends Fragment {

    public static List<ConnectionBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;TextView toolbar_title,request1,farms1,farmer1,people1;
    LinearLayout back_feed,main_layout,request,farms,farmer,people;
    Fragment selectedFragment;
    ImageView filter;


    public static InvitationsLeadsSentFragment newInstance() {
        InvitationsLeadsSentFragment fragment = new InvitationsLeadsSentFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ivitations_leads_recy, container, false);
        main_layout=view.findViewById(R.id.main_layout);
        request=view.findViewById(R.id.request);
        farms=view.findViewById(R.id.farms);
        farmer=view.findViewById(R.id.farmer);
        people=view.findViewById(R.id.people);
        request1=view.findViewById(R.id.request1);
        farms1=view.findViewById(R.id.farms1);
        farmer1=view.findViewById(R.id.farmer1);
        people1=view.findViewById(R.id.people1);

        request1.setBackgroundResource(R.drawable.border_red_curve);
        farms1.setBackgroundResource(R.drawable.border_empty_curve);
        farmer1.setBackgroundResource(R.drawable.border_empty_curve);
        people1.setBackgroundResource(R.drawable.border_empty_curve);

        farms1.setTextColor(Color.parseColor("#333333"));
        farmer1.setTextColor(Color.parseColor("#333333"));
        request1.setTextColor(Color.parseColor("#ffffff"));
        people1.setTextColor(Color.parseColor("#333333"));


        System.out.println("comeeeeeee");
        selectedFragment = InvitationsRequestsSentFragment.newInstance();
        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.invi_frame, selectedFragment);
        // transaction.addToBackStack("invitation_back");
        transaction.commit();

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request1.setBackgroundResource(R.drawable.border_red_curve);
                farms1.setBackgroundResource(R.drawable.border_empty_curve);
                farmer1.setBackgroundResource(R.drawable.border_empty_curve);
                people1.setBackgroundResource(R.drawable.border_empty_curve);

                farms1.setTextColor(Color.parseColor("#333333"));
                farmer1.setTextColor(Color.parseColor("#333333"));
                request1.setTextColor(Color.parseColor("#ffffff"));
                people1.setTextColor(Color.parseColor("#333333"));


                selectedFragment = InvitationsRequestsSentFragment.newInstance();
                FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.invi_frame, selectedFragment);
                // transaction.addToBackStack("invitation_back");
                transaction.commit();

            }
        });

        farms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                farms1.setBackgroundResource(R.drawable.border_red_curve);
                request1.setBackgroundResource(R.drawable.border_empty_curve);
                farmer1.setBackgroundResource(R.drawable.border_empty_curve);
                people1.setBackgroundResource(R.drawable.border_empty_curve);

                request1.setTextColor(Color.parseColor("#333333"));
                farmer1.setTextColor(Color.parseColor("#333333"));
                farms1.setTextColor(Color.parseColor("#ffffff"));
                people1.setTextColor(Color.parseColor("#333333"));


                selectedFragment = InvitationsRequestsSentFragment.newInstance();
                FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.invi_frame, selectedFragment);
                // transaction.addToBackStack("invitation_back");
                transaction.commit();

            }
        });

        farmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                farmer1.setBackgroundResource(R.drawable.border_red_curve);
                request1.setBackgroundResource(R.drawable.border_empty_curve);
                farms1.setBackgroundResource(R.drawable.border_empty_curve);
                people1.setBackgroundResource(R.drawable.border_empty_curve);

                farms1.setTextColor(Color.parseColor("#333333"));
                request1.setTextColor(Color.parseColor("#333333"));
                farmer1.setTextColor(Color.parseColor("#ffffff"));
                people1.setTextColor(Color.parseColor("#333333"));


                selectedFragment = InvitationsRequestsSentFragment.newInstance();
                FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.invi_frame, selectedFragment);
                // transaction.addToBackStack("invitation_back");
                transaction.commit();

            }
        });

        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people1.setBackgroundResource(R.drawable.border_red_curve);
                request1.setBackgroundResource(R.drawable.border_empty_curve);
                farmer1.setBackgroundResource(R.drawable.border_empty_curve);
                farms1.setBackgroundResource(R.drawable.border_empty_curve);

                farms1.setTextColor(Color.parseColor("#333333"));
                request1.setTextColor(Color.parseColor("#333333"));
                farmer1.setTextColor(Color.parseColor("#333333"));
                people1.setTextColor(Color.parseColor("#ffffff"));


                selectedFragment = InvitationsRequestsSentFragment.newInstance();
                FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.invi_frame, selectedFragment);
                // transaction.addToBackStack("invitation_back");
                transaction.commit();

            }
        });

        return view;
    }


}
