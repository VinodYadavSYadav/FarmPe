package com.renewin.FarmPe.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.renewin.FarmPe.Bean.Bank_Account_Bean;
import com.renewin.FarmPe.Fragment.Add_New_Bank_Details_Fragment;
import com.renewin.FarmPe.R;
import com.renewin.FarmPe.SessionManager;
import com.renewin.FarmPe.Urls;
import com.renewin.FarmPe.Utils;
import com.renewin.FarmPe.Volly_class.Crop_Post;
import com.renewin.FarmPe.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONObject;

import java.util.List;

public class Bank_Account_Details_Adapter extends RecyclerView.Adapter<Bank_Account_Details_Adapter.MyViewHolder>{


    private List<Bank_Account_Bean> productList;
    Activity activity;
    Fragment selectedFragment ;
    public static String bank_id,bank_name,acc_number,account_name,ifsc_code;
    String status,message;
    SessionManager sessionManager;



    public Bank_Account_Details_Adapter(List<Bank_Account_Bean> moviesList, Activity activity) {

        this.productList = moviesList;
        this.activity=activity;

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,bank_name,acc_holder_name,acc_number,ifsc_code,edit_1,delete_1;



        public MyViewHolder(View view) {
            super(view);

            bank_name = view.findViewById(R.id.bank_name);
            acc_holder_name= view.findViewById(R.id.acc_holder_name);
            acc_number= view.findViewById(R.id.acc_number);
            ifsc_code= view.findViewById(R.id.ifsc_code);
            edit_1= view.findViewById(R.id.edit_1);
            delete_1= view.findViewById(R.id.delete_1);

            sessionManager = new SessionManager(activity);


        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bank_detail_layout, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Bank_Account_Bean products = productList.get(position);

        bank_name = products.getBnk_name();
                account_name = products.getAcc_name();
                        acc_number=products.getAcc_no();
        ifsc_code=products.getIfsc_cod();




        holder.bank_name.setText(products.getBnk_name());
        holder.acc_holder_name.setText(products.getAcc_name());
        holder.acc_number.setText(products.getAcc_no());
        holder.ifsc_code.setText(products.getIfsc_cod());



        holder.delete_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bank_id = products.getBnk_id();


                final TextView yes1,no1,text_desc,heading;
                System.out.println("aaaaaaaaaaaa");
                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.address_delete_popup);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(Utils.setCancleable);


                text_desc = dialog.findViewById(R.id.text_desc);
                text_desc.setText("Are you sure you want to delete the bank details?");

                heading =  dialog.findViewById(R.id.popup_heading);
                heading.setText("Remove Bank Details");

                ImageView image = dialog.findViewById(R.id.close_popup);
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                no1 =  dialog.findViewById(R.id.no_1);
                no1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                yes1 =  dialog.findViewById(R.id.yes_1);
                yes1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bank_id = products.getBnk_id();


                        try{

                            JSONObject jsonObject  = new JSONObject();
                            jsonObject.put("Id",bank_id);
                            jsonObject.put("UserId",sessionManager.getRegId("userId"));

                            Crop_Post.crop_posting(activity, Urls.Delete_Bank_Details, jsonObject, new VoleyJsonObjectCallback() {
                                @Override
                                public void onSuccessResponse(JSONObject result) {
                                    System.out.println("111111dddd" + result);

                                    try{

                                        status = result.getString("Status");
                                        message = result.getString("Message");

                                        if(status.equals("1")){

                                            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                                            productList.remove(position);
                                            notifyDataSetChanged();

                                        }



                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            });


                        }catch (Exception e){
                            e.printStackTrace();
                        }


        dialog.dismiss();

                    }
                });

       dialog.show();

             }
        });



        holder.edit_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                bank_id = products.getBnk_id();

                Bundle bundle = new Bundle();
                bundle.putString("Bank_name",products.getBnk_name());
                bundle.putString("Account_number",products.getAcc_no());
                bundle.putString("Ifsc_Code",products.getIfsc_cod());
                bundle.putString("Acc_name",products.getAcc_name());
                bundle.putString("BankId",bank_id);

                bundle.putString("ADD_NBANK", "BANK_ADAPTR");



                selectedFragment = Add_New_Bank_Details_Fragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_home, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.commit();


            }
        });



    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}

