package com.FarmPe.India.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.India.R;
import com.FarmPe.India.ReadSms;
import com.FarmPe.India.SessionManager;
import com.FarmPe.India.SmsListener;

import org.json.JSONException;
import org.json.JSONObject;


public class Thank_U_New extends AppCompatActivity {

   LinearLayout back_thank_u;
   TextView thanktu_submit,otp_text,thank_title;
   EditText enter_otp,otp_forgot_pass;
    JSONObject lngObject;
    public  static String toast_otp,toast_invalid_otp;
    public  static String otp_get_text,sessionId;
    LinearLayout linearLayout;
    private Context mContext=Thank_U_New.this;
    private static final int REQUEST=1;
   BroadcastReceiver receiver;
    SessionManager sessionManager;

    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(Thank_U_New.this).registerReceiver(receiver, new IntentFilter("otp_forgot"));
        super.onResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thank_you_otp);
        linearLayout=findViewById(R.id.main_layout);
        back_thank_u=findViewById(R.id.arrow_thank_u);
        thanktu_submit=findViewById(R.id.thanktu_submit);
        enter_otp=findViewById(R.id.otp_forgot_pass);
        otp_text=findViewById(R.id.thanktu);
        thank_title=findViewById(R.id.thank);


        setupUI(linearLayout);
        sessionId= getIntent().getStringExtra("otp_forgot");
        sessionManager = new SessionManager(this);

      //  sessionManager.getRegId("lng_object");
       // System.out.println("llllllllllll" + sessionManager.getRegId("lng_object"));



        try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));


            thanktu_submit.setText(lngObject.getString("SendOTP"));
            thank_title.setText(lngObject.getString("OneTimePassword"));
            otp_text.setText(lngObject.getString("PleaseentertheOTPbelowtoresetpassword"));
            enter_otp.setHint(lngObject.getString("EntertheOTP"));
            toast_otp = lngObject.getString("EntertheOTP");
            toast_invalid_otp = lngObject.getString("InvalidOTP");

        } catch (JSONException e) {
            e.printStackTrace();
        }


//
//        if (checkAndRequestPermissions()) {
//            // carry on the normal flow, as the case of  permissions  granted.
//        }
//       // next=findViewById(R.id.next);

        back_thank_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Thank_U_New.this,ForgotPasswordNew.class);
                startActivity(intent);
                finish();
            }
        });

        ReadSms.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) { // autofetching
                //ed.setText(messageText);
                System.out.println("autofetch_msg"+messageText);
                enter_otp.setText(messageText);

            }
        });

        thanktu_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp_get_text=enter_otp.getText().toString();
                if (otp_get_text.equals("")){
                    Snackbar snackbar = Snackbar
                            .make(linearLayout,toast_otp, Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(Thank_U_New.this,R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    snackbar.show();

                }else {
                    if (otp_get_text.equals(sessionId)){
                        Intent intent=new Intent(Thank_U_New.this,ResetPasswordNew.class);
                        startActivity(intent);
                    }else{
                        Snackbar snackbar = Snackbar
                                .make(linearLayout,toast_invalid_otp, Snackbar.LENGTH_LONG);
                        View snackbarView = snackbar.getView();
                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                        tv.setBackgroundColor(ContextCompat.getColor(Thank_U_New.this,R.color.orange));
                        tv.setTextColor(Color.WHITE);
                        snackbar.show();
                    }
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        //System.exit(0);

        Intent intent=new Intent(Thank_U_New.this,ForgotPasswordNew.class);
        startActivity(intent);
        finish();
    }


//    private  boolean checkAndRequestPermissions() {
//
//        if (Build.VERSION.SDK_INT >= 23) {
//            String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.READ_SMS};
//            if (!hasPermissions(mContext, PERMISSIONS)) {
//                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST);
//            } else {
//                //do here
//            }
//        } else {
//            //do here
//        }
//        return true;
//    }
//    private static boolean hasPermissions(Context context, String... permissions) {
//        if (context != null && permissions != null) {
//            for (String permission : permissions) {
//                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(Thank_U_New.this);
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
    }


}
