package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class dtoDealDetail {

    @SerializedName("code")
    @Expose
    String code;
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
    private boolean auto_apply;
    @SerializedName("override_delivery")
    @Expose
    private boolean override_delivery;
    @SerializedName("on_menu")
    @Expose
    private boolean on_menu;
    @SerializedName("auto_discount")
    @Expose
    private boolean auto_discount;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("coupon_only")
    @Expose
    private Boolean coupon_only;
    @SerializedName("min_cart_value")
    @Expose
    private String min_cart_value;
    @SerializedName("for_customer")
    @Expose
    private String for_customer;
    @SerializedName("once_per_customer")
    @Expose
    private boolean once_per_customer;
    @SerializedName("limited_stock")
    @Expose
    private boolean limited_stock;
    @SerializedName("stock_count")
    @Expose
    private String stock_count;
    @SerializedName("category_id")
    @Expose
    private Integer category_id;
    @SerializedName("discount_options")
    @Expose
    private boolean discount_options;
    @SerializedName("non_stop")
    @Expose
    private boolean non_stop;
    @SerializedName("start_date")
    @Expose
    private Date start_date;
    @SerializedName("end_date")
    @Expose
    private Date end_date;
    @SerializedName("overlapping")
    @Expose
    private String overlapping;
    @SerializedName("created_at")
    @Expose
    private Date created_at;
    @SerializedName("updated_at")
    @Expose
    private Date updated_at;
    @SerializedName("for_order_types")
    @Expose
    private List<dtoOrderTypes> for_order_types = null;
    @SerializedName("groups")
    @Expose
    private List<dtoGroup> groups = null;
    @SerializedName("image")
    @Expose
    private dtoImage image;

    private int quantity;


    /*Getter and Setter*/

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getAutoApply() {
        return auto_apply;
    }

    public void setAutoApply(boolean auto_apply) {
        this.auto_apply = auto_apply;
    }

    public boolean getOverrideDelivery() {
        return override_delivery;
    }

    public void setOverrideDelivery(Boolean override_delivery) {
        this.override_delivery = override_delivery;
    }

    public boolean getOnMenu() {
        return on_menu;
    }

    public void setOnMenu(boolean on_menu) {
        this.on_menu = on_menu;
    }

    public boolean getAutoDiscount() {
        return auto_discount;
    }

    public void setAutoDiscount(boolean auto_discount) {
        this.auto_discount = auto_discount;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean getCouponOnly() {
        return coupon_only;
    }

    public void setCouponOnly(boolean coupon_only) {
        this.coupon_only = coupon_only;
    }

    public String getMinCartValue() {
        return min_cart_value;
    }

    public void setMinCartValue(String min_cart_value) {
        this.min_cart_value = min_cart_value;
    }

    public String getForCustomer() {
        return for_customer;
    }

    public void setForCustomer(String for_customer) {
        this.for_customer = for_customer;
    }

    public boolean getOncePerCustomer() {
        return once_per_customer;
    }

    public void setOncePerCustomer(boolean once_per_customer) {
        this.once_per_customer = once_per_customer;
    }

    public boolean getLimitedStock() {
        return limited_stock;
    }

    public void setLimitedStock(boolean limited_stock) {
        this.limited_stock = limited_stock;
    }

    public String getStockCount() {
        return stock_count;
    }

    public void setStockCount(String stock_count) {
        this.stock_count = stock_count;
    }

    public Integer getCategoryId() {
        return category_id;
    }

    public void setCategoryId(Integer category_id) {
        this.category_id = category_id;
    }

    public Boolean getDiscountOptions() {
        return discount_options;
    }

    public void setDiscountOptions(Boolean discount_options) {
        this.discount_options = discount_options;
    }

    public boolean getNonStop() {
        return non_stop;
    }

    public void setNonStop(boolean non_stop) {
        this.non_stop = non_stop;
    }

    public Date getStartDate() {
        return start_date;
    }

    public void setStartDate(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEndDate() {
        return end_date;
    }

    public void setEndDate(Date end_date) {
        this.end_date = end_date;
    }

    public String getOverlapping() {
        return overlapping;
    }

    public void setOverlapping(String overlapping) {
        this.overlapping = overlapping;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(Date updated_at) {
        this.updated_at = updated_at;
    }

    public List<dtoOrderTypes> getForOrderTypes() {
        return for_order_types;
    }

    public void setForOrderTypes(List<dtoOrderTypes> for_order_types) {
        this.for_order_types = for_order_types;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
