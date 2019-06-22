package com.locatemybus.myorderbox.dto;

import com.google.gson.annotations.SerializedName;

public class AbpResponse<T> {
    @SerializedName("result")
    public T Result;
    @SerializedName("success")
    public boolean Success;
    @SerializedName("error")
    public AbpError Error;
    @SerializedName("unAuthorizedRequest")
    public boolean UnAuthorizedRequest;
    @SerializedName("__abp")
    public boolean __abp;
}
