package com.renewin.FarmPeFarmer.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.renewin.FarmPeFarmer.Adapter.FarmerImageAdapter;
import com.renewin.FarmPeFarmer.Bean.FarmsImageBean1;
import com.renewin.FarmPeFarmer.R;
import com.renewin.FarmPeFarmer.Urls;
import com.renewin.FarmPeFarmer.Volly_class.Login_post;
import com.renewin.FarmPeFarmer.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FarmerFragment extends Fragment {

    public static List<FarmsImageBean1> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static FarmerImageAdapter farmadapter;


    public static FarmerFragment newInstance() {
        FarmerFragment fragment = new FarmerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.looking_for_recy, container, false);
        recyclerView=view.findViewById(R.id.recycler_looking);

        FarmersList();
        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

       /* FarmsImageBean1 img1=new FarmsImageBean1(R.drawable.jk,"Jagdish Kumar","","","","","Rampura Bahjoi","");
        newOrderBeansList.add(img1);

        FarmsImageBean1 img2=new FarmsImageBean1(R.drawable.ragi_sir,"Mallikarjun Ragi","","","","","Naregal,Ron","");
        newOrderBeansList.add(img2);

        FarmsImageBean1 img3=new FarmsImageBean1(R.drawable.ravi,"Ravi Kumar Hattikal","","","","","Rampura Bahjoi","");
        newOrderBeansList.add(img3);

        FarmsImageBean1 img4=new FarmsImageBean1(R.drawable.manoj,"Manoj Kumar","","","","","Rampura Bahjoi","");
        newOrderBeansList.add(img4);
        newOrderBeansList.add(img4);
        newOrderBeansList.add(img4);


        farmadapter=new FarmerImageAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(farmadapter);
*/
        return view;
    }

    private void FarmersList() {

        try {
            newOrderBeansList.clear();

            JSONObject userRequestjsonObject = new JSONObject();


            JSONObject postjsonObject = new JSONObject();
            postjsonObject.put("objCropDetails", userRequestjsonObject);


            Login_post.login_posting(getActivity(), Urls.GetFarmerDetailsList,userRequestjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("cropsresult"+result);
                    JSONArray cropsListArray=null;
                    try {
                        cropsListArray=result.getJSONArray("FarmersList");
                        System.out.println("e     e e ddd"+cropsListArray.length());
                        for (int i=0;i<cropsListArray.length();i++){
                            JSONObject jsonObject1=cropsListArray.getJSONObject(i);
                            String farmer_name=jsonObject1.getString("FarmerName");
                           // String location=jsonObject1.getString("Location");
                            String village=jsonObject1.getString("Village");
                            String district=jsonObject1.getString("District");
                            String image=jsonObject1.getString("FarmerPhoto");
                            System.out.println("farmer_photo"+image);
                            String id=jsonObject1.getString("Id");


                            System.out.println("madelslistt"+newOrderBeansList.size());

                            if (!(village.equals("")||district.equals(""))){
                                FarmsImageBean1 crops = new FarmsImageBean1(image,farmer_name,district,village,"","","",id);
                                newOrderBeansList.add(crops);
                            }



                        }
                        farmadapter=new FarmerImageAdapter(getActivity(),newOrderBeansList);
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
