package com.renewin.xohriFarmer.Activity;

import android.app.Activity;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.renewin.xohriFarmer.Fragment.HomeMenuFragment;
import com.renewin.xohriFarmer.R;


public class LandingPageActivity extends AppCompatActivity {
    public static TextView name,variety,loc,grade,quantity,uom,price,add_cart,prof_name,buy_now;
    Fragment selectedFragment = null;
    public static ImageView cart_img;
    public static BottomSheetBehavior mBottomSheetBehavior6;
    View Profile;
    AHBottomNavigation bottomNavigation;
    public  static Activity activity;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        name=findViewById(R.id.selling_item_name);
       // loc=findViewById(R.id.loc);
       // quantity=findViewById(R.id.quantity);
        price=findViewById(R.id.price);
        add_cart=findViewById(R.id.add_cart);
        buy_now=findViewById(R.id.buy_now);
       // prof_name=findViewById(R.id.prof_name);
        cart_img=findViewById(R.id.cart_img);

        activity= this;

        System.out.println("landiiiiiing");

       //getWindow().setStatusBarColor(Color.parseColor("#000000"));

        bottomNavigation = findViewById(R.id.bottom_navigation_land);

        System.out.println("landiiiiiing");

        selectedFragment = HomeMenuFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();

        Profile = findViewById(R.id.profile_view);

        mBottomSheetBehavior6 = BottomSheetBehavior.from(Profile);

        mBottomSheetBehavior6.setPeekHeight(0);


        mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_COLLAPSED);

        mBottomSheetBehavior6.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {

                }
                else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                }
                else if (newState == BottomSheetBehavior.STATE_HIDDEN) {

                }
            }


            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
            }

        });

    }


}
