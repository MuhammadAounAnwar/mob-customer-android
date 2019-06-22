package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class dtoCategories {

    @SerializedName("categories")
    @Expose
    private ArrayList<dtoCategory> categories = null;
    public ArrayList<dtoCategory> getCategories() {
        return categories;
    }
    public void setCategories(ArrayList<dtoCategory> categories) {
        this.categories = categories;
    }

}