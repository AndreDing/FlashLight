package com.ialway.android.flashlight.service;

import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.ManagerFactoryParameters;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import com.ialway.android.flashlight.common.Constants;
import com.ialway.android.flashlight.ui.MainApp;
import com.ialway.android.flashlight.utils.LogUtils;

public class ManageLightService extends Service {

    private static final String TAG = ManageLightService.class.getSimpleName();

    private Handler mHandler = new Handler();
    private Timer mTimer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        flags = Service.START_STICKY;
        if (mTimer == null) {
            mTimer = new Timer();
            mTimer.scheduleAtFixedRate(new ShowSwitchTash(), 0, Constants.CHECK_REPEAT_TIME);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        mTimer.cancel();
        mTimer = null;
    }

    class ShowSwitchTash extends TimerTask {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            if (!MyWindowManager.getInstance().isWindowShowing()) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        MyWindowManager.getInstance().createSwitchWindow(MainApp.getApp());
                    }
                });
            }
        }
    }
}
