package com.renewin.Xohri.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renewin.Xohri.Adapter.AddHpAdapter;
import com.renewin.Xohri.Bean.FarmsImageBean;
import com.renewin.Xohri.R;

import java.util.ArrayList;
import java.util.List;

public class RequestFormFragment extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static AddHpAdapter farmadapter;
    TextView toolbar_title;
    Fragment selectedFragment;
    LinearLayout back_feed,address_layout;
    CheckBox check_box;


    public static RequestFormFragment newInstance() {
        RequestFormFragment fragment = new RequestFormFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.request_form, container, false);
       // recyclerView=view.findViewById(R.id.recycler_what_looking);
       toolbar_title=view.findViewById(R.id.toolbar_title);
       back_feed=view.findViewById(R.id.back_feed);
       check_box=view.findViewById(R.id.check_box);
       address_layout=view.findViewById(R.id.address_layout);
       toolbar_title.setText("Request for Quotation");

        check_box.setText("I agree that by clicking 'Request for Tractor Price' button, I am explicitly soliciting a call from Xohri App users on my 'Mobile' in order to assist me with my tractor purchase.");

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("model", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus(View.FOCUS_UP);
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("model", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return false;
            }
        });

        address_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    selectedFragment = Add_New_Address_Fragment.newInstance();
                    FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.addToBackStack("request");
                    transaction.commit();
            }
        });


        return view;
    }



}
