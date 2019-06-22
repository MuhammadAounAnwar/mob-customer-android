package com.locatemybus.myorderbox.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dtoPayment {

    @SerializedName("payment")
    @Expose
    private String payment;
    @SerializedName("change")
    @Expose
    private String change;
    @SerializedName("tendered")
    @Expose
    private String tendered;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getTendered() {
        return tendered;
    }

    public void setTendered(String tendered) {
        this.tendered = tendered;
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

}