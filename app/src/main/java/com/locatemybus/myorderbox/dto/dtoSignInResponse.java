package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class dtoSignInResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("first_name")
    @Expose
    private String first_name;
    @SerializedName("last_name")
    @Expose
    private String last_name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("home")
    @Expose
    private String home;
    @SerializedName("other")
    @Expose
    private String other;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("user_id")
    @Expose
    private Integer user_id;
    @SerializedName("store_id")
    @Expose
    private Integer store_id;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("delivery_charges")
    @Expose
    private String delivery_charges;
    @SerializedName("blocked")
    @Expose
    private boolean blocked;
    @SerializedName("flag")
    @Expose
    private boolean flag;
    @SerializedName("special_instructions")
    @Expose
    private String special_instructions;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("distance_from_store")
    @Expose
    private String distance_from_store;
    @SerializedName("rec_id")
    @Expose
    private String rec_id;
    @SerializedName("deleted_at")
    @Expose
    private Date deleted_at;
    @SerializedName("last_change")
    @Expose
    private Date last_change;
    @SerializedName("remote_system_id")
    @Expose
    private Integer remote_system_id;
    @SerializedName("stripe_token")
    @Expose
    private String stripe_token;
    @SerializedName("authentication_token")
    @Expose
    private String authentication_token;
    @SerializedName("redirect_url")
    @Expose
    private String redirect_url;
    @SerializedName("credit_allowed")
    @Expose
    private boolean credit_allowed;
    @SerializedName("discount_max")
    @Expose
    private Integer discount_max;
    @SerializedName("company_id")
    @Expose
    private Integer company_id;
    @SerializedName("sms_marketing")
    @Expose
    private boolean sms_marketing;
    @SerializedName("email_marketing")
    @Expose
    private boolean email_marketing;

    /*Getter Setter*/


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getStoreId() {
        return store_id;
    }

    public void setStoreId(Integer store_id) {
        this.store_id = store_id;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDeliveryCharges() {
        return delivery_charges;
    }

    public void setDeliveryCharges(String delivery_charges) {
        this.delivery_charges = delivery_charges;
    }

    public boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getSpecialInstructions() {
        return special_instructions;
    }

    public void setSpecialInstructions(String special_instructions) {
        this.special_instructions = special_instructions;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDistanceFromStore() {
        return distance_from_store;
    }

    public void setDistanceFromStore(String distance_from_store) {
        this.distance_from_store = distance_from_store;
    }

    public String getRecId() {
        return rec_id;
    }

    public void setRecId(String rec_id) {
        this.rec_id = rec_id;
    }

    public Date getDeletedAt() {
        return deleted_at;
    }

    public void setDeletedAt(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Date getLastChange() {
        return last_change;
    }

    public void setLastChange(Date last_change) {
        this.last_change = last_change;
    }

    public Integer getRemoteSystemId() {
        return remote_system_id;
    }

    public void setRemoteSystemId(Integer remote_system_id) {
        this.remote_system_id = remote_system_id;
    }

    public String getStripeToken() {
        return stripe_token;
    }

    public void setStripeToken(String stripe_token) {
        this.stripe_token = stripe_token;
    }

    public String getAuthenticationToken() {
        return authentication_token;
    }

    public void setAuthenticationToken(String authentication_token) {
        this.authentication_token = authentication_token;
    }

    public String getRedirectUrl() {
        return redirect_url;
    }

    public void setRedirectUrl(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public boolean getCreditAllowed() {
        return credit_allowed;
    }

    public void setCreditAllowed(boolean credit_allowed) {
        this.credit_allowed = credit_allowed;
    }

    public Integer getDiscountMax() {
        return discount_max;
    }

    public void setDiscountMax(Integer discount_max) {
        this.discount_max = discount_max;
    }

    public Integer getCompanyId() {
        return company_id;
    }

    public void setCompanyId(Integer company_id) {
        this.company_id = company_id;
    }

    public boolean getSmsMarketing() {
        return sms_marketing;
    }

    public void setSmsMarketing(boolean sms_marketing) {
        this.sms_marketing = sms_marketing;
    }

    public boolean getEmailMarketing() {
        return email_marketing;
    }

    public void setEmailMarketing(boolean email_marketing) {
        this.email_marketing = email_marketing;
    }

}