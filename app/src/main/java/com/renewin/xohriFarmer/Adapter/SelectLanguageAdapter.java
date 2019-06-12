package com.renewin.xohriFarmer.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renewin.xohriFarmer.Activity.XLoginNew;
import com.renewin.xohriFarmer.Bean.SelectLanguageBean;
import com.renewin.xohriFarmer.R;
import com.renewin.xohriFarmer.SessionManager;
import com.renewin.xohriFarmer.Urls;
import com.renewin.xohriFarmer.Volly_class.Login_post;
import com.renewin.xohriFarmer.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

public class SelectLanguageAdapter extends RecyclerView.Adapter<SelectLanguageAdapter.MyViewHolder>  {
    private List<SelectLanguageBean> productList;
    Activity activity;
    Fragment selectedFragment;
    Date o_date;
    SessionManager session;
    public static CardView cardView;
    public SelectLanguageAdapter(Activity activity, List<SelectLanguageBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
        session=new SessionManager(activity);


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView language_name;
        public LinearLayout submit_langu;


        public MyViewHolder(View view) {
            super(view);
            language_name=view.findViewById(R.id.lang_text);
            submit_langu=view.findViewById(R.id.submit_langu_layout);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.language_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final SelectLanguageBean products = productList.get(position);

        holder.language_name.setText(products.getVendor());
        holder.submit_langu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                session.save_Language(products.getLanguageid());

                JSONObject postlngjsonObject = new JSONObject();


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
                        Intent intent=new Intent(activity, XLoginNew.class);
                        intent.putExtra("my_data",result.toString() );

                        activity.startActivity(intent);

                    }
                });


            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}