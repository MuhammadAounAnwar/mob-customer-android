package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class dtoOrder {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("remote_system_id")
    @Expose
    private String remote_system_id;
    @SerializedName("sub_total")
    @Expose
    private Double sub_total;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("order_tax")
    @Expose
    private Double order_tax;
    @SerializedName("discount_total")
    @Expose
    private String discount_total;
    @SerializedName("weborder")
    @Expose
    private boolean weborder;
    @SerializedName("payment_status")
    @Expose
    private String payment_status;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type_code")
    @Expose
    private String type_code;
    @SerializedName("type_name")
    @Expose
    private String type_name;
    @SerializedName("source_code")
    @Expose
    private String source_code;
    @SerializedName("source_name")
    @Expose
    private String source_name;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("due_time")
    @Expose
    private Date due_time;
    @SerializedName("created_at")
    @Expose
    private Date created_at;
    @SerializedName("updated_at")
    @Expose
    private Date updated_at;
    @SerializedName("deleted_at")
    @Expose
    private Date deleted_at;
    @SerializedName("payments")
    @Expose
    private List<dtoPayment> payments = new ArrayList<>();
    @SerializedName("products")
    @Expose
    private List<dtoMyOrderProduct> products = new ArrayList<>();

    /*Getter Setter*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRemoteSystemId() {
        return remote_system_id;
    }

    public void setRemoteSystemId(String remote_system_id) {
        this.remote_system_id = remote_system_id;
    }

    public Double getSubTotal() {
        return sub_total;
    }

    public void setSubTotal(Double sub_total) {
        this.sub_total = sub_total;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Double getOrderTax() {
        return order_tax;
    }

    public void setOrderTax(Double order_tax) {
        this.order_tax = order_tax;
    }

    public String getDiscountTotal() {
        return discount_total;
    }

    public void setDiscountTotal(String discountTotal) {
        this.discount_total = discount_total;
    }

    public Boolean getWeborder() {
        return weborder;
    }

    public void setWeborder(Boolean weborder) {
        this.weborder = weborder;
    }

    public String getPaymentStatus() {
        return payment_status;
    }

    public void setPaymentStatus(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeCode() {
        return type_code;
    }

    public void setTypeCode(String type_code) {
        this.type_code = type_code;
    }

    public String getTypeName() {
        return type_name;
    }

    public void setTypeName(String type_name) {
        this.type_name = type_name;
    }

    public String getSourceCode() {
        return source_code;
    }

    public void setSourceCode(String source_code) {
        this.source_code = source_code;
    }

    public String getSourceName() {
        return source_name;
    }

    public void setSourceName(String source_name) {
        this.source_name = source_name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDueTime() {
        return due_time;
    }

    public void setDueTime(Date due_time) {
        this.due_time = due_time;
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

    public Date getDeletedAt() {
        return deleted_at;
    }

    public void setDeletedAt(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public List<dtoPayment> getPayments() {
        return payments;
    }

    public void setPayments(List<dtoPayment> payments) {
        this.payments = payments;
    }

    public List<dtoMyOrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<dtoMyOrderProduct> products) {
        this.products = products;
    }

}