package com.renewin.FarmPeFarmer.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renewin.FarmPeFarmer.Adapter.FarmerImageAdapter;
import com.renewin.FarmPeFarmer.Bean.FarmsImageBean1;
import com.renewin.FarmPeFarmer.R;

import java.util.ArrayList;
import java.util.List;

public class FarmerFragment extends Fragment {

    public static List<FarmsImageBean1> newOrderBeansList = new ArrayList<>();
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

        FarmsImageBean1 img1=new FarmsImageBean1(R.drawable.jk,"Jagdish Kumar","3rd Generation Farmer","","Immediately","Paddy,Sugar Cane,Wheat...","Rampura Bahjoi","");
        newOrderBeansList.add(img1);

        FarmsImageBean1 img2=new FarmsImageBean1(R.drawable.ragi_sir,"Mallikarjun Ragi","Rancher,Agripreneur,Farmer","","1 Month","Cotton,Onion,Organic...","Naregal,Ron","");
        newOrderBeansList.add(img2);

        FarmsImageBean1 img3=new FarmsImageBean1(R.drawable.ravi,"Ravi Kumar Hattikal","3rd Generation Farmer","","Immediately","Paddy,Sugar Cane,Wheat...","Rampura Bahjoi","");
        newOrderBeansList.add(img3);

        FarmsImageBean1 img4=new FarmsImageBean1(R.drawable.manoj,"Manoj Kumar","3rd Generation Farmer","","Immediately","Paddy,Sugar Cane,Wheat...","Rampura Bahjoi","");
        newOrderBeansList.add(img4);


        farmadapter=new FarmerImageAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(farmadapter);

        return view;
    }



}
