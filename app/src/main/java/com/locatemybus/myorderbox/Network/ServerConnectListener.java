package com.locatemybus.myorderbox.Network;

public interface ServerConnectListener {
    public void onFailure(String error, RequestCode requestCode);

    public void onSuccess(Object object, RequestCode requestCode);
}
