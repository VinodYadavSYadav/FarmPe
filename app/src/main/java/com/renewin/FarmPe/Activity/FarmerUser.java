package com.renewin.FarmPe.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;


public class FarmerUser extends AppCompatActivity {

   LinearLayout back_thank_u;
   TextView thanktu_submit,otp_text;
   EditText enter_otp;
    public  static String otp_get_text,sessionId;
    LinearLayout farmer_layout,user_layout;
    private Context mContext= FarmerUser.this;
    private static final int REQUEST=1;
   BroadcastReceiver receiver;
    SessionManager sessionManager;

    @Override
    public void onResume() {
       // LocalBroadcastManager.getInstance(FarmerUser.this).registerReceiver(receiver, new IntentFilter("otp_forgot"));
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_or_user);
        farmer_layout=findViewById(R.id.farmer_layout);
        user_layout=findViewById(R.id.user_layout);


        farmer_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FarmerUser.this,FarmerActivity.class);
                startActivity(intent);
            }
        });

        user_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FarmerUser.this,LandingPageActivity.class);
                startActivity(intent);
            }
        });

    }


}
