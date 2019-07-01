package com.FarmPe.India.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.FarmPe.India.Fragment.HomeMenuFragment;
import com.FarmPe.India.R;
import com.FarmPe.India.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;


public class LandingPageActivity extends AppCompatActivity {
    public static TextView name,variety,loc,grade,quantity,uom,price,add_cart,prof_name,buy_now;
    Fragment selectedFragment = null;
    public static ImageView cart_img;
    public static BottomSheetBehavior mBottomSheetBehavior6;
    View Profile;
    JSONObject lngObject;
    CoordinatorLayout coordinate_layout;
    SessionManager sessionManager;

    public  static Activity activity;
    public static String toast_click_back;
    boolean doubleBackToExitPressedOnce = false;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        name=findViewById(R.id.selling_item_name);
        coordinate_layout=findViewById(R.id.coordinator);
       // loc=findViewById(R.id.loc);
       // quantity=findViewById(R.id.quantity);
        price=findViewById(R.id.price);
        add_cart=findViewById(R.id.add_cart);
        buy_now=findViewById(R.id.buy_now);
       // prof_name=findViewById(R.id.prof_name);
        cart_img=findViewById(R.id.cart_img);

        sessionManager = new SessionManager(this);
        activity= this;

        System.out.println("landiiiiiing");

      // getWindow().setStatusBarColor(Color.parseColor("#000000"));

        Window window = activity.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(activity,R.color.colorPrimaryDark));
        LandingPageActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);




        try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));


            toast_click_back = lngObject.getString("PleaseclickBACKagaintoexit");



        } catch (JSONException e) {
            e.printStackTrace();
        }




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
        Snackbar snackbar = Snackbar
                .make(coordinate_layout,toast_click_back, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setBackgroundColor(ContextCompat.getColor(LandingPageActivity.this,R.color.orange));
        tv.setTextColor(Color.WHITE);
        snackbar.show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 3000);

    }


}
