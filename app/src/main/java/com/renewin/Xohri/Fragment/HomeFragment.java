
package com.renewin.Xohri.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.renewin.Xohri.Activity.LandingPageActivity;
import com.renewin.Xohri.Bean.Person;
import com.renewin.Xohri.R;
import com.renewin.Xohri.SessionManager;
import com.renewin.Xohri.Urls;
import com.renewin.Xohri.Volly_class.Login_post;
import com.renewin.Xohri.Volly_class.VoleyJsonObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.renewin.Xohri.Activity.LandingPageActivity.mBottomSheetBehavior6;


public class HomeFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{


    private TileOverlay mMoonTiles;

    public static final int REQ_CD = 1;
    Fragment selectedFragment = null;
    public static ClusterManager<Person> mClusterManager;
    LocationManager locationManager;
    public static IconGenerator mIconGenerator;
    private IconGenerator mIconGenerator1;

    public static ImageView mImageView;
    LinearLayout linearLayout,main_layout;
    public static GoogleMap mMap;
    LocationRequest mLocationRequest;
    public static Location mLastLocation;
    Geocoder geocoder;
    List<Address> addresses;
    FusedLocationProviderClient mFusedLocationClient;
    boolean doubleBackToExitPressedOnce = false;
    TextView todaydeal,farms,shop_cat,my_orders,wish_list,cart,home,account;
    Marker mCurrLocationMarker;
    ImageView menu;
    public static String status,place_order_stat;
    String price_string;
    SessionManager sessionManager;
    public static ImageView  list;
    boolean fragloaded= false;
    FloatingActionButton fab,fab2;

    int mDimension;
    int zoom;

    Context mContext ;
    List<Double> lat = new ArrayList<Double>();
    List<Double> lng = new ArrayList<Double>();

