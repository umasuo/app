package com.umasuo.eva.evaapp.log;

import android.util.Log;

/**
 * Created by liubin8095 on 2017/7/2.
 */

public class LogControl {

    public static boolean PRINT_W = true;

    public static boolean PRINT_I = true;

    public static boolean PRINT_E = true;

    public static boolean PRINT_D = true;

    public static void Print_D(String tag,String msg){
        if(PRINT_D){
            Log.d(tag,msg);
        }
    }
}
