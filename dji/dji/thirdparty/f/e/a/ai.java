package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$f;
import dji.thirdparty.f.f.c;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.b;
import dji.thirdparty.f.m.f;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public final class ai<T> implements d$f<T> {
    volatile b a = new b();
    final AtomicInteger b = new AtomicInteger(0);
    final ReentrantLock c = new ReentrantLock();
    private final c<? extends T> d;

    public ai(c<? extends T> cVar) {
        this.d = cVar;
    }

    public void a(k<? super T> kVar) {
        this.c.lock();
        if (this.b.incrementAndGet() == 1) {
            AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            try {
                this.d.h(a((k) kVar, atomicBoolean));
            } finally {
                if (atomicBoolean.get()) {
                    this.c.unlock();
                }
            }
        } else {
            try {
                a((k) kVar, this.a);
            } finally {
                this.c.unlock();
            }
        }
    }

    private dji.thirdparty.f.d.c<l> a(final k<? super T> kVar, final AtomicBoolean atomicBoolean) {
        return new dji.thirdparty.f.d.c<l>(this) {
            final /* synthetic */ ai c;

            public void a(l lVar) {
                try {
                    this.c.a.a(lVar);
                    this.c.a(kVar, this.c.a);
                } finally {
                    this.c.c.unlock();
                    atomicBoolean.set(false);
                }
            }
        };
    }

    void a(final k<? super T> kVar, final b bVar) {
        kVar.a(a(bVar));
        this.d.a(new k<T>(this, kVar) {
            final /* synthetic */ ai c;

            public void a(Throwable th) {
                d();
                kVar.a(th);
            }

            public void a_(T t) {
                kVar.a_(t);
            }

            public void o_() {
                d();
                kVar.o_();
            }

            void d() {
                this.c.c.lock();
                try {
                    if (this.c.a == bVar) {
                        this.c.a.n_();
                        this.c.a = new b();
                        this.c.b.set(0);
                    }
                    this.c.c.unlock();
                } catch (Throwable th) {
                    this.c.c.unlock();
                }
            }
        });
    }

    private l a(final b bVar) {
        return f.a(new dji.thirdparty.f.d.b(this) {
            final /* synthetic */ ai b;

            public void a() {
                this.b.c.lock();
                try {
                    if (this.b.a == bVar && this.b.b.decrementAndGet() == 0) {
                        this.b.a.n_();
                        this.b.a = new b();
                    }
                    this.b.c.unlock();
                } catch (Throwable th) {
                    this.b.c.unlock();
                }
            }
        });
    }
}
