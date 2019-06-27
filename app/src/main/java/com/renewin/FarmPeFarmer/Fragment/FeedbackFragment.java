package com.renewin.FarmPeFarmer.Fragment;



import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.renewin.FarmPeFarmer.Bean.AgriBean;
import com.renewin.FarmPeFarmer.R;
import com.renewin.FarmPeFarmer.SessionManager;
import com.renewin.FarmPeFarmer.Urls;
import com.renewin.FarmPeFarmer.Volly_class.Crop_Post;
import com.renewin.FarmPeFarmer.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class FeedbackFragment extends Fragment {
    Fragment selectedFragment;
    TextView send_otp, referal_code,feedback_type,submit,feedbacktxt;;
    LinearLayout back, more, whatsapp, insta, facebook, back_feed, twitter;
    public static String status,message;
    Intent intent;
    private ArrayAdapter<AgriBean> arrayAdapter;
    private ListView listView;
    String packageName;
    SessionManager sessionManager;
    EditText feedback_title,feedback_description;
    JSONObject lngObject;

    public static String refer_code;


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
        feedback_title.setFilters(new InputFilter[]{EMOJI_FILTER});
        feedback_description=view.findViewById(R.id.fd_descp);
        feedback_description.setFilters(new InputFilter[]{EMOJI_FILTER});
        feedback_type=view.findViewById(R.id.fd_type);
        submit=view.findViewById(R.id.submit);

        feedbacktxt=view.findViewById(R.id.toolbar_title);
        //clickcopyurltxt=view.findViewById(R.id.clickcopyurl);
        sessionManager = new SessionManager(getActivity());







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

        feedback_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.feedback_title_popup);
                final TextView tractor = (TextView) dialog.findViewById(R.id.tractor);
                final TextView bus = (TextView) dialog.findViewById(R.id.bus);
                final TextView truck = (TextView)dialog.findViewById(R.id.truck) ;
                final TextView car = (TextView)dialog.findViewById(R.id.car) ;
                final TextView others = (TextView)dialog.findViewById(R.id.othrs) ;
                ImageView image = (ImageView) dialog.findViewById(R.id.close_popup);

                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                tractor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        feedback_type.setText(tractor.getText().toString());
                        // gettingAddress("Home");
                        dialog.dismiss();
                    }
                });

                bus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        feedback_type.setText(bus.getText().toString());
                        dialog.dismiss();
                        //   gettingAddress("Barn");

                    }
                });

                truck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        feedback_type.setText(truck.getText().toString());
                        dialog.dismiss();
                        //  gettingAddress("Warehouse");


                    }
                });

                car.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        feedback_type.setText(car.getText().toString());
                        dialog.dismiss();

                        //  gettingAddress("Farm");


                    }
                });


                others.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        feedback_type.setText(others.getText().toString());
                        dialog.dismiss();
                        // gettingAddress("Others");


                    }
                });

                dialog.show();


            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(feedback_type.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Select Feedback Type", Toast.LENGTH_SHORT).show();


                }else if(feedback_title.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Enter Feedback Title", Toast.LENGTH_SHORT).show();



                }else if(feedback_description.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Enter Feedback Description", Toast.LENGTH_SHORT).show();


                }else {

                    Addfeddback();
                }


            }
        });

        try {
            lngObject = new JSONObject(sessionManager.getRegId("language"));
            feedbacktxt.setText(lngObject.getString("FeedBack"));
            submit.setText(lngObject.getString("Submit"));
            feedback_type.setHint(lngObject.getString("FeedbackType"));
            feedback_title.setHint(lngObject.getString("FeedbackTitle"));
            feedback_description.setHint(lngObject.getString("FeedbackDescription"));
        } catch (
                JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

    public static InputFilter EMOJI_FILTER = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            boolean keepOriginal = true;
            StringBuilder sb = new StringBuilder(end - start);
            for (int index = start; index < end; index++) {
                int type = Character.getType(source.charAt(index));
                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
                    return "";
                }
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        if (dstart == 0)
                            return "";
                    }
                }
                return null;
          /*  char c = source.charAt(index);
            if (isCharAllowed(c))
                sb.append(c);
            else
                keepOriginal = false;*/
            }
            if (keepOriginal)
                return null;
            else {
                if (source instanceof Spanned) {
                    SpannableString sp = new SpannableString(sb);
                    TextUtils.copySpansFrom((Spanned) source, start, sb.length(), null, sp, 0);
                    return sp;
                } else {
                    return sb;
                }
            }
        }
    };

    private void Addfeddback() {

        try{

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("FeedbackType",feedback_type.getText().toString());
            jsonObject.put("FeedbackTitle",feedback_title.getText().toString());
            jsonObject.put("FeedbackDescription",feedback_description.getText().toString());
            jsonObject.put("CreatedBy",sessionManager.getRegId("userId"));

            System.out.println("nnnnnnnnnnnnnnnaaaaaaaaa"+jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.AddFeedback, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("AddFeedbackkkkkkkkkkkkkkkkkkkkkkk"+result);

                    try{

                        status= result.getString("Status");
                        message = result.getString("Message");

                        if(!(status.equals("0"))){
                            Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();

                            selectedFragment = SettingFragment.newInstance();
                            FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout, selectedFragment);
                            transaction.commit();


                        }else{

                            Toast.makeText(getActivity(),"Your Feedback not Submitted ",Toast.LENGTH_SHORT).show();
                        }



                    }catch (Exception e){
                        e.printStackTrace();

                    }


                }
            });




        }catch (Exception e){
            e.printStackTrace();
        }




    }




}

//"FeedbackType":"Other",
//
//"FeedbackTitle":"Test",
//
//"FeedbackDescription":"Not working",
//
//"CreatedBy":1

//{"Status":"4","Message":"Feedback submitted Successfully."}