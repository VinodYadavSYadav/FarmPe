package com.renewin.FarmPeFarmer.Fragment;



import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.renewin.FarmPeFarmer.Adapter.DistrictAdapter;
import com.renewin.FarmPeFarmer.Adapter.HoblisAdapter;
import com.renewin.FarmPeFarmer.Adapter.Sell_Location_Adapter;
import com.renewin.FarmPeFarmer.Adapter.StateApdater;
import com.renewin.FarmPeFarmer.Adapter.TalukAdapter;
import com.renewin.FarmPeFarmer.Adapter.VillageAdapter;
import com.renewin.FarmPeFarmer.Bean.StateBean;
import com.renewin.FarmPeFarmer.R;
import com.renewin.FarmPeFarmer.SessionManager;
import com.renewin.FarmPeFarmer.Urls;
import com.renewin.FarmPeFarmer.Volly_class.Crop_Post;
import com.renewin.FarmPeFarmer.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class  Add_New_Address_Fragment extends Fragment {

    RecyclerView recyclerView;
    Sell_Location_Adapter mAdapter;

    static List<StateBean> stateBeanList = new ArrayList<>();
    static List<StateBean> districtBeanList = new ArrayList<>();
    static List<StateBean> talukBeanList = new ArrayList<>();
    static List<StateBean> hobliBeanList = new ArrayList<>();
    static List<StateBean> villageBeanList = new ArrayList<>();
    StateApdater stateApdater;
    DistrictAdapter districtAdapter;
    TalukAdapter talukAdapter;
    HoblisAdapter hoblisAdapter;
    VillageAdapter villageAdapter;
    LinearLayout linear_name, linear_mobile,linear_pincode,linear_city,linear_street,linear_house,linear_landmark;
    LinearLayout back_feed;
    TextView toolbar_titletxt;
    JSONArray jsonArray,state_array,tal_array,hobli_array,village_array;
    StateBean stateBean;

    public static TextView add_new_address;
    Fragment selectedFragment = null;
    JSONObject lngObject;

    public static EditText name,mobile,pincode_no,house_numb,street_name,landmrk,city,state,taluk,hobli,district,village,select_address;
    String status,message;
    String Id;
    SessionManager sessionManager;
    public static Dialog grade_dialog;
    int selected_id,selected_id_time;



    public static Add_New_Address_Fragment newInstance() {
        Add_New_Address_Fragment fragment = new Add_New_Address_Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_add, container, false);
        getActivity().getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        select_address = view.findViewById(R.id.select_address);
        name = view.findViewById(R.id.full_name);
        name.setFilters(new InputFilter[]{EMOJI_FILTER});
        mobile = view.findViewById(R.id.mobile_no);
        back_feed = view.findViewById(R.id.back_feed);

        pincode_no = view.findViewById(R.id.pincode);
        house_numb = view.findViewById(R.id.house_no);
        house_numb.setFilters(new InputFilter[]{EMOJI_FILTER});
        street_name = view.findViewById(R.id.street);
        street_name.setFilters(new InputFilter[]{EMOJI_FILTER});
        landmrk = view.findViewById(R.id.landmark_1);
        landmrk.setFilters(new InputFilter[]{EMOJI_FILTER});
        add_new_address = view.findViewById(R.id.add_address);

        state = view.findViewById(R.id.state);
        city = view.findViewById(R.id.city_1);
        city.setFilters(new InputFilter[]{EMOJI_FILTER});
        district = view.findViewById(R.id.district_1);
        taluk = view.findViewById(R.id.taluk_1);
        hobli = view.findViewById(R.id.hobli_1);
        village = view.findViewById(R.id.village_1);

        toolbar_titletxt=view.findViewById(R.id.toolbar_title);

        selected_id=RequestFormFragment.selectedId;
        selected_id_time=RequestFormFragment.selectedId_time_recent;

        System.out.println("selecteddddd_iddd"+selected_id_time);

        name.setText(getArguments().getString("Addr_name"));
        mobile.setText(getArguments().getString("Addr_mobile"));
        pincode_no.setText(getArguments().getString("Addr_pincode"));
        house_numb.setText(getArguments().getString("Addr_Houseno"));
        street_name.setText(getArguments().getString("Addr_Street"));
        landmrk.setText(getArguments().getString("Addr_landmark"));
        city.setText(getArguments().getString("Addr_city"));


        state.setText(getArguments().getString("Addr_state"));
        district.setText(getArguments().getString("Addr_district"));
        taluk.setText(getArguments().getString("Addr_taluk"));
        hobli.setText(getArguments().getString("Addr_hobli"));
        village.setText(getArguments().getString("Addr_village"));
        select_address.setText(getArguments().getString("Addr_pickup_from"));





        linear_name = view.findViewById(R.id.linea_name1);
        linear_mobile = view.findViewById(R.id.linea_mobile1);
        linear_pincode= view.findViewById(R.id.linea_pincode1);
        linear_house= view.findViewById(R.id.linear_houseno);
        linear_street= view.findViewById(R.id.linea_street);
        linear_landmark= view.findViewById(R.id.linea_landmark);
        linear_city= view.findViewById(R.id.linea_city);


        sessionManager = new SessionManager(getActivity());



        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments().getString("navigation_from").equals("yu_ads_frg")) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("yu_ads_frg", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }else if(getArguments().getString("navigation_from").equals("your_add")){

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("your_add", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                } else{
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("request", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }

            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    if (getArguments().getString("navigation_from").equals("yu_ads_frg")) {

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("yu_ads_frg", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    }else if(getArguments().getString("navigation_from").equals("your_add")){

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("your_add", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    } else{
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack("request", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    }

                    return true;
                }
                return false;
            }
        });


        /*name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                linear_layout_selection(linear_name,linear_mobile,linear_pincode,linear_house,linear_street,linear_landmark,linear_city);
                return false;
            }
        });


        mobile.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                linear_layout_selection(linear_mobile,linear_name,linear_pincode,linear_house,linear_landmark,linear_street,linear_city);

                return false;

            }
        });

        pincode_no.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(linear_pincode,linear_name,linear_mobile,linear_house,linear_landmark,linear_street,linear_city);
                return false;

            }
        });


        house_numb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(linear_house,linear_name,linear_mobile,linear_pincode,linear_street,linear_landmark,linear_city);
                return false;
            }
        });


        street_name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(linear_street,linear_name,linear_mobile,linear_house,linear_landmark,linear_pincode,linear_city);
                return false;
            }
        });



        landmrk.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(linear_landmark,linear_name,linear_mobile,linear_house,linear_street,linear_pincode,linear_city);
                return false;
            }
        });



        city.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linear_layout_selection(linear_city,linear_landmark,linear_name,linear_mobile,linear_house,linear_street,linear_pincode);
                return false;
            }
        });*/



        select_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.select_address_popup);

                ImageView image = (ImageView) dialog.findViewById(R.id.close_popup);
                final TextView home =(TextView)dialog.findViewById(R.id.home_1);
                final TextView barn = (TextView)dialog.findViewById(R.id.barn) ;
                final TextView ware_house = (TextView)dialog.findViewById(R.id.ware_hus) ;
                final TextView farm = (TextView)dialog.findViewById(R.id.farm) ;
                final TextView others = (TextView)dialog.findViewById(R.id.othrs) ;
                final TextView popuptxt = (TextView)dialog.findViewById(R.id.popup_heading) ;
                try {
                    lngObject = new JSONObject(sessionManager.getRegId("language"));
                    popuptxt.setText(lngObject.getString("SelectanAddressType"));
                    home.setText(lngObject.getString("Home"));
                    barn.setText(lngObject.getString("Barn"));
                    ware_house.setText(lngObject.getString("Warehouse"));
                    farm.setText(lngObject.getString("Farm"));
                    others.setText(lngObject.getString("Others"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        select_address.setText(home.getText().toString());
                        dialog.dismiss();
                    }
                });


                barn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        select_address.setText(barn.getText().toString());
                        dialog.dismiss();

                    }
                });

                ware_house.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        select_address.setText(ware_house.getText().toString());
                        dialog.dismiss();

                    }
                });

                farm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        select_address.setText(farm.getText().toString());
                        dialog.dismiss();

                    }
                });

                others.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        select_address.setText(others.getText().toString());
                        dialog.dismiss();

                    }
                });


                dialog.show();

            }
        });


        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                grade_dialog= new Dialog(getActivity());
                grade_dialog.setContentView(R.layout.select_variety_popup);

                TextView popup_heading = (TextView)grade_dialog.findViewById(R.id.popup_heading);
                ImageView image = (ImageView) grade_dialog.findViewById(R.id.close_popup);
                RecyclerView recyclerView = grade_dialog.findViewById(R.id.recycler_view1);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);

                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                stateApdater= new StateApdater(stateBeanList,getActivity());
                recyclerView.setAdapter(stateApdater);

                popup_heading.setText("State");

                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        grade_dialog.dismiss();
                    }
                });


                try{

                    JSONObject jsonObject = new JSONObject();

                    Crop_Post.crop_posting(getActivity(), Urls.State, jsonObject, new VoleyJsonObjectCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject result) {
                            System.out.println("11111ssss" + result);


                            try{
                                stateBeanList.clear();
                                state_array = result.getJSONArray("StateList");
                                for(int i =0;i<state_array.length();i++){
                                    JSONObject jsonObject1 = state_array.getJSONObject(i);

                                    stateBean = new StateBean(jsonObject1.getString("State").trim(),jsonObject1.getString("StateId"));
                                    stateBeanList.add(stateBean);
                                }

                                sorting(stateBeanList);

                                stateApdater.notifyDataSetChanged();
                                grade_dialog.show();




                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    });




                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });



        district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                grade_dialog= new Dialog(getActivity());
                grade_dialog.setContentView(R.layout.select_variety_popup);

                ImageView image = (ImageView) grade_dialog.findViewById(R.id.close_popup);
                TextView popup_heading = (TextView)grade_dialog.findViewById(R.id.popup_heading);
                RecyclerView recyclerView = grade_dialog.findViewById(R.id.recycler_view1);

                popup_heading.setText("District");

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);

                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                districtAdapter= new DistrictAdapter( districtBeanList,getActivity());
                recyclerView.setAdapter(districtAdapter);

                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        grade_dialog.dismiss();
                    }
                });




                try{

                    JSONObject jsonObject = new JSONObject();
                    JSONObject post_jsonobject = new JSONObject();
                    jsonObject.put("StateId",stateApdater.stateid);
                    post_jsonobject.put("Districtobj",jsonObject);

                    Crop_Post.crop_posting(getActivity(), Urls.Districts, post_jsonobject, new VoleyJsonObjectCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject result) {
                            System.out.println("dddddddddddd11111" + result);
                            try{
                                districtBeanList.clear();
                                jsonArray = result.getJSONArray("DistrictList");
                                for(int i =0;i<jsonArray.length();i++){
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    stateBean = new StateBean(jsonObject1.getString("District"),jsonObject1.getString("DistrictId"));
                                    districtBeanList.add(stateBean);
                                }

                                sorting(districtBeanList);


                                districtAdapter.notifyDataSetChanged();
                                grade_dialog.show();

                            }catch (Exception e){
                                e.printStackTrace();
                            }


                        }
                    });


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



        taluk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                grade_dialog= new Dialog(getActivity());
                grade_dialog.setContentView(R.layout.select_variety_popup);

                ImageView image = (ImageView) grade_dialog.findViewById(R.id.close_popup);
                TextView popup_heading = (TextView)grade_dialog.findViewById(R.id.popup_heading);
                RecyclerView recyclerView = grade_dialog.findViewById(R.id.recycler_view1);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                popup_heading.setText("Taluk");

                talukAdapter = new TalukAdapter(talukBeanList,getActivity());
                recyclerView.setAdapter(talukAdapter);

                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        grade_dialog.dismiss();
                    }
                });



                try{

                    JSONObject jsonObject = new JSONObject();
                    JSONObject jsonpost = new JSONObject();
                    jsonObject.put("DistrictId",DistrictAdapter.districtid);
                    jsonpost.put("Talukobj",jsonObject);

                    Crop_Post.crop_posting(getActivity(), Urls.Taluks, jsonpost, new VoleyJsonObjectCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject result) {
                            System.out.println("aaaaaaaaaaaaafffffffffffff"+result);
                            try{
                                talukBeanList.clear();
                                tal_array = result.getJSONArray("TalukList") ;
                                for(int i=0;i<tal_array.length();i++){
                                    JSONObject jsonObject1 = tal_array.getJSONObject(i);
                                    stateBean = new StateBean(jsonObject1.getString("Taluk"),jsonObject1.getString("TalukId"));
                                    talukBeanList.add(stateBean);

                                }
                                sorting(talukBeanList);

                                talukAdapter.notifyDataSetChanged();
                                grade_dialog.show();

                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    });


                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });


        hobli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                grade_dialog= new Dialog(getActivity());
                grade_dialog.setContentView(R.layout.select_variety_popup);

                ImageView image = (ImageView) grade_dialog.findViewById(R.id.close_popup);
                RecyclerView recyclerView = grade_dialog.findViewById(R.id.recycler_view1);
                TextView popup_heading = (TextView)grade_dialog.findViewById(R.id.popup_heading);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                popup_heading.setText("Hobli");

                hoblisAdapter = new HoblisAdapter( hobliBeanList,getActivity());
                recyclerView.setAdapter(hoblisAdapter);

                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        grade_dialog.dismiss();
                    }
                });

                try{

                    final JSONObject jsonObject = new JSONObject();

                    JSONObject json_post = new JSONObject();
                    jsonObject.put("TalukId",TalukAdapter.talukid);
                    json_post.put("Hobliobj",jsonObject);

                    Crop_Post.crop_posting(getActivity(), Urls.Hoblis, json_post, new VoleyJsonObjectCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject result) {
                            System.out.println("hhhhhhhssssskljhgf" + result);

                            try{
                                hobliBeanList.clear();
                                hobli_array = result.getJSONArray("HobliList");
                                for(int i = 0;i<hobli_array.length();i++){
                                    JSONObject jsonObject3 = hobli_array.getJSONObject(i);
                                    stateBean = new StateBean(jsonObject3.getString("Hobli"),jsonObject3.getString("HobliId"));
                                    hobliBeanList.add(stateBean);

                                }
                                sorting(hobliBeanList);

                                hoblisAdapter.notifyDataSetChanged();
                                grade_dialog.show();



                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    });

                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });



        village.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                grade_dialog= new Dialog(getActivity());
                grade_dialog.setContentView(R.layout.select_variety_popup);

                ImageView image = (ImageView) grade_dialog.findViewById(R.id.close_popup);
                TextView popup_heading = (TextView)grade_dialog.findViewById(R.id.popup_heading);
                RecyclerView recyclerView = grade_dialog.findViewById(R.id.recycler_view1);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                villageAdapter = new VillageAdapter(villageBeanList,getActivity());
                recyclerView.setAdapter(villageAdapter);

                popup_heading.setText("Village");


                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        grade_dialog.dismiss();
                    }
                });


                try{
                    JSONObject jsonObject = new JSONObject();
                    JSONObject post_Object = new JSONObject();
                    jsonObject.put("HobliId",hoblisAdapter.hobliid);
                    post_Object.put("Villageobj",jsonObject);

                    Crop_Post.crop_posting(getActivity(), Urls.Villages, post_Object, new VoleyJsonObjectCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject result) {
                            System.out.println("111vvv" + result);

                            try{
                                villageBeanList.clear();
                                village_array = result.getJSONArray("VillageList");
                                for(int i = 0;i<village_array.length();i++) {
                                    JSONObject jsonObject1 = village_array.getJSONObject(i);
                                    stateBean = new StateBean(jsonObject1.getString("Village"), jsonObject1.getString("VillagId"));
                                    villageBeanList.add(stateBean);
                                }

                                sorting(villageBeanList);

                                villageAdapter.notifyDataSetChanged();
                                grade_dialog.show();


                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    });


                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });



        add_new_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(select_address.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Select Address Type", Toast.LENGTH_SHORT).show();


                }else if(name.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Enter Name", Toast.LENGTH_SHORT).show();



                }else if(mobile.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();


                }else if(mobile.length()<10){
                    Toast.makeText(getActivity(), "Incorrect Mobile Number", Toast.LENGTH_SHORT).show();


                }else if(house_numb.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Enter House No/Floor/building", Toast.LENGTH_SHORT).show();



                }else if(street_name.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Enter Street Address", Toast.LENGTH_SHORT).show();



                }else if(landmrk.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Enter Landmark", Toast.LENGTH_SHORT).show();


                }else if(city.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Enter City", Toast.LENGTH_SHORT).show();



                }else if(pincode_no.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Enter Pincode", Toast.LENGTH_SHORT).show();



                }else if(pincode_no.length()<6){
                    Toast.makeText(getActivity(), "Enter a valid Pincode", Toast.LENGTH_SHORT).show();


                }else if(state.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Select State", Toast.LENGTH_SHORT).show();



                }else if(district.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Select District", Toast.LENGTH_SHORT).show();



                }else if(taluk.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Select Taluk", Toast.LENGTH_SHORT).show();



                }else if(hobli.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Select Hobli", Toast.LENGTH_SHORT).show();



                }else if(village.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Select Village", Toast.LENGTH_SHORT).show();


                }else {

                    ComposeCategory();

                }


            }
        });



        try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));
            toolbar_titletxt.setText(lngObject.getString("AddNewAddress"));
            select_address.setHint(lngObject.getString("SelectanAddressType"));
            name.setHint(lngObject.getString("FullName"));
            mobile.setHint(lngObject.getString("PhoneNo"));
            street_name.setHint(lngObject.getString("Colony_Street_Locality"));
            house_numb.setHint(lngObject.getString("Flat_HouseNo_Floor_Building"));
            landmrk.setHint(lngObject.getString("LandMark"));
            city.setHint(lngObject.getString("City"));
            pincode_no.setHint(lngObject.getString("Pincode"));
            state.setHint(lngObject.getString("State"));
            hobli.setHint(lngObject.getString("Hobli"));
            district.setHint(lngObject.getString("District"));
            taluk.setHint(lngObject.getString("Taluk"));
            village.setHint(lngObject.getString("Village"));
            village.setHint(lngObject.getString("Village"));
            add_new_address.setText(lngObject.getString("AddAddress"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;

    }

    private void ComposeCategory() {
        try{

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("DistrictId",DistrictAdapter.districtid);
            jsonObject.put("HobliId", HoblisAdapter.hobliid);
            jsonObject.put("LandMark",landmrk.getText().toString());
            jsonObject.put("City",city.getText().toString());
            jsonObject.put("MobileNo",mobile.getText().toString());
            jsonObject.put("Name",name.getText().toString());
            jsonObject.put("PickUpFrom",select_address.getText().toString());
            jsonObject.put("Pincode",pincode_no.getText().toString());
            jsonObject.put("StateId",StateApdater.stateid);
            jsonObject.put("TalukId",TalukAdapter.talukid);
            jsonObject.put("VillageId", VillageAdapter.villageid);
            jsonObject.put("StreeAddress",house_numb.getText().toString());
            jsonObject.put("StreeAddress1",street_name.getText().toString());
            jsonObject.put("UserId",sessionManager.getRegId("userId"));

            System.out.println("nnnnnnnnnnnnnnnaaaaaaaaa"+jsonObject);



            Crop_Post.crop_posting(getActivity(), Urls.Add_New_Address, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    Bundle bundle=new Bundle();


                    try{

                        status= result.getString("Status");
                        message = result.getString("Message");

                        bundle.putString("add_id",status);
                        bundle.putString("city",city.getText().toString());
                        bundle.putInt("selected_id2",selected_id);
                        bundle.putInt("selected_id_time1",selected_id_time);
                      /*  bundle.putString("add_id",status);
                        bundle.putString("add_id",status);*/

                        if(!(status.equals("0"))){
                            Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();

                            //if (getArguments().getString("request_for")==null){
                            selectedFragment = You_Address_Fragment.newInstance();
                            FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout, selectedFragment);
                            transaction.commit();
                           /* }else{
                                bundle.putString("add_id",status);
                                bundle.putString("city",city.getText().toString());
                                bundle.putInt("selected_id2",selected_id);
                                bundle.putInt("selected_id_time1",selected_id_time);
                                selectedFragment = RequestFormFragment.newInstance();
                                FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.frame_layout, selectedFragment);
                                transaction.commit();
                                selectedFragment.setArguments(bundle);
                            }*/






                                /* selectedFragment = RequestFormFragment.newInstance();
                                 FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                                 transaction.replace(R.id.frame_layout, selectedFragment);
                                 transaction.commit();
                                 selectedFragment.setArguments(bundle);*/


                        }else{

                            Toast.makeText(getActivity(),"Your Address not Added ",Toast.LENGTH_SHORT).show();
                        }



                    }catch (Exception e){
                        e.printStackTrace();

                    }
                }
            });


        }catch (Exception e){
            e.printStackTrace();

        }
    }

    public void linear_layout_selection(LinearLayout selectdl1,LinearLayout l2,LinearLayout l3,LinearLayout l4, LinearLayout l5 ,LinearLayout l6 , LinearLayout l7){
        selectdl1.setBackgroundResource(R.drawable.border);
        l2.setBackgroundResource(R.drawable.border_1_layout);
        l3.setBackgroundResource(R.drawable.border_1_layout);
        l4.setBackgroundResource(R.drawable.border_1_layout);
        l5.setBackgroundResource(R.drawable.border_1_layout);
        l6.setBackgroundResource(R.drawable.border_1_layout);
        l7.setBackgroundResource(R.drawable.border_1_layout);

    }

    public void sorting(List<StateBean> arrayList){

        Collections.sort(arrayList, new Comparator() {
            @Override
            public int compare(Object state_name1, Object state_name2) {
                //use instanceof to verify the references are indeed of the type in question
                return ((StateBean)state_name1).getName()
                        .compareTo(((StateBean)state_name2).getName());
            }
        });
    }

    public static InputFilter EMOJI_FILTER = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            boolean keepOriginal = true;
            StringBuilder sb = new StringBuilder(end - start);
            for (int index = start; index < end; index++) {
                int type = Character.getType(source.charAt(index));
                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
                    return "";
                }
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        if (dstart == 0)
                            return "";
                    }
                }
                return null;
          /*  char c = source.charAt(index);
            if (isCharAllowed(c))
                sb.append(c);
            else
                keepOriginal = false;*/
            }
            if (keepOriginal)
                return null;
            else {
                if (source instanceof Spanned) {
                    SpannableString sp = new SpannableString(sb);
                    TextUtils.copySpansFrom((Spanned) source, start, sb.length(), null, sp, 0);
                    return sp;
                } else {
                    return sb;
                }
            }
        }
    };



}
