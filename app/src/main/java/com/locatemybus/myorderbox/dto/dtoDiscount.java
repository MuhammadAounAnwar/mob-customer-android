package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dtoDiscount {

    @SerializedName("group_number")
    @Expose
    private String groupNumber;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("type_id")
    @Expose
    private Integer typeId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pricing_type")
    @Expose
    private dtoPriceType pricingType;
    @SerializedName("pricing_value")
    @Expose
    private String pricingValue;
    @SerializedName("amount")
    @Expose
    private String amount;

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public dtoPriceType getPricingType() {
        return pricingType;
    }

    public void setPricingType(dtoPriceType pricingType) {
        this.pricingType = pricingType;
    }

    public String getPricingValue() {
        return pricingValue;
    }

    public void setPricingValue(String pricingValue) {
        this.pricingValue = pricingValue;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}