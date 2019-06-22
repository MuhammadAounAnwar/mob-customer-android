package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.SerializedName;

public class dtoOnlineSettings {

    @SerializedName("three_column")
    private boolean three_column;
    @SerializedName("one_line_category_bar")
    private boolean one_line_category_bar;
    @SerializedName("favicon")
    dtoFavIcon favicon;
    @SerializedName("logo")
    dtoLogo logo;
    @SerializedName("homepage_picture")
    dtoHomePage_Picture homepage_picture;
    @SerializedName("products_background_picture")
    dtoProducts_BG_Picture products_background_picture;
    @SerializedName("about_us")
    private boolean about_us;
    @SerializedName("restaurant_name")
    private boolean restaurant_name;
    @SerializedName("tag_line")
    private boolean tag_line;
    @SerializedName("tag_line_text")
    private String tag_line_text;
    @SerializedName("copyright")
    private boolean copyright;
    @SerializedName("copyright_text")
    private String copyright_text;
    @SerializedName("about_us_text")
    private String about_us_text;
    @SerializedName("phone")
    private boolean phone;
    @SerializedName("phone_text")
    private String phone_text;
    @SerializedName("facebook")
    private boolean facebook;
    @SerializedName("facebook_url")
    private String facebook_url;
    @SerializedName("google_plus")
    private boolean google_plus;
    @SerializedName("google_plus_url")
    private String google_plus_url;
    @SerializedName("twitter")
    private boolean twitter;
    @SerializedName("twitter_url")
    private String twitter_url;
    @SerializedName("youtube")
    private boolean youtube;
    @SerializedName("youtube_url")
    private String youtube_url;
    @SerializedName("instagram")
    private boolean instagram;
    @SerializedName("instagram_url")
    private String instagram_url;
    @SerializedName("service_charge_fee")
    private String service_charge_fee;


// Getter Methods

    public boolean getThree_column() {
        return three_column;
    }

    public boolean getOne_line_category_bar() {
        return one_line_category_bar;
    }

    public dtoFavIcon getFavicon() {
        return favicon;
    }

    public dtoLogo getLogo() {
        return logo;
    }

    public dtoHomePage_Picture getHomepage_picture() {
        return homepage_picture;
    }

    public dtoProducts_BG_Picture getProducts_background_picture() {
        return products_background_picture;
    }

    public boolean getAbout_us() {
        return about_us;
    }

    public boolean getRestaurant_name() {
        return restaurant_name;
    }

    public boolean getTag_line() {
        return tag_line;
    }

    public String getTag_line_text() {
        return tag_line_text;
    }

    public boolean getCopyright() {
        return copyright;
    }

    public String getCopyright_text() {
        return copyright_text;
    }

    public String getAbout_us_text() {
        return about_us_text;
    }

    public boolean getPhone() {
        return phone;
    }

    public String getPhone_text() {
        return phone_text;
    }

    public boolean getFacebook() {
        return facebook;
    }

    public String getFacebook_url() {
        return facebook_url;
    }

    public boolean getGoogle_plus() {
        return google_plus;
    }

    public String getGoogle_plus_url() {
        return google_plus_url;
    }

    public boolean getTwitter() {
        return twitter;
    }

    public String getTwitter_url() {
        return twitter_url;
    }

    public boolean getYoutube() {
        return youtube;
    }

    public String getYoutube_url() {
        return youtube_url;
    }

    public boolean getInstagram() {
        return instagram;
    }

    public String getInstagram_url() {
        return instagram_url;
    }

    // Setter Methods

    public void setThree_column(boolean three_column) {
        this.three_column = three_column;
    }

    public void setOne_line_category_bar(boolean one_line_category_bar) {
        this.one_line_category_bar = one_line_category_bar;
    }

    public void setFavicon(dtoFavIcon faviconObject) {
        this.favicon = faviconObject;
    }

    public void setLogo(dtoLogo logoObject) {
        this.logo = logoObject;
    }

    public void setHomepage_picture(dtoHomePage_Picture homepage_pictureObject) {
        this.homepage_picture = homepage_pictureObject;
    }

    public void setProducts_background_picture(dtoProducts_BG_Picture products_background_pictureObject) {
        this.products_background_picture = products_background_pictureObject;
    }

    public void setAbout_us(boolean about_us) {
        this.about_us = about_us;
    }

    public void setRestaurant_name(boolean restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public void setTag_line(boolean tag_line) {
        this.tag_line = tag_line;
    }

    public void setTag_line_text(String tag_line_text) {
        this.tag_line_text = tag_line_text;
    }

    public void setCopyright(boolean copyright) {
        this.copyright = copyright;
    }

    public void setCopyright_text(String copyright_text) {
        this.copyright_text = copyright_text;
    }

    public void setAbout_us_text(String about_us_text) {
        this.about_us_text = about_us_text;
    }

    public void setPhone(boolean phone) {
        this.phone = phone;
    }

    public void setPhone_text(String phone_text) {
        this.phone_text = phone_text;
    }

    public void setFacebook(boolean facebook) {
        this.facebook = facebook;
    }

    public void setFacebook_url(String facebook_url) {
        this.facebook_url = facebook_url;
    }

    public void setGoogle_plus(boolean google_plus) {
        this.google_plus = google_plus;
    }

    public void setGoogle_plus_url(String google_plus_url) {
        this.google_plus_url = google_plus_url;
    }

    public void setTwitter(boolean twitter) {
        this.twitter = twitter;
    }

    public void setTwitter_url(String twitter_url) {
        this.twitter_url = twitter_url;
    }

    public void setYoutube(boolean youtube) {
        this.youtube = youtube;
    }

    public void setYoutube_url(String youtube_url) {
        this.youtube_url = youtube_url;
    }

    public void setInstagram(boolean instagram) {
        this.instagram = instagram;
    }

    public void setInstagram_url(String instagram_url) {
        this.instagram_url = instagram_url;
    }

    public String getService_charge_fee() {
        return service_charge_fee;
    }

    public void setService_charge_fee(String service_charge_fee) {
        this.service_charge_fee = service_charge_fee;
    }

}
