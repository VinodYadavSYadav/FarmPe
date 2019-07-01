package com.FarmPe.Farmer.Bean;

public class CropListBean {

    String veg_name,veriety,location,grade,quantity,uom,price,id,user_name,latitude,longitude,img,category,sellType;
    int farmerid;


    public String getVeg_name() {
        return veg_name;
    }

    public String getVeriety() {
        return veriety;
    }

    public String getLocation() {
        return location;
    }

    public String getGrade() {
        return grade;
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

    public String getId() {
        return id;
    }

    public int getFarmerid() {
        return farmerid;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getCategory() {
        return category;
    }

    public String getImg() {
        return img;
    }

    public String getSellType() {
        return sellType;
    }

    public CropListBean(String veg_name, String veriety, String location, String grade, String quantity, String uom, String price, String id,
                        int farmerid, String user_name, String latitude, String longitude, String img1, String category, String sellType) {
        System.out.println("llllllllllllllllllllll"+img1);

        this.veg_name = veg_name;
        this.veriety = veriety;
        this.location = location;
        this.grade = grade;
        this.quantity = quantity;
        this.uom = uom;
        this.price = price;
        this.id = id;
        this.farmerid = farmerid;
        this.user_name = user_name;
        this.img = img1;
        this.category = category;
        this.sellType = sellType;
this.latitude=latitude;
this.longitude=longitude;
    }
}


