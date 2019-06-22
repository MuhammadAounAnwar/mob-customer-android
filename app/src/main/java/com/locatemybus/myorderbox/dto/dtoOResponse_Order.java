package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class dtoOResponse_Order {

    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("customer_confirmed")
    @Expose
    private boolean customerConfirmed;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("order_source_id")
    @Expose
    private String orderSourceId;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("store_id")
    @Expose
    private Integer storeId;
    @SerializedName("rec_id")
    @Expose
    private String recId;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("web_customer_id")
    @Expose
    private String webCustomerId;
    @SerializedName("sub_total")
    @Expose
    private Double subTotal;
    @SerializedName("time")
    @Expose
    private Date time;
    @SerializedName("address")
    @Expose
    private dtoAddress address;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("order_type_id")
    @Expose
    private Integer orderTypeId;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("due_time")
    @Expose
    private String dueTime;
    @SerializedName("distance")
    @Expose
    private String distance;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean getCustomerConfirmed() {
        return customerConfirmed;
    }

    public void setCustomerConfirmed(boolean customerConfirmed) {
        this.customerConfirmed = customerConfirmed;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getOrderSourceId() {
        return orderSourceId;
    }

    public void setOrderSourceId(String orderSourceId) {
        this.orderSourceId = orderSourceId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getRecId() {
        return recId;
    }

    public void setRecId(String recId) {
        this.recId = recId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWebCustomerId() {
        return webCustomerId;
    }

    public void setWebCustomerId(String webCustomerId) {
        this.webCustomerId = webCustomerId;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public dtoAddress getAddress() {
        return address;
    }

    public void setAddress(dtoAddress address) {
        this.address = address;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(Integer orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

}