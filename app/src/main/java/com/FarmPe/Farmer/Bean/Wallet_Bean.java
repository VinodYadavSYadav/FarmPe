package com.FarmPe.Farmer.Bean;

public class Wallet_Bean {
    String Acc_Id;
    String Acc_No;
    String Balance;
    String points;

    public Wallet_Bean(String acc_Id, String acc_No, String balance, String points) {
        Acc_Id = acc_Id;
        Acc_No = acc_No;
        Balance = balance;
        this.points = points;
    }

    public String getAcc_Id() {
        return Acc_Id;
    }

    public void setAcc_Id(String acc_Id) {
        Acc_Id = acc_Id;
    }

    public String getAcc_No() {
        return Acc_No;
    }

    public void setAcc_No(String acc_No) {
        Acc_No = acc_No;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}


