package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dtoDeliveryPostCodeResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("delivery_postcodes")
    @Expose
    private List<dtoDeliveryPostcode> deliveryPostcodes = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<dtoDeliveryPostcode> getDeliveryPostcodes() {
        return deliveryPostcodes;
    }

    public void setDeliveryPostcodes(List<dtoDeliveryPostcode> deliveryPostcodes) {
        this.deliveryPostcodes = deliveryPostcodes;
    }
}
