package com.dji.frame.c;

import android.util.Log;

public class i {
    private static final boolean a = true;

    public static void a(String str, String str2, boolean z, boolean z2) {
        if (z && z2) {
            Log.d(str, str2);
        }
    }

    public static void b(String str, String str2, boolean z, boolean z2) {
        if (z && z2) {
            Log.e(str, str2);
        }
    }

    public static void c(String str, String str2, boolean z, boolean z2) {
        if (z && z2) {
            Log.i(str, str2);
        }
    }

    public static void d(String str, String str2, boolean z, boolean z2) {
        if (z && z2) {
            Log.v(str, str2);
        }
    }

    public static void e(String str, String str2, boolean z, boolean z2) {
        if (z && z2) {
            Log.w(str, str2);
        }
    }
}
