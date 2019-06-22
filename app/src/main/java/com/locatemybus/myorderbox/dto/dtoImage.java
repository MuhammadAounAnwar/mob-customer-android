package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.SerializedName;

public class dtoImage {

    @SerializedName("url")
    private String url;
    @SerializedName("thumb")
    private dtoThumb thumb;
    @SerializedName("online_sqaure")
    private dtoOnlineSqaure online_sqaure;
    @SerializedName("small")
    private dtoSmall small;
    @SerializedName("medium")
    private dtoMedium medium;
    @SerializedName("medium_plus")
    private dtoMediumPlus medium_plus;
    @SerializedName("large")
    private dtoLarge large;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public dtoThumb getThumb() {
        return thumb;
    }

    public void setThumb(dtoThumb thumb) {
        this.thumb = thumb;
    }

    public dtoOnlineSqaure getOnline_sqaure() {
        return online_sqaure;
    }

    public void setOnline_sqaure(dtoOnlineSqaure online_sqaure) {
        this.online_sqaure = online_sqaure;
    }

    public dtoSmall getSmall() {
        return small;
    }

    public void setSmall(dtoSmall small) {
        this.small = small;
    }

    public dtoMedium getMedium() {
        return medium;
    }

    public void setMedium(dtoMedium medium) {
        this.medium = medium;
    }

    public dtoMediumPlus getMedium_plus() {
        return medium_plus;
    }

    public void setMedium_plus(dtoMediumPlus medium_plus) {
        this.medium_plus = medium_plus;
    }

    public dtoLarge getLarge() {
        return large;
    }

    public void setLarge(dtoLarge large) {
        this.large = large;
    }
}