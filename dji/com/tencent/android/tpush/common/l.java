package com.tencent.android.tpush.common;

import android.content.Context;
import android.content.pm.PackageManager;
import com.tencent.android.tpush.a.a;

public class l {
    private static final String[] a = new String[]{"android.permission.INTERNET", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE", "android.permission.RECEIVE_BOOT_COMPLETED", "android.permission.WRITE_SETTINGS", "android.permission.RECEIVE_USER_PRESENT", "android.permission.WRITE_EXTERNAL_STORAGE"};

    public static boolean a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("The context parameter can not be null!");
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                String[] strArr = packageManager.getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
                if (strArr == null) {
                    return false;
                }
                String[] strArr2 = a;
                int length = strArr2.length;
                int i = 0;
                while (i < length) {
                    String str = strArr2[i];
                    if (a(strArr, str)) {
                        i++;
                    } else {
                        a.i(Constants.LogTag, "The required permission of <" + str + "> does not found!");
                        return false;
                    }
                }
            }
            return true;
        } catch (Throwable e) {
            a.c(Constants.LogTag, "check required permissins exception.", e);
            return false;
        }
    }

    private static boolean a(String[] strArr, String str) {
        for (Object equals : strArr) {
            if (str.equals(equals)) {
                return true;
            }
        }
        return false;
    }
}
