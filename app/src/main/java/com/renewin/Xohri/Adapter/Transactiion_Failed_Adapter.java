package com.renewin.Xohri.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.renewin.Xohri.Bean.Transaction_Bean;
import com.renewin.Xohri.R;
import com.renewin.Xohri.SessionManager;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Transactiion_Failed_Adapter extends RecyclerView.Adapter<Transactiion_Failed_Adapter.MyViewHolder>  {

    private List<Transaction_Bean> productList;
    Activity activity;
    Fragment selectedFragment;
    Date o_date;
    public String status,status1;
   SessionManager sessionManager;
    public static CardView cardView;
    DecimalFormat formatter;


    public Transactiion_Failed_Adapter(Activity activity, List<Transaction_Bean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
      sessionManager=new SessionManager(activity);

    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView trans_descrip,transac_type,amount;



        public MyViewHolder(View view) {
            super(view);

            trans_descrip= view.findViewById(R.id.trans_descrip);
            transac_type= view.findViewById(R.id.transac_type);
            amount= view.findViewById(R.id.amount);


        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_details_layout, parent, false);
        formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);;
        formatter .applyPattern("##,##,##,###");
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Transaction_Bean products = productList.get(position);

        holder.trans_descrip.setText(products.getDescription());
        holder.transac_type.setText(products.getTransact_type());
        holder.amount.setText("Amount" + " - " +products.getAmount());


       /* try {
            double rate_double= (Integer.parseInt(products.getPrice())*Integer.parseInt(products.getQuantity()));
            double rate_double1= (Integer.parseInt(products.getPrice()));
            formatter.format(rate_double);

            holder.price.setText("₹. "   + formatter.format(rate_double) );
            holder.cropname.setText(products.getCrop_name() + "  -  " +  products.getVariety() + " , " + products.getQuantity() + " " + products.getUom() +" , "+ "₹ " +formatter.format(rate_double1)+" / " + products.getUom());


         }catch (Exception e){
            e.printStackTrace();
        }*/


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}