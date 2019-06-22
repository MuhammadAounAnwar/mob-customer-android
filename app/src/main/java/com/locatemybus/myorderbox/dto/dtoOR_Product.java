package com.locatemybus.myorderbox.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class dtoOR_Product {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("option_values")
    @Expose
    private List<dtoMyOrder> optionValues = new ArrayList<>();
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("primary")
    @Expose
    private Boolean primary;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("attribute_id")
    @Expose
    private String attributeId;
    @SerializedName("split")
    @Expose
    private Boolean split;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("product_id")
    @Expose
    private String productId;

    @SerializedName("deal_id")
    @Expose
    private String deal_id;



    public dtoOR_Product(String name, List<dtoMyOrder> optionValues, String price, Boolean primary, String discount, Integer quantity, String attributeId, Boolean split, String id, String productId,String deal_id ) {
        this.name = name;
        this.optionValues = optionValues;
        this.price = price;
        this.primary = primary;
        this.discount = discount;
        this.quantity = quantity;
        this.attributeId = attributeId;
        this.split = split;
        this.id = id;
        this.productId = productId;
        this.deal_id=deal_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<dtoMyOrder> getOptionValues() {
        return optionValues;
    }

    public void setOptionValues(List<dtoMyOrder> optionValues) {
        this.optionValues = optionValues;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public Boolean getSplit() {
        return split;
    }

    public void setSplit(Boolean split) {
        this.split = split;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDeal_id() {
        return deal_id;
    }

    public void setDeal_id(String deal_id) {
        this.deal_id = deal_id;
    }

}