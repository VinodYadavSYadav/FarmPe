package com.renewin.FarmPe.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.renewin.FarmPe.Adapter.SelectLanguageAdapter;
import com.renewin.FarmPe.Adapter.SelectLanguageAdapter2;
import com.renewin.FarmPe.Bean.AddTractorBean;
import com.renewin.FarmPe.Bean.SelectLanguageBean;
import com.renewin.FarmPe.DB.DatabaseHelper;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;
import com.renewin.FarmPe.Urls;
import com.renewin.FarmPe.Volly_class.Login_post;
import com.renewin.FarmPe.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity {
    TextView register, log_in, forgot_pass,mob_text_signin;
    public static EditText mobile_no, pass;
    public static String mobile,loc_text;
    LinearLayout linearLayout;
    SessionManager sessionManager;
    public String status,userId;
    boolean doubleBackToExitPressedOnce = false;
    LinearLayout back_xlogin;
    LinearLayout coordinatorLayout;
    public static CheckBox remember_me;
    DatabaseHelper myDb;
    TextInputLayout textInputLayout,textInputLayout_pass;
    String password,toast_mob,mobile_string,pass_toast;
    EditText spn_localize;
    JSONObject lngObject;
    Snackbar snackbar;
    String mob_no;
    public static  Dialog dialog;
    TextView welcome_back, createaccount, change_lang, enterPassword, forgotPassword;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        linearLayout=findViewById(R.id.main_layout);
        welcome_back = findViewById(R.id.welcome_back);
        createaccount = findViewById(R.id.create_acc);
        change_lang = findViewById(R.id.change_lang);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        log_in = findViewById(R.id.login_button);
        // setLocale("kn");
        forgot_pass =findViewById(R.id.forgot_pass_login);
        mobile_no = findViewById(R.id.mob_no);
        pass = findViewById(R.id.pass);
        //back_xlogin = view.findViewById(R.id.arrow_layout);
        coordinatorLayout =findViewById(R.id.main_layout);
        remember_me = findViewById(R.id.remember_me);
        loc_text="+91";
        setupUI(linearLayout);
        myDb = new DatabaseHelper(this);
        edittext_move(mobile_no, pass);
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
        pass.setFilters(new InputFilter[]{EMOJI_FILTER});
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
        pass.setFilters(new InputFilter[] {filter,new InputFilter.LengthFilter(12) });
       // sessionManager = new SessionManager(this);
        // sessionManager.getRegId("lng_object");
        System.out.println("llllllllllll" + sessionManager.getRegId("langdetails"));
        try {
            lngObject=new JSONObject(sessionManager.getRegId("langdetails"));
            //  createaccount.setHint(lngObject.getString("Createanaccount"));
            //mob_text_signin.setHint(lngObject.getString("Signintoyouraccount"));
            toast_mob=lngObject.getString("EnterPhoneNo");
            pass_toast=lngObject.getString("EnterPassword");
            mobile_no.setHint(lngObject.getString("EnterPhoneNo"));
            pass.setHint(lngObject.getString("EnterPassword"));
            remember_me.setText(lngObject.getString("RememberMe"));
            forgot_pass.setText(lngObject.getString("ForgotPassword")+"?");
            log_in.setText(lngObject.getString("Login"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("ooooooooobb"+toast_mob);

        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordNew.class);
                startActivity(intent);
            }
        });
        change_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("jhfdyug");
                final List<SelectLanguageBean> newOrderBeansList = new ArrayList<>();
                RecyclerView recyclerView;
                final TextView yes1,no1;
                final LinearLayout close_layout;
                final SelectLanguageAdapter2 mAdapter;
                System.out.println("aaaaaaaaaaaa");
                 dialog = new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.change_lang_login);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(false);

                close_layout =  dialog.findViewById(R.id.close_layout);
                recyclerView =  dialog.findViewById(R.id.recycler_change_lang);

                newOrderBeansList.clear();
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(LoginActivity.this);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());


                SelectLanguageBean bean=new SelectLanguageBean("English",1,"");
                newOrderBeansList.add(bean);

                SelectLanguageBean bean1=new SelectLanguageBean("Kannada",1,"");
                newOrderBeansList.add(bean1);

                SelectLanguageBean bean2=new SelectLanguageBean("Hindi",1,"");
                newOrderBeansList.add(bean2);

                SelectLanguageBean bean3=new SelectLanguageBean("Tamil",1,"");
                newOrderBeansList.add(bean3);

                SelectLanguageBean bean4=new SelectLanguageBean("Telgu",1,"");
                newOrderBeansList.add(bean4);

                mAdapter = new SelectLanguageAdapter2(LoginActivity.this, newOrderBeansList);
                recyclerView.setAdapter(mAdapter);
                close_layout.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });


        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile_string=mobile_no.getText().toString();
                mob_no =loc_text+ mobile_no.getText().toString();
                password = pass.getText().toString();

                if (mobile_string.equals("")) {
                    mobile_no.requestFocus();

                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter Your Mobile Number", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();
//                    snackbar = Snackbar
//                            .make(coordinatorLayout,toast_mob, Snackbar.LENGTH_LONG);

                    //snackbar.setActionTextColor(R.color.colorAccent);
//                    View snackbarView = snackbar.getView();
//                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                    tv.setTextColor(Color.RED);
//                    //tv.setText("abc");
//                    snackbar.show();
                  //  Toast.makeText(LoginActivity.this, "Enter Your Mobile Number", Toast.LENGTH_LONG).show();
//
//                }else if (loc_text == null) {
//                    snackbar = Snackbar
//                            .make(coordinatorLayout, "Please select country code", Snackbar.LENGTH_LONG);
//
//                    //snackbar.setActionTextColor(R.color.colorAccent);
//                    View snackbarView = snackbar.getView();
//                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                    tv.setTextColor(Color.RED);
//                    //tv.setText("abc");
//                    snackbar.show();

                } else if (password.equals("")) {
                    pass.requestFocus();

                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter Your Password", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();

                    //Toast.makeText(LoginActivity.this, "Enter Your Password", Toast.LENGTH_LONG).show();
//                    Snackbar snackbar = Snackbar
//                            .make(coordinatorLayout, pass_toast, Snackbar.LENGTH_LONG);
//                    View snackbarView = snackbar.getView();
//                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                    tv.setTextColor(Color.RED);
//                    snackbar.show();
                } else if (password.contains(" ")) {
                    pass.requestFocus();

                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Password should not contain spaces", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();

                //    Toast.makeText(LoginActivity.this, "Password should not contain spaces", Toast.LENGTH_LONG).show();
//                    Snackbar snackbar = Snackbar
//                            .make(coordinatorLayout, "Password should not contain spaces", Snackbar.LENGTH_LONG);
//                    View snackbarView = snackbar.getView();
//                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                    tv.setTextColor(Color.RED);
//                    snackbar.show();
                } else {



                    try{

                        JSONObject jsonObject = new JSONObject();
                        JSONObject post_Object = new JSONObject();

                        jsonObject.put("UserName",mob_no);
                        jsonObject.put("Password",password);
                        post_Object.put("UserRequest",jsonObject);
                        System.out.println("postobjj"+post_Object);

                        Login_post.login_posting(LoginActivity.this, Urls.LOGIN,post_Object, new VoleyJsonObjectCallback()  {
                            @Override
                            public void onSuccessResponse(JSONObject result) {
                                System.out.println("111111user" + result);
                                try{
                                    JSONObject jsonObject;
                                    JSONObject userObject;

                                    jsonObject = result.getJSONObject("ResultObject");

                                    if(!(jsonObject.isNull("user"))){
                                        userObject = jsonObject.getJSONObject("user");
                                        status=jsonObject.getString("Status");
                                        userId=jsonObject.getString("UserId");

                                        System.out.println("useridddd"+userId);

                                        if(status.equals("1")){
                                            System.out.println("jdhyusulogin"+status);
                                            Intent intent = new Intent(LoginActivity.this, LandingPageActivity.class);
                                            startActivity(intent);
                                            sessionManager.createLoginSession(password,mob_no);

                                            sessionManager.save_name(userObject.getString("FullName"),userObject.getString("PhoneNo"));
                                            sessionManager.saveUserId(userObject.getString("Id"));
                                           /* if(remember_me.isChecked()){

                                                if(!myDb.isEmailExists(mobile_no.getText().toString())){

                                                    AddData(mobile_no.getText().toString(),password);
                                                }
                                            }*/

                                            if(remember_me.isChecked()){

                                                if(!myDb.isEmailExists(mobile_no.getText().toString())){

                                                    AddData(mobile_no.getText().toString(),password);
                                                }
                                            }else {
                                                if(myDb.isEmailExists(mobile_no.getText().toString())){

                                                    DeleteData(mobile_no.getText().toString(),password);
                                                }
                                            }
                                        }


                                    } else{

                                        Snackbar snackbar = Snackbar
                                                .make(coordinatorLayout, jsonObject.getString("Message"), Snackbar.LENGTH_LONG);
                                        View snackbarView = snackbar.getView();
                                        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                                        tv.setTextColor(Color.RED);
                                        snackbar.show();


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
        });

    }

    @Override
       public void onBackPressed() {
           //System.exit(0);
           if (doubleBackToExitPressedOnce) {

               Intent intent1 = new Intent(Intent.ACTION_MAIN);
               intent1.addCategory(Intent.CATEGORY_HOME);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                startActivity(intent1);
               finish();
               System.exit(0);                    }

           doubleBackToExitPressedOnce = true;
            Toast.makeText(getApplicationContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

           new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 3000);


       }

    public void edittext_move(final EditText et1, final EditText et2) {
        et1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (et1.getText().toString().length() == 10)     //size as per your requirement
                {


                    Cursor res = myDb.getAllData(et1.getText().toString().trim());


                    if (res.getCount() == 0) {

                        et2.requestFocus();


                        return;
                    }

                    StringBuffer buffer = new StringBuffer();

                    // System.out.println("hhhhhhhhhhhhhhhhh"+buffer.append(res.getString(0)));

                    while (res.moveToNext()) {
                        buffer.append(res.getString(0) + "\n");
                    }
                    System.out.println("gggggggggggggggggggggjjjjjjjjjjj"+buffer.toString().trim());
                    et2.setText(buffer.toString().trim());
                    remember_me.setChecked(true);
                    System.out.println("wwwwwwwwwwwwwwwww"+et2);


                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
    }
    private void AddData(String userId,String pass) {
        System.out.println("kkkkkkkkkkkkk"+userId);
        System.out.println("sssssssssssss"+pass);
        boolean isInserted = myDb.insertData(userId, pass);

        System.out.println("kkkkkkkkkkkkk"+userId);
        System.out.println("sssssssssssss"+pass);
        if (isInserted == true){
        } else{

        }
    }
    private void DeleteData(String userId,String pass) {
        System.out.println("kkkkkkkkkkkkk"+userId);
        System.out.println("sssssssssssss"+pass);
        //  boolean isInserted = myDb.insertData(userId, pass);
        myDb.deleteData(userId);

    }



    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(LoginActivity.this);
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


    }