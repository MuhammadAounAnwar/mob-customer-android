package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.SerializedName;

public class dtoTags {

    @SerializedName("tag")
    String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
