package com.dji.a.d;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class e {
    private static com.dji.a.b d = null;
    private int a;
    private final HashMap<Integer, Handler> b;
    private final boolean[] c;
    private AtomicInteger e;
    private Handler f;

    class a extends Thread {
        int a = 0;
        final /* synthetic */ e b;

        public a(e eVar, int i) {
            this.b = eVar;
            this.a = i;
        }

        public void run() {
            Looper.prepare();
            this.b.a(this.a, new com.dji.a.e.e(this.b.f, this.a, e.d).a);
            this.b.e.addAndGet(1);
            Looper.loop();
        }
    }

    private static final class b {
        private static final e a = new e();
    }

    private e() {
        this.a = 60000;
        this.b = new HashMap();
        this.c = new boolean[1];
        this.e = new AtomicInteger(-1);
        this.f = new f(this);
    }

    public static e a() {
        return b.a;
    }

    public void a(com.dji.a.b bVar) {
        d = bVar;
        com.dji.a.b.a.a().b();
        this.a = com.dji.a.a.a().j();
    }

    public void a(com.dji.a.a.a aVar) {
        b(aVar);
        b();
    }

    public void b(com.dji.a.a.a aVar) {
        com.dji.a.b.a.a().a(aVar);
    }

    public void b() {
        if (-1 == this.e.get()) {
            this.e.set(0);
            if (this.b.isEmpty()) {
                d();
            }
            synchronized (this.b) {
                try {
                    this.b.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    com.dji.a.a.c.c(com.dji.a.a.a, "Error in wait init finish ." + e);
                }
            }
        }
        if (this.e.get() == 1) {
            if (d == null) {
                if (com.dji.a.a.b) {
                    com.dji.a.a.c.a(com.dji.a.a.a, "mConfig is null before distribute");
                }
            } else if (!c.a(d)) {
            } else {
                if (a.a().b() || !com.dji.a.a.d()) {
                    synchronized (this.c) {
                        for (int i = 0; i < 1; i++) {
                            if (!this.c[i]) {
                                this.c[i] = true;
                                Handler handler = (Handler) this.b.get(Integer.valueOf(i));
                                handler.sendMessageDelayed(handler.obtainMessage(), (long) this.a);
                                break;
                            }
                        }
                    }
                    return;
                }
                a.a().c();
                if (com.dji.a.a.b) {
                    com.dji.a.a.c.a(com.dji.a.a.a, "BaseInfo is not send.");
                }
            }
        }
    }

    private void a(int i, boolean z) {
        synchronized (this.c) {
            if (i < 1) {
                this.c[i] = z;
            }
        }
    }

    private void a(int i, Handler handler) {
        synchronized (this) {
            this.b.put(Integer.valueOf(i), handler);
            a(i, false);
            if (this.b.size() == 1) {
                synchronized (this.b) {
                    this.b.notify();
                }
            }
        }
    }

    private void d() {
        for (int i = 0; i < 1; i++) {
            new a(this, i).start();
            this.c[i] = false;
        }
    }
}
