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

import com.FarmPe.India.Bean.FarmsImageBean;
import com.FarmPe.India.Bean.ShortListBean;
import com.FarmPe.India.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class ShortListAdapter extends RecyclerView.Adapter<ShortListAdapter.MyViewHolder>  {
    private List<ShortListBean> productList;
    Activity activity;
    Fragment selectedFragment;

    public LinearLayout linearLayout;
   public static LinearLayout next_arw;
    public static String first;
    public static CardView cardView;
    public ShortListAdapter(Activity activity, List<ShortListBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
//        session=new SessionManager(activity);

    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView farm,model,name;



        public MyViewHolder(View view) {
            super(view);
            farm=view.findViewById(R.id.farm_name);
            model=view.findViewById(R.id.model_nmae);
            name=view.findViewById(R.id.name);
            image=view.findViewById(R.id.prod_img);

            //linearLayout=view.findViewById(R.id.dialog_list);
            //confirmbutton=view.findViewById(R.id.delivery2);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shortlist_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ShortListBean products = productList.get(position);

        System.out.println("allllll"+products.getBrandname()+" "+products.getState());
        holder.farm.setText(products.getFarm());
        holder.model.setText(products.getBrandname().replace("\r\n","")+" "+products.getModel()+" "+products.getHp());
        holder.name.setText(products.getName()+", "+products.getDistrict()+", "+products.getState());


        Glide.with(activity).load(products.getImage())

                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        System.out.println("lengthhhhhhh"+productList.size());
        return productList.size();
    }

}