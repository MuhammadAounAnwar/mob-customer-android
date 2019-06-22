package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class dtoCompany {

    @SerializedName("id")
    private double id;
    @SerializedName("name")
    private String name;
    @SerializedName("enabled")
    private String enabled = null;
    @SerializedName("vat_registration_no")
    private String vat_registration_no = null;
    @SerializedName("contact_no")
    private String contact_no = null;
    @SerializedName("mobile_no")
    private String mobile_no = null;
    @SerializedName("store_ids")
    ArrayList<Integer> store_ids = new ArrayList <> ();
    @SerializedName("home_page_message")
    private String home_page_message;
    @SerializedName("cart_message")
    private String cart_message;
    @SerializedName("description")
    private String description;
    @SerializedName("logo")
    dtoLogo logo;
    @SerializedName("domain")
    private String domain;
    @SerializedName("Online_settings")
    dtoOnlineSettings Online_settings;


    // Getter Methods

    public double getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEnabled() {
        return enabled;
    }

    public String getVat_registration_no() {
        return vat_registration_no;
    }

    public String getContact_no() {
        return contact_no;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public String getHome_page_message() {
        return home_page_message;
    }

    public String getCart_message() {
        return cart_message;
    }

    public String getDescription() {
        return description;
    }

    public dtoLogo getLogo() {
        return logo;
    }

    public String getDomain() {
        return domain;
    }

    public dtoOnlineSettings getOnline_settings() {
        return Online_settings;
    }

    public ArrayList<Integer> getStore_ids() {
        return store_ids;
    }

    // Setter Methods

    public void setId(double id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public void setVat_registration_no(String vat_registration_no) {
        this.vat_registration_no = vat_registration_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public void setHome_page_message(String home_page_message) {
        this.home_page_message = home_page_message;
    }

    public void setCart_message(String cart_message) {
        this.cart_message = cart_message;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLogo(dtoLogo logoObject) {
        this.logo = logoObject;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setOnline_settings(dtoOnlineSettings online_settingsObject) {
        this.Online_settings = online_settingsObject;
    }

    public void setStore_ids(ArrayList<Integer> store_ids) {
        this.store_ids = store_ids;
    }

}
