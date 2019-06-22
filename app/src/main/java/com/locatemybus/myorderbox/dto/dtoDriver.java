package com.locatemybus.myorderbox.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dtoDriver {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("eta_at")
    @Expose
    private Object etaAt;
    @SerializedName("eta_minutes")
    @Expose
    private String etaMinutes;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Object getEtaAt() {
        return etaAt;
    }

    public void setEtaAt(Object etaAt) {
        this.etaAt = etaAt;
    }

    public String getEtaMinutes() {
        return etaMinutes;
    }

    public void setEtaMinutes(String etaMinutes) {
        this.etaMinutes = etaMinutes;
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

}