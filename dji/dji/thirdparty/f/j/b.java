package dji.thirdparty.f.j;

import dji.thirdparty.f.e.c.d;
import dji.thirdparty.f.g;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.c;
import dji.thirdparty.f.m.f;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

final class b extends g {
    final Executor b;

    static final class a extends dji.thirdparty.f.g.a implements Runnable {
        final Executor a;
        final dji.thirdparty.f.m.b b = new dji.thirdparty.f.m.b();
        final ConcurrentLinkedQueue<d> c = new ConcurrentLinkedQueue();
        final AtomicInteger d = new AtomicInteger();
        final ScheduledExecutorService e = dji.thirdparty.f.e.c.b.getInstance();

        public a(Executor executor) {
            this.a = executor;
        }

        public l a(dji.thirdparty.f.d.b bVar) {
            if (b()) {
                return f.b();
            }
            l dVar = new d(bVar, this.b);
            this.b.a(dVar);
            this.c.offer(dVar);
            if (this.d.getAndIncrement() != 0) {
                return dVar;
            }
            try {
                this.a.execute(this);
                return dVar;
            } catch (Throwable e) {
                this.b.b(dVar);
                this.d.decrementAndGet();
                dji.thirdparty.f.i.d.getInstance().b().a(e);
                throw e;
            }
        }

        public void run() {
            do {
                d dVar = (d) this.c.poll();
                if (!dVar.b()) {
                    dVar.run();
                }
            } while (this.d.decrementAndGet() > 0);
        }

        public l a(final dji.thirdparty.f.d.b bVar, long j, TimeUnit timeUnit) {
            if (j <= 0) {
                return a(bVar);
            }
            if (b()) {
                return f.b();
            }
            Object cVar = new c();
            final l cVar2 = new c();
            cVar2.a(cVar);
            this.b.a(cVar2);
            final l a = f.a(new dji.thirdparty.f.d.b(this) {
                final /* synthetic */ a b;

                public void a() {
                    this.b.b.b(cVar2);
                }
            });
            d dVar = new d(new dji.thirdparty.f.d.b(this) {
                final /* synthetic */ a d;

                public void a() {
                    if (!cVar2.b()) {
                        l a = this.d.a(bVar);
                        cVar2.a(a);
                        if (a.getClass() == d.class) {
                            ((d) a).a(a);
                        }
                    }
                }
            });
            cVar.a(dVar);
            try {
                dVar.a(this.e.schedule(dVar, j, timeUnit));
                return a;
            } catch (Throwable e) {
                dji.thirdparty.f.i.d.getInstance().b().a(e);
                throw e;
            }
        }

        public boolean b() {
            return this.b.b();
        }

        public void n_() {
            this.b.n_();
        }
    }

    public b(Executor executor) {
        this.b = executor;
    }

    public dji.thirdparty.f.g.a a() {
        return new a(this.b);
    }
}
