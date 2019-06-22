package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class dtoDeal {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("auto_apply")
    @Expose
    private Boolean autoApply;
    @SerializedName("override_delivery")
    @Expose
    private Boolean overrideDelivery;
    @SerializedName("on_menu")
    @Expose
    private Boolean onMenu;
    @SerializedName("auto_discount")
    @Expose
    private Boolean autoDiscount;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("coupon_only")
    @Expose
    private Boolean couponOnly;
    @SerializedName("min_cart_value")
    @Expose
    private String minCartValue;
    @SerializedName("for_customer")
    @Expose
    private String forCustomer;
    @SerializedName("once_per_customer")
    @Expose
    private Boolean oncePerCustomer;
    @SerializedName("limited_stock")
    @Expose
    private Boolean limitedStock;
    @SerializedName("stock_count")
    @Expose
    private String stockCount;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("discount_options")
    @Expose
    private Boolean discountOptions;
    @SerializedName("non_stop")
    @Expose
    private Boolean nonStop;
    @SerializedName("start_date")
    @Expose
    private Date startDate;
    @SerializedName("end_date")
    @Expose
    private Date endDate;
    @SerializedName("overlapping")
    @Expose
    private String overlapping;
    @SerializedName("created_at")
    @Expose
    private Date createdAt;
    @SerializedName("updated_at")
    @Expose
    private Date updatedAt;
    @SerializedName("for_order_types")
    @Expose
    private List<String> forOrderTypes = null;
    @SerializedName("groups")
    @Expose
    private List<dtoGroup> groups = null;

    @SerializedName("image")
    @Expose
    private dtoImage image;

    /*
    * Extra Price
    * */
    private dtoImage TotalPrice;


    /*Constructor*/

    public dtoDeal(){
        forOrderTypes=new ArrayList<>();
        groups=new ArrayList<>();
    }


    /*Getter and Setter*/


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAutoApply() {
        return autoApply;
    }

    public void setAutoApply(Boolean autoApply) {
        this.autoApply = autoApply;
    }

    public Boolean getOverrideDelivery() {
        return overrideDelivery;
    }

    public void setOverrideDelivery(Boolean overrideDelivery) {
        this.overrideDelivery = overrideDelivery;
    }

    public Boolean getOnMenu() {
        return onMenu;
    }

    public void setOnMenu(Boolean onMenu) {
        this.onMenu = onMenu;
    }

    public Boolean getAutoDiscount() {
        return autoDiscount;
    }

    public void setAutoDiscount(Boolean autoDiscount) {
        this.autoDiscount = autoDiscount;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Boolean getCouponOnly() {
        return couponOnly;
    }

    public void setCouponOnly(Boolean couponOnly) {
        this.couponOnly = couponOnly;
    }

    public String getMinCartValue() {
        return minCartValue;
    }

    public void setMinCartValue(String minCartValue) {
        this.minCartValue = minCartValue;
    }

    public String getForCustomer() {
        return forCustomer;
    }

    public void setForCustomer(String forCustomer) {
        this.forCustomer = forCustomer;
    }

    public Boolean getOncePerCustomer() {
        return oncePerCustomer;
    }

    public void setOncePerCustomer(Boolean oncePerCustomer) {
        this.oncePerCustomer = oncePerCustomer;
    }

    public Boolean getLimitedStock() {
        return limitedStock;
    }

    public void setLimitedStock(Boolean limitedStock) {
        this.limitedStock = limitedStock;
    }

    public String getStockCount() {
        return stockCount;
    }

    public void setStockCount(String stockCount) {
        this.stockCount = stockCount;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getDiscountOptions() {
        return discountOptions;
    }

    public void setDiscountOptions(Boolean discountOptions) {
        this.discountOptions = discountOptions;
    }

    public Boolean getNonStop() {
        return nonStop;
    }

    public void setNonStop(Boolean nonStop) {
        this.nonStop = nonStop;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getOverlapping() {
        return overlapping;
    }

    public void setOverlapping(String overlapping) {
        this.overlapping = overlapping;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getForOrderTypes() {
        return forOrderTypes;
    }

    public void setForOrderTypes(List<String> forOrderTypes) {
        this.forOrderTypes = forOrderTypes;
    }

    public List<dtoGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<dtoGroup> groups) {
        this.groups = groups;
    }

    public dtoImage getImage() {
        return image;
    }

    public void setImage(dtoImage image) {
        this.image = image;
    }

    public dtoImage getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(dtoImage totalPrice) {
        TotalPrice = totalPrice;
    }
}