package com.alibaba.sdk.android.plugin;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class a {
    public static final a a = new a();
    private ConcurrentMap<String, com.alibaba.sdk.android.plugin.a.a> b = new ConcurrentHashMap();
    private ReentrantReadWriteLock c = new ReentrantReadWriteLock();

    private a() {
    }

    public final PluginContext a(PluginInfo pluginInfo) {
        this.c.writeLock().lock();
        try {
            PluginContext pluginContext = (com.alibaba.sdk.android.plugin.a.a) this.b.get(pluginInfo.name);
            if (pluginContext == null) {
                pluginContext = new com.alibaba.sdk.android.plugin.a.a(pluginInfo);
                this.b.put(pluginInfo.name, pluginContext);
            }
            this.c.writeLock().unlock();
            return pluginContext;
        } catch (Throwable th) {
            this.c.writeLock().unlock();
        }
    }

    public final PluginContext a(String str) {
        this.c.writeLock().lock();
        try {
            com.alibaba.sdk.android.plugin.a.a aVar = (com.alibaba.sdk.android.plugin.a.a) this.b.remove(str);
            if (aVar != null) {
                aVar.a();
            }
            return aVar;
        } finally {
            this.c.writeLock().unlock();
        }
    }

    public final PluginContext b(String str) {
        this.c.readLock().lock();
        try {
            PluginContext pluginContext = (PluginContext) this.b.get(str);
            return pluginContext;
        } finally {
            this.c.readLock().unlock();
        }
    }
}
