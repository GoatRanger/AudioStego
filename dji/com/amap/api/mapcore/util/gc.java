package com.amap.api.mapcore.util;

public abstract class gc implements Runnable {
    a d;

    interface a {
        void a(gc gcVar);

        void b(gc gcVar);

        void c(gc gcVar);
    }

    public abstract void a();

    public final void run() {
        try {
            if (this.d != null) {
                this.d.a(this);
            }
            if (!Thread.interrupted()) {
                a();
                if (!Thread.interrupted() && this.d != null) {
                    this.d.b(this);
                }
            }
        } catch (Throwable th) {
            ee.a(th, "ThreadTask", "run");
            th.printStackTrace();
        }
    }

    public final void e() {
        try {
            if (this.d != null) {
                this.d.c(this);
            }
        } catch (Throwable th) {
            ee.a(th, "ThreadTask", "cancelTask");
            th.printStackTrace();
        }
    }
}
