package com.FarmPe.India.Fragment;

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
import android.widget.TextView;

import com.FarmPe.India.Adapter.FarmsImageAdapter;
import com.FarmPe.India.Adapter.NotificationListAdapter;
import com.FarmPe.India.Adapter.ShortListAdapter;
import com.FarmPe.India.Bean.FarmsImageBean;
import com.FarmPe.India.Bean.NotificationBean;
import com.FarmPe.India.Bean.ShortListBean;
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

public class ShortList extends Fragment {

    public static List<ShortListBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static ShortListAdapter farmadapter;
    TextView toolbar_title;
    LinearLayout back_feed;
    Fragment selectedFragment;
    SessionManager sessionManager;
    JSONObject lngObject;

    public static ShortList newInstance() {
        ShortList fragment = new ShortList();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.short_list_recy, container, false);
        recyclerView=view.findViewById(R.id.recycler_short);
       // toolbar_title=view.findViewById(R.id.toolbar_title);
       // back_feed=view.findViewById(R.id.back_feed);

        sessionManager = new SessionManager(getActivity());


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack ("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    /* else if(getArguments().getString("navigation_from").equals("setting")){
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    }*/
                   /* FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);*/


                    return true;
                }
                return false;
            }
        });



        newOrderBeansList.clear();
        GridLayoutManager mLayoutManager_farm = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager_farm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ShortList();


       /* newOrderBeansList.add(img6);
        newOrderBeansList.add(img6);*/
      /*  ShortListBean bean=new ShortListBean("Tractor Price","John Deere 3028 EN","21-30 HP","Jagdish Kumar","Bahjoi","Sambhal","");
         newOrderBeansList.add(bean);
         newOrderBeansList.add(bean);
         newOrderBeansList.add(bean);
         newOrderBeansList.add(bean);
         newOrderBeansList.add(bean);
         newOrderBeansList.add(bean);
         newOrderBeansList.add(bean);
         newOrderBeansList.add(bean);
         newOrderBeansList.add(bean);
         newOrderBeansList.add(bean);
        farmadapter=new ShortListAdapter(getActivity(),newOrderBeansList);
        recyclerView.setAdapter(farmadapter);
*/

        return view;
    }

    private void ShortList() {

        try {
            newOrderBeansList.clear();

            JSONObject userRequestjsonObject = new JSONObject();
            userRequestjsonObject.put("UserId",sessionManager.getRegId("userId"));

          /*  JSONObject postjsonObject = new JSONObject();
            postjsonObject.put("objCropDetails", userRequestjsonObject);
*/
            System.out.println("postObj"+userRequestjsonObject.toString());

            Login_post.login_posting(getActivity(), Urls.GetShortlistedRFQ,userRequestjsonObject,new VoleyJsonObjectCallback() {
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


                            String lookingForDetails=jsonObject1.getString("LookingForDetails");
                            String brandName=jsonObject1.getString("BrandName");
                            String model=jsonObject1.getString("Model");
                            String modelImage=jsonObject1.getString("ModelImage");
                            String id=jsonObject1.getString("CreatedBy");
                            String main_id=jsonObject1.getString("Id");
                            String name=jsonObject2.getString("Name");
                            String district=jsonObject2.getString("District");
                            String state=jsonObject2.getString("State");
                            String hp_range=jsonObject1.getString("HorsePowerRange");


                            System.out.println("madelslistt"+newOrderBeansList.size());

                            ShortListBean crops = new ShortListBean(lookingForDetails,brandName,model,hp_range,name,district,state,modelImage,main_id);
                            newOrderBeansList.add(crops);


                        }


                        farmadapter=new ShortListAdapter(getActivity(),newOrderBeansList);
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
