package com.FarmPe.Farmer.Fragment;



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


import com.FarmPe.Farmer.Bean.AgriBean;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

public class HelpandSupportFragment extends Fragment {
    Fragment selectedFragment;
    TextView first_text, second_text;
    LinearLayout back, more, whatsapp, insta, facebook, back_feed, twitter;
    public static String status;
    Intent intent;
    private ArrayAdapter<AgriBean> arrayAdapter;
    private ListView listView;
    String packageName;
    SessionManager sessionManager;
    public static String refer_code;
    JSONObject lngObject;
    TextView editText,privacypolicytxt,privacypolicytxt1,second_textxt,privacypolicytxt2,second_tx,privacypolicytxt3,second_t;
    private Context context;

    public static HelpandSupportFragment newInstance() {
        HelpandSupportFragment fragment = new HelpandSupportFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.privacy_policy, container, false);
        back_feed=view.findViewById(R.id.back_feed);
        privacypolicytxt=view.findViewById(R.id.toolbar_title);
        privacypolicytxt.setText("Help & Support");
        first_text=view.findViewById(R.id.first_text);
        first_text.setText("Signing Up and Getting Started");
        second_text=view.findViewById(R.id.second_textt);
        second_textxt=view.findViewById(R.id.second_text);
        privacypolicytxt1=view.findViewById(R.id.txt);
        privacypolicytxt1.setText("Settings and logout");
        privacypolicytxt2=view.findViewById(R.id.txtt);
        privacypolicytxt2.setText("Features and Usage");
        privacypolicytxt3=view.findViewById(R.id.tx);
        privacypolicytxt3.setText("Logout");
        second_t=view.findViewById(R.id.second_te);
        second_t.setText("To logout from FarmPe, click on settings and select 'Logout' from the list and tap 'Yes' on ‘Are You sure?’ dialogue box.");
        second_tx=view.findViewById(R.id.second_tex);
        second_tx.setText("1. Looking for: Brand-wise, Model-wise and HP-wise information of Tractors and tools are available. You can request for quotation on finger-tip." +
                "\n" +
                "2. Farms: Farms information such as Dairy farm, Poultry farm and Goat farms are available. User can connect with respective farms with a click on 'Connect'" +
                "\n" +
                "3. Farmer: Various farmers information are available and user can connect to farmers. To connect click on connect which results with 'Send Connection Request' page, verify details and tap on connect.");
        sessionManager = new SessionManager(getActivity());
        second_text.setText("1. Download the FarmPe app from the App store (iPhone) or Google Play Store (Android). Once the app is installed, tap to open it."+
                "\n2. For a new user: Tap Register, enter your Name, Phone number and Password and to continue verify with OTP" +
                "\n3. To sign-in: Enter Mobile Number and Password and tap on Login." +
                "\n4. User can choose the language and proceed further." +
                "\n5. If you've forgotten credentials or no longer access to your FarmPe, need to reset your account info and get OTP, verify the same and regain access to your account.");
        second_textxt.setText("1. Account Info: You can update your account details such as Display Picture, Name, Mobile Number, Mail ID and Password." +
                "\n" +
                "2. To add address: Tap on settings to add your address." +
                "\n" +
                "3. Change language: Tap on settings and select change language to switch to other language.");
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
        try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));
            privacypolicytxt.setText(lngObject.getString("Help_Support"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }
}

