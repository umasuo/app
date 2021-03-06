package com.umasuo.eva.ui.device.add;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * 添加设备时用的 view page
 */
public class AddDeviceViewPager extends ViewPager {
    public AddDeviceViewPager(Context context) {
        super(context);
    }

    public AddDeviceViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
//        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
//        return super.onInterceptTouchEvent(ev);
    }
}
