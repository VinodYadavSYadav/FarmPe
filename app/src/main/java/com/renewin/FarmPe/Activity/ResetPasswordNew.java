package com.renewin.FarmPe.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.renewin.FarmPe.DB.DatabaseHelper;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;


public class ResetPasswordNew extends AppCompatActivity {
   LinearLayout back_reset_pass,linearLayout;
   TextView pass_submit,reset_text,to_continue_text;
    EditText passwd,conf_pass;
    JSONObject lngObject;
    SessionManager sessionManager;
    DatabaseHelper myDb;
    TextInputLayout passwd1_text_input,conf_pass_textinput;
    String forgot_username,localize_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);

        back_reset_pass=findViewById(R.id.arrow_reset_pass);
        pass_submit=findViewById(R.id.password_submit);
        passwd=findViewById(R.id.passwd1);
        conf_pass=findViewById(R.id.conf_pass1);
        linearLayout=findViewById(R.id.main_layout);
        reset_text=findViewById(R.id.reset);
        to_continue_text=findViewById(R.id.tocnt);
        myDb = new DatabaseHelper(this);

        edittext_move(ForgotPasswordNew.mobileno, passwd);

        setupUI(linearLayout);

        sessionManager = new SessionManager(ResetPasswordNew.this);

        try {

            lngObject = new JSONObject(sessionManager.getRegId("language"));

            reset_text.setText(lngObject.getString("ResetPassword"));
            to_continue_text.setText(lngObject.getString("ToContinue"));
            conf_pass.setHint(lngObject.getString("EnterPassword"));
            passwd.setHint(lngObject.getString("ReEnterPassword"));
            pass_submit.setText(lngObject.getString("Submit"));


        } catch (JSONException e) {
            e.printStackTrace();
        }


        back_reset_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResetPasswordNew.this,Thank_U_New.class);
                startActivity(intent);
            }
        });
        pass_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String password = passwd.getText().toString();
                final String confirmP = conf_pass.getText().toString();
                if (password.equals("")) {
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter The Password", Snackbar.LENGTH_LONG);
                    View snackbarView=snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();
                    //Toast.makeText(ResetPassword.this, "Enter The Password", Toast.LENGTH_LONG).show();
                    // passwrd.setError("Enter The Password");

                }else if (password.contains(" ")) {
                    passwd.requestFocus();
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Password should not contain spaces", Snackbar.LENGTH_LONG);
                    View snackbarView=snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();
                }

                else if (password.length()<6){
                    passwd.requestFocus();
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Enter Minimum 6 Characters", Snackbar.LENGTH_LONG);
                    View snackbarView=snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();
                    //Toast.makeText(ResetPassword.this, "Enter Minimum 6 Characters", Toast.LENGTH_LONG).show();
                    // passwrd.setError("Enter Minimum 6 Character");


                }
                else if (confirmP.equals("")) {
                    conf_pass.requestFocus();
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Confirm Your Password", Snackbar.LENGTH_LONG);
                    View snackbarView=snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();
                    //Toast.makeText(ResetPassword.this, "Confirm Your Password", Toast.LENGTH_LONG).show();
                    // confpass.setError("Confirm Your Password");


                }else if (!(password.equals(confirmP))){
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Password Not Matching", Snackbar.LENGTH_LONG);
                    View snackbarView=snackbar.getView();
                    TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.RED);
                    snackbar.show();
                    //Toast.makeText(ResetPassword.this, "Password Not Matching", Toast.LENGTH_LONG).show();
                    // Snackbar snackbar=Snackbar.make(v,"Password Not Matching",Snackbar.LENGTH_LONG);

                }
                else {
                    Intent intent=new Intent(ResetPasswordNew.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        //System.exit(0);

        Intent intent=new Intent(ResetPasswordNew.this,Thank_U_New.class);
        startActivity(intent);
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
                    while (res.moveToNext()) {
                        buffer.append(res.getString(0) + "\n");

                    }
                    et2.setText(buffer.toString().trim());


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

        boolean isInserted = myDb.insertData(userId, pass);
        if (isInserted == true){
        } else{

        }
    }


    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(ResetPasswordNew.this);
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
