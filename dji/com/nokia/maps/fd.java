package com.nokia.maps;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class fd extends Thread {
    AtomicBoolean a = new AtomicBoolean(false);
    private Handler b = null;
    private final List<Runnable> c = new ArrayList();
    private final List<Object> d = new ArrayList();

    public fd() {
        setName("MapWorkerThread");
        start();
    }

    public synchronized void a(Runnable runnable) {
        if (this.b == null) {
            this.c.add(runnable);
        } else {
            this.b.post(runnable);
        }
    }

    public synchronized void a(Runnable runnable, long j) {
        if (this.b == null) {
            this.c.add(runnable);
        } else {
            this.b.postDelayed(runnable, j);
        }
    }

    public synchronized void b(Runnable runnable) {
        if (this.b == null) {
            do {
            } while (this.c.remove(runnable));
        } else {
            this.b.removeCallbacks(runnable);
        }
    }

    public synchronized void a() {
        if (this.b == null) {
            this.a.set(true);
        } else {
            this.b.postAtFrontOfQueue(new Runnable(this) {
                final /* synthetic */ fd a;

                {
                    this.a = r1;
                }

                public void run() {
                    Looper.myLooper().quit();
                }
            });
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.a;	 Catch:{ all -> 0x0032 }
        r0 = r0.get();	 Catch:{ all -> 0x0032 }
        if (r0 == 0) goto L_0x000b;
    L_0x0009:
        monitor-exit(r3);	 Catch:{ all -> 0x0032 }
    L_0x000a:
        return;
    L_0x000b:
        monitor-exit(r3);	 Catch:{ all -> 0x0032 }
        android.os.Looper.prepare();
        monitor-enter(r3);
        r0 = new android.os.Handler;	 Catch:{ all -> 0x002f }
        r0.<init>();	 Catch:{ all -> 0x002f }
        r3.b = r0;	 Catch:{ all -> 0x002f }
        r0 = r3.c;	 Catch:{ all -> 0x002f }
        r1 = r0.iterator();	 Catch:{ all -> 0x002f }
    L_0x001d:
        r0 = r1.hasNext();	 Catch:{ all -> 0x002f }
        if (r0 == 0) goto L_0x0035;
    L_0x0023:
        r0 = r1.next();	 Catch:{ all -> 0x002f }
        r0 = (java.lang.Runnable) r0;	 Catch:{ all -> 0x002f }
        r2 = r3.b;	 Catch:{ all -> 0x002f }
        r2.post(r0);	 Catch:{ all -> 0x002f }
        goto L_0x001d;
    L_0x002f:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x002f }
        throw r0;
    L_0x0032:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0032 }
        throw r0;
    L_0x0035:
        monitor-exit(r3);	 Catch:{ all -> 0x002f }
        android.os.Looper.loop();
        goto L_0x000a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nokia.maps.fd.run():void");
    }
}
