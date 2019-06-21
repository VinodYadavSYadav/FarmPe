package com.renewin.FarmPe.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renewin.FarmPe.Activity.LoginActivity;
import com.renewin.FarmPe.Activity.SignUpActivity;
import com.renewin.FarmPe.Bean.SelectLanguageBean;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;
import com.renewin.FarmPe.Urls;
import com.renewin.FarmPe.Volly_class.Crop_Post;
import com.renewin.FarmPe.Volly_class.VoleyJsonObjectCallback;

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

                getLang(products1.getLanguageid());
                LoginActivity.dialog.dismiss();
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

                    System.out.println("qqqqqqvv" + result);

                    try{

                        sessionManager.saveLanguage(result.toString());

                      /*  LoginActivity.remember_me.setText(log_remember_me);
                        LoginActivity.log_in.setText(log_login);
                        LoginActivity.forgot_pass.setText(log_forgot_passwrd);
                        LoginActivity.pass.setText(log_password);*/


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