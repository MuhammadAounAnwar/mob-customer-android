package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dtoOrderRequest {

    @SerializedName("order")
    @Expose
    private dtoOR_Order order;

    public dtoOR_Order getOrder() {
        return order;
    }

    public void setOrder(dtoOR_Order order) {
        this.order = order;
    }

}