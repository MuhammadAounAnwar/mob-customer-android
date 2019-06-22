package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class dtoProductAttribute {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("position")
    private int position;
    @SerializedName("is_enable")
    private boolean is_enable;
    @SerializedName("product_id")
    private int product_id;
    @SerializedName("created_at")
    private Date created_at;
    @SerializedName("updated_at")
    private Date updated_at;
    @SerializedName("deleted_at")
    private Date deleted_at;
    @SerializedName("plu_id")
    private int plu_id;
    @SerializedName("quick_look_up_code")
    private String quick_look_up_code;
    @SerializedName("prices")
    @Expose
    private List<dtoPrice> prices = null;
    @SerializedName("options")
    @Expose
    private boolean options;
    @SerializedName("price")
    @Expose
    private String price;



    /*Constructor*/
    public  dtoProductAttribute(){
        prices=new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isIs_enable() {
        return is_enable;
    }

    public void setIs_enable(boolean is_enable) {
        this.is_enable = is_enable;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public int getPlu_id() {
        return plu_id;
    }

    public void setPlu_id(int plu_id) {
        this.plu_id = plu_id;
    }

    public String getQuick_look_up_code() {
        return quick_look_up_code;
    }

    public void setQuick_look_up_code(String quick_look_up_code) {
        this.quick_look_up_code = quick_look_up_code;
    }

    public List<dtoPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<dtoPrice> prices) {
        this.prices = prices;
    }

    public boolean getOption() {
        return options;
    }

    public void setOption(boolean options) {
        this.options = options;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
