package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dtoOrderTracking {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("tracking")
    @Expose
    private dtoTracking tracking;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public dtoTracking getTracking() {
        return tracking;
    }

    public void setTracking(dtoTracking tracking) {
        this.tracking = tracking;
    }

}