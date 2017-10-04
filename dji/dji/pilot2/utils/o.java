package dji.pilot2.utils;

import android.util.Log;

public final class o {
    private static boolean a = false;
    private static String b = "VisionCut";

    public static void a(String str) {
        if (!a) {
            Log.i(b, str);
        }
    }

    public static void b(String str) {
        if (!a) {
            Log.e(b, str);
        }
    }

    public static void c(String str) {
        if (!a) {
            Log.d(b, str);
        }
    }

    public static void d(String str) {
        if (!a) {
            Log.w(b, str);
        }
    }

    public static void e(String str) {
        if (!a) {
            Log.v(b, str);
        }
    }

    public static boolean a(String str, int i) {
        return Log.isLoggable(str, i);
    }
}
