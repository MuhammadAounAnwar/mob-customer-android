package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dtoCustomers {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("customers")
    @Expose
    private List<dtoCustomer> customers = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<dtoCustomer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<dtoCustomer> customers) {
        this.customers = customers;
    }

}