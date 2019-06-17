package com.renewin.Xohri.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.renewin.Xohri.Adapter.FarmsImageAdapter;
import com.renewin.Xohri.Bean.FarmsImageBean;
import com.renewin.Xohri.R;

import java.util.ArrayList;
import java.util.List;

public class LookingForFragment extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static FarmsImageAdapter farmadapter;
    boolean doubleBackToExitPressedOnce = false;


    public static LookingForFragment newInstance() {
        LookingForFragment fragment = new LookingForFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.looking_for_recy, container, false);
        recyclerView=view.findViewById(R.id.recycler_looking);

        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        FarmsImageBean img1=new FarmsImageBean(R.drawable.tractor_green,"Tractor Price","Mahindra Jivo 225 DL 20HP","Immediately","Jagdish Kumar","Rampura Bahjoi");
        newOrderBeansList.add(img1);

        FarmsImageBean img2=new FarmsImageBean(R.drawable.gyrovator,"Tractor Implements Price","Mahindra Jivo 225 DL 20HP","1 Month","Jagdish Kumar","Rampura Bahjoi");
        newOrderBeansList.add(img2);

        FarmsImageBean img3=new FarmsImageBean(R.drawable.tractor_green,"Tractor Price","Mahindra Jivo 225 DL 20HP","Immediately","Jagdish Kumar","Rampura Bahjoi");
        newOrderBeansList.add(img3);

        FarmsImageBean img4=new FarmsImageBean(R.drawable.tractor_red,"Tractor Price","Mahindra Jivo 225 DL 20HP","Immediately","Jagdish Kumar","Rampura Bahjoi");
        newOrderBeansList.add(img4);
        newOrderBeansList.add(img4);
        newOrderBeansList.add(img4);
        newOrderBeansList.add(img4);
        newOrderBeansList.add(img4);


        farmadapter=new FarmsImageAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(farmadapter);


        return view;
    }



}
