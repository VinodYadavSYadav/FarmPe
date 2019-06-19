package com.renewin.Xohri.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.renewin.Xohri.R;
import com.renewin.Xohri.SessionManager;
import com.renewin.Xohri.Urls;
import com.renewin.Xohri.Volly_class.Crop_Post;
import com.renewin.Xohri.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;


public class Change_Password_Fragment extends Fragment {
    Fragment selectedFragment;
    TextView confirm_button;
    EditText old_pass,new_pass;
    LinearLayout back_feed,linearLayout;
    SessionManager sessionManager;
   public String oldPassword,newPassword,status;




    public static Change_Password_Fragment newInstance() {
        Change_Password_Fragment fragment = new Change_Password_Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_password_layout, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

          sessionManager = new SessionManager(getActivity());

         back_feed = view.findViewById(R.id.back);
         confirm_button = view.findViewById(R.id.confrm_btn);
         old_pass = view.findViewById(R.id.o_pass);
         new_pass = view.findViewById(R.id.n_pass);
         linearLayout = view.findViewById(R.id.linear_chngpass);



         confirm_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 oldPassword=old_pass.getText().toString();
                 newPassword=new_pass.getText().toString();

                 if(oldPassword.equals("")&&newPassword.equals("")){
                     Snackbar snackbar = Snackbar
                             .make(linearLayout, "Enter All Fields", Snackbar.LENGTH_LONG);
                     View snackbarView = snackbar.getView();
                     TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                     tv.setTextColor(Color.RED);
                     snackbar.show();
                 }

                 else if(oldPassword.equals("")){
                     old_pass.requestFocus();
                     Snackbar snackbar = Snackbar
                             .make(linearLayout, "Enter Your Old Password", Snackbar.LENGTH_LONG);
                     View snackbarView = snackbar.getView();
                     TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                     tv.setTextColor(Color.RED);
                     snackbar.show();
                 }else if(newPassword.equals("")){
                     new_pass.requestFocus();
                     Snackbar snackbar = Snackbar
                             .make(linearLayout, "Enter Your New Password", Snackbar.LENGTH_LONG);
                     View snackbarView = snackbar.getView();
                     TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                     tv.setTextColor(Color.RED);
                     snackbar.show();
                 }else try {
                     JSONObject jsonObject = new JSONObject();
                     JSONObject post_json = new JSONObject();

                     jsonObject.put("Id", sessionManager.getRegId("userId"));
                     jsonObject.put("OldPassword", old_pass.getText().toString());
                     jsonObject.put("Password", new_pass.getText().toString());

                     post_json.put("objUser", jsonObject);

                     Crop_Post.crop_posting(getActivity(), Urls.Update_User_Password, post_json, new VoleyJsonObjectCallback() {
                         @Override
                         public void onSuccessResponse(JSONObject result) {
                             System.out.println("changepassworddddddddddd" + result);
                             try {

                                 JSONObject jsonObject1 = result.getJSONObject("Response");

                                 status = jsonObject1.getString("Status");
                                 System.out.println("sttttt" + status);

                                 if (status.equals("1")) {

                                     Toast.makeText(getActivity(), "Your Password Updated  Successfully", Toast.LENGTH_SHORT).show();

                                     selectedFragment = My_Account_Fragment.newInstance();
                                     FragmentTransaction transaction4 = getActivity().getSupportFragmentManager().beginTransaction();
                                     transaction4.replace(R.id.frame_layout, selectedFragment);

                                     transaction4.commit();

//                                     Intent intent = new Intent(getActivity(), Home_Page_WithBottomMenu_Activity.class);
//                                     intent.putExtra("nav_switch", "CHANGE_PASS");
//                                     startActivity(intent);


                                 } else {

                                     Toast.makeText(getActivity(), "Failed to Change password", Toast.LENGTH_SHORT).show();
                                 }


                             } catch (JSONException e) {
                                 e.printStackTrace();
                             }


                         }
                     });


                 } catch (Exception e) {
                     e.printStackTrace();
                 }

             }
         });


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    selectedFragment = My_Account_Fragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();
                    return true;

                }

                return false;
            }
        });




        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = My_Account_Fragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();

            }
        });





       /* try{

            JSONObject jsonObject = new JSONObject();
            JSONObject post_json = new JSONObject();


            jsonObject.put("Id",sessionManager.getRegId("userId"));
            post_json.put("objUser",jsonObject);

            Crop_Post.crop_posting(getActivity(), Urls.Edit_Profile_Details, post_json, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("dddddddddddd" + result);
                    try{

                        JSONObject jsonObject1 = result.getJSONObject("user");
                        String user_name1 = jsonObject1.getString("FullName");
                        String phone_no1 = jsonObject1.getString("PhoneNo");



                        user_name.setText("   " + user_name1);
                        user_phone.setText("   "+phone_no1);





                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
*/

        return view;
    }
}


/*
http://3.17.6.57:8585/api/Auth/UpdateUserPassword

        {

            "objUser":

            {

                "Id":26,

                "OldPassword":"7654321",

                "Password":"123456"

            }

        }*/
