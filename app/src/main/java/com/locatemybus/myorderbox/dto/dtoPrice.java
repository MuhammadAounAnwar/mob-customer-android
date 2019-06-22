package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.SerializedName;

public class dtoPrice {

    @SerializedName("amount")
    private String amount;
    @SerializedName("price_type")
    private String price_type;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice_type() {
        return price_type;
    }

    public void setPrice_type(String price_type) {
        this.price_type = price_type;
    }
}
