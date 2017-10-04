package com.alibaba.sdk.android.dpa.util;

import android.util.Log;

public class DpaLog {
    private static final String TAG = "Dpa_";
    private static boolean enableLog;

    public static void enableLog() {
        enableLog = true;
    }

    public static void disableLog() {
        enableLog = false;
    }

    public static boolean isEnableLog() {
        return enableLog;
    }

    protected static void logI(String str, String str2) {
        if (enableLog) {
            Log.i(TAG + str, str2);
        }
    }

    protected static void logV(String str, String str2) {
        if (enableLog) {
            Log.v(TAG + str, str2);
        }
    }

    protected static void logW(String str, String str2) {
        if (enableLog) {
            Log.w(TAG + str, str2);
        }
    }

    protected static void logD(String str, String str2) {
        if (enableLog) {
            Log.d(TAG + str, str2);
        }
    }

    protected static void logE(String str, String str2) {
        if (enableLog) {
            Log.e(TAG + str, str2);
        }
    }
}
