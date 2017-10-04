package com.dji.frame.c;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.os.StrictMode.VmPolicy;
import android.view.View;
import android.view.Window;
import dji.thirdparty.afinal.b;
import java.io.File;

public class c {
    static dji.thirdparty.afinal.a a;
    static dji.thirdparty.afinal.c b;
    static b c;
    static com.dji.frame.common.b d;
    private static int e;
    private static boolean f = false;
    private static b.b g;
    @SuppressLint({"InlinedApi"})
    private static int h = 5634;
    private static boolean i = true;
    private static ThreadPolicy j;
    private static VmPolicy k;

    public enum a {
        HIDE,
        HIDE_DELAY,
        SHOW
    }

    public static synchronized dji.thirdparty.afinal.a a(Context context) {
        dji.thirdparty.afinal.a aVar;
        synchronized (c.class) {
            if (a == null) {
                a = dji.thirdparty.afinal.a.a(context.getApplicationContext());
            }
            aVar = a;
        }
        return aVar;
    }

    public static synchronized dji.thirdparty.afinal.c b(Context context) {
        dji.thirdparty.afinal.c cVar;
        synchronized (c.class) {
            if (b == null) {
                b = new dji.thirdparty.afinal.c();
            }
            cVar = b;
        }
        return cVar;
    }

    public static synchronized dji.thirdparty.afinal.c a() {
        dji.thirdparty.afinal.c cVar;
        synchronized (c.class) {
            if (b == null) {
                b = new dji.thirdparty.afinal.c();
            }
            cVar = b;
        }
        return cVar;
    }

    public static void a(int i, b.b bVar) {
        e = i;
        g = bVar;
    }

    public static void a(int i, boolean z, b.b bVar) {
        e = i;
        f = z;
        g = bVar;
    }

    public static synchronized b c(Context context) {
        b bVar;
        synchronized (c.class) {
            if (c == null) {
                File file = new File(d.a(context, "databases/"));
                if (!file.exists()) {
                    file.mkdirs();
                }
                Context applicationContext = context.getApplicationContext();
                String str = "dji.db";
                if (f) {
                    c = b.a(applicationContext, null, str, false, e, g);
                } else {
                    c = b.a(applicationContext, null, str, false, e, g);
                }
            }
            bVar = c;
        }
        return bVar;
    }

    public static synchronized com.dji.frame.common.b d(Context context) {
        com.dji.frame.common.b bVar;
        synchronized (c.class) {
            if (d == null) {
                d = new com.dji.frame.common.b(context.getApplicationContext());
            }
            bVar = d;
        }
        return bVar;
    }

    public static void a(boolean z) {
        i = z;
    }

    public static void a(Window window) {
        if (VERSION.SDK_INT >= 19 && i) {
            window.getDecorView().setSystemUiVisibility(h);
        }
    }

    public static void a(View view) {
        if (VERSION.SDK_INT >= 19 && i) {
            view.setSystemUiVisibility(h);
        }
    }

    public static void b() {
        j = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new Builder(j).permitAll().build());
    }

    public static void c() {
        StrictMode.setThreadPolicy(j);
    }

    public static void d() {
        k = StrictMode.getVmPolicy();
        StrictMode.setVmPolicy(new VmPolicy.Builder(k).detectAll().build());
    }

    public static void e() {
        StrictMode.setVmPolicy(k);
    }
}
