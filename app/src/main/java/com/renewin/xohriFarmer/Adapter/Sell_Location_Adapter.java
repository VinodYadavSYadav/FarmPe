package com.renewin.xohriFarmer.Adapter;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.renewin.xohriFarmer.Bean.Address_Location_Bean;
import com.renewin.xohriFarmer.R;

import java.util.Date;
import java.util.List;

public class Sell_Location_Adapter extends RecyclerView.Adapter<Sell_Location_Adapter.MyViewHolder>  {
    private List<Address_Location_Bean> productList;
    Activity activity;
    Fragment selectedFragment;
    Date o_date;
    public static TextView name,variety,loc,grade,quantity,uom,price,edit;

    public static CardView cardView;


    public Sell_Location_Adapter(List<Address_Location_Bean> moviesList, Activity activity) {

        this.productList = moviesList;
        this.activity=activity;


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.selling_item_name);
        //    variety=view.findViewById(R.id.variety);

           // quantity=view.findViewById(R.id.quantity);
           // uom=view.findViewById(R.id.uom);
            price=view.findViewById(R.id.price);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.you_address_layout, parent, false);
        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Address_Location_Bean products = productList.get(position);


    }

    @Override
    public int getItemCount() {
        return 2;
    }

}