package com.alibaba.sdk.android.executor.a;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class b implements ThreadFactory {
    final /* synthetic */ a a;
    private final AtomicInteger b = new AtomicInteger(1);

    b(a aVar) {
        this.a = aVar;
    }

    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, "ExecutorTask #" + this.b.getAndIncrement());
    }
}
