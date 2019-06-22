package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dtoAttributeOptionResponse {

    @SerializedName("product")
    @Expose
    private dtoAttributeProduct product;

    public dtoAttributeProduct getProduct() {
        return product;
    }

    public void setProduct(dtoAttributeProduct product) {
        this.product = product;
    }

}
