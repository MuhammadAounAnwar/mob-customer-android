package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class dtoDeals {

    @SerializedName("code")
    String code;
    @SerializedName("name")
    String name;
    @SerializedName("deals")
    List<dtoDeal> deals=null;

    public dtoDeals(){
        deals=new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<dtoDeal> getDeals() {
        return deals;
    }

    public void setDeals(List<dtoDeal> deals) {
        this.deals = deals;
    }
}
