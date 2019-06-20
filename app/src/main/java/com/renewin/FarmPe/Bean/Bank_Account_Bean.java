package com.renewin.FarmPe.Bean;

public class Bank_Account_Bean {



    String  bnk_name;
    String bnk_id;
    String acc_name;
    String acc_no;
    String ifsc_cod;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    boolean isSelected;

    public String getAcc_name() {
        return acc_name;
    }

    public void setAcc_name(String acc_name) {
        this.acc_name = acc_name;
    }

    public String getAcc_no() {
        return acc_no;
    }

    public void setAcc_no(String acc_no) {
        this.acc_no = acc_no;
    }

    public String getIfsc_cod() {
        return ifsc_cod;
    }

    public void setIfsc_cod(String ifsc_cod) {
        this.ifsc_cod = ifsc_cod;
    }



    public String getBnk_name() {
        return bnk_name;
    }

    public void setBnk_name(String bnk_name) {
        this.bnk_name = bnk_name;
    }

    public String getBnk_id() {
        return bnk_id;
    }

    public void setBnk_id(String bnk_id) {
        this.bnk_id = bnk_id;
    }


    public Bank_Account_Bean(String bnk_name, String acc_name, String acc_no, String ifsc_cod, String bnk_id, boolean isSelected) {
        this.bnk_name = bnk_name;
        this.acc_name = acc_name;
        this.acc_no = acc_no;
        this.ifsc_cod = ifsc_cod;
        this.bnk_id = bnk_id;
        this.isSelected = isSelected;
    }
}
