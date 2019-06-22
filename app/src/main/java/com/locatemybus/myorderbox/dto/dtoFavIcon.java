package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.SerializedName;

public class dtoFavIcon {

    @SerializedName("url")
    private String url = null;
    @SerializedName("favicon")
    private dtoFavIcon favicon;
    @SerializedName("thumb")
    private dtoThumb thumb;


    public dtoFavIcon getFavicon() {
        return favicon;
    }

    public void setFavicon(dtoFavIcon favicon) {
        this.favicon = favicon;
    }

    public dtoThumb getThumb() {
        return thumb;
    }

    public void setThumb(dtoThumb thumb) {
        this.thumb = thumb;
    }
// Getter Methods

    public String getUrl() {
        return url;
    }

    // Setter Methods
    public void setUrl(String url) {
        this.url = url;
    }

}
