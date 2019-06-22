package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dtoAttributeProduct {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("attribute_id")
    @Expose
    private Integer attributeId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("attribute_name")
    @Expose
    private String attributeName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("for_order_types")
    @Expose
    private List<String> forOrderTypes = null;
    @SerializedName("split_products_charge")
    @Expose
    private String splitProductsCharge;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("prices")
    @Expose
    private List<dtoPrice> prices = null;
    @SerializedName("options")
    @Expose
    private List<dtoAttributeOptions> options = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getForOrderTypes() {
        return forOrderTypes;
    }

    public void setForOrderTypes(List<String> forOrderTypes) {
        this.forOrderTypes = forOrderTypes;
    }

    public String getSplitProductsCharge() {
        return splitProductsCharge;
    }

    public void setSplitProductsCharge(String splitProductsCharge) {
        this.splitProductsCharge = splitProductsCharge;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<dtoPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<dtoPrice> prices) {
        this.prices = prices;
    }

    public List<dtoAttributeOptions> getOptions() {
        return options;
    }

    public void setOptions(List<dtoAttributeOptions> options) {
        this.options = options;
    }

}
