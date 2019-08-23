package com.FarmPe.India.Bean;

public class ShortListBean {

    String farm,model,hp,name,district,state,image,brandname,id;

    public String getFarm() {
        return farm;
    }

    public String getModel() {
        return model;
    }

    public String getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public String getState() {
        return state;
    }

    public String getImage() {
        return image;
    }

    public String getBrandname() {
        return brandname;
    }

    public String getId() {
        return id;
    }

    public ShortListBean(String farm, String brandname, String model, String hp, String name, String district, String state, String image,String id) {

        this.farm = farm;
        this.model = model;
        this.hp = hp;
        this.name = name;
        this.district = district;
        this.state = state;
        this.image = image;
        this.brandname = brandname;
        this.id = id;

    }
}


