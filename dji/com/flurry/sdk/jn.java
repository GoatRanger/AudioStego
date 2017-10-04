package com.flurry.sdk;

import android.app.Activity;
import android.content.Context;
import com.flurry.sdk.jr.a;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public class jn implements a {
    private static jn a;
    private static final String b = jn.class.getSimpleName();
    private final Map<Context, jl> c = new WeakHashMap();
    private final jo d = new jo();
    private final Object e = new Object();
    private long f;
    private long g;
    private jl h;
    private ii<jp> i = new ii<jp>(this) {
        final /* synthetic */ jn a;

        {
            this.a = r1;
        }

        public void a(jp jpVar) {
            this.a.i();
        }
    };
    private ii<ic> j = new ii<ic>(this) {
        final /* synthetic */ jn a;

        {
            this.a = r1;
        }

        public void a(ic icVar) {
            switch (icVar.b) {
                case kStarted:
                    in.a(3, jn.b, "Automatic onStartSession for context:" + icVar.a);
                    this.a.e(icVar.a);
                    return;
                case kStopped:
                    in.a(3, jn.b, "Automatic onEndSession for context:" + icVar.a);
                    this.a.d(icVar.a);
                    return;
                case kDestroyed:
                    in.a(3, jn.b, "Automatic onEndSession (destroyed) for context:" + icVar.a);
                    this.a.d(icVar.a);
                    return;
                default:
                    return;
            }
        }
    };

    public static synchronized jn a() {
        jn jnVar;
        synchronized (jn.class) {
            if (a == null) {
                a = new jn();
            }
            jnVar = a;
        }
        return jnVar;
    }

    public static synchronized void b() {
        synchronized (jn.class) {
            if (a != null) {
                ij.a().a(a.i);
                ij.a().a(a.j);
                jq.a().b("ContinueSessionMillis", a);
            }
            a = null;
        }
    }

    private jn() {
        jr a = jq.a();
        this.f = 0;
        this.g = ((Long) a.a("ContinueSessionMillis")).longValue();
        a.a("ContinueSessionMillis", (a) this);
        in.a(4, b, "initSettings, ContinueSessionMillis = " + this.g);
        ij.a().a("com.flurry.android.sdk.ActivityLifecycleEvent", this.j);
        ij.a().a("com.flurry.android.sdk.FlurrySessionTimerEvent", this.i);
    }

    public long c() {
        return this.f;
    }

    public synchronized int d() {
        return this.c.size();
    }

    public jl e() {
        jl jlVar;
        synchronized (this.e) {
            jlVar = this.h;
        }
        return jlVar;
    }

    private void a(jl jlVar) {
        synchronized (this.e) {
            this.h = jlVar;
        }
    }

    private void b(jl jlVar) {
        synchronized (this.e) {
            if (this.h == jlVar) {
                this.h = null;
            }
        }
    }

    public synchronized void a(Context context) {
        if (context instanceof Activity) {
            if (id.a().c()) {
                in.a(3, b, "bootstrap for context:" + context);
                e(context);
            }
        }
    }

    public synchronized void b(Context context) {
        if (!(id.a().c() && (context instanceof Activity))) {
            in.a(3, b, "Manual onStartSession for context:" + context);
            e(context);
        }
    }

    public synchronized void c(Context context) {
        if (!(id.a().c() && (context instanceof Activity))) {
            in.a(3, b, "Manual onEndSession for context:" + context);
            d(context);
        }
    }

    public synchronized boolean f() {
        boolean z;
        if (e() == null) {
            in.a(2, b, "Session not found. No active session");
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public synchronized void g() {
        for (Entry entry : this.c.entrySet()) {
            jm jmVar = new jm();
            jmVar.a = new WeakReference(entry.getKey());
            jmVar.b = (jl) entry.getValue();
            jmVar.c = jm.a.END;
            jmVar.d = hm.a().d();
            jmVar.b();
        }
        this.c.clear();
        hz.a().b(new kb(this) {
            final /* synthetic */ jn a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.i();
            }
        });
    }

    private synchronized void e(Context context) {
        if (((jl) this.c.get(context)) == null) {
            jm jmVar;
            this.d.a();
            jl e = e();
            if (e == null) {
                e = new jl();
                in.e(b, "Flurry session started for context:" + context);
                jmVar = new jm();
                jmVar.a = new WeakReference(context);
                jmVar.b = e;
                jmVar.c = jm.a.CREATE;
                jmVar.b();
            }
            this.c.put(context, e);
            a(e);
            in.e(b, "Flurry session resumed for context:" + context);
            jmVar = new jm();
            jmVar.a = new WeakReference(context);
            jmVar.b = e;
            jmVar.c = jm.a.START;
            jmVar.b();
            this.f = 0;
        } else if (id.a().c()) {
            in.a(3, b, "Session already started with context:" + context);
        } else {
            in.e(b, "Session already started with context:" + context);
        }
    }

    synchronized void d(Context context) {
        jl jlVar = (jl) this.c.remove(context);
        if (jlVar != null) {
            in.e(b, "Flurry session paused for context:" + context);
            jm jmVar = new jm();
            jmVar.a = new WeakReference(context);
            jmVar.b = jlVar;
            jmVar.d = hm.a().d();
            jmVar.c = jm.a.END;
            jmVar.b();
            if (d() == 0) {
                this.d.a(this.g);
                this.f = System.currentTimeMillis();
            } else {
                this.f = 0;
            }
        } else if (id.a().c()) {
            in.a(3, b, "Session cannot be ended, session not found for context:" + context);
        } else {
            in.e(b, "Session cannot be ended, session not found for context:" + context);
        }
    }

    private synchronized void i() {
        int d = d();
        if (d > 0) {
            in.a(5, b, "Session cannot be finalized, sessionContextCount:" + d);
        } else {
            final jl e = e();
            if (e == null) {
                in.a(5, b, "Session cannot be finalized, current session not found");
            } else {
                in.e(b, "Flurry session ended");
                jm jmVar = new jm();
                jmVar.b = e;
                jmVar.c = jm.a.FINALIZE;
                jmVar.d = hm.a().d();
                jmVar.b();
                hz.a().b(new kb(this) {
                    final /* synthetic */ jn b;

                    public void a() {
                        this.b.b(e);
                    }
                });
            }
        }
    }

    public void a(String str, Object obj) {
        if (str.equals("ContinueSessionMillis")) {
            this.g = ((Long) obj).longValue();
            in.a(4, b, "onSettingUpdate, ContinueSessionMillis = " + this.g);
            return;
        }
        in.a(6, b, "onSettingUpdate internal error!");
    }
}
