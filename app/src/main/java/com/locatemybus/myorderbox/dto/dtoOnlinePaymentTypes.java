package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dtoOnlinePaymentTypes {

    @SerializedName("stripe")
    @Expose
    private boolean stripe;
    @SerializedName("stripe_user_id")
    @Expose
    private String stripe_user_id;


// Getter Setter Methods

    public boolean getStripe() {
        return stripe;
    }

    public void setStripe(boolean stripe) {
        this.stripe = stripe;
    }

    public String getStripe_user_id() {
        return stripe_user_id;
    }

    public void setStripe_user_id(String stripe_user_id) {
        this.stripe_user_id = stripe_user_id;
    }
}
