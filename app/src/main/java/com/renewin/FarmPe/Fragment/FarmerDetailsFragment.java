package com.renewin.FarmPe.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.renewin.FarmPe.Adapter.NearByHorizontalAdapter;
import com.renewin.FarmPe.Adapter.NotificationAdapter;
import com.renewin.FarmPe.Bean.NearByHorizontalBean;
import com.renewin.FarmPe.Bean.NotificationBean;
import com.renewin.FarmPe.R;

import java.util.ArrayList;
import java.util.List;

public class FarmerDetailsFragment extends Fragment {

    public static List<NotificationBean> newOrderBeansList = new ArrayList<>();
    public static List<NearByHorizontalBean> newOrderBeansList_horizontal = new ArrayList<>();
    public static RecyclerView recyclerView,recyclerView_horizontal;
    TextView toolbar_title;
    LinearLayout back_feed;
    RelativeLayout menu;
    NearByHorizontalAdapter madapter;
    public static NotificationAdapter farmadapter;




    public static FarmerDetailsFragment newInstance() {
        FarmerDetailsFragment fragment = new FarmerDetailsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.farmers_detail_page, container, false);
       // recyclerView=view.findViewById(R.id.recycler_what_looking);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);
        menu=view.findViewById(R.id.menu);


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("farmer", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });





        return view;
    }



}
