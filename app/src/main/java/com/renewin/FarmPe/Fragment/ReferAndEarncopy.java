package com.renewin.FarmPe.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.renewin.FarmPe.Bean.AgriBean;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;
import com.renewin.FarmPe.Urls;
import com.renewin.FarmPe.Volly_class.Crop_Post;
import com.renewin.FarmPe.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONObject;

public class ReferAndEarncopy extends Fragment {
    Fragment selectedFragment;

    LinearLayout back, more, whatsapp, insta, facebook, back_feed, twitter;
    public static String status;
    Intent intent;
    private ArrayAdapter<AgriBean> arrayAdapter;
    private ListView listView;
    String packageName;
    SessionManager sessionManager;

    public static String refer_code;

    TextView editText, wallet_balance,referal_code;
    private Context context;

    public static ReferAndEarncopy newInstance() {
        ReferAndEarncopy fragment = new ReferAndEarncopy();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.refer_n_earn, container, false);
        back_feed=view.findViewById(R.id.back_feed);
        wallet_balance=view.findViewById(R.id.wallet_blnc);
        referal_code=view.findViewById(R.id.refer_code);

        sessionManager=new SessionManager(getActivity());

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    return true;
                }
                return false;
            }
        });


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);


            }
        });

// Wallet Balance
        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));

            Crop_Post.crop_posting(getActivity(), Urls.GetWalletDetails, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("getWalletbalanceeee"+result);

                    try {

                        // wallet_array = result.getJSONObject("getWalletbalanceeee");
                        JSONObject jsonObject = result;

                        String balance = jsonObject.getString("BalanceAmount");

                        wallet_balance.setText(balance);

                        /*for(int i=0;i<wallet_array.length();i++){
                            JSONObject jsonObject1 = wallet_array.getJSONObject(i);
                            wallet_bean = new Wallet_Bean(jsonObject1.getString("AccountId"),jsonObject1.getString("AccountNumber"),jsonObject1.getString("BalanceAmount"),jsonObject1.getString("Points"));
                            wallet_beanArrayList.add(wallet_bean);

                        }*/


                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }





       // Referal Code

        try{

            JSONObject jsonObject = new JSONObject();
            JSONObject postjsonObject = new JSONObject();

            jsonObject.put("Id", sessionManager.getRegId("userId"));
            //System.out.println("aaaaaaaaaaaaa" +sessionManager.getRegId("userId"));
            postjsonObject.put("objUser",jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.Refferal_Code, postjsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("Refferal_Codeeeeeeeeeeeeeeeeeee" +result);

                    try{

                        JSONObject jsonObject1;
                        jsonObject1 = result.getJSONObject("user");

                        refer_code= jsonObject1.getString("RefferalCode");

                        referal_code.setText(refer_code);

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

