package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class dtoOR_Order {

    @SerializedName("rec_id")
    @Expose
    private String recId;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("order_source_code")
    @Expose
    private String orderSourceCode;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("customer_rec_id")
    @Expose
    private String customerRecId;
    @SerializedName("sub_total")
    @Expose
    private String subTotal;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("order_type")
    @Expose
    private String orderType;
    @SerializedName("due_time")
    @Expose
    private String dueTime;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("address")
    @Expose
    private dtoAddress address;
    @SerializedName("items")
    @Expose
    private List<dtoOR_Item> items = new ArrayList<>();
    @SerializedName("discounts")
    @Expose
    private List<dtoDiscount> discounts = new ArrayList<>();
    @SerializedName("charges")
    @Expose
    private List<Double> charges = null;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("discount_total")
    @Expose
    private String discountTotal;
    @SerializedName("store_id")
    @Expose
    private String store_id;
    @SerializedName("cart_string")
    @Expose
    private String cart_string;



    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getCart_string() {
        return cart_string;
    }

    public void setCart_string(String cart_string) {
        this.cart_string = cart_string;
    }

    public String getRecId() {
        return recId;
    }

    public void setRecId(String recId) {
        this.recId = recId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderSourceCode() {
        return orderSourceCode;
    }

    public void setOrderSourceCode(String orderSourceCode) {
        this.orderSourceCode = orderSourceCode;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerRecId() {
        return customerRecId;
    }

    public void setCustomerRecId(String customerRecId) {
        this.customerRecId = customerRecId;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public dtoAddress getAddress() {
        return address;
    }

    public void setAddress(dtoAddress address) {
        this.address = address;
    }

    public List<dtoOR_Item> getItems() {
        return items;
    }

    public void setItems(List<dtoOR_Item> items) {
        this.items = items;
    }

    public List<dtoDiscount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<dtoDiscount> discounts) {
        this.discounts = discounts;
    }

    public List<Double> getCharges() {
        return charges;
    }

    public void setCharges(List<Double> charges) {
        this.charges = charges;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(String discountTotal) {
        this.discountTotal = discountTotal;
    }

}