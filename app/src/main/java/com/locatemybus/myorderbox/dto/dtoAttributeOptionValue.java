package com.locatemybus.myorderbox.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dtoAttributeOptionValue {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("cost_price")
    @Expose
    private Object costPrice;
    @SerializedName("plu_id")
    @Expose
    private Integer pluId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("prices")
    @Expose
    private List<dtoPrice> prices = null;
    @SerializedName("default")
    @Expose
    private boolean _default;
    @SerializedName("substitute")
    @Expose
    private Boolean substitute;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("discount")
    @Expose
    private String discount;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Object getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Object costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getPluId() {
        return pluId;
    }

    public void setPluId(Integer pluId) {
        this.pluId = pluId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean getDefault() {
        return _default;
    }

    public void setDefault(Boolean _default) {
        this._default = _default;
    }

    public Boolean getSubstitute() {
        return substitute;
    }

    public void setSubstitute(Boolean substitute) {
        this.substitute = substitute;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

}