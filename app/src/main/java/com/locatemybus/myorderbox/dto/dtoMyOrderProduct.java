package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class dtoMyOrderProduct {

    @SerializedName("item_number")
    @Expose
    private Integer item_number;
    @SerializedName("primary_part")
    @Expose
    private boolean primary_part;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tax")
    @Expose
    private Double tax;
    @SerializedName("tax_total")
    @Expose
    private String tax_total;
    @SerializedName("refunded")
    @Expose
    private boolean refunded;
    @SerializedName("refunded_amount")
    @Expose
    private String refunded_amount;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("options_discount")
    @Expose
    private String options_discount;
    @SerializedName("options")
    @Expose
    private List<dtoAttributeOptions> options = new ArrayList<>();

    /*Getter Setter*/

    public Integer getItemNumber() {
        return item_number;
    }

    public void setItemNumber(Integer item_number) {
        this.item_number = item_number;
    }

    public boolean getPrimaryPart() {
        return primary_part;
    }

    public void setPrimaryPart(boolean primary_part) {
        this.primary_part = primary_part;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public String getTaxTotal() {
        return tax_total;
    }

    public void setTaxTotal(String taxTotal) {
        this.tax_total = tax_total;
    }

    public Boolean getRefunded() {
        return refunded;
    }

    public void setRefunded(Boolean refunded) {
        this.refunded = refunded;
    }

    public String getRefundedAmount() {
        return refunded_amount;
    }

    public void setRefundedAmount(String refunded_amount) {
        this.refunded_amount = refunded_amount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getOptionsDiscount() {
        return options_discount;
    }

    public void setOptionsDiscount(String options_discount) {
        this.options_discount = options_discount;
    }

    public List<dtoAttributeOptions> getOptions() {
        return options;
    }

    public void setOptions(List<dtoAttributeOptions> options) {
        this.options = options;
    }

}