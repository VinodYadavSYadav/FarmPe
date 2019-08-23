package com.FarmPe.India.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.FarmPe.India.R;

public class ConnectionSuccessfulFragment extends Fragment {
    Fragment selectedFragment;
    LinearLayout backfeed;

    public static ConnectionSuccessfulFragment newInstance() {
        ConnectionSuccessfulFragment fragment = new ConnectionSuccessfulFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.successfully_sent_invita, container, false);
        backfeed= view.findViewById(R.id.back_feed);




        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("invitation_back", FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    return true;
                }
                return false;
            }
        });


        backfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (InvitationConnectionFarmsFragment.invitation_status==null&&InvitationConnectionFarmerFragment.invitation_status==null&&InvitationConnectionFragment.invitation_status.equals("looking_invi")){

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("invitation_back", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    InvitationConnectionFragment.invitation_status=null;
                }else if (InvitationConnectionFragment.invitation_status==null&&InvitationConnectionFarmerFragment.invitation_status==null&&InvitationConnectionFarmsFragment.invitation_status.equals("farms_invi")){

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("farms_innnnvi", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    InvitationConnectionFarmsFragment.invitation_status=null;
                }else if (InvitationConnectionFragment.invitation_status==null&&InvitationConnectionFarmsFragment.invitation_status==null&&InvitationConnectionFarmerFragment.invitation_status.equals("farmer_invi")){
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("famer_innvi", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    InvitationConnectionFarmerFragment.invitation_status=null;

                }


            }
        });


        return view;
    }
}

