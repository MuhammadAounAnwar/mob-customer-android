package com.locatemybus.myorderbox.Application;

import android.app.Application;
import android.content.res.Configuration;

import io.paperdb.Paper;

public class LMBApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Paper.init(getApplicationContext());
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}

