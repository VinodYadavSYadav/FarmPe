package com.FarmPe.India.Bean;

public class Variety_Bean {

    String  name;
    String  id;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    boolean isSelected;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Variety_Bean(String name, String id, boolean isSelected) {

        this.name = name;
        this.id = id;
        this.isSelected = isSelected;

    }
    }
