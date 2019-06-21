package com.renewin.FarmPe.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.renewin.FarmPe.Bean.AgriBean;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;

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

    public static String refer_code;

    EditText feedback_title,feedback_description;
    private Context context;

    public static FeedbackFragment newInstance() {
        FeedbackFragment fragment = new FeedbackFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feedback, container, false);
        back_feed=view.findViewById(R.id.back_feed);
        feedback_title=view.findViewById(R.id.fd_title);
        feedback_description=view.findViewById(R.id.fd_descp);

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

        final InputFilter EMOJI_FILTER = new InputFilter() {

            @Override

            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.:-'';§£¥...";
                for (int index = start; index < end; index++) {
                    int type = Character.getType(source.charAt(index));
                    if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL || type == Character.MATH_SYMBOL || specialChars.contains("" + source)||  Character.isWhitespace(0)) {
                        return "";
                    }
                }
                return null;
            }
        };



        feedback_title.setFilters(new InputFilter[]{EMOJI_FILTER});
        feedback_description.setFilters(new InputFilter[]{EMOJI_FILTER});

        final InputFilter filter1 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                //String filtered = "";
                for (int i = start; i < end; i++) {
                    char character = source.charAt(i);
                    if (Character.isWhitespace(source.charAt(i))) {
                        if (dstart == 0)
                            return "";
                    }
                }
                return null;
            }

        };

        feedback_description.setFilters(new InputFilter[] {filter1,new InputFilter.LengthFilter(100) });
        feedback_title.setFilters(new InputFilter[] {filter1,new InputFilter.LengthFilter(15) });





        return view;
    }
}

