package com.renewin.FarmPeFarmer.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.renewin.FarmPeFarmer.Fragment.LoginFragment;
import com.renewin.FarmPeFarmer.R;
import com.renewin.FarmPeFarmer.SessionManager;

import org.json.JSONObject;


public class XLoginNew extends AppCompatActivity {
    TextView register, log_in, change_lang,login_btn,create_account;
    EditText mobile_no, pass;
    public static String mobile;
    public String status;
    boolean doubleBackToExitPressedOnce = false;
    public static TabLayout tabLayout;
    private ViewPager viewPager;
    SessionManager sessionManager;
    JSONObject lngObject;
    TextView or;
    LinearLayout linearLayout;
    Fragment selectedFragment;
    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("loginonStart");
        sessionManager = new SessionManager(getApplicationContext()); //check
        sessionManager.checkLogin();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
       // log_in = findViewById(R.id.log_in);
        //register = findViewById(R.id.register);
        change_lang = findViewById(R.id.change_lang);
        linearLayout = findViewById(R.id.main_layout);
        login_btn = findViewById(R.id.login_btn);
        create_account = findViewById(R.id.create_account);
        //or = findViewById(R.id.or);

        setupUI(linearLayout);

        sessionManager=new SessionManager(getApplicationContext());
        sessionManager.getRegId("langdetails");

        change_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(XLoginNew.this, SelectLanguageActivity.class);
                startActivity(intent);
            }
        });

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XLoginNew.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent=new Intent(XLoginNew.this, LoginActivity.class);
                startActivity(intent);*/

                selectedFragment = LoginFragment.newInstance();
                FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_full, selectedFragment);
                transaction.addToBackStack("xlogin");
                transaction.commit();
            }
        });


        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
    }


    @Override
    public void onBackPressed() {
        //System.exit(0);
        if (doubleBackToExitPressedOnce) {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            startActivity(intent);
            finish();
            System.exit(0);                    }

        doubleBackToExitPressedOnce = true;
        Toast.makeText(getApplicationContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 3000);


    }




    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(XLoginNew.this);
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
 /*InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);*/

        InputMethodManager inputManager = (InputMethodManager)
                activity.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();

        if (focusedView != null) {

            try{
                assert inputManager != null;
                inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }catch(AssertionError e){
                e.printStackTrace();
            }
        }
    }}