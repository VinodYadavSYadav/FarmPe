package com.FarmPe.India.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;


import com.FarmPe.India.Bean.InvitationBean;
import com.FarmPe.India.R;

import java.util.List;


public class InvitationAdapter extends RecyclerView.Adapter<InvitationAdapter.MyViewHolder>  {

    private List<InvitationBean> productList;
    Activity activity;
    Fragment selectedFragment;
    public LinearLayout linearLayout;
    public static LinearLayout next_arw;
    public static String first;
    public static CardView cardView;
    public static String farm_listid;
    public static int selected_position = 0;



    public InvitationAdapter(Activity activity, List<InvitationBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
//        session=new SessionManager(activity);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView actninfo;
        public RadioButton list_farm1;



        public MyViewHolder(View view) {
            super(view);
            actninfo=view.findViewById(R.id.actninfo);

            image=view.findViewById(R.id.image);
            list_farm1=view.findViewById(R.id.network_type);

            //linearLayout=view.findViewById(R.id.dialog_list);
            //confirmbutton=view.findViewById(R.id.delivery2);
        }

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())


                .inflate(R.layout.invitation_list_item, parent, false);
                 return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final InvitationBean products = productList.get(position);

         System.out.println("111wadwd" + products.isIsselected());

         holder.list_farm1.setText(products.getType());
/*

         if (products.isIsselected()){

             holder.list_farm1.setChecked(true);

         }else {

             holder.list_farm1.setChecked(false);
         }
*/

       boolean selectedval= products.isIsselected()? true : false;

        holder.list_farm1.setChecked(selectedval);




        holder.list_farm1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {


             boolean selectedval= products.isIsselected()? false : true;

             holder.list_farm1.setChecked(selectedval);

             farm_listid = products.getId();

             for(int i = 0;i<productList.size();i++){
                 System.out.println("111uuuudwd" + i);

                 if (i==position){
                     products.setIsselected(true);

                 }else {
                     productList.get(i).setIsselected(false);
                 }

             }



             notifyDataSetChanged();




         }
     });






      /*  if (Notification_Recyc_Fragment.list.get(position).equals(products.getNoti_id())){
            holder.actninfo.setText(products.getNoti_txt());
            holder.switch1.setChecked(true);
            FirebaseMessaging.getInstance().subscribeToTopic(products.getNoti_code());

        }else {
            holder.actninfo.setText(products.getNoti_txt());
            holder.switch1.setChecked(false);


        }*/

//
//        JSONObject jsonObject = null;
//        try{
//
//           jsonObject = new JSONObject();
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//        Crop_Post.crop_posting(activity, Urls.GET_NOTIFICATION, jsonObject, new VoleyJsonObjectCallback() {
//                @Override
//                public void onSuccessResponse(JSONObject result) {
//                    System.out.println("ffffffnn" + result);
//
//                    try{
//
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//
//





    }

    @Override
    public int getItemCount() {
        System.out.println("lengthhhhhhh"+productList.size());
        return productList.size();
    }

}