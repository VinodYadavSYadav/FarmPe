package com.renewin.Xohri.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.renewin.Xohri.Adapter.Transactiion_Failed_Adapter;
import com.renewin.Xohri.Bean.Transaction_Bean;
import com.renewin.Xohri.R;
import com.renewin.Xohri.SessionManager;
import com.renewin.Xohri.Urls;
import com.renewin.Xohri.Volly_class.Crop_Post;
import com.renewin.Xohri.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Transaction_Failed_Fragment extends Fragment {
    private List<Transaction_Bean> newTransactionBeanList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Transactiion_Failed_Adapter mAdapter;
    LinearLayout back_feed;
    TextView filters,items_count;
    Transaction_Bean transaction_bean;
    Fragment selectedFragment;
    JSONArray trans_cancelled_array;
    SessionManager sessionManager;
    String item_list;

    public static Transaction_Failed_Fragment newInstance() {
        Transaction_Failed_Fragment fragment = new Transaction_Failed_Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transaction_recyc_layout, container, false);

       // Home_Page_WithBottomMenu_Activity.bottomNavigation.setVisibility(View.GONE);
        recyclerView = view.findViewById(R.id.recycler_view1);
        filters = view.findViewById(R.id.filter);
        back_feed = view.findViewById(R.id.back_feed);
        items_count = view.findViewById(R.id.items_count);

        sessionManager = new SessionManager(getActivity());



        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    selectedFragment = My_Account_Fragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();

                    // getFragmentManager().popBackStack("transaction", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;
                }
                return false;
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                     Bundle bundle = new Bundle();
                bundle.putString("Stat","failed_transaction");
                bundle.putString("T_Stat","30days");
                selectedFragment = Transaction_Filter_Fragment.newInstance();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                selectedFragment.setArguments(bundle);
                ft.replace(R.id.frame_layout,selectedFragment);
                //ft.addToBackStack("orders");
                ft.commit();
                    return true;
                }
                return false;
            }
        });


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Bundle bundle = new Bundle();
                bundle.putString("Stat","failed_transaction");
                bundle.putString("T_Stat","30days");
                selectedFragment = Transaction_Filter_Fragment.newInstance();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                selectedFragment.setArguments(bundle);
                ft.replace(R.id.frame_layout,selectedFragment);
                //ft.addToBackStack("orders");
                ft.commit();

            }
        });


        filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("Stat","failed_transaction");
                bundle.putString("T_Stat","30days");
                selectedFragment = Transaction_Filter_Fragment.newInstance();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                selectedFragment.setArguments(bundle);
                ft.replace(R.id.frame_layout,selectedFragment);
                //ft.addToBackStack("orders");
                ft.commit();
            }
        });


        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


//        TabFeedBean item1 = new TabFeedBean("Agri.Store");
//        newOrderBeansList.add(item1);
//        newOrderBeansList.add(item1);
//        newOrderBeansList.add(item1);
//        newOrderBeansList.add(item1);
//        newOrderBeansList.add(item1);
//        newOrderBeansList.add(item1);


        System.out.println("newOrderBeansListvsdvdv" + newTransactionBeanList.size());


        mAdapter = new Transactiion_Failed_Adapter(getActivity(), newTransactionBeanList);
        recyclerView.setAdapter(mAdapter);

       /* mAdapter = new Transaction_Adapter(transaction_beanArrayList,getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
*/



        try{

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("CreatedBy",sessionManager.getRegId("userId"));

            Crop_Post.crop_posting(getActivity(), Urls.GET_FAILED_TRANSACTION, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("GET_FAILED_TRANSACTIONNNNNNNNN" + result);

                    try{
                        // transaction_beanArrayList.clear();

                        trans_cancelled_array = result.getJSONArray("TransactionLists");
                        for(int i = 0;i<trans_cancelled_array.length();i++){

                            JSONObject jsonObject1 = trans_cancelled_array.getJSONObject(i);
                            transaction_bean = new Transaction_Bean(jsonObject1.getString("Description"),jsonObject1.getString("TransactionType"),jsonObject1.getString("Amount"),jsonObject1.getString("Id"));

                            newTransactionBeanList.add(transaction_bean);

                        }

                        mAdapter.notifyDataSetChanged();


                        if(newTransactionBeanList.size()<=1){

                            item_list = String.valueOf(newTransactionBeanList.size());
                            items_count.setText(item_list  + " "+  "transaction in last 7days");

                        }else{

                            item_list = String.valueOf(newTransactionBeanList.size());
                            items_count.setText(item_list  + " "+  "transactions in last 7days");

                        }




                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }





        return view;
    }


}
