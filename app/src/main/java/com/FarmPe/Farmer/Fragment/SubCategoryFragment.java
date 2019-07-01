package com.FarmPe.Farmer.Fragment;

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

import com.FarmPe.Farmer.Adapter.SubCategoryAdapter;
import com.FarmPe.Farmer.Bean.CategoryBean;
import com.FarmPe.Farmer.R;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryFragment extends Fragment {

    RecyclerView recyclerView;
    SubCategoryAdapter mAdapter;
    LinearLayout back_feed;
    String strtext;
    Fragment selectedFragment;
    public static String id,back_key;
    private List<CategoryBean> newOrderBeansList = new ArrayList<>();
    public static SubCategoryFragment newInstance() {
        SubCategoryFragment fragment = new SubCategoryFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sub_category_recy, container, false);
        recyclerView=view.findViewById(R.id.recycler_view1);
        back_feed=view.findViewById(R.id.back_feed);

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("sub", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("sub", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });
        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        CategoryBean bean=new CategoryBean("Brinjal","1");
        newOrderBeansList.add(bean);

        CategoryBean bean2=new CategoryBean("Ash Gourd","2");
        newOrderBeansList.add(bean2);

        CategoryBean bean3=new CategoryBean("Beet Root","3");
        newOrderBeansList.add(bean3);

        CategoryBean bean4=new CategoryBean("Bell Pepper","4");
        newOrderBeansList.add(bean4);

        CategoryBean bean5=new CategoryBean("Bitter Gourd","5");
        newOrderBeansList.add(bean5);

        CategoryBean bean6=new CategoryBean("Baby Corn","6");
        newOrderBeansList.add(bean6);

        CategoryBean bean7=new CategoryBean("Beans","7");
        newOrderBeansList.add(bean7);

        CategoryBean bean8=new CategoryBean("Amaranthus","8");
        newOrderBeansList.add(bean8);

        CategoryBean bean9=new CategoryBean("Carrot","9");
        newOrderBeansList.add(bean9);

        CategoryBean bean10=new CategoryBean("Cauliflower","10");
        newOrderBeansList.add(bean10);
        CategoryBean bean11=new CategoryBean("Chillies","11");
        newOrderBeansList.add(bean11);
        CategoryBean bean12=new CategoryBean("Capsicum","12");
        newOrderBeansList.add(bean12);
        newOrderBeansList.add(bean12);
        newOrderBeansList.add(bean12);
        newOrderBeansList.add(bean12);
        newOrderBeansList.add(bean12);
        newOrderBeansList.add(bean12);
        newOrderBeansList.add(bean12);


        System.out.println("newOrderBeansListvsdvdv1234567890"+newOrderBeansList.size());

        mAdapter = new SubCategoryAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(mAdapter);

        return view;

    }


}
