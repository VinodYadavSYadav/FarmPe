package com.renewin.Xohri.Fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.renewin.Xohri.R;


public class Transaction_Filter_Fragment extends Fragment {

    RadioGroup radioGroup,time_radio_group;
    RadioButton all_transaction,successful_transaction,failed_transaction,last_30dys,last_60dys,current_year,pre_year,pre_pre_year;
    TextView apply;
    LinearLayout time_layout;
    LinearLayout back_feed;
    String stat;
    String time_stat;
    Fragment selectedFragment = null;


    public static Transaction_Filter_Fragment newInstance() {
        Transaction_Filter_Fragment fragment = new Transaction_Filter_Fragment();
        return fragment;
    }


    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transaction_filter_layout, container, false);
       // Home_Page_WithBottomMenu_Activity.bottomNavigation.setVisibility(View.GONE);
        radioGroup = view.findViewById(R.id.transaction_type);
        time_radio_group = view.findViewById(R.id.time_radio_group);
        all_transaction = view.findViewById(R.id.all_transactn);
        successful_transaction = view.findViewById(R.id.success_transact);
        failed_transaction = view.findViewById(R.id.failed_transact);
        last_30dys = view.findViewById(R.id.last_30dys);
        last_60dys = view.findViewById(R.id.last_6mnths);
        current_year = view.findViewById(R.id.current_year);
        pre_year = view.findViewById(R.id.pre_year);
        pre_pre_year = view.findViewById(R.id.pre_pre_year);
        time_layout = view.findViewById(R.id.time_layout);
        apply = view.findViewById(R.id.apply);
        back_feed = view.findViewById(R.id.back_feed);

        stat = getArguments().getString("Stat");
        time_stat = getArguments().getString("T_Stat");



       time_OrderSelect();

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    getFragmentManager().popBackStack("transaction1", FragmentManager.POP_BACK_STACK_INCLUSIVE);

//                    selectedFragment = Transaction_Fragment.newInstance();
//                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frame_menu, selectedFragment);
//                    transaction.commit();

                    return true;

                }
                return false;
            }
        });

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack("transaction1", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                selectedFragment = Transaction_Fragment.newInstance();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_menu, selectedFragment);
//                transaction.commit();



            }
        });



        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (stat.equals("successful_transaction")) {

                    selectedFragment = Transaction_Sucessfuli_Fragment.newInstance();
                    selectedFragment.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();

                } else if (stat.equals("failed_transaction")) {
                    selectedFragment = Transaction_Failed_Fragment.newInstance();
                    selectedFragment.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();

                }else {

                   // Bundle bundle = new Bundle();
                    bundle.putString("Stat", stat);
                    bundle.putString("T_Stat", time_stat);
                    selectedFragment = Transaction_Fragment.newInstance();
                    selectedFragment.setArguments(bundle);
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.addToBackStack("transact_change");
                    ft.replace(R.id.frame_layout, selectedFragment);
                    ft.commit();
                }

            }
        });



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton radioButton = group.findViewById(checkedId);

                if (radioButton.getTag().toString().equals("1")) {
                    stat = "all_transaction";

                } else if (radioButton.getTag().toString().equals("2")) {
                    stat = "successful_transaction";

                } else if (radioButton.getTag().toString().equals("3")) {
                     stat = "failed_transaction";

                } else
                    time_layout.setVisibility(View.VISIBLE);

            }

        });


        time_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);

                if(radioButton.getTag().toString().equals("4")){
                    time_stat = "30days";

                   // todate=getCalculatedDate("yyyy-MM-dd", -30);
                }
                else if(radioButton.getTag().toString().equals("5")) {
                    time_stat = "6 months";
                   // todate= getCalculatedMonth("yyyy-MM-dd", 6);


                } else if(radioButton.getTag().toString().equals("6")) {
                   time_stat = "2019";
//                    todatDate ="2019-01-01";
//                    todate="2019-12-31";

                } else if(radioButton.getTag().toString().equals("7")) {
                    time_stat = "2018";



                }  else if(radioButton.getTag().toString().equals("8")) {
                    time_stat = "2017";
                }
            }
        });






        return view;
    }

    private void time_OrderSelect() {

        days_rb();


        if (stat.equals("all_transaction")){
            radioGroup.check(R.id.all_transactn);
            // time_OrderSelect();

        }else if (stat.equals("successful_transaction")){
            radioGroup.check(R.id.success_transact);
            //time_OrderSelect();


        }else if (stat.equals("failed_transaction")){
            radioGroup.check(R.id.failed_transact);
            // time_OrderSelect();


        }
    }

    private void days_rb() {

        if (time_stat.equals("30days")){
            time_radio_group.check(R.id.last_30dys);
           // todate=getCalculatedDate("yyyy-MM-dd", -30);

        }else if (time_stat.equals("6 months")){
            time_radio_group.check(R.id.last_6mnths);
          //  todate= getCalculatedMonth("yyyy-MM-dd", 6);

        }else if (time_stat.equals("2019")){
            time_radio_group.check(R.id.current_year);


        }else if (time_stat.equals("2018")){
            time_radio_group.check(R.id.pre_year);



        }else if (time_stat.equals("2017")){
            time_radio_group.check(R.id.pre_pre_year);

        }



    }


}
