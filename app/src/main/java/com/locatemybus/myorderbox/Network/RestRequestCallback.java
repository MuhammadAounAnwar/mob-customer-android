package com.locatemybus.myorderbox.Network;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.locatemybus.myorderbox.BuildConfig;
import com.locatemybus.myorderbox.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RestRequestCallback implements Callback {

    private ServerConnectListener callback;
    private boolean isCanceled = false;
    private Activity context;
    private RequestCode requestCode;

    public RestRequestCallback(Activity context, ServerConnectListener callback, RequestCode requestCode) {
        this.context = context;
        this.callback = callback;
        this.requestCode = requestCode;
    }


    public void cancel() {
        isCanceled = true;
    }

    public boolean isCancelled() {
        return isCanceled;
    }


    @Override
    public void onResponse(Call call, Response response) {
        if (isCancelled()) {
            return;
        }
        if (call != null && !call.isCanceled()) {
            call.cancel();
        }
        if (BuildConfig.DEBUG) {
            Log.e("onResponse: ====>", new Gson().toJson(response.raw().body().toString()));
        }


        if (response.isSuccessful()) {
            if (BuildConfig.DEBUG) {
                Log.e("onSuccess onResponse", new Gson().toJson(response.body()));
            }
            try {
                callback.onSuccess(response.body(), requestCode);
            } catch (Throwable t) {
                //Exception caught from response.body() method
                callback.onFailure(NetworkErrorHandler.get(context, t), requestCode);
            }
        } else {
//            KLog.e("handle request errors yourself");
            int statusCode = response.code();
            // handle request errors yourself
            try {
                if (statusCode == 404) {
                    callback.onFailure(context.getResources().getString(R.string.error_404), requestCode);
//                    callback.onFailure(IMDroidApplication.getAppResources().getString(R.string.error_404), requestCode);
                } else {
                    ResponseBody errorBody = response.errorBody();
                    Log.e("onFailure onResponse", new Gson().toJson(response.errorBody()));
                    callback.onFailure(errorBody.toString(), requestCode);
                }
            } catch (Throwable t) {
                //Exception caught from response.errorBody().string() method
                callback.onFailure(NetworkErrorHandler.get(context, t), requestCode);
            }

        }

    }

    @Override
    public void onFailure(Call call, Throwable t) {
        if (call != null && !call.isCanceled()) {
            call.cancel();
        }
        t.printStackTrace();
        callback.onFailure(NetworkErrorHandler.get(context, t), requestCode);

    }

}