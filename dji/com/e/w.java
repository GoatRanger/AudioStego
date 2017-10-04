package com.e;

import android.content.Context;
import android.text.TextUtils;
import java.lang.Thread.UncaughtExceptionHandler;

public class w implements UncaughtExceptionHandler {
    private static w a;
    private UncaughtExceptionHandler b = Thread.getDefaultUncaughtExceptionHandler();
    private Context c;
    private dc d;

    private w(Context context, dc dcVar) {
        this.c = context.getApplicationContext();
        this.d = dcVar;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    static synchronized w a(Context context, dc dcVar) {
        w wVar;
        synchronized (w.class) {
            if (a == null) {
                a = new w(context, dcVar);
            }
            wVar = a;
        }
        return wVar;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        String a = dd.a(th);
        try {
            if (!TextUtils.isEmpty(a) && ((a.contains("amapdynamic") || a.contains("admic")) && a.contains("com.amap.api"))) {
                v.a(new f(this.c, x.c()), this.c, this.d);
            }
        } catch (Throwable th2) {
            dg.a(th2, "DynamicExceptionHandler", "uncaughtException");
        }
        if (this.b != null) {
            this.b.uncaughtException(thread, th);
        }
    }
}
