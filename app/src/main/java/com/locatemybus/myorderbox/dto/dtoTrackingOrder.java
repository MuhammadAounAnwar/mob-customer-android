package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dtoTrackingOrder {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("public_status")
    @Expose
    private String publicStatus;
    @SerializedName("delivered_at")
    @Expose
    private Object deliveredAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublicStatus() {
        return publicStatus;
    }

    public void setPublicStatus(String publicStatus) {
        this.publicStatus = publicStatus;
    }

    public Object getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Object deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

}