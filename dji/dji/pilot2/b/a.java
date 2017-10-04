package dji.pilot2.b;

import dji.pilot2.utils.o;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class a {
    public static final int a = 0;
    public static final int b = 1;
    private static final String c = "ThreadPoolManager";
    private static final int e = 1;
    private static final int f = 10;
    private static final int k = 200;
    private int d;
    private ExecutorService g;
    private LinkedList<b> h;
    private int i;
    private Thread j;

    private class a implements Runnable {
        final /* synthetic */ a a;

        private a(a aVar) {
            this.a = aVar;
        }

        public void run() {
            o.a("ThreadPoolManager开始轮询");
            while (!Thread.currentThread().isInterrupted()) {
                Runnable a = this.a.c();
                if (a == null) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } catch (Throwable th) {
                        this.a.g.shutdown();
                    }
                } else {
                    this.a.g.execute(a);
                }
            }
            this.a.g.shutdown();
            o.a("ThreadPoolManager结束轮询");
        }
    }

    public a(int i, int i2) {
        int i3;
        this.i = i == 0 ? 0 : 1;
        if (i2 < 1) {
            i3 = 1;
        } else {
            i3 = i2;
        }
        if (i3 > 10) {
            i3 = 10;
        }
        this.d = i3;
        this.g = Executors.newFixedThreadPool(this.d);
        this.h = new LinkedList();
    }

    public void a(b bVar) {
        synchronized (this.h) {
            this.h.addLast(bVar);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private dji.pilot2.b.b c() {
        /*
        r2 = this;
        r1 = r2.h;
        monitor-enter(r1);
        r0 = r2.h;	 Catch:{ all -> 0x0025 }
        r0 = r0.size();	 Catch:{ all -> 0x0025 }
        if (r0 <= 0) goto L_0x0022;
    L_0x000b:
        r0 = r2.i;	 Catch:{ all -> 0x0025 }
        if (r0 != 0) goto L_0x0019;
    L_0x000f:
        r0 = r2.h;	 Catch:{ all -> 0x0025 }
        r0 = r0.removeFirst();	 Catch:{ all -> 0x0025 }
        r0 = (dji.pilot2.b.b) r0;	 Catch:{ all -> 0x0025 }
    L_0x0017:
        monitor-exit(r1);	 Catch:{ all -> 0x0025 }
    L_0x0018:
        return r0;
    L_0x0019:
        r0 = r2.h;	 Catch:{ all -> 0x0025 }
        r0 = r0.removeLast();	 Catch:{ all -> 0x0025 }
        r0 = (dji.pilot2.b.b) r0;	 Catch:{ all -> 0x0025 }
        goto L_0x0017;
    L_0x0022:
        monitor-exit(r1);	 Catch:{ all -> 0x0025 }
        r0 = 0;
        goto L_0x0018;
    L_0x0025:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0025 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot2.b.a.c():dji.pilot2.b.b");
    }

    public void a() {
        if (this.j == null) {
            this.j = new Thread(new a());
            this.j.start();
        }
    }

    public void b() {
        this.j.interrupt();
        this.j = null;
    }
}
