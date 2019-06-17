package com.renewin.Xohri.Fragment;

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

import com.renewin.Xohri.Adapter.AddModelAdapter;
import com.renewin.Xohri.Bean.FarmsImageBean;
import com.renewin.Xohri.R;

import java.util.ArrayList;
import java.util.List;

public class AddModelFragment extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static AddModelAdapter farmadapter;
    TextView toolbar_title;
    LinearLayout back_feed;


    public static AddModelFragment newInstance() {
        AddModelFragment fragment = new AddModelFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_first_recy, container, false);
        recyclerView=view.findViewById(R.id.recycler_what_looking);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);
        toolbar_title.setText("Select Model");

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("hp", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus(View.FOCUS_UP);
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("hp", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return false;
            }
        });

        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        FarmsImageBean img1=new FarmsImageBean(R.drawable.tractor_green,"Yuvraj 215 NXT","","","","");
        newOrderBeansList.add(img1);

        FarmsImageBean img2=new FarmsImageBean(R.drawable.gyrovator,"Jivo 225 DI","","","","");
        newOrderBeansList.add(img2);

        FarmsImageBean img3=new FarmsImageBean(R.drawable.ceat_tyre,"Jivo 225 DI","","","","");
        newOrderBeansList.add(img3);

        FarmsImageBean img4=new FarmsImageBean(R.drawable.jcb,"Jivo 225 DI","","","","");
        newOrderBeansList.add(img4);

        FarmsImageBean img5=new FarmsImageBean(R.drawable.tractor_red,"Jivo 225 DI","","","","");
        newOrderBeansList.add(img5);

        FarmsImageBean img6=new FarmsImageBean(R.drawable.jcb,"Jivo 225 DI","","","","");
        newOrderBeansList.add(img6);
        newOrderBeansList.add(img6);
        newOrderBeansList.add(img6);
       /* newOrderBeansList.add(img6);
        newOrderBeansList.add(img6);*/


        farmadapter=new AddModelAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(farmadapter);

        return view;
    }



}
