package com.ialway.android.flashlight.service;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.ialway.android.flashlight.R;
import com.ialway.android.flashlight.ui.view.SwitchView;

public class MyWindowManager {

    private static MyWindowManager mInstance = new MyWindowManager();

    private WindowManager mWindowManager;
    private View mSwithContainer;
    private SwitchView mStateView;

    private MyWindowManager() {

    }

    public static MyWindowManager getInstance() {
        return mInstance;
    }

    /**
     * 创建手电筒开关
     * @param context
     */
    public void createSwitchWindow(Context context) {

        WindowManager windowManager = getWindowManager(context);
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        int screenHeight = windowManager.getDefaultDisplay().getHeight();

        mSwithContainer = LayoutInflater.from(context).inflate(R.layout.layout_switch, null);
        LayoutParams switchParams = new LayoutParams();
        switchParams.type = LayoutParams.TYPE_PHONE;
        switchParams.format = PixelFormat.RGBA_8888;
        switchParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL | LayoutParams.FLAG_NOT_FOCUSABLE;
        switchParams.gravity = Gravity.LEFT | Gravity.TOP;
        switchParams.width = LayoutParams.WRAP_CONTENT;
        switchParams.height = LayoutParams.WRAP_CONTENT;
        switchParams.x = screenWidth;
        switchParams.y = screenHeight / 2;

        mStateView = (SwitchView) mSwithContainer.findViewById(R.id.switch_state);
        windowManager.addView(mSwithContainer, switchParams);
    }

    /**
     * 从窗口中移除开关
     * @param context
     */
    public void removeSwithWindow(Context context) {

        if (mSwithContainer != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(mSwithContainer);
            mStateView = null;
            mSwithContainer = null;
        }
    }

    /**
     * 改变开关显示的状态
     * @param strResId
     */
    public void changeSwitchState(int strResId) {

        if (mStateView != null) {
            mStateView.setText(strResId);
        }
    }
    
    public boolean isWindowShowing() {
        return mSwithContainer != null;
    }

    /**
     * 获取窗口管理器
     * @param context
     * @return
     */
    private WindowManager getWindowManager(Context context) {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }

}
