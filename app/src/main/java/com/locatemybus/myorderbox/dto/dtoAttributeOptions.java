package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dtoAttributeOptions {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("allow_split")
    @Expose
    private boolean allowSplit;
    @SerializedName("single_select")
    @Expose
    private boolean singleSelect;
    @SerializedName("required")
    @Expose
    private boolean required;
    @SerializedName("option_values")
    @Expose
    private List<dtoAttributeOptionValue> optionValues = null;

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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public boolean getAllowSplit() {
        return allowSplit;
    }

    public void setAllowSplit(boolean allowSplit) {
        this.allowSplit = allowSplit;
    }

    public boolean getSingleSelect() {
        return singleSelect;
    }

    public void setSingleSelect(boolean singleSelect) {
        this.singleSelect = singleSelect;
    }

    public boolean getRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public List<dtoAttributeOptionValue> getOptionValues() {
        return optionValues;
    }

    public void setOptionValues(List<dtoAttributeOptionValue> optionValues) {
        this.optionValues = optionValues;
    }

}