package com.ialway.android.flashlight.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.ialway.android.flashlight.R;
import com.ialway.android.flashlight.common.Constants.LightState;
import com.ialway.android.flashlight.ui.MainApp;
import com.ialway.android.flashlight.utils.LightUtils;

public class SwitchView extends TextView implements OnClickListener, OnLongClickListener {

    private Context mCtx;
    // 当前闪光灯状态
    private int mLightState = LightState.CLOSE;
    private GestureDetector mGestureDetector;
    private boolean canMove;
    private ScrollCallback mCallback;

    public SwitchView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub

        init(context);
    }

    public SwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub

        init(context);
    }

    private void init(Context context) {
        this.mCtx = context;
        this.setOnClickListener(this);
        this.mGestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent arg0) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void onShowPress(MotionEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY) {
                // TODO Auto-generated method stub
                if (mCallback != null) {
                    mCallback.onScroll(event2.getX(), event2.getY());
                } else {
                    throw new RuntimeException("Hadn't set scroll callback");
                }
                return false;
            }

            @Override
            public void onLongPress(MotionEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean onDown(MotionEvent arg0) {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }

    public void setScrollCallback(ScrollCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        mGestureDetector.onTouchEvent(event);
        if (!canMove) {
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (mLightState) {
        case LightState.CLOSE:
            turnOn();
            break;
        case LightState.OPEN:
            turnOff();
            break;
        case LightState.ERROR:
            showNotSupport();
            break;
        }
    }

    private void showNotSupport() {
        Toast.makeText(MainApp.getApp(), MainApp.getApp().getString(R.string.error_not_support), Toast.LENGTH_SHORT).show();
    }

    /*
     * 打开手电筒
     */
    private void turnOn() {
        this.setText(R.string.switch_close);
        this.mLightState = LightState.OPEN;
        LightUtils.getInstance().turnOnLight();
    }

    /*
     * 关闭手电筒
     */
    private void turnOff() {
        this.setText(R.string.switch_open);
        this.mLightState = LightState.CLOSE;
        LightUtils.getInstance().turnOffLight();
    }

    @Override
    public boolean onLongClick(View v) {
        // TODO Auto-generated method stub
        this.canMove = true;
        return false;
    }

    public interface ScrollCallback {

        public void onScroll(double x, double y);
    }
}
