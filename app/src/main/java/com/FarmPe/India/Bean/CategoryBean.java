package com.FarmPe.India.Bean;

public class CategoryBean {

    String category,caticon,id,cat_img;


    public CategoryBean(String category, String id) {
        this.category = category;
        this.id=id;

        }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCaticon() {
        return caticon;
    }

    public void setCaticon(String caticon) {
        this.caticon = caticon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


