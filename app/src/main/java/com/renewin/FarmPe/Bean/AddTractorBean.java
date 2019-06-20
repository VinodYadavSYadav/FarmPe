package com.renewin.FarmPe.Bean;

public class AddTractorBean {

  //  private String image;
    private String prod_name,id;
    private  int image;

    public AddTractorBean(int image, String prod_name, String id) {

        this.image = image;
        this.prod_name = prod_name;
        this.id = id;

    }


    public String getProd_name() {
        return prod_name;
    }

    public String getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

/* public String getImage() {
        return image;
    }*/
}
