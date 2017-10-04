package dji.thirdparty.f.e.c;

import dji.thirdparty.f.e.d.k;
import dji.thirdparty.f.e.d.n;
import dji.thirdparty.f.g;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.f;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class a extends g implements e {
    static final k b = new k(h);
    static final String c = "rx.scheduler.max-computation-threads";
    static final int d;
    static final c e = new c(new k("RxComputationShutdown-"));
    static final b f = new b(0);
    private static final String h = "RxComputationThreadPool-";
    final AtomicReference<b> g = new AtomicReference(f);

    private static class a extends dji.thirdparty.f.g.a {
        private final n a = new n();
        private final dji.thirdparty.f.m.b b = new dji.thirdparty.f.m.b();
        private final n c = new n(this.a, this.b);
        private final c d;

        a(c cVar) {
            this.d = cVar;
        }

        public void n_() {
            this.c.n_();
        }

        public boolean b() {
            return this.c.b();
        }

        public l a(dji.thirdparty.f.d.b bVar) {
            if (b()) {
                return f.b();
            }
            return this.d.a(bVar, 0, null, this.a);
        }

        public l a(dji.thirdparty.f.d.b bVar, long j, TimeUnit timeUnit) {
            if (b()) {
                return f.b();
            }
            return this.d.a(bVar, j, timeUnit, this.b);
        }
    }

    static final class b {
        final int a;
        final c[] b;
        long c;

        b(int i) {
            this.a = i;
            this.b = new c[i];
            for (int i2 = 0; i2 < i; i2++) {
                this.b[i2] = new c(a.b);
            }
        }

        public c a() {
            int i = this.a;
            if (i == 0) {
                return a.e;
            }
            c[] cVarArr = this.b;
            long j = this.c;
            this.c = 1 + j;
            return cVarArr[(int) (j % ((long) i))];
        }

        public void b() {
            for (c n_ : this.b) {
                n_.n_();
            }
        }
    }

    static final class c extends c {
        c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }

    static {
        int intValue = Integer.getInteger(c, 0).intValue();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (intValue <= 0 || intValue > availableProcessors) {
            intValue = availableProcessors;
        }
        d = intValue;
        e.n_();
    }

    public a() {
        c();
    }

    public dji.thirdparty.f.g.a a() {
        return new a(((b) this.g.get()).a());
    }

    public void c() {
        b bVar = new b(d);
        if (!this.g.compareAndSet(f, bVar)) {
            bVar.b();
        }
    }

    public void d() {
        b bVar;
        do {
            bVar = (b) this.g.get();
            if (bVar == f) {
                return;
            }
        } while (!this.g.compareAndSet(bVar, f));
        bVar.b();
    }

    public l a(dji.thirdparty.f.d.b bVar) {
        return ((b) this.g.get()).a().b(bVar, -1, TimeUnit.NANOSECONDS);
    }
}
