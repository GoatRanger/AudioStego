package com.dji.a.f;

import android.content.Context;
import android.graphics.Point;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.WindowManager;
import java.util.Locale;

public class a {
    public static String a(Context context) {
        String macAddress;
        String str = "";
        String str2 = "";
        try {
            macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (macAddress != null) {
                try {
                    macAddress = macAddress.replace(":", "");
                } catch (Exception e) {
                    com.dji.a.a.c.c("ContentValues", "get mac error: ");
                    str = macAddress;
                    macAddress = Build.SERIAL;
                    if (com.dji.a.a.b) {
                        com.dji.a.a.c.a("ContentValues", "Mac address is: " + str + "build serial : " + macAddress);
                    }
                    return str + "_" + macAddress;
                }
            }
            macAddress = "" + System.currentTimeMillis();
            str = macAddress;
        } catch (Exception e2) {
            macAddress = str;
            com.dji.a.a.c.c("ContentValues", "get mac error: ");
            str = macAddress;
            macAddress = Build.SERIAL;
            if (com.dji.a.a.b) {
                com.dji.a.a.c.a("ContentValues", "Mac address is: " + str + "build serial : " + macAddress);
            }
            return str + "_" + macAddress;
        }
        try {
            macAddress = Build.SERIAL;
        } catch (Exception e3) {
            com.dji.a.a.c.c("ContentValues", "get serial error: ");
            macAddress = str2;
        }
        if (com.dji.a.a.b) {
            com.dji.a.a.c.a("ContentValues", "Mac address is: " + str + "build serial : " + macAddress);
        }
        return str + "_" + macAddress;
    }

    public static String b(Context context) {
        String str = "0.0.0";
        try {
            String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str2 != null && str2.length() >= 0) {
                return str2;
            }
        } catch (Exception e) {
            com.dji.a.a.c.c("ContentValues", "Get app version fail.");
        }
        return str;
    }

    public static String a() {
        return Build.BRAND;
    }

    public static String b() {
        return Build.MODEL;
    }

    public static String c(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point.x + "*" + point.y;
    }

    public static int c() {
        return 2;
    }

    public static String d() {
        return VERSION.RELEASE;
    }

    public static String e() {
        return Locale.getDefault().getLanguage();
    }

    public static int d(Context context) {
        return context.getResources().getConfiguration().mcc;
    }

    public static String f() {
        return Locale.getDefault().getCountry();
    }

    public static String g() {
        return "GooglePlay";
    }
}
