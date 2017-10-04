package com.here.b.a;

import android.content.Context;
import com.a.a.e;
import com.a.a.i;
import com.here.b.c.d;
import com.here.b.d.b;
import com.here.b.d.c;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class a {
    static final /* synthetic */ boolean a = (!a.class.desiredAssertionStatus());
    private static com.a.a.a b = null;
    private static Context c;
    private static volatile b<Boolean> d;
    private static b<String> e;
    private static com.here.b.c.b f;
    private static b g;
    private static volatile AtomicBoolean h = new AtomicBoolean(false);
    private static String i;
    private static AtomicInteger j = new AtomicInteger(0);
    private static AtomicInteger k = new AtomicInteger(0);

    private a() {
    }

    public static void a(c cVar) {
        if (h.getAndSet(true)) {
            c.a("analytics You only need to initialize the library once");
        }
        if (cVar != null) {
            i = cVar.d();
            c = cVar.c();
            b = a(new com.a.a.a.a(c, i), cVar);
            e = new b(com.a.a.a.b.a(c), "hereSessionId", null);
            e.a(com.here.b.c.c.a(e.b()));
            d.a(Boolean.valueOf(h()));
            f().a("hereSessionId", e.b());
            return;
        }
        throw new IllegalArgumentException("Setup the HacSettings object before calling initialize");
    }

    public static com.a.a.a a() {
        return b;
    }

    public static boolean b() {
        return h.get();
    }

    static void c() {
        if (!h.get()) {
            throw new IllegalStateException("Please call HacAnalytics.initialize before using the library.");
        }
    }

    private static com.a.a.a a(com.a.a.a.a aVar, c cVar) {
        if (a || aVar != null) {
            f = new com.here.b.c.b();
            aVar.a(f);
            e bVar = new b();
            aVar.a(bVar);
            g = bVar;
            aVar.a(cVar.h());
            Integer a = cVar.a();
            if (a != null) {
                aVar.a(a.intValue());
            } else {
                aVar.a(((Integer) f.c("flushAt")).intValue());
            }
            Long b = cVar.b();
            if (b != null) {
                aVar.a(b.longValue(), TimeUnit.MILLISECONDS);
            } else {
                aVar.a(((Long) f.c("flushAfter")).longValue(), TimeUnit.MILLISECONDS);
            }
            Boolean e = cVar.e();
            if (e != null) {
                f.b(e.booleanValue());
            } else {
                c.a("FlushOnRoaming status is not set. Defaulting to false");
            }
            e = cVar.f();
            if (e != null) {
                f.c(e.booleanValue());
            } else {
                c.a("Offline status is not set. Defaulting to true");
            }
            e = cVar.g();
            if (e != null) {
                d = new b(com.a.a.a.b.a(c), "opt-out", e);
                d.a(e);
            } else {
                d = new b(com.a.a.a.b.a(c), "opt-out", Boolean.FALSE);
                d.a(Boolean.FALSE);
            }
            aVar.a(a(cVar.i()));
            return aVar.a();
        }
        throw new AssertionError();
    }

    protected static com.a.a.a.c a(com.here.b.d.a.a aVar) {
        if (aVar.toString().equals(com.a.a.a.c.b.toString())) {
            return com.a.a.a.c.b;
        }
        if (aVar.toString().equals(com.a.a.a.c.d.toString())) {
            return com.a.a.a.c.d;
        }
        if (aVar.toString().equals(com.a.a.a.c.a.toString())) {
            return com.a.a.a.c.a;
        }
        return com.a.a.a.c.c;
    }

    public static void d() {
        c();
        if (!d.a() && j()) {
            b.a();
            j.set(0);
            k.set(0);
        }
    }

    static int e() {
        return j.get();
    }

    static com.a.a.b f() {
        c();
        return b.b();
    }

    public static String g() {
        c();
        return i;
    }

    public static boolean h() {
        c();
        return d.a();
    }

    public static void a(boolean z) {
        c();
        f.c(z);
        if (z) {
            c.a("Offline mode enabled");
        } else {
            c.a("Offline mode disabled");
        }
    }

    public static boolean i() {
        c();
        return ((Boolean) k().c("offlineMode")).booleanValue();
    }

    private static boolean l() {
        return com.here.b.b.a.a(c);
    }

    static boolean j() {
        boolean l = l();
        c.a("flushOnRoaming : " + l);
        boolean i = i();
        c.a("offlineMode : " + i);
        l = l && !i;
        c.a("isOkToFlush return " + l);
        return l;
    }

    public static com.here.b.c.b k() {
        c();
        return f;
    }

    public static void a(String str) {
        c();
        f.a(str);
    }

    private static void m() {
        j.incrementAndGet();
        if (((Integer) k().c("flushAt")).intValue() < e() && k.get() < 1) {
            k.incrementAndGet();
        }
    }

    public static void a(String str, d dVar) {
        if (!h() && b()) {
            i iVar = new i();
            iVar.a("Amplitude", false);
            b.a(str, dVar, iVar);
            m();
        }
    }
}
