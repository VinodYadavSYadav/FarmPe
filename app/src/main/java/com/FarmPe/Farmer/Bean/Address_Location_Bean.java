package com.FarmPe.Farmer.Bean;

public class Address_Location_Bean {



    String  subcat,icon,id;


    public Address_Location_Bean(String subcat, String icon, String id) {
        this.subcat=subcat;
        this.icon=icon;
        this.id=id;
    }

    public String getSubcat() {
        return subcat;
    }

    public void setSubcat(String subcat) {
        this.subcat = subcat;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


