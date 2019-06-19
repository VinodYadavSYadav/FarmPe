package com.renewin.Xohri.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.renewin.Xohri.Bean.Transaction_Bean;
import com.renewin.Xohri.R;

import java.util.List;

public class Transaction_Adapter extends RecyclerView.Adapter<Transaction_Adapter.MyViewHolder>{


    private List<Transaction_Bean> productList;
    Activity activity;

    Fragment selectedFragment ;



    public Transaction_Adapter(List<Transaction_Bean> moviesList,Activity activity) {

        this.productList = moviesList;
        this.activity=activity;

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,mobile_no,trans_descrip,transac_type,amount;



        public MyViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name1);
            mobile_no= view.findViewById(R.id.mobile_no1);
            trans_descrip= view.findViewById(R.id.trans_descrip);
            transac_type= view.findViewById(R.id.transac_type);
            amount= view.findViewById(R.id.amount);


        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_details_layout, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Transaction_Bean products = productList.get(position);


     holder.trans_descrip.setText(products.getDescription());
     holder.transac_type.setText(products.getTransact_type());
     holder.amount.setText("Amount" + " - " +products.getAmount());





    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}

