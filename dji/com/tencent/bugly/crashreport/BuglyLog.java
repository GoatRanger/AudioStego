package com.tencent.bugly.crashreport;

import android.util.Log;
import com.tencent.bugly.proguard.ab;
import dji.thirdparty.g.b.b.a.c;

public class BuglyLog {
    public static void v(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (ab.a) {
            Log.v(str, str2);
        }
        ab.b(c.il_, str, str2);
    }

    public static void d(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (ab.a) {
            Log.d(str, str2);
        }
        ab.b("D", str, str2);
    }

    public static void i(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (ab.a) {
            Log.i(str, str2);
        }
        ab.b("I", str, str2);
    }

    public static void w(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (ab.a) {
            Log.w(str, str2);
        }
        ab.b("W", str, str2);
    }

    public static void e(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (ab.a) {
            Log.e(str, str2);
        }
        ab.b("E", str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (ab.a) {
            Log.e(str, str2, th);
        }
        ab.a("E", str, th);
    }

    public static void setCache(int i) {
        ab.a(i);
    }
}
