package com.renewin.FarmPeFarmer.Adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.renewin.FarmPeFarmer.Bean.CropListBean;
import com.renewin.FarmPeFarmer.R;
import com.renewin.FarmPeFarmer.SessionManager;
import com.renewin.FarmPeFarmer.Urls;
import com.renewin.FarmPeFarmer.Volly_class.Login_post;
import com.renewin.FarmPeFarmer.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CropsListAdapter extends RecyclerView.Adapter<CropsListAdapter.MyViewHolder>  {
    private List<CropListBean> productList;
    private List<CropListBean> newOrderBeansList = new ArrayList<>();
    Activity activity;
    Fragment selectedFragment;
    SessionManager sessionManager;
    public ImageLoader imageLoader;
    public static String crop_home;
 String userid;
 public static String item_name,item_variety,item_price,item_uom,item_quantity,id,image,pickup_location,category,latitude,lagitude,distance_str,selltype;
 public static int farmer_id;

    //    SessionManager session;

    public CropsListAdapter(Activity activity, List<CropListBean> moviesList) {
        this.productList = moviesList;
        this.activity=activity;
//        session=new SessionManager(activity);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,variety,loc,grade,quantity,uom,price,add_cart,prof_name,distance;
        public LinearLayout item;
        ImageView image;
        public MyViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.selling_item_name);
            item=view.findViewById(R.id.item);
           // loc=view.findViewById(R.id.loc);
            price=view.findViewById(R.id.price);
            image=view.findViewById(R.id.crop_img);
          //  add_cart=view.findViewById(R.id.add_cart);
           // prof_name=view.findViewById(R.id.prof_name);
           // distance=view.findViewById(R.id.distance);
        }



    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crop_list_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final CropListBean products = productList.get(position);

      //  CropAdapterHome.preview_text=null;

        System.out.println("iiiimmmmaaggee"+products.getImg());

        Glide.with(activity).load(products.getImg().replace("http://3.17.6.57:9595",""))

                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);

      //  variety.setText(products.getVeriety());
      //  System.out.println("ghgdsgd "+ HomeFragment.mLastLocation.getLatitude());

        System.out.println("vvvvvvvvvariety"+products.getVeriety());

      //  holder.price.setText("₹ "+products.getPrice());
        holder.name.setText(products.getVeg_name()+" - "+products.getVeriety()+", "+"₹ "+products.getPrice()+"/"+products.getUom()+", "+products.getQuantity()+" "+products.getUom());

      //  holder.loc.setText("Pick From "+products.getLocation());
       // holder.grade.setText(products.getGrade());
       // holder.quantity.setText(products.getQuantity()+" "+products.getUom());
       // holder.uom.setText(products.getUom());

        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
      /*  System.out.println("fffff = "+(Integer.parseInt(products.getPrice())
                *Integer.parseInt(products.getQuantity())));*/
      try {
          holder.price.setText(format.format(new BigDecimal((Integer.parseInt(products.getPrice())
                  * Integer.parseInt(products.getQuantity())))).split("\\.")[0]);
          //  holder.distance.setText(getdistance(products.getLatitude()+","+products.getLongitude()));
      }catch (Exception e){
          holder.price.setText("No data");
      }


       // getdistance(products.getLatitude()+","+products.getLongitude());


        sessionManager = new SessionManager(activity);
         userid=sessionManager.getRegId("userId");

        System.out.println("iddd_user"+userid);
       /* holder.add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCart(products.getId(),products.getFarmerid(),products.getPrice(),products.getQuantity());
            }
        });*/


       holder.item.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               item_name=products.getVeg_name();
               item_variety=products.getVeriety();
               item_price=products.getPrice();
               item_quantity=products.getQuantity();
               item_uom=products.getUom();
               farmer_id=products.getFarmerid();
               category=products.getCategory();
               latitude=products.getLatitude();
               lagitude=products.getLongitude();
               selltype=products.getSellType();

               distance_str=dist;

               System.out.println("awayyyy"+distance_str);

               id=products.getId();
               image=products.getImg();
               pickup_location=products.getLocation();

               //crop_home="home_menu_crop";
               Bundle bundle=new Bundle();
               /*if (DiscoverByCategorySubAdapter.sub_cat==null){
                   System.out.println("jdshfsk");
                   bundle.putString("crop_home","crop_list_pre");
                   selectedFragment = CropListItemPreviewFragment.newInstance();
                   FragmentTransaction transaction6 = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                   transaction6.replace(R.id.frame_layout_home, selectedFragment);
                   selectedFragment.setArguments(bundle);
                   //  transaction6.addToBackStack("crop_list_back");
                   transaction6.commit();
               }else{
                   bundle.putString("category_crop","category_crop_list");
                   selectedFragment = HomeMenuFragment.newInstance();
                   FragmentTransaction transaction6 = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                   transaction6.replace(R.id.frame_layout, selectedFragment);
                   selectedFragment.setArguments(bundle);
                   transaction6.commit();
               }*/


              // crop_home=null;

             /*  if (DiscoverByCategorySubAdapter.preview_crop.equals("cropsList")){
                   crop_home="home_menu_crop";
                   selectedFragment = HomeMenuFragment.newInstance();
                   FragmentTransaction transaction6 = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
                   transaction6.replace(R.id.frame_layout, selectedFragment);
                 //  transaction6.addToBackStack("crop_list_back");
                   transaction6.commit();
               }else
               selectedFragment = CropListItemPreviewFragment.newInstance();
               FragmentTransaction transaction6 = ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();
               transaction6.replace(R.id.frame_layout_home, selectedFragment);
               transaction6.addToBackStack("crop_list_back");
               transaction6.commit();*/
           }
       });



    }

