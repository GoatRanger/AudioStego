package com.a.a;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.a.a.a.a.a.d;
import com.a.a.a.a.a.e;
import com.a.a.a.a.a.f;
import com.a.a.a.c;
import java.io.Closeable;
import java.lang.reflect.Constructor;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

class g implements ActivityLifecycleCallbacks {
    final Map<String, Boolean> a = new ConcurrentHashMap();
    final List<com.a.a.a.a> b = new ArrayList();
    final a c;
    final Application d;
    final d e;
    final c f;
    final n g;
    final a h;
    final c i;
    final HandlerThread j;
    final Handler k;
    final ExecutorService l;
    final m m;
    Queue<h> n;
    Map<String, com.a.a.a.b> o;
    volatile boolean p;

    interface a {
        g a(a aVar);
    }

    static class b extends Handler {
        private final g a;

        b(Looper looper, g gVar) {
            super(looper);
            this.a = gVar;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.b();
                    return;
                case 2:
                    this.a.b((j) message.obj);
                    return;
                case 3:
                    this.a.a((h) message.obj);
                    return;
                case 4:
                    this.a.b((com.a.a.a.a.a.b) message.obj);
                    return;
                case 5:
                    Pair pair = (Pair) message.obj;
                    this.a.a((String) pair.first, (com.a.a.a.b) pair.second);
                    return;
                default:
                    throw new AssertionError("Unknown Integration Manager handler message: " + message);
            }
        }
    }

    static synchronized g a(a aVar, c cVar, d dVar, ExecutorService executorService, n nVar, String str, long j, int i) {
        g gVar;
        synchronized (g.class) {
            gVar = new g(aVar, dVar, executorService, cVar, nVar, new a(aVar.c(), cVar, str), str, j, i);
        }
        return gVar;
    }

    g(a aVar, d dVar, ExecutorService executorService, c cVar, n nVar, a aVar2, String str, long j, int i) {
        this.c = aVar;
        this.d = aVar.c();
        this.e = dVar;
        this.l = executorService;
        this.f = cVar;
        this.g = nVar;
        this.h = aVar2;
        this.i = aVar.d();
        this.j = new HandlerThread("SegmentAnalytics-IntegrationManager", 10);
        this.j.start();
        this.k = new b(this.j.getLooper(), this);
        d();
        this.m = m.a(this.d, dVar, cVar, executorService, nVar, Collections.unmodifiableMap(this.a), str, j, i, this.i);
        this.b.add(this.m);
        if (!aVar2.b() || aVar2.a() == null) {
            a();
        } else {
            a((j) aVar2.a());
            if (((j) aVar2.a()).a() + 86400000 < System.currentTimeMillis()) {
                a();
            }
        }
        this.d.registerActivityLifecycleCallbacks(this);
    }

    private void d() {
        a("com.segment.analytics.internal.integrations.AmplitudeIntegration");
        a("com.segment.analytics.internal.integrations.AppsFlyerIntegration");
        a("com.segment.analytics.internal.integrations.ApptimizeIntegration");
        a("com.segment.analytics.internal.integrations.BugsnagIntegration");
        a("com.segment.analytics.internal.integrations.CountlyIntegration");
        a("com.segment.analytics.internal.integrations.CrittercismIntegration");
        a("com.segment.analytics.internal.integrations.FlurryIntegration");
        a("com.segment.analytics.internal.integrations.GoogleAnalyticsIntegration");
        a("com.segment.analytics.internal.integrations.KahunaIntegration");
        a("com.segment.analytics.internal.integrations.LeanplumIntegration");
        a("com.segment.analytics.internal.integrations.LocalyticsIntegration");
        a("com.segment.analytics.internal.integrations.MixpanelIntegration");
        a("com.segment.analytics.internal.integrations.MoEngageIntegration");
        a("com.segment.analytics.internal.integrations.QuantcastIntegration");
        a("com.segment.analytics.internal.integrations.TapstreamIntegration");
        a("com.segment.analytics.internal.integrations.TaplyticsIntegration");
        a("com.segment.analytics.internal.integrations.UXCamIntegration");
    }

    void a(String str) {
        try {
            a(Class.forName(str));
        } catch (ClassNotFoundException e) {
            if (this.i.a()) {
                com.a.a.a.b.a("Integration for class %s not bundled.", str);
            }
        }
    }

    private void a(Class<com.a.a.a.a> cls) {
        try {
            Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            com.a.a.a.a aVar = (com.a.a.a.a) declaredConstructor.newInstance(new Object[0]);
            this.b.add(aVar);
            this.a.put(aVar.b(), Boolean.valueOf(false));
        } catch (Exception e) {
            throw new AssertionError("Could not create instance of " + cls.getCanonicalName() + ".\n" + e);
        }
    }

    void a() {
        this.k.sendMessage(this.k.obtainMessage(1));
    }

    private void e() {
        this.k.sendMessageDelayed(this.k.obtainMessage(1), 60000);
    }

    void b() {
        boolean a = this.e.a().a();
        if (this.i.a()) {
            com.a.a.a.b.a("isOkToSend status for fetching project settings is " + a, new Object[0]);
        }
        if (com.a.a.a.b.b(this.d) && a) {
            try {
                j jVar = (j) this.l.submit(new Callable<j>(this) {
                    final /* synthetic */ g a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ Object call() throws Exception {
                        return a();
                    }

                    public j a() throws Exception {
                        Closeable closeable = null;
                        try {
                            closeable = this.a.e.c();
                            j a = j.a(this.a.f.a(com.a.a.a.b.a(closeable.b)));
                            return a;
                        } finally {
                            com.a.a.a.b.a(closeable);
                        }
                    }
                }).get();
                this.h.a(jVar);
                a(jVar);
                return;
            } catch (Throwable e) {
                if (this.i.a()) {
                    com.a.a.a.b.a(e, "Thread interrupted while fetching settings.", new Object[0]);
                    return;
                }
                return;
            } catch (Throwable e2) {
                if (this.i.a()) {
                    com.a.a.a.b.a(e2, "Unable to fetch settings. Retrying in %s ms.", Long.valueOf(60000));
                }
                e();
                return;
            }
        }
        e();
    }

    void a(j jVar) {
        this.k.sendMessageAtFrontOfQueue(this.k.obtainMessage(2, jVar));
    }

    void b(j jVar) {
        if (!this.p) {
            p d = jVar.d();
            if (com.a.a.a.b.a((Map) d)) {
                if (this.i.a()) {
                    com.a.a.a.b.a(null, "No integrations enabled in %s. Make sure you have the correct writeKey.", jVar);
                }
                this.a.clear();
                this.b.clear();
            } else {
                if (this.i.a()) {
                    com.a.a.a.b.a("Initializing integrations with %s.", d);
                }
                a(d);
            }
            if (this.o != null) {
                this.o.clear();
                this.o = null;
            }
            f();
            this.p = true;
        }
    }

    private void a(p pVar) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            int i;
            com.a.a.a.b bVar;
            com.a.a.a.a aVar = (com.a.a.a.a) it.next();
            String b = aVar.b();
            p a = pVar.a(b);
            if (!com.a.a.a.b.a((Map) a)) {
                if (this.i.a()) {
                    com.a.a.a.b.a("Initializing integration %s with settings %s.", b, a);
                }
                try {
                    aVar.a(this.c, a);
                    i = 1;
                } catch (Throwable e) {
                    if (this.i.a()) {
                        com.a.a.a.b.a(e, "Could not initialize integration %s.", b);
                    }
                }
                if (i != 0) {
                    it.remove();
                    this.a.remove(b);
                } else if (!com.a.a.a.b.a(this.o)) {
                    bVar = (com.a.a.a.b) this.o.get(b);
                    if (bVar != null) {
                        bVar.a(aVar.a());
                    }
                }
            }
            i = 0;
            if (i != 0) {
                it.remove();
                this.a.remove(b);
            } else if (!com.a.a.a.b.a(this.o)) {
                bVar = (com.a.a.a.b) this.o.get(b);
                if (bVar != null) {
                    bVar.a(aVar.a());
                }
            }
        }
    }

    private void f() {
        if (!com.a.a.a.b.a(this.n)) {
            Iterator it = this.n.iterator();
            while (it.hasNext()) {
                b((h) it.next());
                it.remove();
            }
        }
        this.n = null;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        c(h.a(activity, bundle));
    }

    public void onActivityStarted(Activity activity) {
        c(h.a(activity));
    }

    public void onActivityResumed(Activity activity) {
        c(h.b(activity));
    }

    public void onActivityPaused(Activity activity) {
        c(h.c(activity));
    }

    public void onActivityStopped(Activity activity) {
        c(h.d(activity));
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        c(h.b(activity, bundle));
    }

    public void onActivityDestroyed(Activity activity) {
        c(h.e(activity));
    }

    private void c(h hVar) {
        this.k.sendMessage(this.k.obtainMessage(3, hVar));
    }

    void c() {
        c(h.a());
    }

    void a(com.a.a.a.a.a.b bVar) {
        this.k.sendMessage(this.k.obtainMessage(4, bVar));
    }

    void b(com.a.a.a.a.a.b bVar) {
        h a;
        switch (bVar.b()) {
            case identify:
                a = h.a((d) bVar);
                break;
            case alias:
                a = h.a((com.a.a.a.a.a.a) bVar);
                break;
            case group:
                a = h.a((com.a.a.a.a.a.c) bVar);
                break;
            case e:
                a = h.a((f) bVar);
                break;
            case screen:
                a = h.a((e) bVar);
                break;
            default:
                throw new AssertionError("unknown type " + bVar.b());
        }
        a(a);
    }

    void a(h hVar) {
        if (this.p) {
            b(hVar);
            return;
        }
        if (this.i.a()) {
            com.a.a.a.b.a("Enqueuing action %s.", hVar);
        }
        if (this.n == null) {
            this.n = new ArrayDeque();
        }
        this.n.add(hVar);
    }

    void b(h hVar) {
        if (this.i.a()) {
            com.a.a.a.b.a("Running %s on %s integrations.", hVar, Integer.valueOf(this.b.size()));
        }
        for (int i = 0; i < this.b.size(); i++) {
            com.a.a.a.a aVar = (com.a.a.a.a) this.b.get(i);
            long nanoTime = System.nanoTime();
            hVar.a(aVar, (j) this.h.a());
            nanoTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
            if (this.i.a()) {
                com.a.a.a.b.a("Took %s ms to run action %s on %s.", Long.valueOf(nanoTime), hVar, aVar.b());
            }
            this.g.a(aVar.b(), nanoTime);
        }
    }

    void a(String str, com.a.a.a.b bVar) {
        if (this.p && bVar != null) {
            for (com.a.a.a.a aVar : this.b) {
                if (str.equals(aVar.b())) {
                    bVar.a(aVar.a());
                }
            }
        } else if (bVar != null) {
            if (this.o == null) {
                this.o = new HashMap();
            }
            this.o.put(str, bVar);
        } else if (this.o != null) {
            this.o.remove(str);
        }
    }
}
