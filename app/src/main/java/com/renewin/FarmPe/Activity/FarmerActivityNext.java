package com.renewin.FarmPe.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renewin.FarmPe.Adapter.AddHpAdapter;
import com.renewin.FarmPe.Bean.FarmsImageBean;
import com.renewin.FarmPe.R;

import java.util.ArrayList;
import java.util.List;


public class FarmerActivityNext extends AppCompatActivity {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static AddHpAdapter farmadapter;
    TextView toolbar_title,submit;
    Fragment selectedFragment;
    LinearLayout back_feed,next_layout;
    CheckBox check_box;
    @Override
    public void onResume() {
       // LocalBroadcastManager.getInstance(FarmerUser.this).registerReceiver(receiver, new IntentFilter("otp_forgot"));
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_request_next);
        back_feed=findViewById(R.id.back_feed);
        toolbar_title=findViewById(R.id.toolbar_title);
      //  check_box=findViewById(R.id.check_box);
        submit=findViewById(R.id.submit);
        toolbar_title.setText("Request for Quotation");

       // check_box.setText("I agree that by clicking 'Request for Tractor Price' button, I am explicitly soliciting a call from Xohri App users on my 'Mobile' in order to assist me with my tractor purchase.");

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FarmerActivityNext.this,FarmerActivity.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FarmerActivityNext.this,LandingPageActivity.class);
                startActivity(intent);
            }
        });



}}