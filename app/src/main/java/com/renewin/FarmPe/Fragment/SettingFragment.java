package com.renewin.FarmPe.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renewin.FarmPe.Bean.FarmsImageBean;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class SettingFragment extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    LinearLayout back_feed,logout_layout,noti_setting,refer_earn,feedback,change_lang,policy,notification,acc_info,your_address;
    Fragment selectedFragment;
    SessionManager sessionManager;


    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_layout, container, false);
        back_feed=view.findViewById(R.id.back_feed);
        logout_layout=view.findViewById(R.id.logout_layout);
        noti_setting=view.findViewById(R.id.noti_setting);
        refer_earn=view.findViewById(R.id.refer_earn);
        feedback=view.findViewById(R.id.feedback);
        change_lang=view.findViewById(R.id.change_lang);
        policy=view.findViewById(R.id.policy);
        notification=view.findViewById(R.id.notification);
        acc_info=view.findViewById(R.id.acc_info);
        your_address=view.findViewById(R.id.your_address);

        sessionManager = new SessionManager(getActivity());

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = HomeMenuFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                // transaction.addToBackStack("looking");
                transaction.commit();

            }
        });


       /* view.setFocusableInTouchMode(true);
        view.requestFocus(View.FOCUS_UP);
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                }
                return false;
            }
        });*/

       logout_layout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final TextView yes1,no1;
               final LinearLayout close_layout;
               System.out.println("aaaaaaaaaaaa");
               final Dialog dialog = new Dialog(getContext());
               dialog.setContentView(R.layout.logout_layout);
               dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
               dialog.setCancelable(false);


               no1 =  dialog.findViewById(R.id.no_1);
               close_layout =  dialog.findViewById(R.id.close_layout);
               no1.setOnClickListener(new View.OnClickListener() {

                   @Override
                   public void onClick(View v) {
                       dialog.dismiss();
                   }
               });
               close_layout.setOnClickListener(new View.OnClickListener() {

                   @Override
                   public void onClick(View v) {
                       dialog.dismiss();
                   }
               });

               yes1 =  dialog.findViewById(R.id.yes_1);
               yes1.setOnClickListener(new View.OnClickListener() {

                   @Override
                   public void onClick(View v) {
                       sessionManager.logoutUser();
                       getActivity().finish();

                       dialog.dismiss();
                   }
               });


               dialog.show();

           }
       });

        acc_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = UpdateAccDetailsFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("setting");
                transaction.commit();
            }
        });

       noti_setting.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               selectedFragment = NotificationSettingFragment.newInstance();
               FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
               transaction.replace(R.id.frame_layout, selectedFragment);
               transaction.addToBackStack("setting");
               transaction.commit();
           }
       });
        refer_earn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = ReferAndEarncopy.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("setting");
                transaction.commit();
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = FeedbackFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("setting");
                transaction.commit();
            }
        });

        your_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = You_Address_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("setting");
                transaction.commit();
            }
        });

        change_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = ChangeLanguageFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("setting");
                transaction.commit();
            }
        });

        policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = PrivacyPolicyFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("setting");
                transaction.commit();
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = NotificationFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("setting");
                transaction.commit();
            }
        });

        return view;
    }



}
