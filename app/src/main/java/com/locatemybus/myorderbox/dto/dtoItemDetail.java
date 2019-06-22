package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dtoItemDetail {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("product")
    @Expose
    private dtoProduct product = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public dtoProduct getProduct() {
        return product;
    }

    public void setProduct(dtoProduct product) {
        this.product = product;
    }
}
