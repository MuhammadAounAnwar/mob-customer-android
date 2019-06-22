package com.locatemybus.myorderbox.dto;
import com.google.gson.annotations.SerializedName;

public enum dtoPriceType {
    @SerializedName("0")
    fixedPrice(0),
    @SerializedName("1")
    unchanged(1),
    @SerializedName("2")
    priceOff(2),
    @SerializedName("3")
    percentageOff(3);

    private int value;

    private dtoPriceType(int value) {
        this.value = value;
    }

    public int getPriceType() {
        return value;
    }
    public String getName()
    {
        switch (value)
        {
            case 0:
                return "fixedPrice";
            case 1:
                return "unchanged";
            case 2:
                return "priceOff";
            case 3:
                return "percentageOff";
            default:
                return "";
        }
    }


}