    public  String city,id,userid;
    public  int farmerId;
    public static double city_lat;
    public static double city_lng;
    public static DrawerLayout drawer;



    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.home_map_layout, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mContext=getActivity();
        getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);

        fab=view.findViewById(R.id.fab);
        fab2=view.findViewById(R.id.grid);
        linearLayout=view.findViewById(R.id.main_layout);
      //  todaydeal=view.findViewById(R.id.todaydeal);
      //  my_orders=view.findViewById(R.id.my_orders);
      //  shop_cat=view.findViewById(R.id.shop_cat);
        farms=view.findViewById(R.id.farms);
        //  farming=view.findViewById(R.id.farming);
        home=view.findViewById(R.id.home);
        menu=view.findViewById(R.id.menu);
     //   wish_list=view.findViewById(R.id.wish_list);
        main_layout=view.findViewById(R.id.main_layout);
     //   cart=view.findViewById(R.id.cart);
        drawer = (DrawerLayout)view.findViewById(R.id.drawer_layout);
   //     account = view.findViewById(R.id.account);
        Configuration config = new Configuration();

        // farming.setTextColor(Color.parseColor("#000000"));
        System.out.println("maoppppp");


        sessionManager = new SessionManager(getActivity());
        userid=sessionManager.getRegId("userId");

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    selectedFragment = HomeMenuFragment.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();

                    return true;
                }
                return false;
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = CropsListFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack("discover");
                transaction.commit();
            }
        });
        farms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFragment = FarmsFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_home, selectedFragment);
                transaction.commit();
            }
        });



        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.JELLY_BEAN){
            getContext().createConfigurationContext(config);
        }else { //deprecated
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        }

        mIconGenerator = new IconGenerator(getActivity().getApplicationContext());
        mIconGenerator1 = new IconGenerator(getActivity().getApplicationContext());

        fab.setOnClickListener(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());



        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // intialize cluster manager
        mClusterManager = new ClusterManager<>(getActivity(), mMap);
        if (!fragloaded)
        CropsList();

        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        // add items to manager

        // for showing details relate dto marker
        mMap.setOnInfoWindowClickListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);

        // used for setting image for marker
        mClusterManager.setRenderer(new RenderClusterInfoWindow(getActivity(),mMap, mClusterManager));
        System.out.println("sizeeeee"+mClusterManager.getMarkerCollection());
        /*for (int i = 0; i < lat.size(); i++) {


            mClusterManager.addItem(new Person(lat.get(i), lng.get(i), "", "",""));et


        }*/

        // notify() set
        //   mClusterManager.cluster();



        location_permission();

        fragloaded=true;
    }




    LocationCallback mLocationCallback = new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for (Location location : locationResult.getLocations()) {
                Log.i("MapsActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());
                mLastLocation = location;

                city_lat= location.getLatitude();
                city_lng= location.getLongitude();


                if (mCurrLocationMarker != null) {
                    mCurrLocationMarker.remove();
                }
                try {
                    geocoder = new Geocoder(getActivity(), Locale.getDefault());
                    addresses = geocoder.getFromLocation(
                            location.getLatitude(),
                            location.getLongitude(),
                            1);

                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineInde:@)
                    city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();


                }catch (Exception e){
                }


/*main_layout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (newstate == BottomSheetBehavior.STATE_EXPANDED) {
            System.out.println("111");
            mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }
});*/



                mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<Person>() {
                    @Override
                    public boolean onClusterItemClick(final Person person) {
                        mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_EXPANDED);
                        String lat_lang_pos=person.getPosition().toString();
                        System.out.println("latttlngggg_pos"+lat_lang_pos);
                        LandingPageActivity.name.setText(person.getName()+" - "+"Utsav Premium"+", "+"₹ "+person.getPrice()+"/"+person.getUom()+", "+person.getQuantity()+" "+person.getUom());
                      //  LandingPageActivity.loc.setText("Pick From "+person.getLocation());
                       // LandingPageActivity.quantity.setText(person.getQuantity()+" "+person.getUom());
                        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));

                        LandingPageActivity.price.setText(format.format(new BigDecimal((Integer.parseInt(person.getPrice())
                                *Integer.parseInt(person.getQuantity())))).split("\\.")[0]);
                      //  LandingPageActivity.prof_name.setText(person.getUser_name());

                        Glide.with(getActivity()).load(person.getImagee().replace("http://3.17.6.57:9595",""))

                                .thumbnail(0.5f)
                                .crossFade()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(LandingPageActivity.cart_img);

                       /* LandingPageActivity.buy_now.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                place_order_stat="home_place_order";
                                // AddCart(person.getId(),person.getFarmerid(),person.getPrice(),person.getQuantity());
                                price_string=((Integer.parseInt(person.getPrice())
                                        *Integer.parseInt(person.getQuantity()))+"");

                                Bundle bundle = new Bundle();
                                bundle.putString("Price",price_string);
                                bundle.putString("Delivery_Charges","500");
                                bundle.putString("Coupon_Applied","200");

                            }
                        });*/

                        return false;
                    }
                });

                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                });

                LatLng coordinate = new LatLng(location.getLatitude(), location.getLongitude()); //Store these lat lng values somewhere. These should be constant.
                CameraUpdate location1 = CameraUpdateFactory.newLatLngZoom(
                        coordinate, 12);
                mMap.moveCamera(location1);




            }
        };

    };
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQ_CD: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                        mMap.setMyLocationEnabled(true);
                        mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        mMap.getUiSettings().setCompassEnabled(false);
                    }

                } else {

                    Toast.makeText(getActivity(), "permission denied", Toast.LENGTH_LONG).show();

                    LatLng coordinate = new LatLng(15, 77); //Store these lat lng values somewhere. These should be constant.
                    CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                            coordinate, 0);
                    mMap.animateCamera(location);
                }

                return;
            }
        }
    }

    private void CropsList() {

        try {

            JSONObject userRequestjsonObject = new JSONObject();
            // userRequestjsonObject.put("CreatedBy", "111");


            JSONObject postjsonObject = new JSONObject();
            // postjsonObject.put("objCropDetails", userRequestjsonObject);

            final LatLngBounds.Builder builder = new LatLngBounds.Builder();



            System.out.println("postObj"+postjsonObject.toString());

            Login_post.login_posting(getActivity(), Urls.GetAllCrops,userRequestjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("cropsresult"+result);
                    JSONArray cropsListArray=null;
                    try {

                        cropsListArray=result.getJSONArray("Crops");
                        System.out.println("e     e e ddd"+cropsListArray.length());
                        for (int i=0;i<cropsListArray.length();i++){
                            JSONObject jsonObject1=cropsListArray.getJSONObject(i);
                            String location=jsonObject1.getString("PickupLocation");

                            String cropName=jsonObject1.getString("CropName");
                            System.out.println("crop_name"+cropName);
                            String crop_variety=jsonObject1.getString("Variety");
                            String crop_grade=jsonObject1.getString("Grade");
                            String crop_quantity=jsonObject1.getString("Quantity");
                            String crop_uom=jsonObject1.getString("UoM");
                            String crop_price=jsonObject1.getString("Price");
                            id=jsonObject1.getString("Id");
                            String image_crop=jsonObject1.getString("CropIcon");
                            farmerId=jsonObject1.getInt("FarmerId");
                            String UserName=jsonObject1.getString("UserName");

                            String  latts=jsonObject1.getString("Latitude");
                            String langgs=jsonObject1.getString("Longitude");
                            if(!latts.equals("") | !langgs.equals("")) {

                                Double latt = Double.parseDouble(latts);
                                Double langg = Double.parseDouble(langgs);

                                lat.add(latt);
                                lng.add(langg);

                                System.out.println("sizeeee"+lat.size());
                                //Double.parseDouble(jsonObject1.getString("Latitude"))
                                builder.include(new LatLng(latt, langg));
                                mClusterManager.addItem(new Person(latt,
                                        langg,
                                        cropName, location, crop_quantity, crop_uom, UserName, crop_price, id, farmerId,image_crop));
                            }

                        }

                        LatLngBounds bounds = builder.build();
                        mClusterManager.cluster();
                        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,100));
                        //  recyclerView.setAdapter(mAdapter);
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
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.fab:
                status="map";

                location_permission();



                break;

        }

    }




    private void circle_radius(int radius, LatLng lat_lng) {

        final Circle circle3 = mMap.addCircle(new CircleOptions().center(new LatLng(lat_lng.latitude,lat_lng.longitude)));
        circle3.setRadius(radius);
        circle3.setStrokeColor(Color.parseColor("#ffefef"));
        circle3.setStrokeWidth(2f);
        circle3.setFillColor(Color.parseColor("#268B0000"));
        zoom=getZoomLevel(circle3);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat_lng.latitude, lat_lng.longitude),  12.0f));

    }


    private int getZoomLevel(Circle circle) {

        int zoomLevel = 0;
        if (circle != null){
            double radius = circle.getRadius();
            double scale = radius / 500;
            zoomLevel =(int) (16 - Math.log(scale) / Math.log(2));
        }
        return zoomLevel;
    }







    public void location_permission(){


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
//
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQ_CD);
            }
            else{
               /* Intent in = new Intent(this, LocationGet.class);
                startActivity(in);*/
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mMap.getUiSettings().setCompassEnabled(false);

            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        }
        else {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            mMap.getUiSettings().setCompassEnabled(false);

        }


    }


    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                new AlertDialog.Builder(getActivity())
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    public class RenderClusterInfoWindow extends DefaultClusterRenderer<Person> {


        RenderClusterInfoWindow(Context context, GoogleMap map, ClusterManager<Person> clusterManager) {
            super(context, map, clusterManager);

            View multiProfile = getLayoutInflater().inflate(R.layout.multi_profile, null);
            mIconGenerator.setContentView(multiProfile);

            mImageView = new ImageView(getActivity().getApplicationContext());
            mDimension = (int) getResources().getDimension(R.dimen.bottom_navigation_icon);
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension, mDimension));
            int padding = (int) getResources().getDimension(R.dimen.bottom_navigation_notification_padding);
            mImageView.setPadding(padding, padding, padding, padding);
            mIconGenerator.setContentView(mImageView);

            mIconGenerator.setBackground(
                    ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.circle_profile));


        }

        @Override
        protected void onClusterRendered(Cluster<Person> cluster, Marker marker) {
            super.onClusterRendered(cluster, marker);

        }

        @Override
        protected void onBeforeClusterItemRendered(Person item, MarkerOptions markerOptions) {
            markerOptions.title(item.getName());
            markerOptions.snippet("");

            super.onBeforeClusterItemRendered(item, markerOptions);


            mImageView.setImageResource(R.drawable.ic_farmer);
            Bitmap icon = mIconGenerator.makeIcon();
            /*mIconGenerator.setBackground(
                    ContextCompat.getDrawable(mContext, R.drawable.circle_profile));*/
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
        }






       /* @Override
        protected void onBeforeClusterRendered(Cluster<Person> cluster, MarkerOptions markerOptions) {
            mImageView.setImageResource(R.drawable.ic_farmer_map);
            Bitmap icon = mIconGenerator.makeIcon();
            mIconGenerator.setBackground(
                    ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.circle_profile));
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
        }*/

        @Override
        protected boolean shouldRenderAsCluster(Cluster cluster) {
            return cluster.getSize() > 30;
        }
    }

    private void CartItemCount() {

        try {


            JSONObject userRequestjsonObject = new JSONObject();
            userRequestjsonObject.put("UserId", userid);


            JSONObject postjsonObject = new JSONObject();
            postjsonObject.put("CartRequest", userRequestjsonObject);



            Login_post.login_posting(getActivity(), Urls.CartCount,postjsonObject,new VoleyJsonObjectCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    System.out.println("cropsresult"+result);

                    try {
                        int cart_count=result.getInt("Count");
                        HomeMenuFragment.cart_count_text.setText(cart_count+"");

                        System.out.println("carrtt_countt"+cart_count);
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

