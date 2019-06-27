package com.renewin.FarmPeFarmer.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renewin.FarmPeFarmer.Bean.FarmsImageBean;
import com.renewin.FarmPeFarmer.R;
import com.renewin.FarmPeFarmer.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NotificationSettingFragment extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    LinearLayout back_feed,logout_layout;
    Fragment selectedFragment;
    SessionManager sessionManager;
    TextView notificatn_set,accountinfo;
    JSONObject lngObject;
    public static NotificationSettingFragment newInstance() {
        NotificationSettingFragment fragment = new NotificationSettingFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.noti_setting_layout, container, false);
        back_feed=view.findViewById(R.id.back_feed);
        logout_layout=view.findViewById(R.id.logout_layout);
        accountinfo=view.findViewById(R.id.actninfo);
        notificatn_set=view.findViewById(R.id.toolbar_title);

        sessionManager = new SessionManager(getActivity());

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
       /* view.setFocusableInTouchMode(true);
        view.requestFocus(View.FOCUS_UP);
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("brand", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                }
                return false;
            }
        });
*/

        try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));
            notificatn_set.setText(lngObject.getString("NotificationSetting"));
            accountinfo.setText(lngObject.getString("AccountInfo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }





        return view;
    }



}
