package com.renewin.Xohri.Bean;

public class FarmsImageBean {

    private int image;
    private String prod_price,prod_name,duration,farmer_name,location;
   // private  int imageid;

    public FarmsImageBean(int image, String prod_price, String prod_name,String duration,String farmer_name,String location) {

        this.image = image;
        this.prod_price = prod_price;
        this.prod_name = prod_name;
        this.duration = duration;
        this.farmer_name = farmer_name;
        this.location = location;

    }

    public int getImage() {
        return image;
    }

    public String getProd_price() {
        return prod_price;
    }

    public String getProd_name() {
        return prod_name;
    }

    public String getDuration() {
        return duration;
    }

    public String getFarmer_name() {
        return farmer_name;
    }

    public String getLocation() {
        return location;
    }
}
