package com.renewin.FarmPe.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renewin.FarmPe.Adapter.AddBrandAdapter;
import com.renewin.FarmPe.Adapter.AddFirstAdapter;
import com.renewin.FarmPe.Bean.AddTractorBean;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.Urls;
import com.renewin.FarmPe.Volly_class.Login_post;
import com.renewin.FarmPe.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FarmsDetailsFragment extends Fragment {

    public static List<AddTractorBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static AddBrandAdapter farmadapter;
    TextView toolbar_title;
    LinearLayout back_feed;



    public static FarmsDetailsFragment newInstance() {
        FarmsDetailsFragment fragment = new FarmsDetailsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.farms_details_page, container, false);
       // recyclerView=view.findViewById(R.id.recycler_what_looking);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);

        Bundle bundle=getArguments();
        toolbar_title.setText(bundle.getString("farm_name"));


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("connect", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });



        return view;
    }

    private void BrandList() {
       /* Bundle bundle=getArguments();
        String lookingForId=bundle.getString("looinkgId");*/

        try {
            newOrderBeansList.clear();

            JSONObject userRequestjsonObject = new JSONObject();
             userRequestjsonObject.put("LookingForDetailsId", AddFirstAdapter.looinkgId);


            JSONObject postjsonObject = new JSONObject();
            // postjsonObject.put("objCropDetails", userRequestjsonObject);

            System.out.println("postObj"+userRequestjsonObject.toString());

            Login_post.login_posting(getActivity(), Urls.GetBrandList,userRequestjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("cropsresult"+result);
                    JSONArray cropsListArray=null;
                    try {
                        cropsListArray=result.getJSONArray("BrandList");
                        System.out.println("e     e e ddd"+cropsListArray.length());
                        for (int i=0;i<cropsListArray.length();i++){
                            JSONObject jsonObject1=cropsListArray.getJSONObject(i);

                            String brand_name=jsonObject1.getString("BrandName");

                            String id=jsonObject1.getString("Id");


                           AddTractorBean crops = new AddTractorBean(R.drawable.tractor_green, brand_name,id);
                           newOrderBeansList.add(crops);

                          /*  if(!latts.equals("") | !langgs.equals("")) {

                                CropListBean crops = new CropListBean(cropName, crop_variety, location, crop_grade,
                                        crop_quantity, crop_uom, crop_price, id, farmerId,
                                        UserName,latts,langgs,CropImg,category);
                                newOrderBeansList.add(crops);
                            }*/
                        }
                        farmadapter=new AddBrandAdapter(getActivity(),newOrderBeansList);
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
