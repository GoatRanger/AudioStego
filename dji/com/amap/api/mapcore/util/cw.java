package com.amap.api.mapcore.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

class cw implements ThreadFactory {
    private final AtomicInteger a = new AtomicInteger(1);

    cw() {
    }

    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, "AsyncTask #" + this.a.getAndIncrement());
    }
}
