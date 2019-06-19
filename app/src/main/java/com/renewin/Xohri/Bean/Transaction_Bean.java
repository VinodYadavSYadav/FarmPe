package com.renewin.Xohri.Bean;

public class Transaction_Bean {

    String transact_type;

    public String getTransact_type() {
        return transact_type;
    }

    public void setTransact_type(String transact_type) {
        this.transact_type = transact_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String description;
    String amount;
    String id;






    public Transaction_Bean(String description, String transact_type, String amount, String id) {

        this.description = description;
        this.transact_type = transact_type;
        this.amount = amount;
        this.id = id;
    }
}
