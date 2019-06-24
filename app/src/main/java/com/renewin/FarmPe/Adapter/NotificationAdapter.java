package com.renewin.FarmPe.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.renewin.FarmPe.Bean.FarmsImageBean;
import com.renewin.FarmPe.Bean.NotificationBean;
import com.renewin.FarmPe.Fragment.AddModelFragment;
import com.renewin.FarmPe.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder>  {
    private List<NotificationBean> productList;
    Activity activity;
    Fragment selectedFragment;

    public LinearLayout linearLayout;
   public static LinearLayout next_arw;
    public static String first;
    public static CardView cardView;
    public NotificationAdapter(Activity activity, List<NotificationBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
//        session=new SessionManager(activity);

    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView notification,view_text;
        public LinearLayout noti_layout;



        public MyViewHolder(View view) {
            super(view);
            //agri_text=view.findViewById(R.id.store_agri);
            noti_layout=view.findViewById(R.id.noti_layout);
            notification=view.findViewById(R.id.noti_text);
            view_text=view.findViewById(R.id.view_text);

            //linearLayout=view.findViewById(R.id.dialog_list);
            //confirmbutton=view.findViewById(R.id.delivery2);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final NotificationBean products = productList.get(position);
      //holder.agri_text.setText(products.getAgri_text());
        holder.notification.setText(products.getNotification());

        holder.view_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.noti_layout.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.notification.setTextColor(Color.parseColor("#000000"));
                holder.view_text.setVisibility(View.INVISIBLE);
                /*selectedFragment = AddModelFragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("hp");
                transaction.commit();*/
            }
        });

       /* Glide.with(activity).load(products.getImage())

                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);*/


    }

    @Override
    public int getItemCount() {
        System.out.println("lengthhhhhhh"+productList.size());
        return productList.size();
    }

}