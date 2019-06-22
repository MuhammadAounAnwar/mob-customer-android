package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.SerializedName;

public class dtoCompanyRetriveResponse {

    @SerializedName("success")
    private boolean success;
    @SerializedName("company")
    dtoCompany company;

    // Getter Methods

    public boolean getSuccess() {
        return success;
    }
    public dtoCompany getCompany() {
        return company;
    }

    // Setter Methods

    public void setSuccess(boolean success) {
        this.success = success;
    }
    public void setCompany(dtoCompany companyObject) {
        this.company = companyObject;
    }
}
