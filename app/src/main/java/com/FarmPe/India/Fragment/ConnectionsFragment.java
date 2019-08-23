package com.FarmPe.India.Fragment;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
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

import com.FarmPe.India.Activity.LandingPageActivity;
import com.FarmPe.India.Adapter.ConnectionAdapter;
import com.FarmPe.India.Bean.ConnectionBean;
import com.FarmPe.India.R;
import com.FarmPe.India.SessionManager;
import com.FarmPe.India.Urls;
import com.FarmPe.India.Volly_class.Login_post;
import com.FarmPe.India.Volly_class.VoleyJsonObjectCallback;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConnectionsFragment extends Fragment {

    public static List<ConnectionBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static ConnectionAdapter farmadapter;
    TextView toolbar_title;
    LinearLayout back_feed,main_layout;
    Fragment selectedFragment;
    ImageView filter;
    SessionManager sessionManager;
    JSONObject lngObject;
    public static ConnectionsFragment newInstance() {
        ConnectionsFragment fragment = new ConnectionsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.connections_list_recy, container, false);
        recyclerView=view.findViewById(R.id.recy_connection);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);
        filter=view.findViewById(R.id.filter);
        main_layout=view.findViewById(R.id.main_layout);
       // toolbar_title.setText("Select HP");

        sessionManager=new SessionManager(getActivity());

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  selectedFragment = HomeMenuFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
               // transaction.addToBackStack("invitation");
                transaction.commit();
*/
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack ("home_page", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });

        try {

            lngObject = new JSONObject(sessionManager.getRegId("language"));

            toolbar_title.setText(lngObject.getString("Connections"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("aaaaaaaaaaaa");
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.filter_connection);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(false);

                dialog.show();
                dialog.setCanceledOnTouchOutside(true);


            }
        });





        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ConnectionList();




        return view;
    }

    private void ConnectionList() {

        try {
            newOrderBeansList.clear();

            JSONObject userRequestjsonObject = new JSONObject();
            userRequestjsonObject.put("CreatedBy",sessionManager.getRegId("userId"));
           // userRequestjsonObject.put("CreatedBy",1);


            System.out.println("postObj"+userRequestjsonObject.toString());

            Login_post.login_posting(getActivity(), Urls.GetConnectionList,userRequestjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("cropsresult"+result);
                    JSONArray cropsListArray=null;
                    try {
                        cropsListArray=result.getJSONArray("connectionList");
                        System.out.println("e     e e ddd"+cropsListArray.length());
                        for (int i=0;i<cropsListArray.length();i++){
                            JSONObject jsonObject1=cropsListArray.getJSONObject(i);

                            JSONObject jsonObject2=jsonObject1.getJSONObject("Address");

                            String name=jsonObject1.getString("FullName");
                            String phoneNo=jsonObject1.getString("PhoneNo");
                            String profilePic=jsonObject1.getString("ProfilePic");
                            String state=jsonObject2.getString("State");
                            String district=jsonObject2.getString("District");

                            String id=jsonObject1.getString("Id");

                            String location=district+", "+state;

                            ConnectionBean crops = new ConnectionBean(name, "Farmer", location,profilePic,id,phoneNo);
                            newOrderBeansList.add(crops);


                        }
                        farmadapter=new ConnectionAdapter(getActivity(),newOrderBeansList);
                        recyclerView.setAdapter(farmadapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
