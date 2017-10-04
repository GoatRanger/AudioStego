package com.flurry.sdk;

import android.content.Context;
import android.os.SystemClock;
import com.flurry.sdk.jm.a;
import java.lang.ref.WeakReference;

public class hy {
    private static final String a = hy.class.getSimpleName();
    private final ii<jm> b = new ii<jm>(this) {
        final /* synthetic */ hy a;

        {
            this.a = r1;
        }

        public void a(jm jmVar) {
            if (this.a.c == null || jmVar.b == this.a.c.get()) {
                switch (jmVar.c) {
                    case CREATE:
                        this.a.a(jmVar.b, (Context) jmVar.a.get());
                        return;
                    case START:
                        this.a.a((Context) jmVar.a.get());
                        return;
                    case END:
                        this.a.b((Context) jmVar.a.get());
                        return;
                    case FINALIZE:
                        ij.a().b("com.flurry.android.sdk.FlurrySessionEvent", this.a.b);
                        this.a.a();
                        return;
                    default:
                        return;
                }
            }
        }
    };
    private WeakReference<jl> c;
    private volatile long d = 0;
    private volatile long e = 0;
    private volatile long f = -1;
    private volatile long g = 0;
    private volatile long h = 0;

    public hy() {
        ij.a().a("com.flurry.android.sdk.FlurrySessionEvent", this.b);
    }

    public void a(jl jlVar, Context context) {
        this.c = new WeakReference(jlVar);
        this.d = System.currentTimeMillis();
        this.e = SystemClock.elapsedRealtime();
        b(jlVar, context);
        hz.a().b(new kb(this) {
            final /* synthetic */ hy a;

            {
                this.a = r1;
            }

            public void a() {
                hr.a().c();
            }
        });
    }

    private void b(jl jlVar, Context context) {
        if (jlVar == null || context == null) {
            in.a(3, a, "Flurry session id cannot be created.");
            return;
        }
        in.a(3, a, "Flurry session id started:" + this.d);
        jm jmVar = new jm();
        jmVar.a = new WeakReference(context);
        jmVar.b = jlVar;
        jmVar.c = a.SESSION_ID_CREATED;
        jmVar.b();
    }

    public void a(Context context) {
        long c = jn.a().c();
        if (c > 0) {
            this.g = (System.currentTimeMillis() - c) + this.g;
        }
    }

    public void b(Context context) {
        this.f = SystemClock.elapsedRealtime() - this.e;
    }

    public void a() {
    }

    public String b() {
        return Long.toString(this.d);
    }

    public long c() {
        return this.d;
    }

    public long d() {
        return this.e;
    }

    public long e() {
        return this.f;
    }

    public long f() {
        return this.g;
    }

    public synchronized long g() {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.e;
        if (elapsedRealtime <= this.h) {
            elapsedRealtime = this.h + 1;
            this.h = elapsedRealtime;
        }
        this.h = elapsedRealtime;
        return this.h;
    }
}
