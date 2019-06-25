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

public class PrivacyPolicyFragment extends Fragment {
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

    TextView editText;
    private Context context;

    public static PrivacyPolicyFragment newInstance() {
        PrivacyPolicyFragment fragment = new PrivacyPolicyFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.privacy_policy, container, false);
        back_feed=view.findViewById(R.id.back_feed);
        first_text=view.findViewById(R.id.first_text);
        second_text=view.findViewById(R.id.second_text);

        first_text.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, " +
                "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat." +
                "quis nostrud exercitation ullamco laboris nisi ut aliquip." +
                "quis nostrud exercitation ullamco laboris nisi ut ." +
                "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");


        second_text.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo" +
                " consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                " Excepteur sint occaecat cupidatat non proident," +
                " sunt in culpa qui officia deserunt mollit anim id est laborum.sunt in culpa qui officia deserunt mollit anim id est laborum." +
                "sunt in culpa qui officia deserunt mollit anim id est laborum." +
                "sunt in culpa qui officia deserunt mollit anim id est laborum.");

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

        return view;
    }
}

