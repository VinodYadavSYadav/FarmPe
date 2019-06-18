package com.renewin.Xohri.Activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.renewin.Xohri.R;
import com.renewin.Xohri.SessionManager;
import com.renewin.Xohri.Volly_class.Login_post;
import com.renewin.Xohri.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;


public class ForgotPasswordNew extends AppCompatActivity {
    TextView forgot_submit, forgot_pass_text, forgt_pass_detail, mob_text_forgot;
    LinearLayout forgot_back;
    public static EditText mobileno;
    SessionManager sessionManager;
    Fragment selectedFragment;
    public static String otp, forgot_mob_no, Message,mob_trim;
    LinearLayout coordinatorLayout;
    int status;
    TextInputLayout emter_pasword;
    EditText spn_localize;
    String localize_text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        forgot_back = findViewById(R.id.back_feed);
        forgot_submit = findViewById(R.id.forgot_submit);
        mobileno = findViewById(R.id.mobile_no);
        coordinatorLayout = findViewById(R.id.linear_login);
        forgot_pass_text = findViewById(R.id.forgot);
    //    spn_localize = findViewById(R.id.spn_localize_forgot);
        //emter_pasword=findViewById(R.id.emter_pasword);
        forgt_pass_detail = findViewById(R.id.tocnt);
        mob_text_forgot = findViewById(R.id.mob_text_forgot);
        setupUI(coordinatorLayout);

        // next=findViewById(R.id.next);
        sessionManager = new SessionManager(ForgotPasswordNew.this);
       // sessionManager.getRegId("lng_object");
        //System.out.println("llllllllllll" + sessionManager.getRegId("lng_object"));
        JSONObject lngObject;
        try {
            lngObject=new JSONObject(sessionManager.getRegId("langdetails"));
            forgt_pass_detail.setText(lngObject.getString("ForgotPasswordText"));
            //emter_pasword.setHint(lngObject.getString("EnterPhoneNo"));
            mob_text_forgot.setHint(lngObject.getString("EnterPhoneNo"));
            forgot_pass_text.setText(lngObject.getString("ForgotPassword") + " ?");
            forgot_submit.setText(lngObject.getString("ResetPassword"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        coordinatorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
        forgot_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordNew.this, LoginActivity.class);
                startActivity(intent);

            }
        });
        forgot_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgot_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mobileno.getText().toString().equals("")) {
                            Toast.makeText(ForgotPasswordNew.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                        } else if (mobileno.length() <= 9) {
                            //  mobile.setError("Please enter 10 digits mobile number");
                            Snackbar snackbar = Snackbar
                                    .make(coordinatorLayout, "Please enter 10 digits mobile number", Snackbar.LENGTH_LONG);
                            //snackbar.setActionTextColor(R.color.colorAccent);
                            View snackbarView = snackbar.getView();
                            TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                            tv.setTextColor(Color.RED);
                            snackbar.show();
                        } else {
                            try {
                                localize_text="+91";
                                JSONObject postjsonObject = new JSONObject();
                                postjsonObject.put("UserName", localize_text + mobileno.getText().toString());
                                System.out.println("aaaaaaaaaaaa" + postjsonObject);
                                Login_post.Forgot_Passsword(ForgotPasswordNew.this, postjsonObject, new VoleyJsonObjectCallback() {
                                    @Override
                                    public void onSuccessResponse(JSONObject result) {
                                        System.out.println("nnnnnmnm" + result.toString());
                                        try {
                                            // System.out.println("nnnnnmnm" + result.toString());
                                            otp = result.getString("OTP");
                                            forgot_mob_no = result.getString("UserName");
                                            mob_trim=forgot_mob_no.substring(3);
                                            Message = result.getString("Message");
                                            status= result.getInt("Status");
                                            if(status==0){
                                                Snackbar snackbar = Snackbar
                                                        .make(coordinatorLayout, "Your number is not register", Snackbar.LENGTH_LONG);
                                                //snackbar.setActionTextColor(R.color.colorAccent);
                                                View snackbarView = snackbar.getView();
                                                TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                                tv.setTextColor(Color.RED);
                                                snackbar.show();
                                            }else if (status==2){
                                                Snackbar snackbar = Snackbar
                                                        .make(coordinatorLayout, result.getString("Message"), Snackbar.LENGTH_LONG);
                                                //snackbar.setActionTextColor(R.color.colorAccent);
                                                View snackbarView = snackbar.getView();
                                                TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                                tv.setTextColor(Color.RED);
                                                snackbar.show();
                                            }
                                            else{
                                                Toast.makeText(ForgotPasswordNew.this,Message,Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(ForgotPasswordNew.this, Thank_U_New.class);
                                                intent.putExtra("otp_forgot", otp);
                                                //intent.putExtra("forgot_mob_no", forgot_mob_no);
                                                startActivity(intent);
                                            }
                                            System.out.println("for_ottpp" + otp);
                                           /* if (Message.equals("You have Exceeded the limit of resending OTP")) {
                                                Snackbar snackbar = Snackbar
                                                        .make(coordinatorLayout, "You have Exceeded the limit of resending OTP,Please wait for 24 hours to get OTP", Snackbar.LENGTH_LONG);
                                                //snackbar.setActionTextColor(R.color.colorAccent);
                                                View snackbarView = snackbar.getView();
                                                TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                                tv.setTextColor(Color.RED);
                                                snackbar.show();
                                            } else {
                                                Intent intent = new Intent(ForgotPassword.this, Thank_U.class);
                                                intent.putExtra("otp_forgot", otp);
                                                //intent.putExtra("forgot_mob_no", forgot_mob_no);
                                                startActivity(intent);
                                            }*/

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
                });

            }

        });





    }

    @Override
    public void onBackPressed() {
        //System.exit(0);

        Intent intent=new Intent(ForgotPasswordNew.this,LoginActivity.class);
        startActivity(intent);
    }


    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(ForgotPasswordNew.this);
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

    public static void hideSoftKeyboard(Activity activity)  {
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
    }

}