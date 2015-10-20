package com.ialway.android.flashlight.common;

public class Constants {

    // 每10秒检查一次悬浮框是否还在
    public static final int CHECK_REPEAT_TIME = 10 * 5000;
    
    // 闪光灯状态
    public interface LightState {
        
        public final static int OPEN = 0;
        public final static int CLOSE = 1;
        public final static int ERROR = -1;
    }
}
