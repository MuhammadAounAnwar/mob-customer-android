package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dtoItem {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("product_attribute_id")
    @Expose
    private Integer productAttributeId;
    @SerializedName("product_attribute")
    @Expose
    private dtoProductAttribute productAttribute;
    @SerializedName("product")
    @Expose
    private dtoProduct product;

    /*Getter Setter*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductAttributeId() {
        return productAttributeId;
    }

    public void setProductAttributeId(Integer productAttributeId) {
        this.productAttributeId = productAttributeId;
    }

    public dtoProductAttribute getProductAttribute() {
        return productAttribute;
    }

    public void setProductAttribute(dtoProductAttribute productAttribute) {
        this.productAttribute = productAttribute;
    }

    public dtoProduct getProduct() {
        return product;
    }

    public void setProduct(dtoProduct product) {
        this.product = product;
    }

}