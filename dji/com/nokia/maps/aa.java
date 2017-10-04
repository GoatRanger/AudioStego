package com.nokia.maps;

import android.annotation.TargetApi;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@TargetApi(16)
class aa implements FrameCallback {
    private static final String a = aa.class.getName();
    private Choreographer b;
    private Semaphore c;

    public aa() {
        this.b = null;
        this.c = null;
        this.b = Choreographer.getInstance();
        this.c = new Semaphore(0);
    }

    public void doFrame(long j) {
        this.b.postFrameCallback(this);
        c();
    }

    public void a() {
        this.c.drainPermits();
        this.b.postFrameCallback(this);
    }

    public void b() {
        try {
            if (!this.c.tryAcquire(1, TimeUnit.SECONDS)) {
                bj.c(a, "Timeout waiting to acquire m_waiter", new Object[0]);
            }
        } catch (InterruptedException e) {
        }
    }

    public void c() {
        this.c.release();
    }

    public void d() {
        this.c.release(1000);
        this.b.removeFrameCallback(this);
    }
}
