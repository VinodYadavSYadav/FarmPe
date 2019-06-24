package com.renewin.FarmPe.Adapter;

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

import com.renewin.FarmPe.Bean.SelectLanguageBean;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;

import java.util.Date;
import java.util.List;

public class SelectLanguageAdapter extends RecyclerView.Adapter<SelectLanguageAdapter.MyViewHolder>  {
    private List<SelectLanguageBean> productList;
    Activity activity;
    Fragment selectedFragment;
    Date o_date;
    SessionManager session;
    public static int selected_position=0;
    public static int selected_post =0;

    public static CardView cardView;
    public SelectLanguageAdapter(Activity activity, List<SelectLanguageBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
        session=new SessionManager(activity);


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
            holder.right_img.setImageResource(R.drawable.verified_drawable);
            //  holder.lng_rad_but.setBackgroundColor(Color.WHITE);


        }



        holder.language_name.setText(products.getVendor());
        holder.submit_langu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_position = position;
                notifyDataSetChanged();
                session.save_Language(products.getLanguageid());


                /*holder.right_img.setBackground(R.drawable.ic_verified_filled_grey_white);*/
                /*JSONObject postlngjsonObject = new JSONObject();


                try {
                    postlngjsonObject.put("Id", products.getLanguageid());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Login_post.login_posting(activity, Urls.getting_Languages,postlngjsonObject,new VoleyJsonObjectCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject result) {
                        System.out.println("statussssss"+result);

                        session.saveLangDetails(result.toString());
                       *//* Intent intent=new Intent(activity, XLoginNew.class);
                        intent.putExtra("my_data",result.toString() );

                        activity.startActivity(intent);*//*

                    }
                });

*/
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}