package com.renewin.FarmPe.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.InputFilter;
import android.text.Spanned;
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

import com.renewin.FarmPe.Bean.AgriBean;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;
import com.renewin.FarmPe.Urls;
import com.renewin.FarmPe.Volly_class.Crop_Post;
import com.renewin.FarmPe.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONObject;

public class FeedbackFragment extends Fragment {
    Fragment selectedFragment;
    TextView send_otp, referal_code,feedback_type,submit;
    LinearLayout back, more, whatsapp, insta, facebook, back_feed, twitter;
    public static String status,message;
    Intent intent;
    private ArrayAdapter<AgriBean> arrayAdapter;
    private ListView listView;
    String packageName;
    SessionManager sessionManager;
    EditText feedback_title,feedback_description;

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
        feedback_description=view.findViewById(R.id.fd_descp);
        feedback_type=view.findViewById(R.id.fd_type);
        submit=view.findViewById(R.id.submit);

        sessionManager = new SessionManager(getActivity());

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

        feedback_title.setFilters(new InputFilter[] {filter1,new InputFilter.LengthFilter(15) });
        feedback_description.setFilters(new InputFilter[] {filter1,new InputFilter.LengthFilter(100) });


        final InputFilter EMOJI_FILTER = new InputFilter() {

            @Override

            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                for (int index = start; index < end; index++) {
                    int type = Character.getType(source.charAt(index));
                    if (type == Character.SURROGATE) {
                        return "";
                    }
                }
                return null;
            }
        };
        feedback_title.setFilters(new InputFilter[]{EMOJI_FILTER});
        feedback_description.setFilters(new InputFilter[]{EMOJI_FILTER});



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



        return view;
    }

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