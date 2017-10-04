package dji.thirdparty.f.j;

import dji.thirdparty.f.e.c.e;
import dji.thirdparty.f.e.d.k;
import dji.thirdparty.f.g;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.f;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

final class a extends g implements e {
    static final k b = new k(g);
    static final k c = new k(h);
    static final c d = new c(new k("RxCachedThreadSchedulerShutdown-"));
    static final a f = new a(0, null);
    private static final String g = "RxCachedThreadScheduler-";
    private static final String h = "RxCachedWorkerPoolEvictor-";
    private static final long i = 60;
    private static final TimeUnit j = TimeUnit.SECONDS;
    final AtomicReference<a> e = new AtomicReference(f);

    private static final class a {
        private final long a;
        private final ConcurrentLinkedQueue<c> b;
        private final dji.thirdparty.f.m.b c;
        private final ScheduledExecutorService d;
        private final Future<?> e;

        a(long j, TimeUnit timeUnit) {
            Future scheduleWithFixedDelay;
            ScheduledExecutorService scheduledExecutorService = null;
            this.a = timeUnit != null ? timeUnit.toNanos(j) : 0;
            this.b = new ConcurrentLinkedQueue();
            this.c = new dji.thirdparty.f.m.b();
            if (timeUnit != null) {
                ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, a.c);
                dji.thirdparty.f.e.c.c.b(newScheduledThreadPool);
                scheduledExecutorService = newScheduledThreadPool;
                scheduleWithFixedDelay = newScheduledThreadPool.scheduleWithFixedDelay(new Runnable(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b();
                    }
                }, this.a, this.a, TimeUnit.NANOSECONDS);
            } else {
                scheduleWithFixedDelay = null;
            }
            this.d = scheduledExecutorService;
            this.e = scheduleWithFixedDelay;
        }

        c a() {
            if (this.c.b()) {
                return a.d;
            }
            while (!this.b.isEmpty()) {
                c cVar = (c) this.b.poll();
                if (cVar != null) {
                    return cVar;
                }
            }
            l cVar2 = new c(a.b);
            this.c.a(cVar2);
            return cVar2;
        }

        void a(c cVar) {
            cVar.a(c() + this.a);
            this.b.offer(cVar);
        }

        void b() {
            if (!this.b.isEmpty()) {
                long c = c();
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    c cVar = (c) it.next();
                    if (cVar.d() > c) {
                        return;
                    }
                    if (this.b.remove(cVar)) {
                        this.c.b(cVar);
                    }
                }
            }
        }

        long c() {
            return System.nanoTime();
        }

        void d() {
            try {
                if (this.e != null) {
                    this.e.cancel(true);
                }
                if (this.d != null) {
                    this.d.shutdownNow();
                }
                this.c.n_();
            } catch (Throwable th) {
                this.c.n_();
            }
        }
    }

    private static final class b extends dji.thirdparty.f.g.a {
        static final AtomicIntegerFieldUpdater<b> b = AtomicIntegerFieldUpdater.newUpdater(b.class, "a");
        volatile int a;
        private final dji.thirdparty.f.m.b c = new dji.thirdparty.f.m.b();
        private final a d;
        private final c e;

        b(a aVar) {
            this.d = aVar;
            this.e = aVar.a();
        }

        public void n_() {
            if (b.compareAndSet(this, 0, 1)) {
                this.d.a(this.e);
            }
            this.c.n_();
        }

        public boolean b() {
            return this.c.b();
        }

        public l a(dji.thirdparty.f.d.b bVar) {
            return a(bVar, 0, null);
        }

        public l a(dji.thirdparty.f.d.b bVar, long j, TimeUnit timeUnit) {
            if (this.c.b()) {
                return f.b();
            }
            l b = this.e.b(bVar, j, timeUnit);
            this.c.a(b);
            b.a(this.c);
            return b;
        }
    }

    private static final class c extends dji.thirdparty.f.e.c.c {
        private long c = 0;

        c(ThreadFactory threadFactory) {
            super(threadFactory);
        }

        public long d() {
            return this.c;
        }

        public void a(long j) {
            this.c = j;
        }
    }

    static {
        d.n_();
        f.d();
    }

    public a() {
        c();
    }

    public void c() {
        a aVar = new a(i, j);
        if (!this.e.compareAndSet(f, aVar)) {
            aVar.d();
        }
    }

    public void d() {
        a aVar;
        do {
            aVar = (a) this.e.get();
            if (aVar == f) {
                return;
            }
        } while (!this.e.compareAndSet(aVar, f));
        aVar.d();
    }

    public dji.thirdparty.f.g.a a() {
        return new b((a) this.e.get());
    }
}
