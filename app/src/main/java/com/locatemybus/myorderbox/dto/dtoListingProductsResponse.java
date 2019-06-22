package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class dtoListingProductsResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("deals")
    @Expose
    private List<dtoDeal> deals = null;

    @SerializedName("products")
    @Expose
    private List<dtoProduct> products = null;

    public List<dtoProduct> getProducts() {
        return products;
    }

    public  dtoListingProductsResponse(){
        deals=new ArrayList<>();
        products=new ArrayList<>();
    }

    public void setProducts(List<dtoProduct> products) {
        this.products = products;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<dtoDeal> getDeals() {
        return deals;
    }

    public void setDeals(List<dtoDeal> deals) {
        this.deals = deals;
    }

}
