package com.renewin.Xohri.Adapter;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.renewin.Xohri.Bean.Transportation_Bean;
import com.renewin.Xohri.Fragment.Add_Transportation_Details_Fragment;
import com.renewin.Xohri.R;
import com.renewin.Xohri.SessionManager;
import com.renewin.Xohri.Urls;
import com.renewin.Xohri.Utils;
import com.renewin.Xohri.Volly_class.Crop_Post;
import com.renewin.Xohri.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONObject;

import java.util.List;

public class Transportation_Adapter extends RecyclerView.Adapter<Transportation_Adapter.MyViewHolder>{


    private List<Transportation_Bean> productList;
    Activity activity;
    Fragment selectedFragment ;
    SessionManager sessionManager;
    String status,message;

    public  String transpotation_id;

    public Transportation_Adapter(List<Transportation_Bean> moviesList, Activity activity) {

        this.productList = moviesList;
        this.activity=activity;

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

       public TextView veh_type,veh_no,name,edit,delete;
        LinearLayout add_new_adress;


        public MyViewHolder(View view) {
            super(view);

            veh_type = view.findViewById(R.id.doc_type);
            veh_no = view.findViewById(R.id.doc_numb);
            name = view.findViewById(R.id.doc_name);
            edit = view.findViewById(R.id.edit_1);
            delete = view.findViewById(R.id.delete_1);


            sessionManager = new SessionManager(activity);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kyc_details_layout, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Transportation_Bean products = productList.get(position);


        holder.veh_type.setText(products.getTrans_type());
        holder.veh_no.setText(products.getVeh_num());
        holder.name.setText(products.getName());

       /* holder.name.setText("Truck");
        holder.vehicle_no.setText("Vehicle No"+" - KA 9089");
        holder.txt_1.setText("Lorem Ipsum");*/

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transpotation_id = products.getTransptn_id();

                Bundle bundle = new Bundle();
                bundle.putString("TransportationType", products.getTrans_type());
                bundle.putString("VehicleNumber", products.getVeh_num());
                bundle.putString("OwnerName", products.getName());

                bundle.putString("Id", transpotation_id);

                bundle.putString("ADD_NTRANSPOTATION", "TRANSPOTATION_ADAPTR");


                selectedFragment = Add_Transportation_Details_Fragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                selectedFragment.setArguments(bundle);
                transaction.commit();

            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                transpotation_id = products.getTransptn_id();
                final TextView yes1, no1, text_desc, heading;
                System.out.println("deleteeeeeeeeeeeeeeeeee");
                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.address_delete_popup);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(Utils.setCancleable);


                text_desc = dialog.findViewById(R.id.text_desc);
                text_desc.setText("Are you sure you want to delete the Transportation details?");

                heading = dialog.findViewById(R.id.popup_heading);
                heading.setText("Remove Transportation Details");

                ImageView image = dialog.findViewById(R.id.close_popup);
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                no1 = dialog.findViewById(R.id.no_1);
                no1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                yes1 = dialog.findViewById(R.id.yes_1);
                yes1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        transpotation_id = products.getTransptn_id();


                        try {

                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("Id", transpotation_id);
                            jsonObject.put("CreatedBy", sessionManager.getRegId("userId"));

                            Crop_Post.crop_posting(activity, Urls.DeleteTransportationDetails, jsonObject, new VoleyJsonObjectCallback() {
                                @Override
                                public void onSuccessResponse(JSONObject result) {
                                    System.out.println("DeleteTransportationDetailsssssssssssss" + result);

                                    try {

                                        status = result.getString("Status");
                                        message = result.getString("Message");

                                        if (status.equals("1")) {

                                            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                                            productList.remove(position);
                                            notifyDataSetChanged();

                                        }


                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        dialog.dismiss();

                    }
                });

                dialog.show();

            }


        });

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

}

