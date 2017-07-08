package com.umasuo.eva.infra.log;

import android.util.Log;

/**
 * Created by liubin8095 on 2017/7/2.
 * log util, used to log different logs.
 */
public class LogControl {

    public static boolean PRINT_WARN = true;

    public static boolean PRINT_INFO = true;

    public static boolean PRINT_ERROR = true;

    public static boolean PRINT_DEBUG = true;

    public static void warn(String tag, String msg) {
        if (PRINT_WARN) {
            Log.w(tag, msg);
        }
    }

    public static void info(String tag, String msg) {
        if (PRINT_INFO) {
            Log.i(tag, msg);
        }
    }

    public static void error(String tag, String msg) {
        if (PRINT_ERROR) {
            Log.e(tag, msg);
        }
    }

    public static void debug(String tag, String msg) {
        if (PRINT_DEBUG) {
            Log.d(tag, msg);
        }
    }
}
