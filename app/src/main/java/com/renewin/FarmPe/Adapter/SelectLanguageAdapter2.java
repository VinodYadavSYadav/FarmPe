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

import com.renewin.FarmPe.Activity.LoginActivity;
import com.renewin.FarmPe.Bean.SelectLanguageBean;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;

import java.util.Date;
import java.util.List;

public class SelectLanguageAdapter2 extends RecyclerView.Adapter<SelectLanguageAdapter2.MyViewHolder>  {
    private List<SelectLanguageBean> productList;
    Activity activity;
    Fragment selectedFragment;
    Date o_date;
    SessionManager session;
    public static int selected_position=0;

    public static CardView cardView;
    public SelectLanguageAdapter2(Activity activity, List<SelectLanguageBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
        session=new SessionManager(activity);


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
        final SelectLanguageBean products = productList.get(position);


        holder.language_name.setText(products.getVendor());
        holder.language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.dialog.dismiss();
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}