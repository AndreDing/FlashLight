package com.ialway.android.flashlight.utils;

import android.util.Log;

public class LogUtils {

    public static final boolean OPEN_V_LOG = true;
    public static final boolean OPEN_LOG = true;

    public static void vLog(String tag, String msg) {

        if (OPEN_LOG && OPEN_V_LOG) {
            Log.v(tag, msg);
        }
    }

    public static void dLog(String tag, String msg) {
        if (OPEN_LOG) {
            Log.d(tag, msg);
        }
    }

    public static void eLog(String tag, String msg) {
        if (OPEN_LOG) {
            Log.e(tag, msg);
        }
    }

    public static void wLog(String tag, String msg) {
        if (OPEN_LOG) {
            Log.w(tag, msg);
        }
    }
}
