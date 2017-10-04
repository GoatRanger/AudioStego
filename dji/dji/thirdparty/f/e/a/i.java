package dji.thirdparty.f.e.a;

import dji.thirdparty.f.b;
import dji.thirdparty.f.b$a;
import dji.thirdparty.f.b$c;
import dji.thirdparty.f.c.c;
import dji.thirdparty.f.d;
import dji.thirdparty.f.e.d.b.z;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.e;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public final class i implements b$a {
    final d<? extends b> a;
    final int b;

    static final class a extends k<b> {
        static final AtomicIntegerFieldUpdater<a> g = AtomicIntegerFieldUpdater.newUpdater(a.class, "f");
        final b$c a;
        final int b;
        final e c = new e();
        final z<b> d;
        volatile boolean e;
        volatile int f;
        final a h = new a(this);
        final AtomicInteger i = new AtomicInteger();

        final class a implements b$c {
            final /* synthetic */ a a;

            a(a aVar) {
                this.a = aVar;
            }

            public void a(l lVar) {
                this.a.c.a(lVar);
            }

            public void a(Throwable th) {
                this.a.b(th);
            }

            public void b() {
                this.a.d();
            }
        }

        public /* synthetic */ void a_(Object obj) {
            a((b) obj);
        }

        public a(b$c dji_thirdparty_f_b_c, int i) {
            this.a = dji_thirdparty_f_b_c;
            this.b = i;
            this.d = new z(i);
            a(this.c);
            a((long) i);
        }

        public void a(b bVar) {
            if (!this.d.offer(bVar)) {
                a(new c());
            } else if (this.i.getAndIncrement() == 0) {
                e();
            }
        }

        public void a(Throwable th) {
            if (g.compareAndSet(this, 0, 1)) {
                this.a.a(th);
            } else {
                dji.thirdparty.f.i.d.getInstance().b().a(th);
            }
        }

        public void o_() {
            if (!this.e) {
                this.e = true;
                if (this.i.getAndIncrement() == 0) {
                    e();
                }
            }
        }

        void b(Throwable th) {
            n_();
            a(th);
        }

        void d() {
            if (this.i.decrementAndGet() != 0) {
                e();
            }
            if (!this.e) {
                a(1);
            }
        }

        void e() {
            boolean z = this.e;
            b bVar = (b) this.d.poll();
            if (bVar != null) {
                bVar.a(this.h);
            } else if (!z) {
                dji.thirdparty.f.i.d.getInstance().b().a(new IllegalStateException("Queue is empty?!"));
            } else if (g.compareAndSet(this, 0, 1)) {
                this.a.b();
            }
        }
    }

    public i(d<? extends b> dVar, int i) {
        this.a = dVar;
        this.b = i;
    }

    public void a(b$c dji_thirdparty_f_b_c) {
        l aVar = new a(dji_thirdparty_f_b_c, this.b);
        dji_thirdparty_f_b_c.a(aVar);
        this.a.b(aVar);
    }
}
