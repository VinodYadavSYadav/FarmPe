package com.renewin.Xohri.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renewin.Xohri.Adapter.Complete_KYC_Adapter;
import com.renewin.Xohri.Bean.Kyc_Bean;
import com.renewin.Xohri.R;
import com.renewin.Xohri.SessionManager;
import com.renewin.Xohri.Urls;
import com.renewin.Xohri.Volly_class.Crop_Post;
import com.renewin.Xohri.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Complete_KYC_Fragment extends Fragment {

    Fragment selectedFragment;
    private RecyclerView recyclerView;
    LinearLayout back_feed;
    SessionManager sessionManager;
    JSONArray kyc_array;

    ArrayList<Kyc_Bean> kyc_beanArrayList = new ArrayList<>();
    Kyc_Bean kyc_bean;
    TextView add_kyc;
    Complete_KYC_Adapter mAdapter;



    public static Complete_KYC_Fragment newInstance() {
        Complete_KYC_Fragment fragment = new Complete_KYC_Fragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kyc_recyc_layout, container, false);

        recyclerView = view.findViewById(R.id.recycler_2);
        add_kyc = view.findViewById(R.id.add_kyc_details);
        back_feed = view.findViewById(R.id.back_feed);
        sessionManager = new SessionManager(getActivity());

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    selectedFragment = My_Account_Fragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();



//                    Intent i=new Intent(getActivity(), Home_Page_WithBottomMenu_Activity.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                  //  i.putExtra("nav_switch","COMPLETE_KYC");
//                    startActivity(i);


                    return true;
                }
                return false;
            }
        });

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                selectedFragment = My_Account_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();


//                Intent i=new Intent(getActivity(), Home_Page_WithBottomMenu_Activity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//             //   i.putExtra("nav_switch","COMPLETE_KYC");
//                startActivity(i);

            }
        });


        add_kyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = Add_KYC_Details_Fragment.newInstance();
                FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
               transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("kyc_details");
                transaction.commit();
            }
        });




        mAdapter = new Complete_KYC_Adapter(kyc_beanArrayList,getActivity());
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        try{

            JSONObject  jsonObject = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));


            Crop_Post.crop_posting(getActivity(), Urls.KYC_Details, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("11111kkkk" + result);
                    try{
                        kyc_beanArrayList.clear();

                        kyc_array = result.getJSONArray("UserKYCDetails");

                        for(int i = 0;i<kyc_array.length();i++){
                            JSONObject jsonObject1 = kyc_array.getJSONObject(i);
                            kyc_bean = new Kyc_Bean(jsonObject1.getString("DocumentType"),jsonObject1.getString("DocumentNumber"),jsonObject1.getString("NameAsPerID"),jsonObject1.getString("Id"));
                            kyc_beanArrayList.add(kyc_bean);

                        }

                        mAdapter.notifyDataSetChanged();


                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });



        }catch (Exception e){
            e.printStackTrace();
        }



        return view;
    }
}
