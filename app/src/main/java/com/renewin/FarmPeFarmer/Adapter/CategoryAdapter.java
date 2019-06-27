package com.renewin.FarmPeFarmer.Adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renewin.FarmPeFarmer.Bean.CategoryBean;
import com.renewin.FarmPeFarmer.Fragment.SubCategoryFragment;
import com.renewin.FarmPeFarmer.R;


import java.util.Date;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>  {
    private List<CategoryBean> productList;
    Activity activity;
    Fragment selectedFragment;
    Date o_date;
    public static String strtext,id;

   public static LinearLayout next_arw;
    boolean list_flag;

    public static String first;
    //    SessionManager session;
    public static CardView cardView;
    public CategoryAdapter(Activity activity, List<CategoryBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
//        session=new SessionManager(activity);

    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout item;
        public TextView namel;
        ImageView cat_img;
        public RecyclerView recyclerView;


        public MyViewHolder(View view) {
            super(view);
            //agri_text=view.findViewById(R.id.store_agri);
            item=view.findViewById(R.id.item);
            namel=view.findViewById(R.id.feed_text);

           cat_img=view.findViewById(R.id.cat_img);

            //confirmbutton=view.findViewById(R.id.delivery2);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final CategoryBean products = productList.get(position);
      //holder.agri_text.setText(products.getAgri_text());
        holder.namel.setText(products.getCategory());

       /* Glide.with(activity).load(products.getCaticon())

                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.cat_img);
*/
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = SubCategoryFragment.newInstance();
                FragmentTransaction transaction = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("sub");
                transaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        System.out.println("sizzzeeee"+productList.size());
        return productList.size();
    }

}