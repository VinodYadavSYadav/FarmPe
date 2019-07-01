package com.FarmPe.Farmer.Adapter;

import android.app.Activity;
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
import com.FarmPe.Farmer.Bean.FarmsImageBean;
import com.FarmPe.Farmer.Fragment.LokingForDetailsFragment;
import com.FarmPe.Farmer.R;
import com.FarmPe.Farmer.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class FarmsImageAdapter extends RecyclerView.Adapter<FarmsImageAdapter.MyViewHolder>  {
    private List<FarmsImageBean> productList;
    Activity activity;
    Fragment selectedFragment;
    JSONObject lngObject;
    public LinearLayout linearLayout;
   public static LinearLayout next_arw;
    public static String first,looking_forId;
 SessionManager session;
    boolean flag;
    public static CardView cardView;
    public FarmsImageAdapter(Activity activity, List<FarmsImageBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
    session=new SessionManager(activity);

    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image,short_list_image;;
        public TextView prod_price,prod_name,duration,farmer_name,location,connect;
        public LinearLayout shortlist_layout;



        public MyViewHolder(View view) {
            super(view);
            //agri_text=view.findViewById(R.id.store_agri);
           // item_2=view.findViewById(R.id.item_2);
            prod_price=view.findViewById(R.id.prod_price);
            prod_name=view.findViewById(R.id.prod_name);
            duration=view.findViewById(R.id.duration);
            farmer_name=view.findViewById(R.id.farmer_name);
            location=view.findViewById(R.id.location);
            connect=view.findViewById(R.id.connect);
            image=view.findViewById(R.id.prod_img);
            short_list_image=view.findViewById(R.id.short_list_image);
            shortlist_layout=view.findViewById(R.id.shortlist_layout);
            //linearLayout=view.findViewById(R.id.dialog_list);
            //confirmbutton=view.findViewById(R.id.delivery2);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.farm_img_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final FarmsImageBean products = productList.get(position);
      //holder.agri_text.setText(products.getAgri_text());
        holder.prod_price.setText(products.getProd_price());
        holder.prod_name.setText(products.getModelname()+" "+products.getHp());
     //   holder.duration.setText(products.getDuration());
        holder.farmer_name.setText(products.getFarmer_name());
        holder.location.setText(products.getLocation());
        try {
            Glide.with(activity).load(products.getImage())
          //  Glide.with(activity).load(R.drawable.tractor_sonalika)

                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.image);
    } catch (
    Exception e) {
        e.printStackTrace();
    }






        holder.connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                looking_forId=products.getId();

                selectedFragment = LokingForDetailsFragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("farmer");
                transaction.commit();
            }
        });

        holder.shortlist_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag==false){
                   holder.short_list_image.setImageResource(R.drawable.ic_star_filled);
                    flag=true;
                }else{
                    holder.short_list_image.setImageResource(R.drawable.ic_star);
                    flag=false;
                }
            }
        });

        try {
            lngObject = new JSONObject(session.getRegId("language"));
            holder.connect.setText(lngObject.getString("connect"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        System.out.println("lengthhhhhhh"+productList.size());
        return productList.size();
    }

}