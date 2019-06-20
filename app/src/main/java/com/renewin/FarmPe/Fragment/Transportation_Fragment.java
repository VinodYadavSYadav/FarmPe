package com.renewin.Xohri.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renewin.Xohri.Adapter.Transportation_Adapter;
import com.renewin.Xohri.Bean.Transportation_Bean;
import com.renewin.Xohri.R;
import com.renewin.Xohri.SessionManager;
import com.renewin.Xohri.Urls;
import com.renewin.Xohri.Volly_class.Crop_Post;
import com.renewin.Xohri.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Transportation_Fragment extends Fragment {

     Fragment selectedFragment;
     private RecyclerView recyclerView;
     LinearLayout back_feed;
     SessionManager sessionManager;

    TextView add_newtrnspton_details,toolbar_title;
    Transportation_Adapter mAdapter;
    ArrayList<Transportation_Bean> transportation_ArrayList = new ArrayList<>();
    JSONArray transpo_array;
    Transportation_Bean transportation_bean;



    public static Transportation_Fragment newInstance() {
        Transportation_Fragment fragment = new Transportation_Fragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kyc_recyc_layout, container, false);

        recyclerView = view.findViewById(R.id.recycler_2);
        add_newtrnspton_details = view.findViewById(R.id.add_kyc_details);
        toolbar_title = view.findViewById(R.id.toolbar_title);
        back_feed = view.findViewById(R.id.back_feed);

        toolbar_title.setText("Your Transportation");
        add_newtrnspton_details.setText("Add New Transportation");

        sessionManager = new SessionManager(getActivity());


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {


                    selectedFragment = My_Account_Fragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();

//
//                    Intent i=new Intent(getActivity(), Home_Page_WithBottomMenu_Activity.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                  //  i.putExtra("nav_switch","TRANSPORTATION_FRAGMENT");
//                    startActivity(i);


                    return true;
                }
                return false;
            }
        });


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                selectedFragment = My_Account_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();

//                Intent i=new Intent(getActivity(), Home_Page_WithBottomMenu_Activity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                startActivity(i);

            }
        });


        add_newtrnspton_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("TransportationType","");
                bundle.putString("VehicleNumber","");
                bundle.putString("OwnerName","");
                bundle.putString("Id","");

                bundle.putString("ADD_NTRANSPOTATION","Transpotation details");

                selectedFragment = Add_Transportation_Details_Fragment.newInstance();
                FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                selectedFragment.setArguments(bundle);
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("add_transport");
                transaction.commit();

            }
        });


       /* dataModels= new ArrayList<>();
        for (int i=0;i<=4;i++){
            dataModels.add(new Bank_Account_Bean("Apple Pie", "Android 1.0","","","",false));
        }
*/

        mAdapter = new Transportation_Adapter(transportation_ArrayList,getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        try{

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("CreatedBy",sessionManager.getRegId("userId"));

            Crop_Post.crop_posting(getActivity(), Urls.GetTransportationDetails, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("GetTransportationDetailsssssssssssssssss" + result);
                    try{
                        transportation_ArrayList.clear();


                        transpo_array = result.getJSONArray("TransportationLists");
                        for(int i = 0;i<transpo_array.length();i++){
                            JSONObject jsonObject1 = transpo_array.getJSONObject(i);

                            transportation_bean = new Transportation_Bean(jsonObject1.getString("Id"),jsonObject1.getString("TransportationType"),jsonObject1.getString("VehicleNumber"),jsonObject1.getString("OwnerName"));
                            transportation_ArrayList.add(transportation_bean);

                        }

                        mAdapter.notifyDataSetChanged();




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
