package com.renewin.Xohri.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.renewin.Xohri.R;
import com.renewin.Xohri.SessionManager;
import com.renewin.Xohri.Urls;
import com.renewin.Xohri.Volly_class.Login_post;
import com.renewin.Xohri.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    TextView create_acc,continue_sign_up,mob_text,backtologin,referal_text;
    LinearLayout back_feed;
    SessionManager sessionManager;
    public static EditText name,mobile_no,password,referal_code;
    String status,status_resp;
    Activity activity;
    LinearLayout linearLayout;
    BroadcastReceiver receiver;
    EditText spn_localize;
    String localize_text;
    TextInputLayout textInputLayout_name,textInputLayout_pass;
    public static String contact,mob_contact;
    String refer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_new);
        linearLayout=findViewById(R.id.linear_login);
        back_feed=findViewById(R.id.back_feed);
      //  spn_localize=findViewById(R.id.spn_localize);
        create_acc=findViewById(R.id.create_account);
        continue_sign_up=findViewById(R.id.sign_up_continue);
        name=findViewById(R.id.name);
       // backtologin=findViewById(R.id.create_acc);
        mobile_no=findViewById(R.id.mobilesignup);
        password=findViewById(R.id.passsignup);
       // referal_text=findViewById(R.id.referal_text);
       // referal_code=findViewById(R.id.referal_code);
       // textInputLayout_pass=findViewById(R.id.text_pass);
        GetUserDetails();

             setupUI(linearLayout);
        String[] localize = { "+91"};

       /* backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(mIntent);
            }
        });*/

        back_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
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
        password.setFilters(new InputFilter[]{EMOJI_FILTER});
      /*  linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });*/



        sessionManager=new SessionManager(this);
        //  sessionManager.getRegId("lng_object");
        System.out.println("signupresponse" + sessionManager.getRegId("langdetails"));
        JSONObject lngObject;
        try {
            lngObject=new JSONObject(sessionManager.getRegId("langdetails"));
            create_acc.setText(lngObject.getString("CreateAccount"));
           // mob_text.setText(lngObject.getString("PhoneNo"));
            mobile_no.setHint(lngObject.getString("PhoneNo"));
            name.setHint(lngObject.getString("FullName"));
            password.setHint(lngObject.getString("EnterPassword"));
            continue_sign_up.setText(lngObject.getString("Register"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

  /*    //  sessionManager.getRegId("lng_object");
        System.out.println("signupresponse" + sessionManager.getRegId("langdetails"));
        JSONObject lngObject;
        try {
           // lngObject=new JSONObject(sessionManager.getRegId("langdetails"));
        //    create_acc.setText(lngObject.getString("CreateAccount"));
         //   mob_text.setText(lngObject.getString("PhoneNo"));
          //  mobile_no.setHint(lngObject.getString("PhoneNo"));
          //  textInputLayout_name.setHint(lngObject.getString("FullName"));
          //  textInputLayout_pass.setHint(lngObject.getString("EnterPassword"));
          //  continue_sign_up.setText(lngObject.getString("Register"));

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
       /* sign_up_arw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlphaAnimation buttonClick = new AlphaAnimation(4F, 3F);
                v.startAnimation(buttonClick);
                Intent intent=new Intent(getActivity(), XLogin.class);
                startActivity(intent);
            }
        });*/

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

        password.setFilters(new InputFilter[] {filter,new InputFilter.LengthFilter(12) });
        continue_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent=new Intent(SignUp.this,SignUpContinue.class);
                startActivity(intent);*/
                localize_text="+91";
                 String name_text = name.getText().toString();
                // final String email = SignUp.email.getText().toString();
                contact =localize_text+mobile_no.getText().toString();
                System.out.println("connttaaactt"+contact);
                // mob_contact=contact.substring(5);
                System.out.println("mmoobb"+mob_contact);
                 String password_text= password.getText().toString();
                //  final String confirmpassword = SignUp.conf_pass.getText().toString();


                System.out.println("nameeee"+name);
                System.out.println("contacttttt"+contact);
                System.out.println("passss"+password);

                if (name_text.equals("")&&mobile_no.getText().toString().equals("")&&password_text.equals("")){
                    System.out.println("enterrrr");
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter All Text Fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();
                }
                else if (name_text.equals("")) {

                    //Toast.makeText(SignUp.this, "Enter Your Name", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter Your Name", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();
                }else if (name_text.length()<2){
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Name should contain minimum 2 characters", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();
                }
                else if (name_text.startsWith(" ")){
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Name should not starts with space", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();


                }

                else if (contact.equals("")) {

                    //Toast.makeText(SignUp.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter Mobile Number", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();
                } else if (!(contact.length() == 13)) {

                    //Toast.makeText(SignUp.this, "Enter valid Mobile Number", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter valid Mobile Number", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();

                }/*else if (contact.equals(sessionManager.getRegId("phone"))){
                    System.out.println("fdgjhhijihujhgcgfhghghkkk");
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "This Mobile has already registered", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();
                }*/
                else if (password.equals("")) {

                    //Toast.makeText(SignUp.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter Your Password", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();
                } else if (password.length() < 6) {
                    // Sign_Up.this.pass.requestFocus();
                    //Toast.makeText(SignUp.this, "Enter password of length 6 characters", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter password of length 6 characters", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();
                }else if (password_text.contains(" ")) {

                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Password should not contain spaces", Snackbar.LENGTH_LONG);
                    View snackbarView=snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
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



    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

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

            try{
                assert inputManager != null;
                inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }catch(AssertionError e){
                e.printStackTrace();
            }
        }
    }

    private void GetUserDetails() {

        try {

            JSONObject userRequestjsonObject = new JSONObject();
            userRequestjsonObject.put("Id",sessionManager.getRegId("userId"));

            JSONObject postjsonObject = new JSONObject();
            postjsonObject.put("objUser", userRequestjsonObject);
            System.out.println("crop_discover_sub"+postjsonObject);

            Login_post.login_posting(activity, Urls.GetUserDetails,postjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("GFGFGFGF"+result);
                    JSONObject jsonObject;

                    try {

                        jsonObject=result.getJSONObject("user");
                        refer=jsonObject.getString("RefferalCode");
                        System.out.println("referrrrr"+refer);

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
            userRequestjsonObject.put("RefferalCode",referal_code.getText().toString());

            JSONObject postjsonObject = new JSONObject();
            postjsonObject.put("objUser", userRequestjsonObject);
            System.out.println("rreeddert"+postjsonObject);

            Login_post.login_posting(SignUpActivity.this,Urls.ValidateReferalCode,postjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("hgdysfdytf"+result);
                    JSONObject jsonObject;

                    try {
                        System.out.println("referrrrrADFG"+refer);


                        jsonObject=result.getJSONObject("user");
                        refer=jsonObject.getString("IsReferValidated");
                        System.out.println("referrrrr"+refer);
                        if (refer.equals("true")){
                            System.out.println("tryryru"+refer);
                            register();
                        }else{
                            Toast.makeText(activity,"Invalid Referal Code",Toast.LENGTH_LONG).show();
                        }
                        System.out.println("referrrrr"+refer);

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
                                    .make(linearLayout, "This Mobile has already registered", Snackbar.LENGTH_LONG);
                            View snackbarView = snackbar.getView();
                            TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                            tv.setTextColor(Color.RED);
                            snackbar.show();

                        } else {
                            jsonObject = result.getJSONObject("user");
                            status = jsonObject.getString("OTP");
                            String userid = jsonObject.getString("Id");
                            System.out.println("useerrrriidd" + userid);
                            sessionManager.saveUserId(userid);
                            sessionManager.save_name(jsonObject.getString("FullName"));
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
