package com.alibaba.sdk.android.app;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class a {
    public static final a a = new a();
    private Map<String, com.alibaba.sdk.android.app.a.a> b = new HashMap();
    private ReentrantReadWriteLock c = new ReentrantReadWriteLock();

    private a() {
    }

    public final AppContext a(String str) {
        this.c.readLock().lock();
        try {
            AppContext appContext = (AppContext) this.b.get(str);
            return appContext;
        } finally {
            this.c.readLock().unlock();
        }
    }

    public final AppContext b(String str) {
        this.c.writeLock().lock();
        try {
            AppContext appContext = (com.alibaba.sdk.android.app.a.a) this.b.get(str);
            if (appContext == null) {
                appContext = new com.alibaba.sdk.android.app.a.a(str);
                this.b.put(str, appContext);
            }
            this.c.writeLock().unlock();
            return appContext;
        } catch (Throwable th) {
            this.c.writeLock().unlock();
        }
    }

    public final AppContext c(String str) {
        this.c.writeLock().lock();
        try {
            com.alibaba.sdk.android.app.a.a aVar = (com.alibaba.sdk.android.app.a.a) this.b.remove(str);
            if (aVar != null) {
                aVar.a();
            }
            return aVar;
        } finally {
            this.c.writeLock().unlock();
        }
    }
}
