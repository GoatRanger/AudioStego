package com.flurry.sdk;

import java.util.concurrent.ThreadFactory;

public class js implements ThreadFactory {
    private final ThreadGroup a;
    private final int b;

    public js(String str, int i) {
        this.a = new ThreadGroup(str);
        this.b = i;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.a, runnable);
        thread.setName(this.a.getName() + ":" + thread.getId());
        thread.setPriority(this.b);
        return thread;
    }
}
