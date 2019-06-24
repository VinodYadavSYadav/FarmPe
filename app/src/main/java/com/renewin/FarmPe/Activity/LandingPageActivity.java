package com.renewin.FarmPe.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.renewin.FarmPe.Fragment.HomeMenuFragment;
import com.renewin.FarmPe.R;


public class LandingPageActivity extends AppCompatActivity {
    public static TextView name,variety,loc,grade,quantity,uom,price,add_cart,prof_name,buy_now;
    Fragment selectedFragment = null;
    public static ImageView cart_img;
    public static BottomSheetBehavior mBottomSheetBehavior6;
    View Profile;
    AHBottomNavigation bottomNavigation;
    public  static Activity activity;
    boolean doubleBackToExitPressedOnce = false;


   @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            startActivity(intent);
           activity.
                    finish();
            System.exit(0);                    }

        doubleBackToExitPressedOnce = true;
        Toast.makeText(activity, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 3000);

    }
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

       getWindow().setStatusBarColor(Color.parseColor("#000000"));

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
