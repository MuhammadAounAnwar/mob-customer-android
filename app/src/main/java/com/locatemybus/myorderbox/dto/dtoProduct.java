package com.locatemybus.myorderbox.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class dtoProduct {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_online")
    @Expose
    private String nameOnline;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("discount_applicable")
    @Expose
    private Boolean discountApplicable;
    @SerializedName("featured")
    @Expose
    private Boolean featured;
    @SerializedName("has_attributes")
    @Expose
    private Boolean hasAttributes;
    @SerializedName("prod_type")
    @Expose
    private String prodType;
    @SerializedName("enabled_for")
    @Expose
    private String enabledFor;
    @SerializedName("show_for")
    @Expose
    private String showFor;
    @SerializedName("prep_time")
    @Expose
    private String prepTime;
    @SerializedName("name_receipt")
    @Expose
    private String nameReceipt;
    @SerializedName("time")
    @Expose
    private Boolean time;
    @SerializedName("when")
    @Expose
    private Date when;
    @SerializedName("time_from")
    @Expose
    private Date timeFrom;
    @SerializedName("time_to")
    @Expose
    private Date timeTo;
    @SerializedName("custom_price")
    @Expose
    private Boolean customPrice;
    @SerializedName("mon")
    @Expose
    private Boolean mon;
    @SerializedName("tue")
    @Expose
    private Boolean tue;
    @SerializedName("wed")
    @Expose
    private Boolean wed;
    @SerializedName("thu")
    @Expose
    private Boolean thu;
    @SerializedName("fri")
    @Expose
    private Boolean fri;
    @SerializedName("sat")
    @Expose
    private Boolean sat;
    @SerializedName("sun")
    @Expose
    private Boolean sun;
    @SerializedName("tags")
    @Expose
    private List<dtoTags> tags;
    @SerializedName("time_restriction")
    @Expose
    private Boolean timeRestriction;
    @SerializedName("time_interval")
    @Expose
    private String timeInterval;
    @SerializedName("quick_look_up_code")
    @Expose
    private String quickLookUpCode;
    @SerializedName("enabled_for_eat_in")
    @Expose
    private Boolean enabledForEatIn;
    @SerializedName("enabled_for_collection")
    @Expose
    private Boolean enabledForCollection;
    @SerializedName("enabled_for_delivery")
    @Expose
    private Boolean enabledForDelivery;
    @SerializedName("options")
    @Expose
    private Boolean options;
    @SerializedName("attributes")
    @Expose
    private List<dtoProductAttribute> attributes = null;
    @SerializedName("image")
    @Expose
    private dtoImage image = null;

    /*
    * Extra
    * */


    private List<dtoMyOrder> attributeOptions = null;
    private dtoProductAttribute attribute;
    private double TotalAmount;
    private double ItemAmount;
    private double ExtraAmount;
    private double DiscountAmount;
    private int quantity;



    /*Constructor*/
    public dtoProduct(){
        tags=new ArrayList<>();
        attributes=new ArrayList<>();
        image=new dtoImage();
        attributeOptions=new ArrayList<>();
    }

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

    public String getNameOnline() {
        return nameOnline;
    }

    public void setNameOnline(String nameOnline) {
        this.nameOnline = nameOnline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getDiscountApplicable() {
        return discountApplicable;
    }

    public void setDiscountApplicable(Boolean discountApplicable) {
        this.discountApplicable = discountApplicable;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Boolean getHasAttributes() {
        return hasAttributes;
    }

    public void setHasAttributes(Boolean hasAttributes) {
        this.hasAttributes = hasAttributes;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getEnabledFor() {
        return enabledFor;
    }

    public void setEnabledFor(String enabledFor) {
        this.enabledFor = enabledFor;
    }

    public String getShowFor() {
        return showFor;
    }

    public void setShowFor(String showFor) {
        this.showFor = showFor;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getNameReceipt() {
        return nameReceipt;
    }

    public void setNameReceipt(String nameReceipt) {
        this.nameReceipt = nameReceipt;
    }

    public Boolean getTime() {
        return time;
    }

    public void setTime(Boolean time) {
        this.time = time;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    public Boolean getCustomPrice() {
        return customPrice;
    }

    public void setCustomPrice(Boolean customPrice) {
        this.customPrice = customPrice;
    }

    public Boolean getMon() {
        return mon;
    }

    public void setMon(Boolean mon) {
        this.mon = mon;
    }

    public Boolean getTue() {
        return tue;
    }

    public void setTue(Boolean tue) {
        this.tue = tue;
    }

    public Boolean getWed() {
        return wed;
    }

    public void setWed(Boolean wed) {
        this.wed = wed;
    }

    public Boolean getThu() {
        return thu;
    }

    public void setThu(Boolean thu) {
        this.thu = thu;
    }

    public Boolean getFri() {
        return fri;
    }

    public void setFri(Boolean fri) {
        this.fri = fri;
    }

    public Boolean getSat() {
        return sat;
    }

    public void setSat(Boolean sat) {
        this.sat = sat;
    }

    public Boolean getSun() {
        return sun;
    }

    public void setSun(Boolean sun) {
        this.sun = sun;
    }

    public List<dtoTags> getTags() {
        return tags;
    }

    public void setTags(List<dtoTags> tags) {
        this.tags = tags;
    }

    public Boolean getTimeRestriction() {
        return timeRestriction;
    }

    public void setTimeRestriction(Boolean timeRestriction) {
        this.timeRestriction = timeRestriction;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getQuickLookUpCode() {
        return quickLookUpCode;
    }

    public void setQuickLookUpCode(String quickLookUpCode) {
        this.quickLookUpCode = quickLookUpCode;
    }

    public Boolean getEnabledForEatIn() {
        return enabledForEatIn;
    }

    public void setEnabledForEatIn(Boolean enabledForEatIn) {
        this.enabledForEatIn = enabledForEatIn;
    }

    public Boolean getEnabledForCollection() {
        return enabledForCollection;
    }

    public void setEnabledForCollection(Boolean enabledForCollection) {
        this.enabledForCollection = enabledForCollection;
    }

    public Boolean getEnabledForDelivery() {
        return enabledForDelivery;
    }

    public void setEnabledForDelivery(Boolean enabledForDelivery) {
        this.enabledForDelivery = enabledForDelivery;
    }

    public Boolean getOptions() {
        return options;
    }

    public void setOptions(Boolean options) {
        this.options = options;
    }

    public List<dtoProductAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<dtoProductAttribute> attributes) {
        this.attributes = attributes;
    }

    public dtoImage getImage() {
        return image;
    }

    public void setImage(dtoImage image) {
        this.image = image;
    }

    /*
    * Extra
    * */

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        TotalAmount = totalAmount;
    }

    public double getExtraAmount() {
        return ExtraAmount;
    }

    public void setExtraAmount(double extraAmount) {
        ExtraAmount = extraAmount;
    }

    public double getDiscountAmount() {
        return DiscountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        DiscountAmount = discountAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemAmount() {
        return ItemAmount;
    }

    public void setItemAmount(double itemAmount) {
        ItemAmount = itemAmount;
    }

    public dtoProductAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(dtoProductAttribute attribute) {
        this.attribute = attribute;
    }

    public List<dtoMyOrder> getAttributeOptions() {
        return attributeOptions;
    }

    public void setAttributeOptions(List<dtoMyOrder> attributeOptions) {
        this.attributeOptions = attributeOptions;
    }
}
