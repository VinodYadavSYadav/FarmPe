package com.renewin.FarmPeFarmer.Fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.renewin.FarmPeFarmer.Adapter.NotificationAdapter;
import com.renewin.FarmPeFarmer.Bean.NotificationBean;
import com.renewin.FarmPeFarmer.R;
import com.renewin.FarmPeFarmer.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {

    public static List<NotificationBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static NotificationAdapter farmadapter;
    TextView toolbar_title;
    LinearLayout back_feed;
    Fragment selectedFragment;
    SessionManager sessionManager;
    JSONObject lngObject;

    public static NotificationFragment newInstance() {
        NotificationFragment fragment = new NotificationFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification_recy, container, false);
        recyclerView=view.findViewById(R.id.recycler_noti);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);

        sessionManager = new SessionManager(getActivity());
        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getArguments().getString("navigation_from").equals("home")){
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                } /*else if(getArguments().getString("navigation_from").equals("setting")){
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                }*/
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    if(getArguments().getString("navigation_from").equals("home")){
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack ("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    }/* else if(getArguments().getString("navigation_from").equals("setting")){
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    }*/
                   /* FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);*/


                    return true;
                }
                return false;
            }
        });

//

        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        NotificationBean img1=new NotificationBean("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);

       /* newOrderBeansList.add(img6);
        newOrderBeansList.add(img6);*/


        farmadapter=new NotificationAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(farmadapter);

        try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));
            toolbar_title.setText(lngObject.getString("Notifications"));
        } catch (JSONException e) {
            e.printStackTrace();
        }



        return view;
    }



}
