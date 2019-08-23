package com.FarmPe.India.Adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmPe.India.Fragment.ConnectionSuccessfulFragment;
import com.FarmPe.India.Fragment.DashboardFragment;
import com.FarmPe.India.Fragment.InvitationConnectionFragment;
import com.FarmPe.India.Urls;
import com.FarmPe.India.Volly_class.Login_post;
import com.FarmPe.India.Volly_class.VoleyJsonObjectCallback;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.FarmPe.India.Bean.FarmsImageBean;
import com.FarmPe.India.R;
import com.FarmPe.India.SessionManager;

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
    public static int isShortListed;
    SessionManager session;
    boolean flag,isShortlisted;
    SessionManager sessionManager;
    String createdBy,mainId,userid;


    public static CardView cardView;
    public FarmsImageAdapter(Activity activity, List<FarmsImageBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
        session=new SessionManager(activity);

    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image,short_list_image;
        public TextView prod_price,prod_name,duration,farmer_name,location,connect;
        public LinearLayout shortlist_layout,linear_looking_main;



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
            image=view.findViewById(R.id.image_looking);
            short_list_image=view.findViewById(R.id.short_list_image);
            shortlist_layout=view.findViewById(R.id.shortlist_layout);
            linear_looking_main=view.findViewById(R.id.linear_looking_main);
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

        System.out.println("shooooooo"+products.isShortlisted());

        if (products.isShortlisted()==true){
            holder.short_list_image.setImageResource(R.drawable.ic_star_grey);
        }else{
            holder.short_list_image.setImageResource(R.drawable.ic_star_look);
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width_px = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height_px =Resources.getSystem().getDisplayMetrics().heightPixels;
        int height_set=(int)(height_px*0.5);
        System.out.println("height&Width"+width_px+","+height_px);
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width_px,height_set);
        holder.linear_looking_main.setLayoutParams(parms);

        System.out.println("presentimage"+products.getImage());
        sessionManager = new SessionManager(activity);

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

      //  "https://xohricontentimages.s3.us-east-2.amazonaws.com/Tractors/Mahindra/MAHINDRA+YUVRAJ+215+NXT.png"

      //  "https://xohricontentimages.s3.us-east-2.amazonaws.com/Tractors/Mahindra/MAHINDRA_YUVRAJ_215_NXT.png"

      //  "https://xohricontentimages.s3.us-east-2.amazonaws.com/Tractors/Mahindra/MAHINDRA_JIVO_225_DI_2WD.png"

        holder.connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String createdby=products.getId();
                String mainid=products.getMain_id();
               /* Bundle bundle=new Bundle();
                bundle.putString("CreatedBy",products.getId());
                bundle.putString("MainId",products.getMain_id());
                //looking_forId=products.getId();*/
                SendInvitation(createdby,mainid);
                holder.connect.setText("Interested");
                holder.connect.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_pass_mark, 0, 0, 0);
                holder.connect.setTextColor(Color.parseColor("#26a541"));
                holder.connect.setBackgroundResource(R.drawable.border_green);

               /* selectedFragment = InvitationConnectionFragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("lookinnng");
                selectedFragment.setArguments(bundle);
                transaction.commit();*/
            }
        });

        holder.shortlist_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createdBy=products.getId();
                mainId=products.getMain_id();


                userid=sessionManager.getRegId("userId");

                System.out.println("idsssss"+createdBy+", "+mainId+","+userid);

                if (flag==false){
                    holder.short_list_image.setImageResource(R.drawable.ic_star_grey);
                    isShortListed=1;
                    flag=true;

                }else{
                    holder.short_list_image.setImageResource(R.drawable.ic_star_look);
                    isShortListed=0;
                    flag=false;
                }

                shortListed(userid,createdBy,mainId);




            }
        });

      /*  if (LookingForFragment.isShortlisted==true){
            holder.short_list_image.setImageResource(R.drawable.ic_star_filled);
        }else{
            holder.short_list_image.setImageResource(R.drawable.ic_star);
        }*/


        try {
            lngObject = new JSONObject(session.getRegId("language"));
           // holder.connect.setText(lngObject.getString("connect"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

/*
        Glide.with(activity).load(products.getImage())

                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);

*/

    }

    @Override
    public int getItemCount() {
        System.out.println("lengthhhhhhh"+productList.size());
        return productList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private void shortListed(String userid,String createdBy,String mainId) {

        try {

            JSONObject userRequestjsonObject = new JSONObject();
            userRequestjsonObject.put("FarmPeUserId",userid);
            userRequestjsonObject.put("FarmerId",createdBy);
            userRequestjsonObject.put("LookingForId",mainId);
            userRequestjsonObject.put("IsShortlisted",isShortListed);
            userRequestjsonObject.put("CreatedBy",userid);


          /*  JSONObject postjsonObject = new JSONObject();
            postjsonObject.put("objCropDetails", userRequestjsonObject);
*/
            System.out.println("postObj"+userRequestjsonObject.toString());

            Login_post.login_posting(activity, Urls.ShortListRFQ,userRequestjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("cropsresult"+result);

                    try {
                        String status=result.getString("Status");
                        // String message=result.getString("Message");

                        if (!(status.equals("0"))){
                            System.out.println("isselecttt");
                           // notifyDataSetChanged();
                           // Toast.makeText(acti,"Invitation has sent.",Toast.LENGTH_LONG).show();
                        }

                       /* selectedFragment = HomeMenuFragment.newInstance();
                        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();*/

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void SendInvitation(String createdBy,String mainId) {


        try {

            JSONObject userRequestjsonObject = new JSONObject();
            userRequestjsonObject.put("RequestBy",sessionManager.getRegId("userId"));
            userRequestjsonObject.put("RequestTo", createdBy);
            userRequestjsonObject.put("ConnectionNetworkType", "1");
            userRequestjsonObject.put("ConnectionType", DashboardFragment.looking_for_type);
            userRequestjsonObject.put("ConnectionRelatedId", mainId);
            userRequestjsonObject.put("CreatedBy", sessionManager.getRegId("userId"));



          /*  JSONObject postjsonObject = new JSONObject();
            postjsonObject.put("objCropDetails", userRequestjsonObject);
*/
            System.out.println("postObj"+userRequestjsonObject.toString());

            Login_post.login_posting(activity, Urls.RequestConnection,userRequestjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("cropsresult"+result);

                    try {
                        String status=result.getString("Status");
                        // String message=result.getString("Message");

                        if (!(status.equals("0"))){
                            System.out.println("isselecttt00000");

                            //Toast.makeText(getActivity(),"Invitation has sent.",Toast.LENGTH_LONG).show();
                           /* selectedFragment = ConnectionSuccessfulFragment.newInstance();
                            FragmentTransaction transaction =((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout, selectedFragment);
                            transaction.addToBackStack("invitation_back");
                            transaction.commit();*/
                        }

                       /* selectedFragment = HomeMenuFragment.newInstance();
                        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();*/

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}