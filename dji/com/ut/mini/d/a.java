package com.ut.mini.d;

import android.util.Log;

public class a {
    private static final String a = a.class.getSimpleName();

    public static String a(String str) {
        String str2 = "";
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class}).invoke(cls.newInstance(), new Object[]{str});
        } catch (Throwable e) {
            Log.e(a, "get() ERROR!!! Exception!", e);
            return str2;
        }
    }

    public static String a(String str, String str2) {
        String str3 = "";
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls.newInstance(), new Object[]{str, str2});
        } catch (Throwable e) {
            Log.e(a, "get() ERROR!!! Exception!", e);
            return str3;
        }
    }
}
