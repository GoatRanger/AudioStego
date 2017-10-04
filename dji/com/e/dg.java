package com.e;

import android.content.Context;
import java.lang.Thread.UncaughtExceptionHandler;

public class dg {
    protected static dg a;
    protected UncaughtExceptionHandler b;
    protected boolean c = true;

    public static void a(Throwable th, String str, String str2) {
        th.printStackTrace();
        if (a != null) {
            a.a(th, 1, str, str2);
        }
    }

    protected void a(Context context, dc dcVar, boolean z) {
    }

    protected void a(dc dcVar, String str) {
    }

    protected void a(Throwable th, int i, String str, String str2) {
    }
}
