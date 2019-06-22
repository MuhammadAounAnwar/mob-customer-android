package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.SerializedName;

public enum dtoForOrderTypes {
    @SerializedName("0")
    eat_in(0),
    @SerializedName("1")
    collection(1),
    @SerializedName("2")
    delivery(2);

    private int value;
    private dtoForOrderTypes(int value) {
        this.value = value;
    }

    public int getOrderType() {
        return value;
    }
    public String getName()
    {
        switch (value)
        {
            case 0:
                return "eat_in";
            case 1:
                return "collection";
            case 2:
                return "delivery";
            default:
                return "";
        }
    }


}