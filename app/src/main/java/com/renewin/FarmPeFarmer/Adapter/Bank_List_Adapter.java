package com.renewin.FarmPeFarmer.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.renewin.FarmPeFarmer.Bean.Bank_Account_Bean;
import com.renewin.FarmPeFarmer.R;

import java.util.List;


public class Bank_List_Adapter extends RecyclerView.Adapter<Bank_List_Adapter.MyViewHolder>{

    public static int selected_position=0;
    private List<Bank_Account_Bean> productList;
    Activity activity;
    Fragment selectedFragment ;
    public static String bank_name;
    public static String acc_number;
    public static String ifsc_code;
    public static String account_name;




    public Bank_List_Adapter(List<Bank_Account_Bean> moviesList,Activity activity) {

        this.productList = moviesList;
        this.activity=activity;

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,mobile_no;
       RadioButton bank1;


        public MyViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name1);
            mobile_no= view.findViewById(R.id.mobile_no1);
            bank1= view.findViewById(R.id.bnk_1);



        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bank_list_names_layout, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Bank_Account_Bean products = productList.get(position);


        System.out.println("llllllllllllllllll"+products.getBnk_name());

        holder.bank1.setText(products.getBnk_name());
        holder.bank1.setChecked(productList.get(position).isSelected());



        holder.bank1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                System.out.println("llllllllllllllllll"+isChecked);


                if(isChecked) {
                    for (int i = 0; i < productList.size(); i++) {
                        productList.get(i).setSelected(false);

                    }

                    bank_name=productList.get(position).getBnk_name();
                    productList.get(position).setSelected(isChecked);
                    notifyDataSetChanged();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}

