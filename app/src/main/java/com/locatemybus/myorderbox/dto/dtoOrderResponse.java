package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dtoOrderResponse {

    @SerializedName("order")
    @Expose
    private dtoOResponse_Order order;
    @SerializedName("success")
    @Expose
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public dtoOResponse_Order getOrder() {
        return order;
    }

    public void setOrder(dtoOResponse_Order order) {
        this.order = order;
    }

}