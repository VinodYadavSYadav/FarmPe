package com.renewin.Xohri.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renewin.Xohri.Bean.Kyc_Bean;
import com.renewin.Xohri.R;

import java.util.List;


public class Complete_KYC_Adapter extends RecyclerView.Adapter<Complete_KYC_Adapter.MyViewHolder>{


    private List<Kyc_Bean> productList;
    Activity activity;
    Fragment selectedFragment ;
   public static String kyc_id;




    public Complete_KYC_Adapter(List<Kyc_Bean> moviesList, Activity activity) {

        this.productList = moviesList;
        this.activity=activity;

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView doc_type,doc_numb,doc_name;
        LinearLayout add_new_adress,linearLayout;


        public MyViewHolder(View view) {
            super(view);

            doc_type = view.findViewById(R.id.doc_type);
            doc_numb= view.findViewById(R.id.doc_numb);
            doc_name= view.findViewById(R.id.doc_name);
            linearLayout= view.findViewById(R.id.linearedit);


        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kyc_details_cardview, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Kyc_Bean products = productList.get(position);

        holder.linearLayout.setVisibility(View.GONE);
        kyc_id = products.getKyc_id();

        holder.doc_type.setText(products.getKyc_document());
        holder.doc_numb.setText(products.getKyc_number());
        holder.doc_name.setText(products.getKyc_name());




    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}

