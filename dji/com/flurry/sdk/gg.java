package com.flurry.sdk;

import android.content.Context;
import com.flurry.android.FlurryEventRecordStatus;
import java.util.Map;

public class gg implements iq {
    private static final String a = gg.class.getSimpleName();
    private gy b;
    private hh c;
    private hc d;

    public static synchronized gg a() {
        gg ggVar;
        synchronized (gg.class) {
            ggVar = (gg) hz.a().a(gg.class);
        }
        return ggVar;
    }

    public void a(Context context) {
        jl.a(hk.class);
        this.b = new gy();
        this.c = new hh();
        this.d = new hc();
        b(context);
    }

    private void b(Context context) {
        if (!jz.a(context, "android.permission.INTERNET")) {
            in.b(a, "Application must declare permission: android.permission.INTERNET");
        }
        if (!jz.a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            in.e(a, "It is highly recommended that the application declare permission: android.permission.ACCESS_NETWORK_STATE");
        }
    }

    public void b() {
        if (this.d != null) {
            this.d.c();
            this.d = null;
        }
        if (this.c != null) {
            this.c.a();
            this.c = null;
        }
        if (this.b != null) {
            this.b.a();
            this.b = null;
        }
        jl.b(hk.class);
    }

    public gy c() {
        return this.b;
    }

    public hh d() {
        return this.c;
    }

    public hc e() {
        return this.d;
    }

    public void f() {
        hk h = h();
        if (h != null) {
            h.a();
        }
    }

    public FlurryEventRecordStatus a(String str) {
        hk h = h();
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (h != null) {
            return h.a(str, null, false);
        }
        return flurryEventRecordStatus;
    }

    public FlurryEventRecordStatus a(String str, Map<String, String> map) {
        hk h = h();
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (h != null) {
            return h.a(str, (Map) map, false);
        }
        return flurryEventRecordStatus;
    }

    public FlurryEventRecordStatus a(String str, boolean z) {
        hk h = h();
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (h != null) {
            return h.a(str, null, z);
        }
        return flurryEventRecordStatus;
    }

    public FlurryEventRecordStatus a(String str, Map<String, String> map, boolean z) {
        hk h = h();
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (h != null) {
            return h.a(str, (Map) map, z);
        }
        return flurryEventRecordStatus;
    }

    public void b(String str) {
        hk h = h();
        if (h != null) {
            h.a(str, null);
        }
    }

    public void b(String str, Map<String, String> map) {
        hk h = h();
        if (h != null) {
            h.a(str, (Map) map);
        }
    }

    @Deprecated
    public void a(String str, String str2, String str3) {
        StackTraceElement[] stackTraceElementArr;
        Object stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null || stackTrace.length <= 2) {
            Object obj = stackTrace;
        } else {
            stackTraceElementArr = new StackTraceElement[(stackTrace.length - 2)];
            System.arraycopy(stackTrace, 2, stackTraceElementArr, 0, stackTraceElementArr.length);
        }
        Throwable th = new Throwable(str2);
        th.setStackTrace(stackTraceElementArr);
        hk h = h();
        if (h != null) {
            h.a(str, str2, str3, th);
        }
    }

    public void a(String str, String str2, Throwable th) {
        hk h = h();
        if (h != null) {
            h.a(str, str2, th.getClass().getName(), th);
        }
    }

    public void c(String str) {
        hk h = h();
        if (h != null) {
            h.a(str, null, false);
        }
    }

    public void c(String str, Map<String, String> map) {
        hk h = h();
        if (h != null) {
            h.a(str, (Map) map, false);
        }
    }

    public void g() {
        hk h = h();
        if (h != null) {
            h.b();
        }
    }

    private hk h() {
        return a(jn.a().e());
    }

    private hk a(jl jlVar) {
        if (jlVar == null) {
            return null;
        }
        return (hk) jlVar.c(hk.class);
    }
}
