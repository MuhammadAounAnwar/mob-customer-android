package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.SerializedName;

public class dtoDealDetailResponse {

    @SerializedName("success")
    boolean success;
    @SerializedName("deal")
    dtoDealDetail deal;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public dtoDealDetail getDeal() {
        return deal;
    }

    public void setDeal(dtoDealDetail deal) {
        this.deal = deal;
    }
}
