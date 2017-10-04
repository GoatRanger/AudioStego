package com.tencent.android.tpush.a;

import java.util.concurrent.ThreadFactory;

public class c implements ThreadFactory {
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setPriority(10);
        return thread;
    }
}
