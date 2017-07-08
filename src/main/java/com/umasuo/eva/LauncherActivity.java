package com.umasuo.eva;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * 启动页面
 */
public class LauncherActivity extends Activity {

    private String TAG = "LauncherActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.launcher);
    }
}
