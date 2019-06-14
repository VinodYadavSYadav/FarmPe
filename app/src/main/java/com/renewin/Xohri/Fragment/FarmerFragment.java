package com.renewin.Xohri.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renewin.Xohri.Adapter.FarmerImageAdapter;
import com.renewin.Xohri.Bean.FarmsImageBean;
import com.renewin.Xohri.R;

import java.util.ArrayList;
import java.util.List;

public class FarmerFragment extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static FarmerImageAdapter farmadapter;


    public static FarmerFragment newInstance() {
        FarmerFragment fragment = new FarmerFragment();
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

        FarmsImageBean img1=new FarmsImageBean(R.drawable.jk,"Jagdish Kumar","3rd Generation Farmer","Immediately","Paddy,Sugar Cane,Wheat...","Rampura Bahjoi");
        newOrderBeansList.add(img1);

        FarmsImageBean img2=new FarmsImageBean(R.drawable.ragi_sir,"Mallikarjun Ragi","Rancher,Agripreneur,Farmer","1 Month","Cotton,Onion,Organic...","Naregal,Ron");
        newOrderBeansList.add(img2);

        FarmsImageBean img3=new FarmsImageBean(R.drawable.ravi,"Ravi Kumar Hattikal","3rd Generation Farmer","Immediately","Paddy,Sugar Cane,Wheat...","Rampura Bahjoi");
        newOrderBeansList.add(img3);

        FarmsImageBean img4=new FarmsImageBean(R.drawable.manoj,"Manoj Kumar","3rd Generation Farmer","Immediately","Paddy,Sugar Cane,Wheat...","Rampura Bahjoi");
        newOrderBeansList.add(img4);


        farmadapter=new FarmerImageAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(farmadapter);

        return view;
    }



}
