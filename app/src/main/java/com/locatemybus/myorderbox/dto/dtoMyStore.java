package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dtoMyStore {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("online_ordering")
    @Expose
    private Boolean online_ordering;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("min_order")
    @Expose
    private String min_order;
    @SerializedName("domain")
    @Expose
    private String domain;
    @SerializedName("prep_time")
    @Expose
    private Integer prep_time;
    @SerializedName("enabled")
    @Expose
    private Boolean enabled;
    @SerializedName("delivery_radius")
    @Expose
    private String delivery_radius;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("call_centre_app")
    @Expose
    private Boolean call_centre_app;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("country_code")
    @Expose
    private String country_code;
    @SerializedName("time_zone")
    @Expose
    private String time_zone;
    @SerializedName("company_id")
    @Expose
    private Integer company_id;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("company_name_legal")
    @Expose
    private String company_name_legal;
    @SerializedName("vat_registration_no")
    @Expose
    private String vat_registration_no;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("delivery_time_mins")
    @Expose
    private Integer delivery_time_mins;
    @SerializedName("delivery_service_charges")
    @Expose
    private Boolean delivery_service_charges;
    @SerializedName("allow_table_reservations")
    @Expose
    private Boolean allow_table_reservations;
    @SerializedName("unregistered_customers")
    @Expose
    private Boolean unregistered_customers;
    @SerializedName("opening_time")
    @Expose
    private String opening_time;
    @SerializedName("closing_time")
    @Expose
    private String closing_time;
    @SerializedName("delivery_from")
    @Expose
    private String delivery_from;
    @SerializedName("delivery_to")
    @Expose
    private String delivery_to;
    @SerializedName("home_page_message")
    @Expose
    private String home_page_message;
    @SerializedName("cart_message")
    @Expose
    private String cart_message;
    @SerializedName("offer_collection")
    @Expose
    private Boolean offer_collection;
    @SerializedName("offer_delivery")
    @Expose
    private Boolean offer_delivery;
    @SerializedName("collection_temp_disabled")
    @Expose
    private Boolean collection_temp_disabled;
    @SerializedName("delivery_temp_disabled")
    @Expose
    private Boolean delivery_temp_disabled;
    @SerializedName("online_payment_types")
    @Expose
    private dtoOnlinePaymentTypes online_payment_types;
    @SerializedName("online_settings")
    @Expose
    private dtoOnlineSettings online_settings;
    @SerializedName("address")
    @Expose
    private dtoAddress address;
    @SerializedName("delivery_postcodes")
    @Expose
    private List<dtoDeliveryPostcode> delivery_postcodes = null;


    /*Getter Setter*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOnlineOrdering() {
        return online_ordering;
    }

    public void setOnlineOrdering(Boolean online_ordering) {
        this.online_ordering = online_ordering;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinOrder() {
        return min_order;
    }

    public void setMinOrder(String min_order) {
        this.min_order = min_order;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getPrepTime() {
        return prep_time;
    }

    public void setPrepTime(Integer prepTime) {
        this.prep_time = prep_time;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getDeliveryRadius() {
        return delivery_radius;
    }

    public void setDeliveryRadius(String delivery_radius) {
        this.delivery_radius = delivery_radius;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Boolean getCallCentreApp() {
        return call_centre_app;
    }

    public void setCallCentreApp(Boolean call_centre_app) {
        this.call_centre_app = call_centre_app;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountryCode() {
        return country_code;
    }

    public void setCountryCode(String country_code) {
        this.country_code = country_code;
    }

    public String getTimeZone() {
        return time_zone;
    }

    public void setTimeZone(String time_zone) {
        this.time_zone = time_zone;
    }

    public Integer getCompanyId() {
        return company_id;
    }

    public void setCompanyId(Integer company_id) {
        this.company_id = company_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCompanyNameLegal() {
        return company_name_legal;
    }

    public void setCompanyNameLegal(String company_name_legal) {
        this.company_name_legal = company_name_legal;
    }

    public String getVatRegistrationNo() {
        return vat_registration_no;
    }

    public void setVatRegistrationNo(String vat_registration_no) {
        this.vat_registration_no = vat_registration_no;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getDeliveryTimeMins() {
        return delivery_time_mins;
    }

    public void setDeliveryTimeMins(Integer delivery_time_mins) {
        this.delivery_time_mins = delivery_time_mins;
    }

    public Boolean getDeliveryServiceCharges() {
        return delivery_service_charges;
    }

    public void setDeliveryServiceCharges(Boolean delivery_service_charges) {
        this.delivery_service_charges = delivery_service_charges;
    }

    public Boolean getAllowTableReservations() {
        return allow_table_reservations;
    }

    public void setAllowTableReservations(Boolean allowTableReservations) {
        this.allow_table_reservations = allow_table_reservations;
    }

    public Boolean getUnregisteredCustomers() {
        return unregistered_customers;
    }

    public void setUnregisteredCustomers(Boolean unregistered_customers) {
        this.unregistered_customers = unregistered_customers;
    }

    public String getOpeningTime() {
        return opening_time;
    }

    public void setOpeningTime(String opening_time) {
        this.opening_time = opening_time;
    }

    public String getClosingTime() {
        return closing_time;
    }

    public void setClosingTime(String closing_time) {
        this.closing_time = closing_time;
    }

    public String getDeliveryFrom() {
        return delivery_from;
    }

    public void setDeliveryFrom(String delivery_from) {
        this.delivery_from = delivery_from;
    }

    public String getDeliveryTo() {
        return delivery_to;
    }

    public void setDeliveryTo(String delivery_to) {
        this.delivery_to = delivery_to;
    }

    public String getHomePageMessage() {
        return home_page_message;
    }

    public void setHomePageMessage(String home_page_message) {
        this.home_page_message = home_page_message;
    }

    public String getCartMessage() {
        return cart_message;
    }

    public void setCartMessage(String cart_message) {
        this.cart_message = cart_message;
    }

    public Boolean getOfferCollection() {
        return offer_collection;
    }

    public void setOfferCollection(Boolean offer_collection) {
        this.offer_collection = offer_collection;
    }

    public Boolean getOfferDelivery() {
        return offer_delivery;
    }

    public void setOfferDelivery(Boolean offer_delivery) {
        this.offer_delivery = offer_delivery;
    }

    public Boolean getCollectionTempDisabled() {
        return collection_temp_disabled;
    }

    public void setCollectionTempDisabled(Boolean collection_temp_disabled) {
        this.collection_temp_disabled = collection_temp_disabled;
    }

    public Boolean getDeliveryTempDisabled() {
        return delivery_temp_disabled;
    }

    public void setDeliveryTempDisabled(Boolean delivery_temp_disabled) {
        this.delivery_temp_disabled = delivery_temp_disabled;
    }

    public dtoOnlinePaymentTypes getOnlinePaymentTypes() {
        return online_payment_types;
    }

    public void setOnlinePaymentTypes(dtoOnlinePaymentTypes online_payment_types) {
        this.online_payment_types = online_payment_types;
    }

    public dtoOnlineSettings getOnlineSettings() {
        return online_settings;
    }

    public void setOnlineSettings(dtoOnlineSettings online_settings) {
        this.online_settings = online_settings;
    }

    public dtoAddress getAddress() {
        return address;
    }

    public void setAddress(dtoAddress address) {
        this.address = address;
    }

    public List<dtoDeliveryPostcode> getDeliveryPostcodes() {
        return delivery_postcodes;
    }

    public void setDeliveryPostcodes(List<dtoDeliveryPostcode> deliveryPostcodes) {
        this.delivery_postcodes = delivery_postcodes;
    }

}