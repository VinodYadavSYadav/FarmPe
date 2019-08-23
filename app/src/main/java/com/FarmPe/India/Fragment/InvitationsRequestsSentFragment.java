package com.FarmPe.India.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.FarmPe.India.Adapter.InvitationLeadSentAdapter;
import com.FarmPe.India.Bean.ConnectionBean;
import com.FarmPe.India.R;

import java.util.ArrayList;
import java.util.List;

public class InvitationsRequestsSentFragment extends Fragment {

    public static List<ConnectionBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static InvitationLeadSentAdapter farmadapter;
    TextView toolbar_title;
    LinearLayout back_feed,main_layout;
    Fragment selectedFragment;
    ImageView filter;


    public static InvitationsRequestsSentFragment newInstance() {
        InvitationsRequestsSentFragment fragment = new InvitationsRequestsSentFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ivitations_req_recy, container, false);
        recyclerView=view.findViewById(R.id.recy_connection_invi);
       // toolbar_title=view.findViewById(R.id.toolbar_title);
      //  back_feed=view.findViewById(R.id.back_feed);
      //  filter=view.findViewById(R.id.filter);
        main_layout=view.findViewById(R.id.main_layout);
       // toolbar_title.setText("Select HP");

        System.out.println("neeeext");

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


        farmadapter=new InvitationLeadSentAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(farmadapter);

        return view;
    }


}
