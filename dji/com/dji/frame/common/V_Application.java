package com.dji.frame.common;

import android.app.Application;
import android.content.Context;
import dji.thirdparty.afinal.b;
import dji.thirdparty.afinal.c;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Deprecated
public class V_Application extends Application {
    private static final ThreadFactory a = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "GreenDroid thread #" + this.a.getAndIncrement());
        }
    };
    private ExecutorService b;
    private dji.thirdparty.afinal.a c;
    private c d;
    private b e;
    private b f;
    private ArrayList<WeakReference<a>> g = new ArrayList();

    public interface a {
        void a();
    }

    public ExecutorService a() {
        if (this.b == null) {
            this.b = Executors.newFixedThreadPool(5, a);
        }
        return this.b;
    }

    public dji.thirdparty.afinal.a b() {
        if (this.c == null) {
            this.c = dji.thirdparty.afinal.a.a((Context) this);
        }
        return this.c;
    }

    public c c() {
        if (this.d == null) {
            this.d = new c();
        }
        return this.d;
    }

    public b d() {
        if (this.e == null) {
            this.e = b.a((Context) this);
        }
        return this.e;
    }

    public b e() {
        if (this.f == null) {
            this.f = new b(this);
        }
        return this.f;
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.g.add(new WeakReference(aVar));
        }
    }

    public void b(a aVar) {
        if (aVar != null) {
            int i = 0;
            while (i < this.g.size()) {
                int i2;
                a aVar2 = (a) ((WeakReference) this.g.get(i)).get();
                if (aVar2 == null || aVar2 == aVar) {
                    this.g.remove(i);
                    i2 = i;
                } else {
                    i2 = i + 1;
                }
                i = i2;
            }
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        int i = 0;
        while (i < this.g.size()) {
            int i2;
            a aVar = (a) ((WeakReference) this.g.get(i)).get();
            if (aVar == null) {
                this.g.remove(i);
                i2 = i;
            } else {
                aVar.a();
                i2 = i + 1;
            }
            i = i2;
        }
    }
}
