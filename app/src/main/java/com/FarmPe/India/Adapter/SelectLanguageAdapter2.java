package com.FarmPe.India.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.India.Activity.LoginActivity_new;
import com.FarmPe.India.Bean.SelectLanguageBean;
import com.FarmPe.India.R;
import com.FarmPe.India.SessionManager;
import com.FarmPe.India.Urls;
import com.FarmPe.India.Volly_class.Crop_Post;
import com.FarmPe.India.Volly_class.VoleyJsonObjectCallback;
import org.json.JSONObject;
import java.util.Date;
import java.util.List;




public class SelectLanguageAdapter2 extends RecyclerView.Adapter<SelectLanguageAdapter2.MyViewHolder>  {
    private List<SelectLanguageBean> productList;
    Activity activity;


    Fragment selectedFragment;
    Date o_date;
    SessionManager sessionManager;
    public static int selected_position=0;



    public static CardView cardView;
    public SelectLanguageAdapter2(Activity activity, List<SelectLanguageBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
        sessionManager = new SessionManager(activity);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView language_name;
        public LinearLayout language;
        public ImageView right_img;




        public MyViewHolder(View view) {
            super(view);
            language_name=view.findViewById(R.id.lang_text);
            language=view.findViewById(R.id.language);
            right_img=view.findViewById(R.id.right_img);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.language_item_2, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final SelectLanguageBean products1 = productList.get(position);


        holder.language_name.setText(products1.getVendor());

        System.out.print("iiidddddd" + products1.getLanguageid());

        holder.language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sessionManager.saveLanguage_name(products1.getVendor());
                getLang(products1.getLanguageid());
                LoginActivity_new.change_lang.setText(products1.getVendor());
                LoginActivity_new.dialog.dismiss();

            }
        });
    }


    private void getLang(int id) {

        try{


              JSONObject jsonObject = new JSONObject();
              jsonObject.put("Id",id);


               System.out.print("iiidddddd"+ id);

               Crop_Post.crop_posting(activity, Urls.CHANGE_LANGUAGE, jsonObject, new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {

                    System.out.println("qqqqqqvvhhhhhhhhhhhh" + result);

                    try{



                         sessionManager.saveLanguage(result.toString());


                        String lang_title1 = result.getString("ChangeLanguage");
                        System.out.println("ckkkkkkk"+result.getString("ChangeLanguage"));
                        LoginActivity_new.popup_heading.setText(result.getString("ChangeLanguage"));
                        System.out.println("ppppoooo"+LoginActivity_new.popup_heading.getText().toString());

                          String log_login = result.getString("Login");
                          String log_mobile = result.getString("DigitMobileNumber");
                          String log_password = result.getString("Password");
                          String log_remember_me = result.getString("RememberMe");
                          String log_forgot_passwrd = result.getString("ForgotPassword");
                          String log_register = result.getString("Register");

                          
                      //  LoginActivity_new_new.popup_heading.setText(lang_title1);

                           LoginActivity_new.remember_me.setText(log_remember_me);
                           LoginActivity_new.log_in.setText(log_login);
                           LoginActivity_new.forgot_pass.setText(log_forgot_passwrd+ "?");
                           LoginActivity_new.text_pass.setHint(log_password);
                           LoginActivity_new.welcome_back.setText(log_login);
                           LoginActivity_new.createaccount.setText(log_register);
                           LoginActivity_new.text_mobile.setHint(log_mobile);
                           LoginActivity_new.mob_toast = result.getString("EnterPhoneNo");
                           LoginActivity_new.pass_toast = result.getString("EnterPassword");
                           LoginActivity_new.toast_invalid = result.getString("InvalidCredentials");
                           LoginActivity_new.toast_click_back = result.getString("PleaseclickBACKagaintoexit");



                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }


    }


    @Override
    public int getItemCount() {
        return productList.size();
    }
}