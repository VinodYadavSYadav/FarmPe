package com.renewin.Xohri.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renewin.Xohri.R;


public class PrivacyPoIciesdetail_fragment extends Fragment {



    boolean doubleBackToExitPressedOnce = false;


    public static PrivacyPoIciesdetail_fragment newInstance() {
        PrivacyPoIciesdetail_fragment fragment = new PrivacyPoIciesdetail_fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.privacypolicies_detail_layout, container, false);


/*
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    if (doubleBackToExitPressedOnce) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                        startActivity(intent);
                        getActivity().finish();
                        System.exit(0);                    }

                    doubleBackToExitPressedOnce = true;
                    Toast.makeText(getActivity(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce=false;
                        }
                    }, 3000);


                    return true;
                }
                return false;
            }
        });*/


        return view;


    }



}
