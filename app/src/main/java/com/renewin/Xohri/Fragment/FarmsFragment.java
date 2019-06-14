
package com.renewin.Xohri.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.renewin.Xohri.Bean.Person;
import com.renewin.Xohri.R;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.renewin.Xohri.Activity.LandingPageActivity.mBottomSheetBehavior6;


public class FarmsFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    private static final String MOON_MAP_URL_FORMAT =
            "http://mw1.google.com/mw-planetary/lunar/lunarmaps_v1/clem_bw/%d/%d/%d.jpg";
    private TileOverlay mMoonTiles;

    public static final int REQ_CD = 1;
    Fragment selectedFragment = null;
    public static ClusterManager<Person> mClusterManager;
    LocationManager locationManager;
    public static IconGenerator mIconGenerator;
    private IconGenerator mIconGenerator1;
    boolean isGPSEnabled = false;
    public static String map_item;
    public static ImageView mImageView;
    LinearLayout linearLayout;
    public static GoogleMap mMap;
    LocationRequest mLocationRequest;
    public static Location mLastLocation;
    Geocoder geocoder;
    List<Address> addresses;
    FusedLocationProviderClient mFusedLocationClient;
    JSONArray marker_jsonArray;
    boolean doubleBackToExitPressedOnce = false;
    TextView todaydeal,farms,shop_cat,farming,my_orders,wish_list,cart,home;
    Marker mCurrLocationMarker;
    ImageView menu;

    Location location = null;
    public static ImageView filter_image, list;

    FloatingActionButton fab;
    LatLng current_latLng;
    RecyclerView professional_recyclerview;

    int mDimension;
    int zoom;
    public static String status;
    LatLng coordinate;
    public  static   String professional_masterId = "";
    public double current_lat,current_lng;
    Context mContext ;
    List<Double> lat = new ArrayList<Double>();
    List<Double> lng = new ArrayList<Double>();
    List<String> loc_name = new ArrayList<String>();
    public static TextView findingText;
    public static String city;
    public static double city_lat;
    public static double city_lng;
    public static DrawerLayout drawer;


    public static FarmsFragment newInstance() {
        FarmsFragment fragment = new FarmsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_navigation_farm, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mContext=getActivity();
        fab=view.findViewById(R.id.fab);
     //   fab2=view.findViewById(R.id.list_crop);
        // fab3=view.findViewById(R.id.fab_3);
        linearLayout=view.findViewById(R.id.main_layout);
       // todaydeal=view.findViewById(R.id.todaydeal);
        farming=view.findViewById(R.id.farming);
        menu=view.findViewById(R.id.menu);
        home=view.findViewById(R.id.home);
       /* my_orders=view.findViewById(R.id.my_orders);
        shop_cat=view.findViewById(R.id.shop_cat);
        wish_list=view.findViewById(R.id.wish_list);
        cart=view.findViewById(R.id.cart);*/
        drawer = (DrawerLayout)view.findViewById(R.id.drawer_layout);
        String language= "hi"; //Hindi language!.
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        // farms.setTextColor(Color.parseColor("#000000"));
        NavigationView navigationView = (NavigationView)view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    /*    menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer = (DrawerLayout)view.findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        System.out.println("SOP");
                        selectedFragment = HomeFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        // drawer.closeDrawers();
                    }
                });
                todaydeal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        status="1";
                        selectedFragment = HomeMenuFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();

                    }
                });
                shop_cat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        status="2";
                        selectedFragment = HomeMenuFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                    }
                });
                my_orders.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        status="3";
                        selectedFragment = HomeMenuFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                    }
                });

                wish_list.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        status="4";
                        selectedFragment = HomeMenuFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                    }
                });
                cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        status="5";
                        selectedFragment = HomeMenuFragment.newInstance();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                    }
                });

            }
        });
*/

        farming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // farming.setTextColor(Color.parseColor("#000000"));
                selectedFragment = HomeFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout_home, selectedFragment);
                transaction.commit();
            }
        });

