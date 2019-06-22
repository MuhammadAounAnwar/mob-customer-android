package com.locatemybus.myorderbox.dto;
import com.google.gson.annotations.SerializedName;

public enum dtoOrderTypes {
    @SerializedName("0")
    eat_in(0),
    @SerializedName("1")
    collection(1),
    @SerializedName("2")
    delivery(2);

    private int value;

    private dtoOrderTypes(int value) {
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
                return "Parent";
            case 1:
                return "Child";
            case 2:
                return "Council";
            default:
                return "";
        }
    }


}