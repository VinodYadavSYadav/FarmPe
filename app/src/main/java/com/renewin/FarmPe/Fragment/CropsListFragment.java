package com.renewin.FarmPe.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renewin.FarmPe.Adapter.CropsListAdapter;
import com.renewin.FarmPe.Bean.CropListBean;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.Urls;
import com.renewin.FarmPe.Volly_class.Login_post;
import com.renewin.FarmPe.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CropsListFragment extends Fragment {
    private List<CropListBean> newOrderBeansList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CropsListAdapter mAdapter;
    Fragment selectedFragment;
    String id;
    public static int cropId;
    public static TabLayout tabLayout;
    private ViewPager viewPager;
    int farmerId;
    boolean fragloaded= false;
    LinearLayout back_feed;
    TextView toolbar_title;


    public static CropsListFragment newInstance() {
        CropsListFragment fragment = new CropsListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.farmers_near_me, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_crops);
        back_feed=view.findViewById(R.id.back_feed);


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("discover", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });
        if(!fragloaded)
        CropsList();

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("discover", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        System.out.println("newOrderBeansListvsdvdv" + newOrderBeansList.size());

        mAdapter = new CropsListAdapter(getActivity(), newOrderBeansList);
        recyclerView.setAdapter(mAdapter);

        fragloaded=true;

        return view;
    }

    private void CropsList() {

        try {
            newOrderBeansList.clear();

            JSONObject userRequestjsonObject = new JSONObject();
           // userRequestjsonObject.put("CreatedBy", "111");


            JSONObject postjsonObject = new JSONObject();
           // postjsonObject.put("objCropDetails", userRequestjsonObject);

            System.out.println("postObj"+postjsonObject.toString());

            Login_post.login_posting(getActivity(), Urls.GetAllCrops,userRequestjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("cropsresult"+result);
                    JSONArray cropsListArray=null;
                    try {
                        cropsListArray=result.getJSONArray("Crops");
                        System.out.println("e     e e ddd"+cropsListArray.length());
                        for (int i=0;i<cropsListArray.length();i++){
                            JSONObject jsonObject1=cropsListArray.getJSONObject(i);
                            String location=jsonObject1.getString("PickupLocation");

                                String cropName=jsonObject1.getString("CropName");
                                System.out.println("crop_name"+cropName);
                                String crop_variety=jsonObject1.getString("Variety");
                                String crop_grade=jsonObject1.getString("Grade");
                                String crop_quantity=jsonObject1.getString("Quantity").trim();
                                String crop_uom=jsonObject1.getString("UoM");
                                String category=jsonObject1.getString("Category");
                                String crop_price=jsonObject1.getString("Price").trim();
                                System.out.println("pprriiccee"+crop_price);
                                id=jsonObject1.getString("Id");
                            String sellType = jsonObject1.getString("SellType");
                                farmerId=jsonObject1.getInt("FarmerId");
                                String UserName=jsonObject1.getString("UserName");
                                String CropImg=jsonObject1.getString("CropIcon");
                                System.out.println("Farmeriddddd"+farmerId);
                                //cropId=jsonObject1.getInt("CropId");

                            String  latts=jsonObject1.getString("Latitude");
                            String langgs=jsonObject1.getString("Longitude");

                            CropListBean crops = new CropListBean(cropName, crop_variety, location, crop_grade,
                                    crop_quantity, crop_uom, crop_price, id, farmerId,
                                    UserName,latts,langgs,CropImg,category,sellType);
                            newOrderBeansList.add(crops);

                          /*  if(!latts.equals("") | !langgs.equals("")) {

                                CropListBean crops = new CropListBean(cropName, crop_variety, location, crop_grade,
                                        crop_quantity, crop_uom, crop_price, id, farmerId,
                                        UserName,latts,langgs,CropImg,category);
                                newOrderBeansList.add(crops);
                            }*/
                        }
                        recyclerView.setAdapter(mAdapter);
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
