package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dtoSignIn {

    @SerializedName("customer")
    @Expose
    dtoCustomerSignUp customer;

    public dtoSignIn(dtoCustomerSignUp customer) {
        this.customer = customer;
    }

    public dtoCustomerSignUp getCustomer() {
        return customer;
    }

    public void setCustomer(dtoCustomerSignUp customer) {
        this.customer = customer;
    }
}
