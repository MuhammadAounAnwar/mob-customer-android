package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dtoMyStoreResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("store")
    @Expose
    private dtoMyStore store;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public dtoMyStore getStore() {
        return store;
    }

    public void setStore(dtoMyStore store) {
        this.store = store;
    }

}