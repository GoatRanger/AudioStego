package com.flurry.sdk;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class kf {
    private static kf a;
    private final UncaughtExceptionHandler b = Thread.getDefaultUncaughtExceptionHandler();
    private final Map<UncaughtExceptionHandler, Void> c = new WeakHashMap();

    final class a implements UncaughtExceptionHandler {
        final /* synthetic */ kf a;

        private a(kf kfVar) {
            this.a = kfVar;
        }

        public void uncaughtException(Thread thread, Throwable th) {
            this.a.a(thread, th);
            this.a.b(thread, th);
        }
    }

    public static synchronized kf a() {
        kf kfVar;
        synchronized (kf.class) {
            if (a == null) {
                a = new kf();
            }
            kfVar = a;
        }
        return kfVar;
    }

    public static synchronized void b() {
        synchronized (kf.class) {
            if (a != null) {
                a.d();
            }
            a = null;
        }
    }

    public void a(UncaughtExceptionHandler uncaughtExceptionHandler) {
        synchronized (this.c) {
            this.c.put(uncaughtExceptionHandler, null);
        }
    }

    private Set<UncaughtExceptionHandler> c() {
        Set<UncaughtExceptionHandler> keySet;
        synchronized (this.c) {
            keySet = this.c.keySet();
        }
        return keySet;
    }

    private kf() {
        Thread.setDefaultUncaughtExceptionHandler(new a());
    }

    private void d() {
        Thread.setDefaultUncaughtExceptionHandler(this.b);
    }

    private void a(Thread thread, Throwable th) {
        for (UncaughtExceptionHandler uncaughtException : c()) {
            try {
                uncaughtException.uncaughtException(thread, th);
            } catch (Throwable th2) {
            }
        }
    }

    private void b(Thread thread, Throwable th) {
        if (this.b != null) {
            try {
                this.b.uncaughtException(thread, th);
            } catch (Throwable th2) {
            }
        }
    }
}
