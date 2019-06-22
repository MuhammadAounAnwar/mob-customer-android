package com.locatemybus.myorderbox.Network;

public enum CustomErrorCode {
    SUCCESS(200), ERROR_SYSTEM(1), ERROR_REQUIRED(2), ERROR_INVALID(3), ERROR_NO_DATA_EXISTS(5), ERROR_ALREADY_EXISTS(6), ERROR_NOT_AUTHORIZED(7);
    private int value;

    //    Code 	ServerResponse 	            ServerResponse Description
    private CustomErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
