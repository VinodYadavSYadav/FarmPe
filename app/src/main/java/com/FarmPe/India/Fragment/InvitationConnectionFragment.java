package com.FarmPe.India.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.FarmPe.India.Adapter.InvitationAdapter;
import com.FarmPe.India.Bean.InvitationBean;
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

public class InvitationConnectionFragment extends Fragment {

    public static List<InvitationBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static InvitationAdapter farmadapter;
    TextView toolbar_title;
    LinearLayout back_feed,send;
    Fragment selectedFragment;
    ImageView filter;
    public static String farms;
    SessionManager sessionManager;
    Bundle bundle;
    public static String invitation_status;


    public static InvitationConnectionFragment newInstance() {
        InvitationConnectionFragment fragment = new InvitationConnectionFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.invitation_connect, container, false);
        back_feed=view.findViewById(R.id.back_feed);
        send=view.findViewById(R.id.send);
        recyclerView=view.findViewById(R.id.recy_network_type);

        bundle=getArguments();

        sessionManager=new SessionManager(getActivity());

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                farms="farm_back";
                InvitationConnectionFarmerFragment.farmer_farmer=null;
                InvitationConnectionFarmsFragment.farmer_farms=null;

                selectedFragment = HomeMenuFragment.newInstance();
                FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
               // transaction.addToBackStack("invitation_back");
                transaction.commit();

               /* FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("lookinnng", FragmentManager.POP_BACK_STACK_INCLUSIVE);*/

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"Invitation has sent.",Toast.LENGTH_LONG).show();
                SendInvitation();
            }
        });


        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        InvitationConnection();


        return view;
    }

    private void InvitationConnection() {

        try {
            newOrderBeansList.clear();

            JSONObject userRequestjsonObject = new JSONObject();


            System.out.println("postObj"+userRequestjsonObject.toString());

            Login_post.login_posting(getActivity(), Urls.GetConnectionNetworkTypes,userRequestjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("cropsresult"+result);
                    JSONArray cropsListArray=null;
                    try {
                        cropsListArray=result.getJSONArray("connectionNetworkTypes");
                        System.out.println("e     e e ddd"+cropsListArray.length());
                        for (int i=0;i<cropsListArray.length();i++){
                            JSONObject jsonObject1=cropsListArray.getJSONObject(i);

                            String type=jsonObject1.getString("ConnectionNetworkType");

                            String id=jsonObject1.getString("Id");

                            if (i==0) {
                                InvitationBean crops = new InvitationBean(type, id, true);
                                newOrderBeansList.add(crops);

                            }else {
                                InvitationBean crops = new InvitationBean(type, id, false);
                                newOrderBeansList.add(crops);

                            }

                        }
                        farmadapter=new InvitationAdapter(getActivity(),newOrderBeansList);
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


    private void SendInvitation() {


        try {
            newOrderBeansList.clear();

            JSONObject userRequestjsonObject = new JSONObject();
            userRequestjsonObject.put("RequestBy",sessionManager.getRegId("userId"));
            userRequestjsonObject.put("RequestTo", bundle.getString("CreatedBy"));
            userRequestjsonObject.put("ConnectionNetworkType", InvitationAdapter.farm_listid);
            userRequestjsonObject.put("ConnectionType", DashboardFragment.looking_for_type);
            userRequestjsonObject.put("ConnectionRelatedId", bundle.getString("MainId"));
            userRequestjsonObject.put("CreatedBy", sessionManager.getRegId("userId"));



          /*  JSONObject postjsonObject = new JSONObject();
            postjsonObject.put("objCropDetails", userRequestjsonObject);
*/
            System.out.println("postObj"+userRequestjsonObject.toString());

            Login_post.login_posting(getActivity(), Urls.RequestConnection,userRequestjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("cropsresult"+result);

                    try {
                        String status=result.getString("Status");
                       // String message=result.getString("Message");

                        if (!(status.equals("0"))){
                             invitation_status="looking_invi";

                            //Toast.makeText(getActivity(),"Invitation has sent.",Toast.LENGTH_LONG).show();
                            selectedFragment = ConnectionSuccessfulFragment.newInstance();
                            FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout, selectedFragment);
                            transaction.addToBackStack("invitation_back");
                            transaction.commit();
                        }

                       /* selectedFragment = HomeMenuFragment.newInstance();
                        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();*/

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




