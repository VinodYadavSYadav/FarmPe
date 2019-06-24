package com.renewin.FarmPe.Bean;


public class Select_Language_LoginBean {

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getLanguageid() {
        return languageid;
    }

    public void setLanguageid(int languageid) {
        this.languageid = languageid;
    }

    String vendor;

    int languageid;




    public Select_Language_LoginBean( String vendor, int languageid) {

        this.vendor = vendor;
        this.languageid=languageid;

    }
}


