package com.alibaba.sdk.android.man.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.regex.Pattern;

public class ToolKit {
    private static final String TAG = "MAN_ToolKit";
    private static Pattern patternHost = Pattern.compile(validHostnameRegex);
    private static Pattern patternIp = Pattern.compile(validIp);
    private static final String validHostnameRegex = "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$";
    private static final String validIp = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";

    public static Object checkNotNull(Object obj, Object obj2) {
        if (obj != null) {
            return obj;
        }
        throw new IllegalArgumentException(String.valueOf(obj2));
    }

    public static long getCurrentThreadId() {
        return Thread.currentThread().getId();
    }

    public static long convertStr2Long(String str) {
        try {
            return Long.valueOf(str).longValue();
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static String getMetaDataChannel(Context context) {
        NameNotFoundException e;
        if (context == null) {
            return "";
        }
        String str = "";
        String str2;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                str = applicationInfo.metaData.getString(MANConfig.MAN_CHANNEL_META_DATA_KEY);
                MANLog.Logi(TAG, "channel : " + str);
            }
            str2 = str;
            if (str2 != null) {
                return str2;
            }
            try {
                return "";
            } catch (NameNotFoundException e2) {
                e = e2;
                e.printStackTrace();
                return str2;
            }
        } catch (NameNotFoundException e3) {
            NameNotFoundException nameNotFoundException = e3;
            str2 = str;
            e = nameNotFoundException;
            e.printStackTrace();
            return str2;
        }
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isHost(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return patternHost.matcher(str).matches();
    }

    public static boolean isIp(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return patternIp.matcher(str).matches();
    }
}
