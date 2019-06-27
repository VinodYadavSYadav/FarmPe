package com.renewin.FarmPeFarmer.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.renewin.FarmPeFarmer.Bean.AgriBean;
import com.renewin.FarmPeFarmer.R;
import com.renewin.FarmPeFarmer.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

public class FeedbackFragment extends Fragment {
    Fragment selectedFragment;
    TextView send_otp, referal_code;
    LinearLayout back, more, whatsapp, insta, facebook, back_feed, twitter;
    public static String status;
    Intent intent;
    private ArrayAdapter<AgriBean> arrayAdapter;
    private ListView listView;
    String packageName;
    SessionManager sessionManager;
JSONObject lngObject;
    public static String refer_code;

    TextView editText,feedbacktxt,clickcopyurltxt;
    private Context context;

    public static FeedbackFragment newInstance() {
        FeedbackFragment fragment = new FeedbackFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feedback, container, false);
        back_feed=view.findViewById(R.id.back_feed);
        feedbacktxt=view.findViewById(R.id.toolbar_title);
        clickcopyurltxt=view.findViewById(R.id.clickcopyurl);
        sessionManager = new SessionManager(getActivity());
       /* view.setFocusableInTouchMode(true);
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
        });*/


        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);


            }
        });
        try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));
            feedbacktxt.setText(lngObject.getString("FeedBack"));
            clickcopyurltxt.setText(lngObject.getString("ClickHeretoCopyURL"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}

