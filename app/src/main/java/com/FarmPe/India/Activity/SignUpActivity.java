package com.FarmPe.India.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.FarmPe.India.Adapter.SelectLanguageAdapter_SignUP;
import com.FarmPe.India.Bean.SelectLanguageBean;
import com.FarmPe.India.R;
import com.FarmPe.India.SessionManager;
import com.FarmPe.India.Urls;
import com.FarmPe.India.Volly_class.Crop_Post;
import com.FarmPe.India.Volly_class.Login_post;
import com.FarmPe.India.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

   public static TextView create_acc, continue_sign_up, change_lang, backtologin, referal_text;
    LinearLayout back_feed;
    SessionManager sessionManager;
    public static EditText name, mobile_no, password, referal_code;
    String status, status_resp;
    JSONArray lng_array;
    Activity activity;
    JSONObject lngObject;
    public static TextInputLayout sign_name,sign_mobile,sign_pass;
    public static String mob_toast,passwrd_toast,minimum_character_toast,enter_all_toast,name_toast,mobile_registered_toast;

    List<SelectLanguageBean>language_arrayBeanList = new ArrayList<>();
    SelectLanguageBean selectLanguageBean;
    SelectLanguageAdapter_SignUP mAdapter;

    LinearLayout linearLayout;
    BroadcastReceiver receiver;
    EditText spn_localize;
    String localize_text;
    TextInputLayout textInputLayout_name, textInputLayout_pass;
    public static String contact, mob_contact;
    String refer;
     public static   Dialog dialog;


    @Nullable
    @Override
    public ActionBar getSupportActionBar() {
        return super.getSupportActionBar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_new);


        linearLayout = findViewById(R.id.linear_login);
        back_feed = findViewById(R.id.back_feed);
        //back_feed = findViewById(R.id.back_feed);
        //  spn_localize=findViewById(R.id.spn_localize);
        create_acc = findViewById(R.id.toolbar_title);
        sign_mobile = findViewById(R.id.sign_mobile);
        sign_name = findViewById(R.id.sign_name);
        sign_pass = findViewById(R.id.sign_pass);
        continue_sign_up = findViewById(R.id.sign_up_continue);
        name = findViewById(R.id.name);
        // backtologin=findViewById(R.id.create_acc);
        mobile_no = findViewById(R.id.mobilesignup);
        password = findViewById(R.id.passsignup);
        change_lang = findViewById(R.id.change_lang);
        // referal_text=findViewById(R.id.referal_text);
        // referal_code=findViewById(R.id.referal_code);
        // textInputLayout_pass=findViewById(R.id.text_pass);



        sessionManager = new SessionManager(SignUpActivity.this);

        if(sessionManager.getRegId("language_name").equals("")){

            change_lang.setText("English");

        }else {

            change_lang.setText(sessionManager.getRegId("language_name"));
        }




        setupUI(linearLayout);
        String[] localize = {"+91"};

       /* backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(mIntent);
            }
        });*/

        try {

            if ((sessionManager.getRegId("language")).equals("")) {
                getLang(1);

            }else{

                lngObject=new JSONObject(sessionManager.getRegId("language"));

                create_acc.setText(lngObject.getString("Register"));
                sign_name.setHint(lngObject.getString("FullName"));
                sign_mobile.setHint(lngObject.getString("DigitMobileNumber"));
                sign_pass.setHint(lngObject.getString("Password"));
               // textInputLayout_name.setHint(lngObject.getString("FullName"));
              //  textInputLayout_pass.setHint(lngObject.getString("EnterPassword"));
                continue_sign_up.setText(lngObject.getString("Register"));



                passwrd_toast = lngObject.getString("Enterpasswordoflength6characters");
                mob_toast = lngObject.getString("Entervalidmobilenumber");
                minimum_character_toast = lngObject.getString("NameShouldContainMinimum2Characters");
                enter_all_toast = lngObject.getString("EnterAllTextFields");
                name_toast = lngObject.getString("Enteryourname");
                mobile_registered_toast = lngObject.getString("Thismobilehasalreadyregistered");



            }


        }catch (Exception e){
            e.printStackTrace();
        }





        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


       /* final InputFilter EMOJI_FILTER = new InputFilter() {

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
        };*/


     /*  linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });*/


        sessionManager = new SessionManager(this);
        //  sessionManager.getRegId("lng_object");
