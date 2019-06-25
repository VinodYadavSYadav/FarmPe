package com.renewin.FarmPe.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
import com.renewin.FarmPe.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SettingFragment extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    LinearLayout back_feed,logout_layout,noti_setting,refer_earn,feedback,change_lang,policy,notification,acc_info,your_address;
    Fragment selectedFragment;
    TextView notificatn,change_language,your_addresss,acc_info1,refer_ern,feedbk,help_1,abt_frmpe,polic_1,logot,setting_tittle;
    SessionManager sessionManager;

    JSONObject lngObject;

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
        setting_tittle=view.findViewById(R.id.setting_tittle);
        notification=view.findViewById(R.id.notification);
        acc_info=view.findViewById(R.id.acc_info);
        your_address=view.findViewById(R.id.ur_address);



        notificatn=view.findViewById(R.id.notificatn);
        change_language=view.findViewById(R.id.change_language);
        your_addresss=view.findViewById(R.id.your_addresss);
        acc_info1=view.findViewById(R.id.acc_info1);
        refer_ern=view.findViewById(R.id.refer_ern);
        feedbk=view.findViewById(R.id.feedbk);
        help_1=view.findViewById(R.id.help_1);
        abt_frmpe=view.findViewById(R.id.abt_frmpe);
        polic_1=view.findViewById(R.id.polic_1);
        logot=view.findViewById(R.id.logot);




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


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                //    getFragmentManager().popBackStack("home_menu", android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    selectedFragment = HomeMenuFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();

                    return  true;
                    /*FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack ("home_m", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;*/
                }
                return false;
            }
        });



        try {

            lngObject = new JSONObject(sessionManager.getRegId("language"));

            setting_tittle.setText(lngObject.getString("Settings"));
            acc_info1.setText(lngObject.getString("AccountInfo"));
            your_addresss.setText(lngObject.getString("YourAddress"));
            refer_ern.setText(lngObject.getString("Refer_Earn"));
            change_language.setText(lngObject.getString("ChangeLanguage"));
            polic_1.setText(lngObject.getString("PrivacyPolicy"));

            help_1.setText(lngObject.getString("Help_Support"));
            abt_frmpe.setText(lngObject.getString("AboutFarmPe"));
            feedbk.setText(lngObject.getString("FeedBack"));
            notificatn.setText(lngObject.getString("Notifications"));
            logot.setText(lngObject.getString("Logout"));




        } catch (JSONException e) {
            e.printStackTrace();
        }




       /* view.setFocusableInTouchMode(true);
        view.requestFocus(View.FOCUS_UP);
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                   *//* FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack("home_menu", FragmentManager.POP_BACK_STACK_INCLUSIVE);*//*
                    selectedFragment = HomeMenuFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    // transaction.addToBackStack("looking");
                    transaction.commit();

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
               dialog.setCancelable(true);


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

                Bundle bundle = new Bundle();
                bundle.putString("navigation_from", "setting");
                selectedFragment = NotificationFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.addToBackStack("setting");
                transaction.commit();
            }
        });
        your_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedFragment = You_Address_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("setting");
                transaction.commit();


            }
        });




        return view;
    }



}
