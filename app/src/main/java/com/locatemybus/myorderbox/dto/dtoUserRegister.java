package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.SerializedName;

public class dtoUserRegister {

    @SerializedName("customer")
    dtoCustomerSignUp customer;

    public dtoUserRegister(dtoCustomerSignUp customer) {
        this.customer = customer;
    }

    public dtoCustomerSignUp getCustomer() {
        return customer;
    }

    public void setCustomer(dtoCustomerSignUp customer) {
        this.customer = customer;
    }

}
