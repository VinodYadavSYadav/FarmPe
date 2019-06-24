package com.renewin.FarmPe.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renewin.FarmPe.Adapter.FarmsHomeAdapter;
import com.renewin.FarmPe.Bean.FarmsImageBean1;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.Urls;
import com.renewin.FarmPe.Volly_class.Login_post;
import com.renewin.FarmPe.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FarmsHomePageFragment extends Fragment {

    public static List<FarmsImageBean1> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static FarmsHomeAdapter farmadapter;


    public static FarmsHomePageFragment newInstance() {
        FarmsHomePageFragment fragment = new FarmsHomePageFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.looking_for_recy, container, false);
        recyclerView=view.findViewById(R.id.recycler_looking);
        FarmsList();
        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

      /*  FarmsImageBean1 img1=new FarmsImageBean1(R.drawable.cow_dairy,"Amrutha Dairy Farm","Commertial Dairy Farming Training,Consulting Project Reporting","","","Jagdish Kumar","Halenahalli DoddaBallapura","");
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);
        newOrderBeansList.add(img1);*/

       /* FarmsImageBean img2=new FarmsImageBean(R.drawable.gyrovator,"Tractor Implements Price","Mahindra Jivo 225 DL 20HP","1 Month","Jagdish Kumar","Rampura Bahjoi");
        newOrderBeansList.add(img2);

        FarmsImageBean img3=new FarmsImageBean(R.drawable.tractor_green,"Tractor Price","Mahindra Jivo 225 DL 20HP","Immediately","Jagdish Kumar","Rampura Bahjoi");
        newOrderBeansList.add(img3);

        FarmsImageBean img4=new FarmsImageBean(R.drawable.tractor_red,"Tractor Price","Mahindra Jivo 225 DL 20HP","Immediately","Jagdish Kumar","Rampura Bahjoi");
        newOrderBeansList.add(img4);*/


       /* farmadapter=new FarmsHomeAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(farmadapter);
*/
        return view;
    }
    private void FarmsList() {

        try {
            newOrderBeansList.clear();

            JSONObject userRequestjsonObject = new JSONObject();


            JSONObject postjsonObject = new JSONObject();
            postjsonObject.put("objCropDetails", userRequestjsonObject);

            System.out.println("postObj"+userRequestjsonObject.toString());

            Login_post.login_posting(getActivity(), Urls.GetFarmDetailsList,userRequestjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("cropsresult"+result);
                    JSONArray cropsListArray=null;
                    try {
                        cropsListArray=result.getJSONArray("FarmsList");
                        System.out.println("e     e e ddd"+cropsListArray.length());
                        for (int i=0;i<cropsListArray.length();i++){
                            JSONObject jsonObject1=cropsListArray.getJSONObject(i);
                            String farm_name=jsonObject1.getString("FarmName");
                            String location=jsonObject1.getString("Location");
                            //  String image=jsonObject1.getString("ModelImage");
                            String id=jsonObject1.getString("Id");


                            System.out.println("madelslistt"+newOrderBeansList.size());

                            FarmsImageBean1 crops = new FarmsImageBean1(R.drawable.cow,farm_name,"","","Commertial Dairy Farming Training,Consulting Project Reporting","Jagdish Kumar",location,id);
                            newOrderBeansList.add(crops);




                        }
                        farmadapter=new FarmsHomeAdapter(getActivity(),newOrderBeansList);
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
