package com.FarmPe.Farmer.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.FarmPe.Farmer.Adapter.FarmerImageAdapter;
import com.FarmPe.Farmer.Adapter.NearByHorizontalAdapter;
import com.FarmPe.Farmer.Adapter.NotificationAdapter;
import com.FarmPe.Farmer.Bean.NearByHorizontalBean;
import com.FarmPe.Farmer.Bean.NotificationBean;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FarmerDetailsFragment extends Fragment {

    public static List<NotificationBean> newOrderBeansList = new ArrayList<>();
    public static List<NearByHorizontalBean> newOrderBeansList_horizontal = new ArrayList<>();
    public static RecyclerView recyclerView,recyclerView_horizontal;
    TextView toolbar_title,farmer_name,farmer_phone,farmer_email,farmer_loc,connect_btn;
    LinearLayout back_feed,farmer_connect;
    RelativeLayout menu;
    CircleImageView prod_img;
    JSONObject lngObject;
    SessionManager session;
    NearByHorizontalAdapter madapter;
    public static NotificationAdapter farmadapter;




    public static FarmerDetailsFragment newInstance() {
        FarmerDetailsFragment fragment = new FarmerDetailsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.farmers_detail_page, container, false);
       // recyclerView=view.findViewById(R.id.recycler_what_looking);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);
        menu=view.findViewById(R.id.menu);
        farmer_name=view.findViewById(R.id.farmer_name);
        farmer_phone=view.findViewById(R.id.phone_no);
        farmer_email=view.findViewById(R.id.email);
        connect_btn=view.findViewById(R.id.connect_btn);
        farmer_loc=view.findViewById(R.id.loc);
        prod_img=view.findViewById(R.id.prod_img);
        farmer_connect=view.findViewById(R.id.farmer_connect);

        session = new SessionManager(getActivity());

        Glide.with(getActivity()).load(FarmerImageAdapter.farmer_image)

                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.avatarmale)
                .into(prod_img);

        farmer_name.setText(FarmerImageAdapter.farmer_name1);
        farmer_phone.setText(FarmerImageAdapter.farmer_phn);
        farmer_email.setText(FarmerImageAdapter.farmer_emil);
        farmer_loc.setText(FarmerImageAdapter.farmer_loc);

        try{


            lngObject = new JSONObject(session.getRegId("language"));


            connect_btn.setText(lngObject.getString("connect"));


        }catch (Exception e){
            e.printStackTrace();
        }




        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("farmer", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        farmer_connect.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("8861326072"));
                //startActivity(callIntent);
                try {
                    startActivity(callIntent);
                   // finish();
                   // Log.i("Finished making a call", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
                }


            }
        });






        return view;
    }



}
