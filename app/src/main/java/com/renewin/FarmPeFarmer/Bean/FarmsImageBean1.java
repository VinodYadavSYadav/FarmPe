package com.renewin.FarmPeFarmer.Bean;

public class FarmsImageBean1 {

   // private int image;
    private String image,prod_price,modelname,mobile_no,farmer_name,location,id,hp,email;
   // private  int imageid;

    public FarmsImageBean1(String image, String prod_price, String modelname, String hp, String mobile_no, String farmer_name, String location, String id,String email) {

        this.image = image;
        this.prod_price = prod_price;
        this.modelname = modelname;
        this.mobile_no = mobile_no;
        this.farmer_name = farmer_name;
        this.location = location;
        this.id = id;
        this.hp = hp;
        this.email = email;

    }

    public String getImage() {
        return image;
    }

    public String getProd_price() {
        return prod_price;
    }

    public String getModelname() {
        return modelname;
    }

    public String getHp() {
        return hp;
    }

    public String getDuration() {
        return mobile_no;
    }

    public String getFarmer_name() {
        return farmer_name;
    }

    public String getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public String getEmail() {
        return email;
    }
}
