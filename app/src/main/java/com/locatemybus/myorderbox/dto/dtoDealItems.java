package com.locatemybus.myorderbox.dto;

public class dtoDealItems {

    private dtoProduct product;

    public dtoDealItems(dtoProduct product) {
        this.product = product;
    }

    public dtoProduct getProduct() {
        return product;
    }

    public void setProduct(dtoProduct product) {
        this.product = product;
    }

}