//        System.out.println("signupresponse" + sessionManager.getRegId("langdetails"));
//        JSONObject lngObject;
//        try {
//            lngObject = new JSONObject(sessionManager.getRegId("langdetails"));
//            create_acc.setText(lngObject.getString("Register"));
//            // mob_text.setText(lngObject.getString("PhoneNo"));
//            mobile_no.setHint(lngObject.getString("PhoneNo"));
//            name.setHint(lngObject.getString("FullName"));
//            password.setHint(lngObject.getString("EnterPassword"));
//            continue_sign_up.setText(lngObject.getString("Register"));
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        change_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                System.out.println("jhfdyug");

                RecyclerView recyclerView;
                final TextView yes1, no1;
                final LinearLayout close_layout;
                System.out.println("aaaaaaaaaaaa");
                dialog = new Dialog(SignUpActivity.this);
                dialog.setContentView(R.layout.change_lang_login);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(false);
                close_layout = dialog.findViewById(R.id.close_layout);

                recyclerView = dialog.findViewById(R.id.recycler_change_lang);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(SignUpActivity.this);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                mAdapter = new SelectLanguageAdapter_SignUP(SignUpActivity.this, language_arrayBeanList);
                recyclerView.setAdapter(mAdapter);


                try{

                    JSONObject jsonObject = new JSONObject();

                    Crop_Post.crop_posting(SignUpActivity.this, Urls.Languages, jsonObject, new VoleyJsonObjectCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject result) {
                            System.out.print("111111ang" + result);
                            try{

                                language_arrayBeanList.clear();
                                lng_array = result.getJSONArray("LanguagesList");
                                for(int i=0;i<lng_array.length();i++){
                                    JSONObject  jsonObject1 = lng_array.getJSONObject(i);

                                    selectLanguageBean = new SelectLanguageBean(jsonObject1.getString("Language"),jsonObject1.getInt("Id"),"");
                                    language_arrayBeanList.add(selectLanguageBean);



                                }

                                mAdapter.notifyDataSetChanged();




                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    });


                }catch (Exception e){
                    e.printStackTrace();
                }



                close_layout.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });


      //  sessionManager.getRegId("lng_object");
//        System.out.println("signupresponse" + sessionManager.getRegId("langdetails"));
//        JSONObject lngObject;
//        try {
//            lngObject=new JSONObject(sessionManager.getRegId("langdetails"));
//            create_acc.setText(lngObject.getString("CreateAccount"));
//            mob_text.setText(lngObject.getString("PhoneNo"));
//            mobile_no.setHint(lngObject.getString("PhoneNo"));
//            textInputLayout_name.setHint(lngObject.getString("FullName"));
//            textInputLayout_pass.setHint(lngObject.getString("EnterPassword"));
//            continue_sign_up.setText(lngObject.getString("Register"));
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
       /* sign_up_arw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlphaAnimation buttonClick = new AlphaAnimation(4F, 3F);
                v.startAnimation(buttonClick);
                Intent intent=new Intent(getActivity(), XLogin.class);
                startActivity(intent);
            }
        });*/

       //with space in between nt in starting
