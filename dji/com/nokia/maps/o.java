package com.nokia.maps;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class o {
    private boolean a;
    private Object b = new Object();
    private ScheduledFuture<?> c;
    private final ScheduledExecutorService d = Executors.newSingleThreadScheduledExecutor();

    boolean a() {
        boolean z;
        synchronized (this.b) {
            z = this.a;
        }
        return z;
    }

    void b() {
        synchronized (this.b) {
            if (this.a && this.c != null) {
                this.c.cancel(false);
            }
            this.a = true;
            this.c = this.d.schedule(new Runnable(this) {
                final /* synthetic */ o a;

                {
                    this.a = r1;
                }

                public void run() {
                    synchronized (this.a.b) {
                        this.a.a = false;
                    }
                }
            }, 5, TimeUnit.SECONDS);
        }
    }
}
