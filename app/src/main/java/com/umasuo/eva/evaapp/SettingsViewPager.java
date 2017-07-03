package com.umasuo.eva.evaapp;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by liubin8095 on 2017/7/2.
 */

public class SettingsViewPager extends ViewPager {
    public SettingsViewPager(Context context) {
        super(context);
    }

    public SettingsViewPager(Context context, AttributeSet attrs) {
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
