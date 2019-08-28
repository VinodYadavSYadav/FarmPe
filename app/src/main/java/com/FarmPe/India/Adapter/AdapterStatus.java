package com.FarmPe.India.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.India.Bean.NotificationBean;
import com.FarmPe.India.Bean.StatusBean;
import com.FarmPe.India.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterStatus extends RecyclerView.Adapter<AdapterStatus.MyViewHolder>  {
    private List<StatusBean> productList;
    Activity activity;
    Fragment selectedFragment;
    public LinearLayout linearLayout;


    public static LinearLayout next_arw;
    public static String first;
    public static CardView cardView;
    public AdapterStatus(Activity activity, List<StatusBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
//        session=new SessionManager(activity);

    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name_status,timings;
        public LinearLayout noti_layout;
        public CircleImageView imageView;
        public View view_line;


        public MyViewHolder(View view) {
            super(view);
            //agri_text=view.findViewById(R.id.store_agri);
            noti_layout=view.findViewById(R.id.noti_layout);
            name_status=view.findViewById(R.id.name_status);
            timings=view.findViewById(R.id.timings);
            imageView=view.findViewById(R.id.prod_img);
            view_line=view.findViewById(R.id.view_line);

            linearLayout=view.findViewById(R.id.noti_layout);
            //confirmbutton=view.findViewById(R.id.delivery2);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.a_ns_status_recent, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final StatusBean products = productList.get(position);
        //holder.agri_text.setText(products.getAgri_text());
        holder.name_status.setText(products.getStatusName());
        holder.timings.setText(products.getStatusTime());

        if (position==getItemCount()-1){
            holder.view_line.setVisibility(View.INVISIBLE);
        }else{
            holder.view_line.setVisibility(View.VISIBLE);

        }

        Glide.with(activity).load(R.drawable.jk)

                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        System.out.println("lengthhhhhhh"+productList.size());
        return productList.size();
    }

}