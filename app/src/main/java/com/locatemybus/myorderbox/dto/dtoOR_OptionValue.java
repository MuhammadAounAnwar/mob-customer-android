package com.locatemybus.myorderbox.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dtoOR_OptionValue {

    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}