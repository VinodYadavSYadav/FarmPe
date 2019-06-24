package com.renewin.FarmPe.Bean;

public class NearByHorizontalBean {

    String farm_name,id;
    int image;

    public int getImage() {
        return image;
    }

    public String getFarm_name() {
        return farm_name;
    }

    public String getId() {
        return id;
    }

    public NearByHorizontalBean(int image, String farm_name, String id) {
        this.image = image;
        this.farm_name = farm_name;
        this.id = id;

    }
}


