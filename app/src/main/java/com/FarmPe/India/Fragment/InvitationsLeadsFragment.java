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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.India.Adapter.InvitationLeadAdapter;
import com.FarmPe.India.Bean.ConnectionBean;
import com.FarmPe.India.R;

import java.util.ArrayList;
import java.util.List;

public class InvitationsLeadsFragment extends Fragment {

    public static List<ConnectionBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static InvitationLeadAdapter farmadapter;
    TextView toolbar_title;
    LinearLayout back_feed,main_layout;
    Fragment selectedFragment;
    ImageView filter;


    public static InvitationsLeadsFragment newInstance() {
        InvitationsLeadsFragment fragment = new InvitationsLeadsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ivitations_leads_recy, container, false);
        recyclerView=view.findViewById(R.id.recy_connection_invi);
       // toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);
      //  filter=view.findViewById(R.id.filter);
        main_layout=view.findViewById(R.id.main_layout);
       // toolbar_title.setText("Select HP");

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("home_page", FragmentManager.POP_BACK_STACK_INCLUSIVE);

             /*   selectedFragment = HomeMenuFragment.newInstance();
                HomeMenuFragment.drawer.openDrawer(Gravity.START);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();*/



            }
        });



        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ConnectionBean img1=new ConnectionBean("Jagdish Kumar","Farmer","Rampura, Bahjoi","","","");
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);


        farmadapter=new InvitationLeadAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(farmadapter);

        return view;
    }


}
