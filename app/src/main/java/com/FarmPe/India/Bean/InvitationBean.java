package com.FarmPe.India.Bean;

public class InvitationBean {

    String  type,id;
    boolean isselected;

    public InvitationBean(String type, String id,boolean isselected) {

        this.type = type;
        this.id = id;
        this.isselected = isselected;

        }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public boolean isIsselected() {
        return isselected;
    }

    public void setIsselected(boolean isselected) {
        this.isselected = isselected;
    }
}


