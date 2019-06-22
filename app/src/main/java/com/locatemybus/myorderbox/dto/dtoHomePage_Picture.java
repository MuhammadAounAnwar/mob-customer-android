package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.SerializedName;

public class dtoHomePage_Picture {

    @SerializedName("url")
    private String url = null;
    @SerializedName("thumb")
    dtoThumb thumb;
    @SerializedName("preview")
    dtoPreview preview;


    // Getter Methods

    public String getUrl() {
        return url;
    }

    public dtoThumb getThumb() {
        return thumb;
    }

    public dtoPreview getPreview() {
        return preview;
    }

    // Setter Methods

    public void setUrl(String url) {
        this.url = url;
    }

    public void setThumb(dtoThumb thumbObject) {
        this.thumb = thumbObject;
    }

    public void setPreview(dtoPreview previewObject) {
        this.preview = previewObject;
    }


}
