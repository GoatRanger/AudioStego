package com.alibaba.sdk.android.trace;

import android.util.Log;
import com.alibaba.sdk.android.message.Message;

public class AliSDKLogger {
    public static void log(String str) {
        log(1, str);
    }

    public static void log(int i, String str) {
        d(i, null, null, str);
    }

    public static void i(String str, String str2) {
        TraceLoggerManager.INSTANCE.log(str, 2, 4, null, null, str2);
    }

    public static void d(String str, String str2) {
        TraceLoggerManager.INSTANCE.log(str, 2, 3, null, null, str2);
    }

    public static void d(String str) {
        TraceLoggerManager.INSTANCE.log(2, 3, null, null, str);
    }

    public static void d(String str, int i, String str2, String str3, String str4) {
        TraceLoggerManager.INSTANCE.log(str, i, 3, str2, str3, str4);
    }

    public static void d(int i, String str, String str2, String str3) {
        TraceLoggerManager.INSTANCE.log(i, 3, str, str2, str3);
    }

    public static void w(String str, String str2) {
        TraceLoggerManager.INSTANCE.log(str, 2, 5, null, null, str2);
    }

    public static void w(int i, String str, String str2, String str3) {
        TraceLoggerManager.INSTANCE.log(i, 5, str, str2, str3);
    }

    public static void w(String str, int i, String str2, String str3, String str4) {
        TraceLoggerManager.INSTANCE.log(str, i, 5, str2, str3, str4);
    }

    public static void w(int i, String str, String str2, String str3, Throwable th) {
        TraceLoggerManager.INSTANCE.log(i, 5, str, str2, str3 + '\n' + Log.getStackTraceString(th));
    }

    public static void w(String str, int i, String str2, String str3, String str4, Throwable th) {
        TraceLoggerManager.INSTANCE.log(str, i, 5, str2, str3, str4 + '\n' + Log.getStackTraceString(th));
    }

    public static void e(int i, String str, String str2, String str3) {
        TraceLoggerManager.INSTANCE.log(i, 6, str, str2, str3);
    }

    public static void e(String str, int i, String str2, String str3, String str4) {
        TraceLoggerManager.INSTANCE.log(str, i, 6, str2, str3, str4);
    }

    public static void e(int i, String str, String str2, String str3, Throwable th) {
        TraceLoggerManager.INSTANCE.log(i, 6, str, str2, str3 + '\n' + Log.getStackTraceString(th));
    }

    public static void e(String str, int i, String str2, String str3, String str4, Throwable th) {
        TraceLoggerManager.INSTANCE.log(str, i, 6, str2, str3, str4 + '\n' + Log.getStackTraceString(th));
    }

    public static void e(String str, String str2, Throwable th) {
        TraceLoggerManager.INSTANCE.log(str, 2, 6, null, null, str2 + '\n' + Log.getStackTraceString(th));
    }

    public static void e(String str, String str2) {
        TraceLoggerManager.INSTANCE.log(str, 2, 6, null, null, str2);
    }

    public static void e(String str, Throwable th) {
        TraceLoggerManager.INSTANCE.log(str, 2, 6, null, null, Log.getStackTraceString(th));
    }

    public static boolean isDebugEnabled() {
        return TraceLoggerManager.INSTANCE.isLogCatDebugEnabled();
    }

    public static void printStackTraceAndMore(Throwable th) {
        TraceLoggerManager.INSTANCE.log(2, 6, null, null, Log.getStackTraceString(th));
    }

    public static void log(String str, Message message, Throwable th) {
        if (isDebugEnabled()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("***********************************************************\n");
            stringBuilder.append("错误编码 = ").append(message.code).append("\n");
            stringBuilder.append("错误消息 = ").append(message.message).append("\n");
            stringBuilder.append("解决建议 = ").append(message.action).append("\n");
            if (th != null) {
                stringBuilder.append("错误堆栈 = ").append(Log.getStackTraceString(th)).append("\n");
            }
            stringBuilder.append("***********************************************************\n");
            String str2 = message.type;
            if ("D".equals(str2)) {
                d(str, stringBuilder.toString());
            } else if ("E".equals(str2)) {
                e(str, stringBuilder.toString());
            } else if ("W".equals(str2)) {
                w(str, stringBuilder.toString());
            } else {
                i(str, stringBuilder.toString());
            }
        }
    }

    public static void log(String str, Message message) {
        log(str, message, null);
    }
}
