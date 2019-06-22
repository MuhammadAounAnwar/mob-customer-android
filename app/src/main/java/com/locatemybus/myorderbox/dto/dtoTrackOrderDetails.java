package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dtoTrackOrderDetails {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("order")
    @Expose
    private dtoOR_Order order;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public dtoOR_Order getOrder() {
        return order;
    }

    public void setOrder(dtoOR_Order order) {
        this.order = order;
    }

}
