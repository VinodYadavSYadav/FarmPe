package com.renewin.Xohri.Bean;

public class Kyc_Bean {

    String kyc_document;
    String kyc_name;
    String kyc_number;
    String kyc_id;


    public String getKyc_document() {
        return kyc_document;
    }

    public void setKyc_document(String kyc_document) {
        this.kyc_document = kyc_document;
    }

    public String getKyc_name() {
        return kyc_name;
    }

    public void setKyc_name(String kyc_name) {
        this.kyc_name = kyc_name;
    }

    public String getKyc_number() {
        return kyc_number;
    }

    public void setKyc_number(String kyc_number) {
        this.kyc_number = kyc_number;
    }

    public String getKyc_id() {
        return kyc_id;
    }

    public void setKyc_id(String kyc_id) {
        this.kyc_id = kyc_id;
    }




    public Kyc_Bean(String kyc_document, String kyc_name, String kyc_number, String kyc_id) {
        this.kyc_document = kyc_document;
        this.kyc_name = kyc_name;
        this.kyc_number = kyc_number;
        this.kyc_id = kyc_id;

    }
}
