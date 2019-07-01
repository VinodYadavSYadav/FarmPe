package com.FarmPe.India.Adapter;

import android.app.Activity;
import android.os.Bundle;
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
import com.FarmPe.India.Bean.AddTractorBean;
import com.FarmPe.India.Fragment.AddFirstFragment;
import com.FarmPe.India.R;

import java.util.List;

public class AddFirstLookingForAdapter extends RecyclerView.Adapter<AddFirstLookingForAdapter.MyViewHolder>  {
    private List<AddTractorBean> productList;
    Activity activity;
    Fragment selectedFragment;

    public LinearLayout linearLayout;
   public static LinearLayout next_arw;
    public static String first;
    public static CardView cardView;
    public AddFirstLookingForAdapter(Activity activity, List<AddTractorBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
//        session=new SessionManager(activity);

    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public LinearLayout item;
        public TextView prod_price,prod_name,duration,farmer_name,location,connect;




        public MyViewHolder(View view) {
            super(view);
            //agri_text=view.findViewById(R.id.store_agri);
           // item_2=view.findViewById(R.id.item_2);
            prod_price=view.findViewById(R.id.prod_price);
            image=view.findViewById(R.id.prod_img);
            item=view.findViewById(R.id.item);

            //linearLayout=view.findViewById(R.id.dialog_list);
            //confirmbutton=view.findViewById(R.id.delivery2);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_first_layout_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final AddTractorBean products = productList.get(position);
      //holder.agri_text.setText(products.getAgri_text());
        holder.prod_price.setText(products.getProd_name());

        Glide.with(activity).load("https://xohricontentimages.s3.us-east-2.amazonaws.com/Tractors/Mahindra/MAHINDRA+JIVO+225+DI+2WD.jpg")

                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("looking_first_id",products.getId());
                selectedFragment = AddFirstFragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("first_looking");
                selectedFragment.setArguments(bundle);
                transaction.commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        System.out.println("lengthhhhhhh"+productList.size());
        return productList.size();
    }

}