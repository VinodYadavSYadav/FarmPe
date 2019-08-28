package com.FarmPe.India.Bean;

public class StatusBean {

    String statusName,statusTime,image;

    public String getStatusName() {
        return statusName;
    }

    public String getStatusTime() {
        return statusTime;
    }

    public String getImage() {
        return image;
    }

    public StatusBean(String statusName, String statusTime, String image) {

        this.statusName = statusName;
        this.statusTime = statusTime;
        this.image = image;

    }

}


