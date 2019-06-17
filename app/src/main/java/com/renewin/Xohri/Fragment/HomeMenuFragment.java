
package com.renewin.Xohri.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.renewin.Xohri.R;
import com.renewin.Xohri.SessionManager;


public class HomeMenuFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{
Fragment selectedFragment;
    public static DrawerLayout drawer;
    ImageView plus_sign_add;
    RelativeLayout menu,prof_tab;
    SessionManager sessionManager;
    String userid;
    TextView home,shop_cat,map,looking_for,farms,farmer,account,wallet,sell_on_xohri,help_center,notification;
    public static TextView cart_count_text,user_name_menu;
    View looking_view,farms_view,farmer_view;
static boolean fragloaded;

static Fragment myloadingfragment;
public static NestedScrollView scrollView;
    boolean doubleBackToExitPressedOnce = false;


    public static HomeMenuFragment newInstance() {
        fragloaded =true;
        HomeMenuFragment fragment = new HomeMenuFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_navigation_menu_home, container, false);

        menu=view.findViewById(R.id.menu);
       //scrollView=view.findViewById(R.id.scroll);
        home=view.findViewById(R.id.home);
        shop_cat=view.findViewById(R.id.shop_cat);
        map=view.findViewById(R.id.map);
       /* looking_for=view.findViewById(R.id.looking_for);
        farms=view.findViewById(R.id.farms);
        farmer=view.findViewById(R.id.farmer);*/
        looking_view=view.findViewById(R.id.looking_view);
        farms_view=view.findViewById(R.id.farms_view);
        farmer_view=view.findViewById(R.id.farmer_view);
        plus_sign_add=view.findViewById(R.id.plus_sign_add);
        user_name_menu=view.findViewById(R.id.user_name_menu);
        account=view.findViewById(R.id.account);
        sessionManager = new SessionManager(getActivity());
        userid=sessionManager.getRegId("userId");

        user_name_menu.setText("Hello, "+sessionManager.getRegId("name"));
        drawer = (DrawerLayout)view.findViewById(R.id.drawer_layout);

        System.out.println("lajfdhsjkd");

       // scrollView.requestFocus(View.FOCUS_UP);

        NavigationView navigationView = (NavigationView)view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        System.out.println("hhhrtryur");

        selectedFragment = DashboardFragment.newInstance();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.first_full_frame, selectedFragment);
        transaction.commit();

        plus_sign_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = AddFirstFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("looking");
                transaction.commit();
            }
        });


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer = (DrawerLayout)view.findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedFragment = DashboardFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.first_full_frame, selectedFragment);
                        transaction.commit();
                        drawer.closeDrawers();
                        DashboardFragment.scrollView.post(new Runnable() {

                            @Override
                            public void run() {
                                DashboardFragment.scrollView.fullScroll(ScrollView.FOCUS_UP);
                            }
                        });
                    }
                });

                map.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        selectedFragment = HomeFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.addToBackStack("map");
                        transaction.commit();
                        drawer.closeDrawers();
                    }
                });

                account.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*selectedFragment = My_Account_Fragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.first_full_frame, selectedFragment);
                        transaction.commit();
                        drawer.closeDrawers();*/
                    }
                });
                shop_cat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawers();
                        final TextView yes1,no1;
                        final LinearLayout close_layout;
                        System.out.println("aaaaaaaaaaaa");
                        final Dialog dialog = new Dialog(getContext());
                        dialog.setContentView(R.layout.logout_layout);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.setCancelable(false);


                        no1 =  dialog.findViewById(R.id.no_1);
                        close_layout =  dialog.findViewById(R.id.close_layout);
                        no1.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        close_layout.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        yes1 =  dialog.findViewById(R.id.yes_1);
                        yes1.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                sessionManager.logoutUser();
                                getActivity().finish();

                                dialog.dismiss();
                            }
                        });


                        dialog.show();

                    }
                });


            }
        });





        return view;
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}

