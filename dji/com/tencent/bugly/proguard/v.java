package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import dji.pilot.usercenter.mode.n;

public class v {
    private static SharedPreferences a;
    private static String b;
    private static String c = n.bi;
    private static String d;
    private static String e = "qim";

    public static synchronized void a(Context context) {
        synchronized (v.class) {
            a = context.getSharedPreferences("bugly_data", 0);
        }
    }

    public static synchronized void a(ax axVar) {
        synchronized (v.class) {
            if (axVar != null) {
                if (!(axVar.d == null || b == axVar.d)) {
                    a.edit().putString(c, axVar.d).apply();
                    z.c("[response] update gatewayIp: %s", axVar.d);
                }
                if (!(axVar.g == null || d == axVar.g)) {
                    a.edit().putString(e, axVar.g).apply();
                    z.c("[response] update qimei: %s", axVar.g);
                }
            }
        }
    }

    public static synchronized String a() {
        String str;
        synchronized (v.class) {
            try {
                if (b != null) {
                    str = b;
                } else {
                    str = a.getString(c, "");
                }
            } catch (Throwable th) {
                str = "";
            }
        }
        return str;
    }

    public static synchronized String b() {
        String str;
        synchronized (v.class) {
            try {
                if (d != null) {
                    str = d;
                } else {
                    str = a.getString(e, "");
                }
            } catch (Throwable th) {
                str = "";
            }
        }
        return str;
    }
}
