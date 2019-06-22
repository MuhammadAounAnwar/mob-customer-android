package com.locatemybus.myorderbox.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dtoCustomerSignUp {

    @SerializedName("store_id")
    @Expose
    private Integer store_Id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("password_confirmation")
    @Expose
    private String password_confirmation;
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
    @SerializedName("special_instructions")
    @Expose
    private String special_instructions;
    @SerializedName("skip_registration")
    @Expose
    private String skip_registration;
    @SerializedName("addresses_attributes")
    @Expose
    private dtoAddressAttributes addressesAttributes;
    @SerializedName("email_marketing")
    @Expose
    private boolean email_marketing;
    @SerializedName("sms_marketing")
    @Expose
    private boolean sms_marketing;

    /*Constructor*/
    public dtoCustomerSignUp(String email, String password, String first_name) {
        this.email = email;
        this.password = password;
        this.first_name = first_name;
    }
    public dtoCustomerSignUp(Integer store_Id, String email, String password) {
        this.store_Id = store_Id;
        this.email = email;
        this.password = password;
    }
    public dtoCustomerSignUp(Integer store_Id, String email, String password,String password_confirmation, String first_name, String last_name, String mobile) {
        this.store_Id = store_Id;
        this.email = email;
        this.password = password;
        this.password_confirmation=password_confirmation;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mobile = mobile;
    }

    /*Getter Setter*/

    public Integer getStoreId() {
        return store_Id;
    }

    public void setStoreId(Integer storeId) {
        this.store_Id = storeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return password_confirmation;
    }

    public void setPasswordConfirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
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

    public String getSpecialInstructions() {
        return special_instructions;
    }

    public void setSpecialInstructions(String special_instructions) {
        this.special_instructions = special_instructions;
    }

    public String getSkipRegistration() {
        return skip_registration;
    }

    public void setSkipRegistration(String skip_registration) {
        this.skip_registration = skip_registration;
    }

    public dtoAddressAttributes getAddressesAttributes() {
        return addressesAttributes;
    }

    public void setAddressesAttributes(dtoAddressAttributes addressesAttributes) {
        this.addressesAttributes = addressesAttributes;
    }

    public boolean getEmailMarketing() {
        return email_marketing;
    }

    public void setEmailMarketing(boolean email_marketing) {
        this.email_marketing = email_marketing;
    }

    public boolean getSmsMarketing() {
        return sms_marketing;
    }

    public void setSmsMarketing(boolean sms_marketing) {
        this.sms_marketing = sms_marketing;
    }

}
