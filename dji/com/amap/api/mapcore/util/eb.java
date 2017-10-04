package com.amap.api.mapcore.util;

import android.content.Context;
import java.lang.Thread.UncaughtExceptionHandler;

public class eb {
    protected static eb a;
    protected UncaughtExceptionHandler b;
    protected boolean c = true;

    public static void a(Throwable th, String str, String str2) {
        th.printStackTrace();
        if (a != null) {
            a.a(th, 1, str, str2);
        }
    }

    protected void a(Throwable th, int i, String str, String str2) {
    }

    protected void a(Context context, dv dvVar, boolean z) {
    }
}
