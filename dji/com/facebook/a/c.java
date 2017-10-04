package com.facebook.a;

import android.os.Bundle;
import com.facebook.internal.x;
import com.facebook.y;
import com.here.odnp.config.OdnpConfigStatic;
import java.io.Serializable;
import java.util.Locale;

class c implements Serializable {
    private static final long a = 1;
    private static final String b = b.class.getCanonicalName();
    private static final long c = -1;
    private static final long d = 1000;
    private static final long e = 60000;
    private static final long f = 300000;
    private static final long[] g = new long[]{300000, 900000, OdnpConfigStatic.OEM_MAX_MEDIUM_POWER_INTERVAL, 3600000, 21600000, 43200000, 86400000, 172800000, 259200000, 604800000, 1209600000, 1814400000, 2419200000L, 5184000000L, 7776000000L, 10368000000L, 12960000000L, 15552000000L, 31536000000L};
    private boolean h;
    private boolean i;
    private long j;
    private long k;
    private long l;
    private long m;
    private int n;
    private String o;

    private static class a implements Serializable {
        private static final long a = 6;
        private final long b;
        private final long c;
        private final long d;
        private final int e;

        a(long j, long j2, long j3, int i) {
            this.b = j;
            this.c = j2;
            this.d = j3;
            this.e = i;
        }

        private Object a() {
            return new c(this.b, this.c, this.d, this.e);
        }
    }

    private static class b implements Serializable {
        private static final long a = 6;
        private final long b;
        private final long c;
        private final long d;
        private final int e;
        private final String f;

        b(long j, long j2, long j3, int i, String str) {
            this.b = j;
            this.c = j2;
            this.d = j3;
            this.e = i;
            this.f = str;
        }

        private Object a() {
            return new c(this.b, this.c, this.d, this.e, this.f);
        }
    }

    private c(long j, long j2, long j3, int i) {
        b();
        this.k = j;
        this.l = j2;
        this.m = j3;
        this.n = i;
    }

    c() {
        b();
    }

    private c(long j, long j2, long j3, int i, String str) {
        b();
        this.k = j;
        this.l = j2;
        this.m = j3;
        this.n = i;
        this.o = str;
    }

    private Object a() {
        return new b(this.k, this.l, this.m, this.n, this.o);
    }

    void a(b bVar, long j) {
        long j2 = 0;
        if (this.i) {
            long j3 = j - this.k;
            if (j3 < 0) {
                x.a(y.APP_EVENTS, b, "Clock skew detected");
            } else {
                j2 = j3;
            }
            this.m = j2 + this.m;
            this.l = j;
            this.i = false;
            return;
        }
        x.a(y.APP_EVENTS, b, "Suspend for inactive app");
    }

    void a(b bVar, long j, String str) {
        long j2 = 0;
        if (d() || j - this.j > 300000) {
            Bundle bundle = new Bundle();
            bundle.putString(a.C, str);
            bVar.a(a.a, bundle);
            this.j = j;
        }
        if (this.i) {
            x.a(y.APP_EVENTS, b, "Resume for active app");
            return;
        }
        long j3 = c() ? j - this.l : 0;
        if (j3 < 0) {
            x.a(y.APP_EVENTS, b, "Clock skew detected");
        } else {
            j2 = j3;
        }
        if (j2 > 60000) {
            b(bVar, j2);
        } else if (j2 > 1000) {
            this.n++;
        }
        if (this.n == 0) {
            this.o = str;
        }
        this.k = j;
        this.i = true;
    }

    private void b(b bVar, long j) {
        Bundle bundle = new Bundle();
        bundle.putInt(a.c, this.n);
        bundle.putString(a.d, String.format(Locale.ROOT, "session_quanta_%d", new Object[]{Integer.valueOf(a(j))}));
        bundle.putString(a.C, this.o);
        bVar.a(a.b, (double) (this.m / 1000), bundle);
        b();
    }

    private static int a(long j) {
        int i = 0;
        while (i < g.length && g[i] < j) {
            i++;
        }
        return i;
    }

    private void b() {
        this.i = false;
        this.k = -1;
        this.l = -1;
        this.n = 0;
        this.m = 0;
    }

    private boolean c() {
        return this.l != -1;
    }

    private boolean d() {
        boolean z = !this.h;
        this.h = true;
        return z;
    }
}