/*linearLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
});*/

        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.JELLY_BEAN){
            config.setLocale(locale);
            getContext().createConfigurationContext(config);
        }else { //deprecated
            config.locale = locale;
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        }
       /* fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_COLLAPSED);
                selectedFragment = Farmers_near_me.newInstance();
                FragmentTransaction transaction =(getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
            }
        });*/

       /* fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_COLLAPSED);
                Intent intent=new Intent(getActivity(),NavigationFarmActivity.class);
                startActivity(intent);
            }
        });
*/

        mIconGenerator = new IconGenerator(getActivity().getApplicationContext());
        mIconGenerator1 = new IconGenerator(getActivity().getApplicationContext());

        fab.setOnClickListener(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());


        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(getActivity(), Locale.getDefault());

        lat.add(12.909536099999999);
        lng.add(77.5274633);
        try {
            addresses = geocoder.getFromLocation(lat.get(0), lng.get(0), 1);

            loc_name.add("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        lat.add(12.902843703352639);
        lng.add(77.53223419189453);
        try {
            addresses = geocoder.getFromLocation(lat.get(1), lng.get(1), 1);
            loc_name.add("");
        } catch (IOException e) {
            e.printStackTrace();
        }


        lat.add( 12.92041241538409);
        lng.add(77.50614166259767);
        try {
            addresses = geocoder.getFromLocation(lat.get(2), lng.get(2), 1);
            loc_name.add("");
        } catch (IOException e) {
            e.printStackTrace();
        }


        lat.add( 12.897991174836472);
        lng.add(77.5569534301758);
        try {
            addresses = geocoder.getFromLocation(lat.get(3), lng.get(3), 1);
            loc_name.add("");
        } catch (IOException e) {
            e.printStackTrace();
        }


        lat.add( 12.88125759630339);
        lng.add(77.48777389526369);
        try {
            addresses = geocoder.getFromLocation(lat.get(4), lng.get(4), 1);
            loc_name.add("");
        } catch (IOException e) {
            e.printStackTrace();
        }




        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("ONBACK", "keyCode: " + keyCode);
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    Log.i("ONBACK", "onKey Back listener is working!!!");
                   /* selectedFragment = Sign_In.newInstance();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                  //  transaction.commitAllowingStateLoss();
                    transaction.commit();*/
                    if (doubleBackToExitPressedOnce) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                        startActivity(intent);
                        getActivity().
                                finish();
                        System.exit(0);                    }

                    doubleBackToExitPressedOnce = true;
                    Toast.makeText(getActivity(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce=false;
                        }
                    }, 3000);
                    return true;
                }
                return false;
            }
        });


        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try {


        } catch (Resources.NotFoundException e) {
            //Log.e(TAG, "Can't find style. Error: ", e);
        }
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mClusterManager = new ClusterManager<>(getActivity(), googleMap);
        //  googleMap.setOnInfoWindowClickListener(mClusterManager);
        googleMap.setOnMarkerClickListener(mClusterManager);
        mClusterManager.setRenderer(new RenderClusterInfoWindow(getActivity(),googleMap, mClusterManager));

        for (int i = 0; i < lat.size(); i++) {


            mClusterManager.addItem(new Person(lat.get(i), lng.get(i), loc_name.get(i), "","","","","","",1,""));


        }

        mClusterManager.cluster();
       /* mClusterManager.setOnClusterItemInfoWindowClickListener(
                new ClusterManager.OnClusterItemInfoWindowClickListener<Person>() {
                    @Override
                    public void onClusterItemInfoWindowClick(final Person item) {
                        System.out.println("oooo");
                              *//*  Intent p_profile=new Intent(getActivity().getApplicationContext(),HomeActivity.class);
                                p_profile.putExtra("A_NAME", "PROF_PROFILE");
                                p_profile.putExtra("PROF_ID", item.getId().toString());
                                startActivity(p_profile);*//*


                    }
                });*/


        location_permission();

        /*TileProvider tileProvider = new UrlTileProvider(256, 256) {
            @Override
            public synchronized URL getTileUrl(int x, int y, int zoom) {
                // The moon tile coordinate system is reversed.  This is not normal.
                int reversedY = (1 << zoom) - y - 1;
                String s = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    s = String.format(new Locale("kn","IN"), MOON_MAP_URL_FORMAT, zoom, x, reversedY);
                }
                else
                    System.out.println("djhdijhdkjdhkjdhkjkd");
                URL url = null;
                try {
                    url = new URL(s);
                } catch (MalformedURLException e) {
                    throw new AssertionError(e);
                }
                return url;
            }
        };

        mMoonTiles = googleMap.addTileOverlay(new TileOverlayOptions().tileProvider(tileProvider));*/
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



                mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<Person>() {
                    @Override
                    public boolean onClusterItemClick(Person person) {
                        mBottomSheetBehavior6.setState(BottomSheetBehavior.STATE_EXPANDED);

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


            mImageView.setImageResource(R.drawable.ic_farmer_map);
            Bitmap icon = mIconGenerator.makeIcon();
            mIconGenerator.setBackground(
                    ContextCompat.getDrawable(mContext, R.drawable.circle_profile));
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
            return cluster.getSize() > 10;
        }
    }




}

