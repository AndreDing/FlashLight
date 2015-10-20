package com.ialway.android.flashlight.utils;

import android.content.pm.PackageManager;
import android.hardware.Camera;

import com.ialway.android.flashlight.common.Constants;
import com.ialway.android.flashlight.ui.MainApp;

@SuppressWarnings("deprecation")
public class LightUtils {
    
    private static LightUtils instance = new LightUtils();
    private Camera cam;
    private Camera.Parameters camParam;
    
    private LightUtils() {
        cam = Camera.open();
        camParam = cam.getParameters(); 
    }
    
    public static LightUtils getInstance() {
        return instance;
    }

    /*
     * 获取闪光灯状态
     */
    public int getLightState() {
        int lightState = Constants.LightState.CLOSE;
        String result = camParam.getFlashMode();
        if (result == null) {
            lightState = Constants.LightState.ERROR;
        } else if (result.equals(Camera.Parameters.FLASH_MODE_OFF)) {
            lightState = Constants.LightState.CLOSE;
        } else if (result.equals(Camera.Parameters.FLASH_MODE_ON)) {
            lightState = Constants.LightState.OPEN;
        }
        return lightState;
    }

    /*
     * 判断是否支持闪光灯
     */
    public boolean isLightSupport() {
        if (!MainApp.getApp().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            return false;
        }
        return true;
    }

    public void turnOnLight() {
        camParam.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        cam.setParameters(camParam);
        cam.startPreview();
    }

    public void turnOffLight() {
        camParam.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        cam.setParameters(camParam);
        cam.stopPreview();
//        cam.release();
    }

}
