package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class dtoOR_Item {

    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("products")
    @Expose
    private List<dtoOR_Product> products = new ArrayList<>();
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("deal_id")
    @Expose
    private Integer dealId;
    @SerializedName("name")
    @Expose
    private String name;

    public dtoOR_Item(String quantity, String price, Integer dealId, String name, List<dtoOR_Product> products) {
        this.quantity = quantity;
        this.price = price;
        this.dealId = dealId;
        this.name = name;
        this.products = products;
    }

    public dtoOR_Item(String quantity, List<dtoOR_Product> products, String price) {
        this.quantity = quantity;
        this.products = products;
        this.price = price;
    }


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public List<dtoOR_Product> getProducts() {
        return products;
    }

    public void setProducts(List<dtoOR_Product> products) {
        this.products = products;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getDealId() {
        return dealId;
    }

    public void setDealId(Integer dealId) {
        this.dealId = dealId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}