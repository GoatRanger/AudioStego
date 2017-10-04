package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.lang.Thread.UncaughtExceptionHandler;

public class fb implements UncaughtExceptionHandler {
    private static fb a;
    private UncaughtExceptionHandler b = Thread.getDefaultUncaughtExceptionHandler();
    private Context c;
    private dv d;

    private fb(Context context, dv dvVar) {
        this.c = context.getApplicationContext();
        this.d = dvVar;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    static synchronized fb a(Context context, dv dvVar) {
        fb fbVar;
        synchronized (fb.class) {
            if (a == null) {
                a = new fb(context, dvVar);
            }
            fbVar = a;
        }
        return fbVar;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        String a = dx.a(th);
        try {
            if (!TextUtils.isEmpty(a) && ((a.contains("amapdynamic") || a.contains("admic")) && a.contains("com.amap.api"))) {
                fa.a(new ek(this.c, fc.a()), this.c, this.d);
            }
        } catch (Throwable th2) {
            eb.a(th2, "DynamicExceptionHandler", "uncaughtException");
        }
        if (this.b != null) {
            this.b.uncaughtException(thread, th);
        }
    }
}
