package com.FarmPe.India.Fragment;

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

import com.FarmPe.India.Adapter.AdapterStatus;
import com.FarmPe.India.Adapter.AdapterStatusViewed;
import com.FarmPe.India.Adapter.NotificationListAdapter;
import com.FarmPe.India.Bean.NotificationBean;
import com.FarmPe.India.Bean.StatusBean;
import com.FarmPe.India.R;
import com.FarmPe.India.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AaStatusFragment extends Fragment {

    public static List<StatusBean> newOrderBeansList = new ArrayList<>();
    public static List<StatusBean> newOrderBeansList_v = new ArrayList<>();
    public static RecyclerView recyclerView,recyclerView_viewd;
    public static AdapterStatus farmadapter;
    public static AdapterStatusViewed farmadapter_v;
    TextView toolbar_title;
    LinearLayout back_feed;
    Fragment selectedFragment;
    SessionManager sessionManager;
    JSONObject lngObject;

    public static AaStatusFragment newInstance() {
        AaStatusFragment fragment = new AaStatusFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_ns_status, container, false);
        recyclerView=view.findViewById(R.id.recycler_recent);
        recyclerView_viewd=view.findViewById(R.id.recycler_viewed);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);

        sessionManager = new SessionManager(getActivity());
        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack ("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    /* else if(getArguments().getString("navigation_from").equals("setting")){
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    }*/
                   /* FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);*/


                    return true;
                }
                return false;
            }
        });



        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


       /* newOrderBeansList.add(img6);
        newOrderBeansList.add(img6);*/
         StatusBean bean=new StatusBean("Jagdish Kumar","Yesterday 12:40 pm","");
         newOrderBeansList.add(bean);
         newOrderBeansList.add(bean);
         newOrderBeansList.add(bean);
         newOrderBeansList.add(bean);
         newOrderBeansList.add(bean);

        farmadapter=new AdapterStatus(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(farmadapter);

        newOrderBeansList_v.clear();
        GridLayoutManager mLayoutManager_view = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView_viewd.setLayoutManager(mLayoutManager_view);
        recyclerView_viewd.setItemAnimator(new DefaultItemAnimator());


       /* newOrderBeansList.add(img6);
        newOrderBeansList.add(img6);*/
        StatusBean bean1=new StatusBean("Jagdish Kumar","Yesterday 12:40 pm","");
        newOrderBeansList_v.add(bean1);
        newOrderBeansList_v.add(bean1);
        newOrderBeansList_v.add(bean1);

        farmadapter_v=new AdapterStatusViewed(getActivity(),newOrderBeansList_v);
        recyclerView_viewd.setAdapter(farmadapter_v);



        return view;
    }



}
