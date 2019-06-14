package com.renewin.Xohri.Bean;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class Person implements ClusterItem {

    private final LatLng mPosition;
    private String name, location, quantity, uom, price, user_name;
    private String id;
    private int farmerid;
    private String imagee;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getUom() {
        return uom;
    }

    public String getPrice() {
        return price;
    }

    public String getUser_name() {
        return user_name;
    }

    public int getFarmerid() {
        return farmerid;
    }

    public String getImagee() {
        return imagee;
    }

    public Person(double lat, double lng, String name, String location, String quantity, String uom, String user_name, String price, String id, int farmerid, String imagee) {
        this.name = name;
        this.location = location;
        this.quantity = quantity;
        this.uom = uom;
        this.user_name = user_name;
        this.price = price;
        this.farmerid = farmerid;
        mPosition = new LatLng(lat, lng);
        this.id = id;
        this.imagee = imagee;
    }


    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getSnippet() {
        return null;
    }

}