//
//        final InputFilter filter1 = new InputFilter() {
//            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//                //String filtered = "";
//                for (int i = start; i < end; i++) {
//                    char character = source.charAt(i);
//                    if (Character.isWhitespace(source.charAt(i))) {
//                        if (dstart == 0)
//                            return "";
//                    }
//                }
//                return null;
//            }
//
//        };
//
//
//        name.setFilters(new InputFilter[] {filter1,new InputFilter.LengthFilter(30) });

      name.setFilters(new InputFilter[]{EMOJI_FILTER});


       //without space
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


        password.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(12)});

        continue_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent=new Intent(SignUp.this,SignUpContinue.class);
                startActivity(intent);*/
                localize_text = "+91";
                String name_text = name.getText().toString();
                // final String email = SignUp.email.getText().toString();
                contact = localize_text + mobile_no.getText().toString();
                System.out.println("connttaaactt" + contact);
                // mob_contact=contact.substring(5);
                System.out.println("mmoobb" + mob_contact);
                String password_text = password.getText().toString();

                //  final String confirmpassword = SignUp.conf_pass.getText().toString();


                System.out.println("nameeee" + name);
                System.out.println("contacttttt" + contact);
                System.out.println("passss" + password);

                if (name_text.equals("") && mobile_no.getText().toString().equals("") && password_text.equals("")) {
                    System.out.println("enterrrr");
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, enter_all_toast, Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(SignUpActivity.this,R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    snackbar.show();
                } else if (name_text.equals("")) {

                    //Toast.makeText(SignUp.this, "Enter Your Name", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, name_toast, Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(SignUpActivity.this,R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    snackbar.show();

                } else if (name_text.length() < 2) {
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, minimum_character_toast, Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(SignUpActivity.this,R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    snackbar.show();

                } else if (name_text.startsWith(" ")) {
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Name should not starts with space", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(SignUpActivity.this,R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    snackbar.show();


                } else if (contact.equals("")) {

                    //Toast.makeText(SignUp.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, passwrd_toast, Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(SignUpActivity.this,R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    snackbar.show();
                } else if (!(contact.length() == 13)) {

                    //Toast.makeText(SignUp.this, "Enter valid Mobile Number", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, mob_toast, Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(SignUpActivity.this,R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    snackbar.show();

                }/*else if (contact.equals(sessionManager.getRegId("phone"))){
                    System.out.println("fdgjhhijihujhgcgfhghghkkk");
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "This Mobile has already registered", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();
                }*/ else if (password.equals("")) {

                    //Toast.makeText(SignUp.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter Your Password", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(SignUpActivity.this,R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    snackbar.show();

                } else if (password.length() < 6) {
                    // Sign_Up.this.pass.requestFocus();
                    //Toast.makeText(SignUp.this, "Enter password of length 6 characters", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, passwrd_toast, Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(SignUpActivity.this,R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    snackbar.show();

                } else if (password_text.contains(" ")) {

                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Password should not contain spaces", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setBackgroundColor(ContextCompat.getColor(SignUpActivity.this,R.color.orange));
                    tv.setTextColor(Color.WHITE);
                    snackbar.show();
                } else {

                    register();
                   /* try {

                        // mobile=mob_no;
                        JSONObject userRequestjsonObject = new JSONObject();
                        JSONObject postjsonObject = new JSONObject();


                        userRequestjsonObject.put("PhoneNo", contact);
                        userRequestjsonObject.put("Password", password);
                        userRequestjsonObject.put("DeviceId", "123456789");
                        userRequestjsonObject.put("DeviceType", "Android");
                        userRequestjsonObject.put("FullName", name);


                        postjsonObject.putOpt("objUser", userRequestjsonObject);

                        System.out.println("post_oobject"+postjsonObject);


                        Login_post.login_posting(SignUpActivity.this, Urls.SIGNUP, postjsonObject, new VoleyJsonObjectCallback() {
                            @Override
                            public void onSuccessResponse(JSONObject result) {
                                System.out.println("statussssss" + result);
                                JSONObject jsonObject=new JSONObject();
                                JSONObject jsonObject_resp=new JSONObject();
                                try {
                                    if (result.isNull("user")){
                                        jsonObject_resp = result.getJSONObject("Response");
                                        status_resp= jsonObject_resp.getString("Status");
                                        Snackbar snackbar = Snackbar
                                                .make(linearLayout, "This Mobile has already registered", Snackbar.LENGTH_LONG);
                                        View snackbarView = snackbar.getView();
                                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                        tv.setTextColor(Color.RED);
                                        snackbar.show();
                                    }else{
                                        jsonObject = result.getJSONObject("user");
                                        status = jsonObject.getString("OTP");
                                        String userid=jsonObject.getString("Id");
                                        System.out.println("useerrrriidd"+userid);
                                        sessionManager.saveUserId(userid);
                                        Intent intent = new Intent(SignUpActivity.this, EnterOTP.class);
                                        intent.putExtra("otpnumber", status);
                                        startActivity(intent);
                                    }

                                    // args.putString("OTP", jsonObject.getString("OTP"));



                                    Intent intent = new Intent(SignUp.this, EnterOTP.class);
                                    intent.putExtra("otpnumber", status);
                                    startActivity(intent);

                                    if (status_resp.equals("1")){

                                    }else {

                                        Snackbar snackbar = Snackbar
                                                .make(linearLayout, "This Mobile has already registered", Snackbar.LENGTH_LONG);
                                        View snackbarView = snackbar.getView();
                                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                        tv.setTextColor(Color.RED);
                                        snackbar.show();

                                        Snackbar snackbar = Snackbar
                                                .make(linearLayout, "Something Went Wrong", Snackbar.LENGTH_LONG);
                                        View snackbarView = snackbar.getView();
                                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                        tv.setTextColor(Color.RED);
                                        snackbar.show();
                                        //Toast.makeText(Login.this,"Invalid Mobile number or Password",Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/

                  /* if (referal_code.getText().toString().equals("")){
                       register();

                   }else
                    ValidateUser();*/
                }
            }
        });


    }

    private void getLang(int id) {

        try{


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Id",id);

            System.out.print("iiidddddd"+ id);

            Crop_Post.crop_posting(SignUpActivity.this, Urls.CHANGE_LANGUAGE, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("qqqqqqvv" + result);

                    try{

                        sessionManager.saveLanguage(result.toString());


                        String log_regi = result.getString("Register");
                        String log_name = result.getString("FullName");
                        String log_mobile = result.getString("DigitMobileNumber");
                        String log_password = result.getString("Password");
                        String log_register = result.getString("Register");

                        passwrd_toast = result.getString("Enterpasswordoflength6characters");
                        mob_toast = result.getString("Entervalidmobilenumber");
                        name_toast = result.getString("Enteryourname");
                       mobile_registered_toast = result.getString("Thismobilehasalreadyregistered");




                        sign_name.setHint(log_name);
                        sign_mobile.setHint(log_mobile);
                        sign_pass.setHint(log_password);
                        create_acc.setText(log_regi);
                        continue_sign_up.setText(log_register);

                        name.setFilters(new InputFilter[]{EMOJI_FILTER});





                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
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

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(SignUpActivity.this);
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        /*InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);*/

        InputMethodManager inputManager = (InputMethodManager)
                activity.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();

        if (focusedView != null) {

            try {
                assert inputManager != null;
                inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (AssertionError e) {
                e.printStackTrace();
            }
        }
    }

    private void GetUserDetails() {

        try {

            JSONObject userRequestjsonObject = new JSONObject();
            userRequestjsonObject.put("Id", sessionManager.getRegId("userId"));

            JSONObject postjsonObject = new JSONObject();
            postjsonObject.put("objUser", userRequestjsonObject);
            System.out.println("crop_discover_sub" + postjsonObject);

            Login_post.login_posting(activity, Urls.GetUserDetails, postjsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("GFGFGFGF" + result);
                    JSONObject jsonObject;

                    try {

                        jsonObject = result.getJSONObject("user");
                        refer = jsonObject.getString("RefferalCode");
                        System.out.println("referrrrr" + refer);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void ValidateUser() {

        try {

            JSONObject userRequestjsonObject = new JSONObject();
            userRequestjsonObject.put("RefferalCode", referal_code.getText().toString());

            JSONObject postjsonObject = new JSONObject();
            postjsonObject.put("objUser", userRequestjsonObject);
            System.out.println("rreeddert" + postjsonObject);

            Login_post.login_posting(SignUpActivity.this, Urls.ValidateReferalCode, postjsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("hgdysfdytf" + result);
                    JSONObject jsonObject;

                    try {
                        System.out.println("referrrrrADFG" + refer);


                        jsonObject = result.getJSONObject("user");
                        refer = jsonObject.getString("IsReferValidated");
                        System.out.println("referrrrr" + refer);
                        if (refer.equals("true")) {
                            System.out.println("tryryru" + refer);
                            register();
                        } else {
                            Toast.makeText(activity, "Invalid Referal Code", Toast.LENGTH_LONG).show();
                        }
                        System.out.println("referrrrr" + refer);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void register() {

        try {

            // mobile=mob_no;
            JSONObject userRequestjsonObject = new JSONObject();
            JSONObject postjsonObject = new JSONObject();


            userRequestjsonObject.put("PhoneNo", contact);
            userRequestjsonObject.put("Password", password.getText().toString());
            userRequestjsonObject.put("DeviceId", "123456789");
            userRequestjsonObject.put("DeviceType", "Android");
            userRequestjsonObject.put("FullName", name.getText().toString());


            postjsonObject.putOpt("objUser", userRequestjsonObject);

            System.out.println("post_oobject" + postjsonObject);



            Login_post.login_posting(SignUpActivity.this, Urls.SIGNUP, postjsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("statussssss" + result);
                    JSONObject jsonObject = new JSONObject();
                    JSONObject jsonObject_resp = new JSONObject();

                    try {
                        if (result.isNull("user")) {
                            jsonObject_resp = result.getJSONObject("Response");
                            status_resp = jsonObject_resp.getString("Status");
                            Snackbar snackbar = Snackbar
                                    .make(linearLayout,mobile_registered_toast, Snackbar.LENGTH_LONG);
                            View snackbarView = snackbar.getView();
                            TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                            tv.setBackgroundColor(ContextCompat.getColor(SignUpActivity.this,R.color.orange));
                            tv.setTextColor(Color.WHITE);
                            snackbar.show();


                        } else {
                            jsonObject = result.getJSONObject("user");
                            status = jsonObject.getString("OTP");
                            String userid = jsonObject.getString("Id");
                            System.out.println("useerrrriidd" + userid);
                            sessionManager.saveUserId(userid);
                            sessionManager.save_name(jsonObject.getString("FullName"), jsonObject.getString("PhoneNo"));
                            Intent intent = new Intent(SignUpActivity.this, EnterOTP.class);
                            intent.putExtra("otpnumber", status);
                            startActivity(intent);
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


}

