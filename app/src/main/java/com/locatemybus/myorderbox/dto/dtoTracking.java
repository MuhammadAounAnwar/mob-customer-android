package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dtoTracking {

    @SerializedName("store")
    @Expose
    private dtoTrackingStore store;
    @SerializedName("order")
    @Expose
    private dtoTrackingOrder order;
    @SerializedName("driver")
    @Expose
    private dtoDriver driver;

    public dtoTrackingStore getStore() {
        return store;
    }

    public void setStore(dtoTrackingStore store) {
        this.store = store;
    }

    public dtoTrackingOrder getOrder() {
        return order;
    }

    public void setOrder(dtoTrackingOrder order) {
        this.order = order;
    }

    public dtoDriver getDriver() {
        return driver;
    }

    public void setDriver(dtoDriver driver) {
        this.driver = driver;
    }

}