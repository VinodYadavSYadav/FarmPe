
package com.renewin.FarmPe.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.renewin.FarmPe.Activity.LandingPageActivity;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;


public class HomeMenuFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{
Fragment selectedFragment;
    public static DrawerLayout drawer;
    ImageView plus_sign_add;
    RelativeLayout menu,prof_tab;
    LinearLayout update_acc_layout,near_by;
    SessionManager sessionManager;
    String userid;
    TextView home,shop_cat,map,settings,farms,farmer,account,wallet,sell_on_xohri,help_center,notification;
    public static TextView cart_count_text,user_name_menu;
    View looking_view,farms_view,farmer_view;
    RelativeLayout notification_bell;
static boolean fragloaded;

static Fragment myloadingfragment;
public static NestedScrollView scrollView;
    boolean doubleBackToExitPressedOnce = false;


    public static HomeMenuFragment newInstance() {
        fragloaded =true;
        HomeMenuFragment fragment = new HomeMenuFragment();
        return fragment;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_navigation_menu_home, container, false);

        menu=view.findViewById(R.id.menu);
       //scrollView=view.findViewById(R.id.scroll);
        home=view.findViewById(R.id.home);
        //map=view.findViewById(R.id.map);
        update_acc_layout=view.findViewById(R.id.update_acc_layout);
        notification_bell=view.findViewById(R.id.notification_bell);
        settings=view.findViewById(R.id.settings);
       /* looking_for=view.findViewById(R.id.looking_for);
        farms=view.findViewById(R.id.farms);
        farmer=view.findViewById(R.id.farmer);*/
        looking_view=view.findViewById(R.id.looking_view);
        farms_view=view.findViewById(R.id.farms_view);
        farmer_view=view.findViewById(R.id.farmer_view);
        plus_sign_add=view.findViewById(R.id.plus_sign_add);
        user_name_menu=view.findViewById(R.id.user_name_menu);
        near_by=view.findViewById(R.id.near_by);
        sessionManager = new SessionManager(getActivity());
        userid=sessionManager.getRegId("userId");

        //user_name_menu.setText(sessionManager.getRegId("name"));
        user_name_menu.setText("Jagdish Kumar");
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
                selectedFragment = AddFirstLookingFor.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("looking");
                transaction.commit();
            }
        });

        notification_bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = NotificationFragment.newInstance();
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
                        transaction.addToBackStack("home_menu");
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

               /* update_acc_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedFragment = UpdateAccDetailsFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                       // transaction.addToBackStack("looking");
                        transaction.commit();
                        drawer.closeDrawers();
                    }
                });*/


                settings.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedFragment = SettingFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        drawer.closeDrawers();
                        transaction.addToBackStack("home");
                    }
                });

                near_by.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedFragment = NearByFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.first_full_frame, selectedFragment);
                        transaction.commit();
                        drawer.closeDrawers();
                        /*NearByFragment.scrollView.post(new Runnable() {

                            @Override
                            public void run() {
                                NearByFragment.scrollView.fullScroll(ScrollView.FOCUS_UP);
                            }
                        });*/

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

