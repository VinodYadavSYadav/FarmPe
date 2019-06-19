package com.renewin.Xohri.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.renewin.Xohri.Bean.Wallet_Bean;
import com.renewin.Xohri.R;
import com.renewin.Xohri.SessionManager;
import com.renewin.Xohri.Urls;
import com.renewin.Xohri.Volly_class.Crop_Post;
import com.renewin.Xohri.Volly_class.VoleyJsonObjectCallback;
import org.json.JSONObject;
import java.util.ArrayList;



public class WalletFragment extends Fragment {

    Fragment selectedFragment;
    LinearLayout back_feed;
    TextView recharge,wallet_balance;
   ArrayList<Wallet_Bean> wallet_beanArrayList = new ArrayList<>();


    SessionManager sessionManager;
    JSONObject wallet_array;
    Wallet_Bean wallet_bean;


    public static WalletFragment newInstance() {
        WalletFragment fragment = new WalletFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wallet, container, false);

        //getActivity().getActionBar().hide();


        back_feed= view.findViewById(R.id.back_feed);
        wallet_balance= view.findViewById(R.id.w_balance);

        sessionManager = new SessionManager(getActivity());

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("menu", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });

//        recharge=view.findViewById(R.id.recharge);
////        recharge.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                recharge.setVisibility(View.INVISIBLE);
////                selectedFragment = RechargeFragment.newInstance();
////                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
////                transaction.replace(R.id.frame_layout2, selectedFragment);
////                // transaction.addToBackStack("home");
////                transaction.commit();
////            }
////        });

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = My_Account_Fragment.newInstance();
                FragmentTransaction transaction4 = getActivity().getSupportFragmentManager().beginTransaction();
                transaction4.replace(R.id.frame_layout_home, selectedFragment);
                transaction4.commit();
            }
        });

        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserId",sessionManager.getRegId("userId"));

            Crop_Post.crop_posting(getActivity(), Urls.GetWalletBalance, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("getWalletbalanceeee"+result);

                    try {

                       // wallet_array = result.getJSONObject("getWalletbalanceeee");
                        JSONObject jsonObject = result;
                        String acc_no = jsonObject.getString("AccountNumber");
                        String id = jsonObject.getString("AccountId");
                        String balance = jsonObject.getString("BalanceAmount");
                        String points = jsonObject.getString("Points");

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




        return view;
    }
}
