package com.here.b.d;

import android.util.Log;

public class c {
    private static boolean a;

    public static void a(boolean z) {
        a = z;
    }

    public static void a(String str) {
        if (a) {
            Log.d("analytics", str);
        }
    }

    public static void b(String str) {
        if (a) {
            Log.i("analytics", str);
        }
    }

    public static void c(String str) {
        if (a) {
            Log.w("analytics", str);
        }
    }
}
