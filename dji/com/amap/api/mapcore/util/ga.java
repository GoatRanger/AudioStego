package com.amap.api.mapcore.util;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

public final class ga {
    private static ga a = null;
    private ExecutorService b;
    private ConcurrentHashMap<gc, Future<?>> c = new ConcurrentHashMap();
    private a d = new gb(this);

    public static synchronized ga a(int i) {
        ga gaVar;
        synchronized (ga.class) {
            if (a == null) {
                a = new ga(i);
            }
            gaVar = a;
        }
        return gaVar;
    }

    private ga(int i) {
        try {
            this.b = Executors.newFixedThreadPool(i);
        } catch (Throwable th) {
            ee.a(th, "TPool", "ThreadPool");
            th.printStackTrace();
        }
    }

    public void a(gc gcVar) throws dk {
        try {
            if (!b(gcVar) && this.b != null && !this.b.isShutdown()) {
                gcVar.d = this.d;
                Future submit = this.b.submit(gcVar);
                if (submit != null) {
                    a(gcVar, submit);
                }
            }
        } catch (RejectedExecutionException e) {
        } catch (Throwable th) {
            th.printStackTrace();
            ee.a(th, "TPool", "addTask");
            dk dkVar = new dk("thread pool has exception");
        }
    }

    public static synchronized void a() {
        synchronized (ga.class) {
            try {
                if (a != null) {
                    a.b();
                    a = null;
                }
            } catch (Throwable th) {
                ee.a(th, "TPool", "onDestroy");
                th.printStackTrace();
            }
        }
    }

    private void b() {
        try {
            for (Entry key : this.c.entrySet()) {
                Future future = (Future) this.c.get((gc) key.getKey());
                if (future != null) {
                    future.cancel(true);
                }
            }
            this.c.clear();
            this.b.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            ee.a(th, "TPool", "destroy");
            th.printStackTrace();
        }
    }

    private synchronized boolean b(gc gcVar) {
        boolean z;
        z = false;
        try {
            z = this.c.containsKey(gcVar);
        } catch (Throwable th) {
            ee.a(th, "TPool", "contain");
            th.printStackTrace();
        }
        return z;
    }

    private synchronized void a(gc gcVar, Future<?> future) {
        try {
            this.c.put(gcVar, future);
        } catch (Throwable th) {
            ee.a(th, "TPool", "addQueue");
            th.printStackTrace();
        }
    }

    private synchronized void a(gc gcVar, boolean z) {
        try {
            Future future = (Future) this.c.remove(gcVar);
            if (z && future != null) {
                future.cancel(true);
            }
        } catch (Throwable th) {
            ee.a(th, "TPool", "removeQueue");
            th.printStackTrace();
        }
    }
}
