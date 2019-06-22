package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.SerializedName;

public class dtoLogo {

    @SerializedName("url")
    private String url;
    @SerializedName("thumb")
    dtoThumb thumb;
    @SerializedName("online")
    dtoOnline online;


    // Getter Methods

    public String getUrl() {
        return url;
    }

    public dtoThumb getThumb() {
        return thumb;
    }

    public dtoOnline getOnline() {
        return online;
    }

    // Setter Methods

    public void setUrl(String url) {
        this.url = url;
    }

    public void setThumb(dtoThumb thumbObject) {
        this.thumb = thumbObject;
    }

    public void setOnline(dtoOnline onlineObject) {
        this.online = onlineObject;
    }


}
