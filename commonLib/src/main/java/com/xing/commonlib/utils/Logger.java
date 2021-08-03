package com.xing.commonlib.utils;

import android.util.Log;

/**
 * @author:x
 * @date:2020-06-22
 * @des:
 */
public class Logger {
    private static boolean isShowDebug=false;

    /**
     * 打印详情信息
     * @param tag
     * @param verbose
     */
    public static void v(String tag,String verbose){
        if (isShowDebug) {
            Log.v("xlogv"+tag,verbose);
        }
    }

    /**
     * 通告信息
     * @param tag
     * @param verbose
     */
    public static void i(String tag,String verbose){
        if (isShowDebug) {
            Log.i("xlogi"+tag,verbose);
        }
    }

    /**
     * 调试信息
     * @param tag
     * @param verbose
     */
    public static void d (String tag,String verbose){
        if (isShowDebug) {
            Log.d("xlogd"+tag,verbose);
        }
    }

    /**
     * 打印警告信息
     * @param tag
     * @param verbose
     */
    public static void w (String tag,String verbose){
        if (isShowDebug) {
            Log.w("xlogw"+tag,verbose);
        }
    }

    /**
     * 打印错误信息
     * @param tag
     * @param verbose
     */
    public static void e (String tag,String verbose){
        if (isShowDebug) {
            Log.e("xloge"+tag,verbose);
        }
    }
}
