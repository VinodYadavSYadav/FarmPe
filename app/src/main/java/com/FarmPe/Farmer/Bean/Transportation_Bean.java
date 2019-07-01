package com.FarmPe.Xohri.Bean;

public class Transportation_Bean {

    String transptn_id;
    String  trans_type;
    String veh_num;
    String name;

    public Transportation_Bean(String transptn_id, String trans_type, String veh_num, String name) {
        this.transptn_id = transptn_id;
        this.trans_type = trans_type;
        this.veh_num = veh_num;
        this.name = name;
    }


    public String getTransptn_id() {
        return transptn_id;
    }

    public void setTransptn_id(String transptn_id) {
        this.transptn_id = transptn_id;
    }

    public String getTrans_type() {
        return trans_type;
    }
    public void setTrans_type(String trans_type) {
        this.trans_type = trans_type;
    }

    public String getVeh_num() {
        return veh_num;
    }

    public void setVeh_num(String veh_num) {
        this.veh_num = veh_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