String dist;
   /* private void getdistance(String destination) {

        RequestQueue queue = Volley.newRequestQueue(activity);
// enable distancematrix api in google developer console
        // and add respective key
        try {
            String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" +
                    HomeFragment.mLastLocation.getLatitude() + "," + HomeFragment.mLastLocation.getLongitude() +
                    "&destinations=" + destination + "&departure_time=now&key=AIzaSyDgQSmB4zuUBFUv4rzBhY_e-ZRygBRVT4U";


            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                System.out.println("fhfghf " + response);
                                JSONArray jsonArray = jsonObject.getJSONArray("rows");
                                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                                JSONArray jsonArray1 = jsonObject1.getJSONArray("elements");
                                JSONObject jsonObject2 = jsonArray1.getJSONObject(0);
                                JSONObject jsonObject3 = jsonObject2.getJSONObject("distance");

                                int distance = jsonObject3.getInt("value");

                                System.out.println("srss " + distance);

                                if (distance < 1000) {
                                    dist = "less than Km away";
                                    System.out.println("kkkkmmm" + distance_str);
                                } else dist = (distance / 1000 + " Kms away");
                                System.out.println("fhfghf " + distance_str);
                            } catch (JSONException e) {

                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            queue.add(stringRequest);

            //     return distanceGot[0];
        }catch (Exception e){

        }

    }
*/
    private void AddCart(String cropid,int farmerid,String price,String quant) {

        try {
        //    newOrderBeansList.clear();

            JSONObject userRequestjsonObject = new JSONObject();

            userRequestjsonObject.put("UserId",userid);

            userRequestjsonObject.put("CropListId", cropid);
            userRequestjsonObject.put("FarmerId", farmerid);
            userRequestjsonObject.put("Price",price );
            userRequestjsonObject.put("Quantity", quant);


            JSONObject postjsonObject = new JSONObject();
            postjsonObject.put("CartRequest", userRequestjsonObject);

            System.out.println("postObj_cart"+postjsonObject.toString());

            Login_post.login_posting(activity, Urls.AddToCart,postjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("cropsresult"+result);
                    JSONObject object=new JSONObject();


                    try {
                        object=result.getJSONObject("ResultObject");
                        System.out.println("getting_status"+object.toString());



                            int status=object.getInt("Status");

                            if (status==1){

                                Toast.makeText(activity,"Your Order has added to cart",Toast.LENGTH_LONG).show();


                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {

        System.out.println("e     e e "+productList.size());

        return productList.size();
    }

}