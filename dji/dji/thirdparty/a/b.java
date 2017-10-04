package dji.thirdparty.a;

import android.util.Log;

final class b implements Runnable {
    private final h a = new h();
    private volatile boolean b;
    private final c c;

    b(c cVar) {
        this.c = cVar;
    }

    public void a(l lVar, Object obj) {
        g a = g.a(lVar, obj);
        synchronized (this) {
            this.a.a(a);
            if (!this.b) {
                this.b = true;
                c.a.execute(this);
            }
        }
    }

    public void run() {
        while (true) {
            try {
                g a = this.a.a(1000);
                if (a == null) {
                    synchronized (this) {
                        a = this.a.a();
                        if (a == null) {
                            this.b = false;
                            this.b = false;
                            return;
                        }
                    }
                }
                this.c.a(a);
            } catch (Throwable e) {
                Log.w("Event", new StringBuilder(String.valueOf(Thread.currentThread().getName())).append(" was interruppted").toString(), e);
                this.b = false;
                return;
            } catch (Throwable th) {
                this.b = false;
            }
        }
    }
}
