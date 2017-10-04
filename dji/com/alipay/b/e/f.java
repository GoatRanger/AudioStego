package com.alipay.b.e;

import android.content.Context;
import com.alipay.e.a.a.b.b;
import com.alipay.e.a.a.b.d;

public final class f {
    private static String a = "";
    private static String b = "";

    public static synchronized String a() {
        String str;
        synchronized (f.class) {
            str = a;
        }
        return str;
    }

    public static synchronized void a(b bVar) {
        synchronized (f.class) {
            if (bVar != null) {
                a = bVar.a();
                b = bVar.c();
                b.a("Update Token Storage. apdid = " + a + ", token = " + b);
            }
        }
    }

    public static synchronized boolean a(Context context) {
        boolean z;
        synchronized (f.class) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                long b = e.b(context);
                b.a("[*]validTime=" + b);
                b.a("[*]Now      =" + currentTimeMillis);
                if (Math.abs(currentTimeMillis - b) < 86400000) {
                    z = true;
                }
            } catch (Throwable th) {
                d.a(th);
            }
            z = false;
        }
        return z;
    }

    public static synchronized String b() {
        String str;
        synchronized (f.class) {
            str = b;
        }
        return str;
    }
}
