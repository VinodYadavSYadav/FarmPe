package com.renewin.FarmPeFarmer.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renewin.FarmPeFarmer.Adapter.FarmsImageAdapter;
import com.renewin.FarmPeFarmer.Bean.FarmsImageBean;
import com.renewin.FarmPeFarmer.R;
import com.renewin.FarmPeFarmer.Urls;
import com.renewin.FarmPeFarmer.Volly_class.Login_post;
import com.renewin.FarmPeFarmer.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.renewin.FarmPeFarmer.Fragment.HomeMenuFragment.searchView;

public class LookingForFragment extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    private List<FarmsImageBean> searchresultAraaylist = new ArrayList<>();

    public static RecyclerView recyclerView;
    public static FarmsImageAdapter farmadapter;
    boolean doubleBackToExitPressedOnce = false;
    String location;


    public static LookingForFragment newInstance() {
        LookingForFragment fragment = new LookingForFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.looking_for_recy, container, false);
        recyclerView=view.findViewById(R.id.recycler_looking);
        LookingForList();
        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
/*

        FarmsImageBean img1=new FarmsImageBean(R.drawable.tractor_green,"Tractor Price","Mahindra Jivo 225 DL 20HP","","Immediately","Jagdish Kumar","Rampura Bahjoi","");
        newOrderBeansList.add(img1);

        FarmsImageBean img2=new FarmsImageBean(R.drawable.gyrovator,"Tractor Implements Price","Mahindra Jivo 225 DL 20HP","","1 Month","Jagdish Kumar","Rampura Bahjoi","");
        newOrderBeansList.add(img2);

        FarmsImageBean img3=new FarmsImageBean(R.drawable.tractor_green,"Tractor Price","Mahindra Jivo 225 DL 20HP","","Immediately","Jagdish Kumar","Rampura Bahjoi","");
        newOrderBeansList.add(img3);

        FarmsImageBean img4=new FarmsImageBean(R.drawable.tractor_red,"Tractor Price","Mahindra Jivo 225 DL 20HP","","Immediately","Jagdish Kumar","Rampura Bahjoi","");
        newOrderBeansList.add(img4);
        newOrderBeansList.add(img4);
        newOrderBeansList.add(img4);
        newOrderBeansList.add(img4);
        newOrderBeansList.add(img4);


        farmadapter=new FarmsImageAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(farmadapter);
*/


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                searchView.clearFocus();
                System.out.println("lknkknknknknknknknnk");
             /*   if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }*/
                return false;

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onQueryTextChange(String newText) {
                // back_feed.setVisibility(View.GONE);
                //title.setVisibility(View.GONE);
                System.out.println("lknkknknknknknknknnk"+newText);
                sorting(newText);


                return false;
            }
        });


        return view;
    }
    private void LookingForList() {

        try {
            newOrderBeansList.clear();

            JSONObject userRequestjsonObject = new JSONObject();


          /*  JSONObject postjsonObject = new JSONObject();
            postjsonObject.put("objCropDetails", userRequestjsonObject);
*/
            System.out.println("postObj"+userRequestjsonObject.toString());

            Login_post.login_posting(getActivity(), Urls.GetLookingForList,userRequestjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("cropsresult"+result);
                    JSONArray cropsListArray=null;
                    try {
                        cropsListArray=result.getJSONArray("LookingForList");
                        System.out.println("e     e e ddd"+cropsListArray.length());
                        for (int i=0;i<cropsListArray.length();i++){
                            JSONObject jsonObject1=cropsListArray.getJSONObject(i);
                            JSONObject jsonObject2=jsonObject1.getJSONObject("Address");


                            String model=jsonObject1.getString("Model");
                            String purchaseTimeline=jsonObject1.getString("PurchaseTimeline");
                            String image=jsonObject1.getString("ModelImage");
                            String id=jsonObject1.getString("CreatedBy");
                            String name=jsonObject2.getString("Name");
                            String city=jsonObject2.getString("City");
                            String state=jsonObject2.getString("State");
                            String hp_range=jsonObject1.getString("HorsePowerRange");

                            if (city.equals("")){
                                 location="Bangalore"+", "+state;
                            }else{
                                 location=city+", "+state;
                            }



                            System.out.println("madelslistt"+newOrderBeansList.size());

                            FarmsImageBean crops = new FarmsImageBean(image,"Tractor Price",model,hp_range,purchaseTimeline,name,location,id);
                            newOrderBeansList.add(crops);



                          /*  if(!latts.equals("") | !langgs.equals("")) {

                                CropListBean crops = new CropListBean(cropName, crop_variety, location, crop_grade,
                                        crop_quantity, crop_uom, crop_price, id, farmerId,
                                        UserName,latts,langgs,CropImg,category);
                                newOrderBeansList.add(crops);
                            }*/
                        }
                        farmadapter=new FarmsImageAdapter(getActivity(),newOrderBeansList);
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


    public  void sorting(String filter_text){

        searchresultAraaylist.clear();
        for (FarmsImageBean composeMsgOrderSecondBean: newOrderBeansList) {
            System.out.println("llllllllllllllll"+composeMsgOrderSecondBean.getProd_price());
            final String text = composeMsgOrderSecondBean.getProd_price().toLowerCase();

            if (text.contains(filter_text)) {

                searchresultAraaylist.add(composeMsgOrderSecondBean);
            }
        }

        farmadapter=new FarmsImageAdapter(getActivity(),searchresultAraaylist);
        recyclerView.setAdapter(farmadapter);

    }

}
