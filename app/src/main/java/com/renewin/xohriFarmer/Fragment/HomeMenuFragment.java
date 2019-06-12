
package com.renewin.xohriFarmer.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.renewin.xohriFarmer.R;
import com.renewin.xohriFarmer.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;


public class HomeMenuFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{
Fragment selectedFragment;
    public static DrawerLayout drawer;
    ImageView cart_icon;
    LinearLayout menu,prof_tab;
    SessionManager sessionManager;
    String userid;
    TextView home,shop_cat,todaysdeal,my_orders,wish_list,cart,account,wallet,sell_on_xohri,help_center,notification;
    public static TextView cart_count_text,user_name_menu;
static boolean fragloaded;

static Fragment myloadingfragment;

    public static HomeMenuFragment newInstance() {
        fragloaded =true;
        HomeMenuFragment fragment = new HomeMenuFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_navigation_menu_home, container, false);

        menu=view.findViewById(R.id.menu);
        home=view.findViewById(R.id.home);
        shop_cat=view.findViewById(R.id.shop_cat);
        account = view.findViewById(R.id.account);
       /* shop_cat=view.findViewById(R.id.shop_cat);
        todaysdeal=view.findViewById(R.id.todaydeal);
        my_orders=view.findViewById(R.id.my_orders);
        wish_list=view.findViewById(R.id.wish_list);
        cart=view.findViewById(R.id.cart);*/
      //  cart_icon=view.findViewById(R.id.cart_icon);
       /* account=view.findViewById(R.id.account);
        wallet=view.findViewById(R.id.wallet);*/
       // cart_count_text=view.findViewById(R.id.cart_count);
        user_name_menu=view.findViewById(R.id.user_name_menu);
       /* prof_tab=view.findViewById(R.id.prof_tab);
        sell_on_xohri=view.findViewById(R.id.sell_on_xohri);
        help_center=view.findViewById(R.id.help_center);
        notification=view.findViewById(R.id.notification);
*/
        sessionManager = new SessionManager(getActivity());
        userid=sessionManager.getRegId("userId");

        user_name_menu.setText("Hello, "+sessionManager.getRegId("name"));
        drawer = (DrawerLayout)view.findViewById(R.id.drawer_layout);

        System.out.println("lajfdhsjkd");


        NavigationView navigationView = (NavigationView)view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        System.out.println("hhhrtryur");

        selectedFragment = HomeFragment.newInstance();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_home, selectedFragment);
        transaction.commit();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer = (DrawerLayout)view.findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedFragment = HomeFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout_home, selectedFragment);
                        transaction.commit();
                        drawer.closeDrawers();
                    }
                });


                account.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedFragment = My_Account_Fragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout_home, selectedFragment);
                        transaction.commit();
                        drawer.closeDrawers();

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

