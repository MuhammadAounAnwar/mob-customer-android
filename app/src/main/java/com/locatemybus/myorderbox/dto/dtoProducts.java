package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dtoProducts {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("products")
    @Expose
    private List<dtoProduct> products = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<dtoProduct> getProducts() {
        return products;
    }

    public void setProducts(List<dtoProduct> products) {
        this.products = products;
    }
}
