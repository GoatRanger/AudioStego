package com.alibaba.sdk.android.executor;

import android.os.Looper;

public interface ExecutorService {
    Looper getDefaultLooper();

    void postHandlerTask(Runnable runnable);

    void postTask(Runnable runnable);

    void postUITask(Runnable runnable);
}
