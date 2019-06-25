package com.renewin.FarmPe.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renewin.FarmPe.Activity.LoginActivity;
import com.renewin.FarmPe.Activity.SignUpActivity;
import com.renewin.FarmPe.Activity.XLoginNew;
import com.renewin.FarmPe.Bean.SelectLanguageBean;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;
import com.renewin.FarmPe.Urls;
import com.renewin.FarmPe.Volly_class.Crop_Post;
import com.renewin.FarmPe.Volly_class.Login_post;
import com.renewin.FarmPe.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

public class SelectLanguageAdapter extends RecyclerView.Adapter<SelectLanguageAdapter.MyViewHolder>  {
    private List<SelectLanguageBean> productList;
    SelectLanguageBean selectLanguageBean;
    Activity activity;
    JSONArray lng_array;
    Fragment selectedFragment;
    Date o_date;
    SessionManager sessionManager;
    public static int selected_position=0;

    public static CardView cardView;
    public SelectLanguageAdapter(Activity activity, List<SelectLanguageBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
        sessionManager=new SessionManager(activity);


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView language_name;
        public LinearLayout submit_langu;
        public ImageView right_img;


        public MyViewHolder(View view) {
            super(view);
            language_name=view.findViewById(R.id.lang_text);
            submit_langu=view.findViewById(R.id.submit_langu_layout);
            right_img=view.findViewById(R.id.right_img);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.language_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final SelectLanguageBean products = productList.get(position);

        if (selected_position==position){
            holder.right_img.setImageResource(R.drawable.ic_verified_filled_grey_white);
            //  holder.lng_rad_but.setBackgroundColor(Color.GREEN);

        }else {

            holder.right_img.setImageResource(R.drawable.filled_grey_circle);

//            holder.right_img.setImageResource(R.drawable.v);

            //  holder.lng_rad_but.setBackgroundColor(Color.WHITE);


        }

        holder.language_name.setText(products.getVendor());

        holder.submit_langu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_position = position;
                notifyDataSetChanged();
                sessionManager.saveLanguage_name(products.getVendor());
                getLang(products.getLanguageid());




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