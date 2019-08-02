package com.FarmPe.India.Bean;

public class FarmsImageBean {

  //  private String image;
    private String prod_price,modelname,duration,farmer_name,location,id,hp;
    private  String image,main_id;
    boolean isShortlisted;

    public FarmsImageBean(String image, String prod_price, String modelname,String hp,String duration,String farmer_name,String location,String id,String main_id,boolean isShortlisted) {

        this.image = image;
        this.prod_price = prod_price;
        this.modelname = modelname;
        this.duration = duration;
        this.farmer_name = farmer_name;
        this.location = location;
        this.id = id;
        this.hp = hp;
        this.main_id = main_id;
        this.isShortlisted = isShortlisted;

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
        return duration;
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

    public String getMain_id() {
        return main_id;
    }

    public boolean isShortlisted() {
        return isShortlisted;
    }
}
