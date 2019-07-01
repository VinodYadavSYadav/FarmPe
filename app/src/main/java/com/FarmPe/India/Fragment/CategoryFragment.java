package com.FarmPe.India.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.FarmPe.India.Adapter.CategoryAdapter;
import com.FarmPe.India.Bean.CategoryBean;
import com.FarmPe.India.R;
import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    RecyclerView recyclerView;
    CategoryAdapter mAdapter;
    Fragment selectedFragment;
    private List<CategoryBean> newOrderBeansList = new ArrayList<>();
    boolean fragloaded= false;
    LinearLayout back_feed;
    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_recy, container, false);
        recyclerView=view.findViewById(R.id.compose_recycler1);
        back_feed = view.findViewById(R.id.back_feed);

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = HomeMenuFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
            }
        });
       /* view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                   *//* FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("homeee", FragmentManager.POP_BACK_STACK_INCLUSIVE);*//*

                    selectedFragment = HomeLandingPageFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout_home, selectedFragment);
                    transaction.commit();
                    return true;
                }
                return false;
            }
        });*/
        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        CategoryBean bean=new CategoryBean("Cash Crops","1");
        newOrderBeansList.add(bean);

        CategoryBean bean2=new CategoryBean("Cereals","2");
        newOrderBeansList.add(bean2);

        CategoryBean bean3=new CategoryBean("Flowers","3");
        newOrderBeansList.add(bean3);

        CategoryBean bean4=new CategoryBean("Fodder Crops","4");
        newOrderBeansList.add(bean4);

        CategoryBean bean5=new CategoryBean("Fruits","5");
        newOrderBeansList.add(bean5);

        CategoryBean bean6=new CategoryBean("Medicinal","6");
        newOrderBeansList.add(bean6);

        CategoryBean bean7=new CategoryBean("Millets","7");
        newOrderBeansList.add(bean7);

        CategoryBean bean8=new CategoryBean("Oilseeds","8");
        newOrderBeansList.add(bean8);

        CategoryBean bean9=new CategoryBean("Others","9");
        newOrderBeansList.add(bean9);

        CategoryBean bean10=new CategoryBean("Pulses","10");
        newOrderBeansList.add(bean10);
        CategoryBean bean11=new CategoryBean("Spices","11");
        newOrderBeansList.add(bean11);
        CategoryBean bean12=new CategoryBean("Vegitables","12");
        newOrderBeansList.add(bean12);
        mAdapter = new CategoryAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(mAdapter);
        return view;
    }
    }

