package com.FarmPe.India.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.India.R;
import com.FarmPe.India.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

public class DashboardFragment extends Fragment {
    boolean doubleBackToExitPressedOnce = false;
    LinearLayout looking_for,farms,farmer;
    View looking_view,farms_view,farmer_view;
    Fragment selectedFragment;
    JSONObject lngObject;
    public static String dashboard_status;
    CoordinatorLayout coordinate_layout;

    SessionManager sessionManager;

    TextView farmer1,farms1,looking_for1;
  public static NestedScrollView scrollView;
  public static String looking_for_type="LookingFor";


    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_main, container, false);
        looking_for=view.findViewById(R.id.looking_for);
        farms=view.findViewById(R.id.farms);
        farmer=view.findViewById(R.id.farmer);
        looking_view=view.findViewById(R.id.looking_view);
        farms_view=view.findViewById(R.id.farms_view);

       // farm1=view.findViewById(R.id.farms_view);
        farmer_view=view.findViewById(R.id.farmer_view);
        looking_for1 = view.findViewById(R.id.looking_for1);
        coordinate_layout = view.findViewById(R.id.coordinate_layout);
        farms1 = view.findViewById(R.id.farms1);
        farmer1 = view.findViewById(R.id.farmer1);
        sessionManager = new SessionManager(getActivity());

//
//        looking_for1=view.findViewById(R.id.looking_for1);
//        farm1=view.findViewById(R.id.farm1);
//        farmer1=view.findViewById(R.id.farmer1);
//
        final Bundle bundle=new Bundle();
       scrollView=view.findViewById(R.id.scroll);

        farms_view.setVisibility(View.INVISIBLE);
        farmer_view.setVisibility(View.INVISIBLE);
        farms1.setTextColor(Color.parseColor("#8c8c8c"));
        farmer1.setTextColor(Color.parseColor("#8c8c8c"));


     /*  scrollView.post(new Runnable() {

            @Override
            public void run() {
               scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });*/

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                   /* FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);*/

                    if (doubleBackToExitPressedOnce) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                        startActivity(intent);
                        getActivity().
                                finish();
                        System.exit(0);
                    }

                    doubleBackToExitPressedOnce = true;
                    Snackbar snackbar = Snackbar
                            .make(coordinate_layout, "Please click BACK again to exit", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    snackbar.show();
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce = false;
                        }
                    }, 3000);

                }
                return true;
            }

        });

        selectedFragment = LookingForFragment.newInstance();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_dashboard, selectedFragment);
        transaction.commit();

        if (InvitationConnectionFragment.farms==null&&InvitationConnectionFarmsFragment.farmer_farms==null&&InvitationConnectionFarmerFragment.farmer_farmer==null){
            farms_view.setVisibility(View.INVISIBLE);
            farmer_view.setVisibility(View.INVISIBLE);
            looking_view.setVisibility(View.VISIBLE);
            selectedFragment = LookingForFragment.newInstance();
            FragmentTransaction transaction1 = getActivity().getSupportFragmentManager().beginTransaction();
            transaction1.replace(R.id.frame_dashboard, selectedFragment);
            transaction1.commit();
        }else if (InvitationConnectionFragment.farms==null&&InvitationConnectionFarmsFragment.farmer_farms==null&&InvitationConnectionFarmerFragment.farmer_farmer.equals("farmer_detail")){
            farms_view.setVisibility(View.INVISIBLE);
            looking_view.setVisibility(View.INVISIBLE);
            farmer_view.setVisibility(View.VISIBLE);
            selectedFragment = FarmerFragment.newInstance();
            FragmentTransaction transaction2 = getActivity().getSupportFragmentManager().beginTransaction();
            transaction2.replace(R.id.frame_dashboard, selectedFragment);
            transaction2.commit();
        }
        else if (InvitationConnectionFarmsFragment.farmer_farms==null&&InvitationConnectionFarmerFragment.farmer_farmer==null&&InvitationConnectionFragment.farms.equals("farm_back")){
            farms_view.setVisibility(View.INVISIBLE);
            farmer_view.setVisibility(View.INVISIBLE);
            looking_view.setVisibility(View.VISIBLE);
            selectedFragment = LookingForFragment.newInstance();
            FragmentTransaction transaction3 = getActivity().getSupportFragmentManager().beginTransaction();
            transaction3.replace(R.id.frame_dashboard, selectedFragment);
            transaction3.commit();
        }else if (InvitationConnectionFragment.farms==null&&InvitationConnectionFarmerFragment.farmer_farmer==null&&InvitationConnectionFarmsFragment.farmer_farms.equals("farms_detail")){
            looking_view.setVisibility(View.INVISIBLE);
            farmer_view.setVisibility(View.INVISIBLE);
            farms_view.setVisibility(View.VISIBLE);
            selectedFragment = FarmsHomePageFragment.newInstance();
            FragmentTransaction transaction3 = getActivity().getSupportFragmentManager().beginTransaction();
            transaction3.replace(R.id.frame_dashboard, selectedFragment);
            transaction3.commit();
        }else{
            farms_view.setVisibility(View.INVISIBLE);
            farmer_view.setVisibility(View.INVISIBLE);
            looking_view.setVisibility(View.VISIBLE);
            selectedFragment = LookingForFragment.newInstance();
            FragmentTransaction transaction4 = getActivity().getSupportFragmentManager().beginTransaction();
            transaction4.replace(R.id.frame_dashboard, selectedFragment);
            transaction4.commit();
        }


        try {

            lngObject = new JSONObject(sessionManager.getRegId("language"));

            looking_for1.setText(lngObject.getString("Lookingfor"));
            farms1.setText(lngObject.getString("Farms"));
            farmer1.setText(lngObject.getString("Farmer"));



        } catch (JSONException e) {
            e.printStackTrace();
        }



        looking_for.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                looking_for_type="LookingFor";
                farms_view.setVisibility(View.INVISIBLE);
                farmer_view.setVisibility(View.INVISIBLE);
                looking_view.setVisibility(View.VISIBLE);


                farms1.setTextColor(Color.parseColor("#8c8c8c"));
                farmer1.setTextColor(Color.parseColor("#8c8c8c"));
                looking_for1.setTextColor(Color.parseColor("#ffffff"));


                selectedFragment = LookingForFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_dashboard, selectedFragment);
                transaction.commit();
            }
        });

        farms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                looking_for_type="Farms";

                looking_view.setVisibility(View.INVISIBLE);
                farmer_view.setVisibility(View.INVISIBLE);
                farms_view.setVisibility(View.VISIBLE);
                looking_for1.setTextColor(Color.parseColor("#8c8c8c"));
                farmer1.setTextColor(Color.parseColor("#8c8c8c"));
                farms1.setTextColor(Color.parseColor("#ffffff"));



                selectedFragment = FarmsHomePageFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_dashboard, selectedFragment);
                transaction.commit();
            }
        });

        farmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                looking_for_type="Farmer";

                farms_view.setVisibility(View.INVISIBLE);
                looking_view.setVisibility(View.INVISIBLE);
                farmer_view.setVisibility(View.VISIBLE);
                farms1.setTextColor(Color.parseColor("#8c8c8c"));
                looking_for1.setTextColor(Color.parseColor("#8c8c8c"));
                farmer1.setTextColor(Color.parseColor("#ffffff"));

                selectedFragment = FarmerFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_dashboard, selectedFragment);
                transaction.commit();
            }
        });

        return view;
    }



}
