package com.FarmPe.India.Fragment;

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


import com.FarmPe.India.Adapter.NearByHorizontalAdapter;
import com.FarmPe.India.Adapter.NotificationAdapter;
import com.FarmPe.India.Bean.NearByHorizontalBean;
import com.FarmPe.India.Bean.NotificationBean;
import com.FarmPe.India.R;

import java.util.ArrayList;
import java.util.List;

public class FarmsDetailsFragment extends Fragment {

    public static List<NotificationBean> newOrderBeansList = new ArrayList<>();
    public static List<NearByHorizontalBean> newOrderBeansList_horizontal = new ArrayList<>();
    public static RecyclerView recyclerView,recyclerView_horizontal;
    TextView toolbar_title;
    LinearLayout back_feed;
    RelativeLayout menu;
    public static String farms;
    NearByHorizontalAdapter madapter;
    public static NotificationAdapter farmadapter;




    public static FarmsDetailsFragment newInstance() {
        FarmsDetailsFragment fragment = new FarmsDetailsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.farms_details_page_copy, container, false);
       // recyclerView=view.findViewById(R.id.recycler_what_looking);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);
        menu=view.findViewById(R.id.menu);
        recyclerView_horizontal = view.findViewById(R.id.recycler_view_horizontal);
        recyclerView = view.findViewById(R.id.recycler_farm);

       /* DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        System.out.println("heightgd"+height);*/



        Bundle bundle=getArguments();
        toolbar_title.setText(bundle.getString("farm_name"));


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  FarmerDetailsFragment.farmer=null;
                farms="farm_back";
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("connect", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        newOrderBeansList_horizontal.clear();
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView_horizontal.setLayoutManager(mLayoutManager);
        recyclerView_horizontal.setItemAnimator(new DefaultItemAnimator());


        NearByHorizontalBean crop=new NearByHorizontalBean(R.drawable.cow_dairy,"","");
        newOrderBeansList_horizontal.add(crop);
        newOrderBeansList_horizontal.add(crop);
        newOrderBeansList_horizontal.add(crop);
        newOrderBeansList_horizontal.add(crop);
        newOrderBeansList_horizontal.add(crop);
        newOrderBeansList_horizontal.add(crop);

        madapter = new NearByHorizontalAdapter(getActivity(), newOrderBeansList_horizontal);
        recyclerView_horizontal.setAdapter(madapter);


        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

   /*     NotificationBean img1=new NotificationBean("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);

       *//* newOrderBeansList.add(img6);
        newOrderBeansList.add(img6);*//*


        farmadapter=new NotificationAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(farmadapter);
*/


        return view;
    }



}
