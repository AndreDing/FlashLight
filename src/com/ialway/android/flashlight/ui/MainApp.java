package com.ialway.android.flashlight.ui;

import android.app.Application;

public class MainApp extends Application {

    private static MainApp instance;

    public static MainApp getApp() {
        if (instance == null) {
            instance = new MainApp();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        instance = this;
    }
}
