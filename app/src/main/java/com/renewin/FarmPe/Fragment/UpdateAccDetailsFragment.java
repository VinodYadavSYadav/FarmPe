package com.renewin.FarmPe.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.renewin.FarmPe.Adapter.AddHpAdapter;
import com.renewin.FarmPe.Bean.FarmsImageBean;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;
import com.renewin.FarmPe.Urls;
import com.renewin.FarmPe.Volly_class.Crop_Post;
import com.renewin.FarmPe.Volly_class.VoleyJsonObjectCallback;
import com.renewin.FarmPe.Volly_class.VolleySingletonQuee;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateAccDetailsFragment extends Fragment {

    public static List<FarmsImageBean> newOrderBeansList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static AddHpAdapter farmadapter;
    SessionManager sessionManager;
    TextView toolbar_title;
    LinearLayout update_btn;
    EditText profile_name,profile_phone,profile_mail,profile_passwrd;
    CircleImageView prod_img;


    LinearLayout back_feed;
    Fragment selectedFragment;


    public static UpdateAccDetailsFragment newInstance() {
        UpdateAccDetailsFragment fragment = new UpdateAccDetailsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_acc_details, container, false);
        recyclerView=view.findViewById(R.id.recycler_what_looking);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        back_feed=view.findViewById(R.id.back_feed);
        profile_name=view.findViewById(R.id.profile_name);
        profile_phone=view.findViewById(R.id.profile_phone);
        profile_mail=view.findViewById(R.id.profile_mail);
        profile_passwrd=view.findViewById(R.id.profile_passwrd);
        prod_img=view.findViewById(R.id.prod_img);
        update_btn=view.findViewById(R.id.update_btn);

        sessionManager = new SessionManager(getActivity());



        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);
              /*  selectedFragment = HomeMenuFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                // transaction.addToBackStack("looking");
                transaction.commit();*/
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
     //   pass.setFilters(new InputFilter[]{EMOJI_FILTER});



        final InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String filtered = "";
                for (int i = start; i < end; i++) {
                    char character = source.charAt(i);
                    if (!Character.isWhitespace(character)) {
                        filtered += character;
                    }
                }
                return filtered;
            }

        };



       // pass.setFilters(new InputFilter[] {filter,new InputFilter.LengthFilter(12) });


        try{

            JSONObject jsonObject = new JSONObject();
            JSONObject post_object = new JSONObject();

            jsonObject.put("Id",sessionManager.getRegId("userId"));
            post_object.put("objUser",jsonObject);


            Crop_Post.crop_posting(getActivity(), Urls.Get_Profile_Details, post_object, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("ggpgpgpg" + result);

                    try{

                        JSONObject jsonObject1 = result.getJSONObject("user");
                        String ProfileName = jsonObject1.getString("FullName");
                        String ProfilePhone = jsonObject1.getString("PhoneNo");
                        String ProfileEmail = jsonObject1.getString("EmailId");
                        String ProfileImage = jsonObject1.getString("ProfilePic");


                        profile_name.setText(ProfileName);
                        profile_phone.setText(ProfilePhone);
                        profile_mail.setText(ProfileEmail);





                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }


        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                update_profile_details();
            }
        });




        return view;
    }

    private void update_profile_details() {

        try{

            StringRequest postRequest = new StringRequest(Request.Method.POST, "http://3.17.6.57:8686/api/Auth/UpdateUserProfile",
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            // response
                            Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<String, String>();

                    params.put("UserId",sessionManager.getRegId("userId"));
                    params.put("FullName",profile_name.getText().toString());
                    params.put("PhoneNo",profile_phone.getText().toString());
                    params.put("EmailId",profile_mail.getText().toString());
                    params.put("Password",profile_passwrd.getText().toString());

                    return params;
                }
            };
            VolleySingletonQuee.getInstance(getActivity()).addToRequestQueue(postRequest);

//            StringRequest stringRequest=new StringRequest(new VoleyJsonObjectCallback() {
//                @Override
//                public void onSuccessResponse(JSONObject result) {
//
//                }
//            })



        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
