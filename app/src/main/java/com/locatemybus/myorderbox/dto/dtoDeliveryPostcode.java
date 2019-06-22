package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dtoDeliveryPostcode {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("postcode")
    @Expose
    private String postcode;
    @SerializedName("minimum")
    @Expose
    private String minimum;
    @SerializedName("charge")
    @Expose
    private String charge;
    @SerializedName("store_id")
    @Expose
    private Integer storeId;
    @SerializedName("domain")
    @Expose
    private String domain;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

}
