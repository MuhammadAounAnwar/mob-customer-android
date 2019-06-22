package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dtoDealsResponse {

    @SerializedName("success")
    boolean success;
    @SerializedName("deal_categories")
    List<dtoDeals> deal_categories;

    /*Getter Setter*/

    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public List<dtoDeals> getDeal_categories() {
        return deal_categories;
    }
    public void setDeal_categories(List<dtoDeals> deal_categories) {
        this.deal_categories = deal_categories;
    }





}
