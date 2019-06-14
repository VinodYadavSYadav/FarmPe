package com.renewin.Xohri.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
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

        address_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    selectedFragment = Add_New_Address_Fragment.newInstance();
                    FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();
            }
        });

       /* newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        FarmsImageBean img1=new FarmsImageBean(R.drawable.tractor_green,"UPTO 20HP","","","","");
        newOrderBeansList.add(img1);

        FarmsImageBean img2=new FarmsImageBean(R.drawable.gyrovator,"21-30 HP","","","","");
        newOrderBeansList.add(img2);

        FarmsImageBean img3=new FarmsImageBean(R.drawable.ceat_tyre,"31-40 HP","","","","");
        newOrderBeansList.add(img3);

        FarmsImageBean img4=new FarmsImageBean(R.drawable.jcb,"41-50 HP","","","","");
        newOrderBeansList.add(img4);

        FarmsImageBean img5=new FarmsImageBean(R.drawable.tractor_red,"51-60 HP","","","","");
        newOrderBeansList.add(img5);

        FarmsImageBean img6=new FarmsImageBean(R.drawable.jcb,"60 HP","","","","");
        newOrderBeansList.add(img6);
        newOrderBeansList.add(img6);
        newOrderBeansList.add(img6);
       *//* newOrderBeansList.add(img6);
        newOrderBeansList.add(img6);*//*


        farmadapter=new AddHpAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(farmadapter);
*/
        return view;
    }



}
