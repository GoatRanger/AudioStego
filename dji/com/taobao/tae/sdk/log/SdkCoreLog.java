package com.taobao.tae.sdk.log;

import android.util.Log;
import com.alibaba.sdk.android.ConfigManager;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SdkCoreLog {
    public static final String FAILURE = "failure";
    public static final String SUCCESS = "success";
    private static Map<String, Long> a = new ConcurrentHashMap();

    public static void d(String str, String str2) {
        if (ConfigManager.DEBUG && str != null && str2 != null) {
            Log.d("SdkCoreDebug", str + ":" + str2);
        }
    }

    public static String content(String str, long j, String... strArr) {
        StringBuilder stringBuilder = new StringBuilder(str + ":" + j + ":");
        for (String append : strArr) {
            stringBuilder.append(append);
        }
        return stringBuilder.toString();
    }

    public static long getTimeUsed(String str) {
        Long l = (Long) a.remove(str);
        if (l == null) {
            return 0;
        }
        return System.currentTimeMillis() - l.longValue();
    }

    public static void startTimeRecord(String str) {
        a.put(str, Long.valueOf(System.currentTimeMillis()));
    }
}
