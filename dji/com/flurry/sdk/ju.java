package com.flurry.sdk;

public class ju {
    private static long a = 100;
    private static ju b = null;
    private final jv c = new jv();

    public static synchronized ju a() {
        ju juVar;
        synchronized (ju.class) {
            if (b == null) {
                b = new ju();
            }
            juVar = b;
        }
        return juVar;
    }

    public static synchronized void b() {
        synchronized (ju.class) {
            if (b != null) {
                b.c();
                b = null;
            }
        }
    }

    public ju() {
        this.c.a(a);
        this.c.a(true);
    }

    public synchronized void a(ii<jt> iiVar) {
        ij.a().a("com.flurry.android.sdk.TickEvent", iiVar);
        if (ij.a().b("com.flurry.android.sdk.TickEvent") > 0) {
            this.c.a();
        }
    }

    public synchronized void b(ii<jt> iiVar) {
        ij.a().b("com.flurry.android.sdk.TickEvent", iiVar);
        if (ij.a().b("com.flurry.android.sdk.TickEvent") == 0) {
            this.c.b();
        }
    }

    public synchronized void c() {
        ij.a().a("com.flurry.android.sdk.TickEvent");
        this.c.b();
    }
}
