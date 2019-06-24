package com.renewin.FarmPe.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.renewin.FarmPe.Bean.NearByHorizontalBean;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NearByHorizontalAdapter extends RecyclerView.Adapter<NearByHorizontalAdapter.MyViewHolder>  {
    private List<NearByHorizontalBean> productList;
    Activity activity;
    Fragment selectedFragment;
    SessionManager sessionManager;
 String userid;
 public static String item_name,item_variety,item_price,item_uom,item_quantity,id;
 public static int farmer_id;

    //    SessionManager session;

    public NearByHorizontalAdapter(Activity activity, List<NearByHorizontalBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
//        session=new SessionManager(activity);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
       ImageView imageView;
        public LinearLayout item;
        public MyViewHolder(View view) {
            super(view);
            imageView=view.findViewById(R.id.image_near);

        }



    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nearby_horizontal_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final NearByHorizontalBean products = productList.get(position);
        Glide.with(activity).load(products.getImage())

                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);



    }



    @Override
    public int getItemCount() {

        System.out.println("e     e e "+productList.size());

        return productList.size();
    }

}