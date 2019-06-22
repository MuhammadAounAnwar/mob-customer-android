package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class dtoMyOrderResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("orders")
    @Expose
    private List<dtoOrder> orders = new ArrayList<>();

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<dtoOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<dtoOrder> orders) {
        this.orders = orders;
    }

}
