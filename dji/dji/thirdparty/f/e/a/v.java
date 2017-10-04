package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.e.d.b.an;
import dji.thirdparty.f.e.d.b.z;
import dji.thirdparty.f.e.d.l;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.m.e;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class v<T, R> implements d$f<R> {
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = 2;
    final d<? extends T> a;
    final o<? super T, ? extends d<? extends R>> b;
    final int c;
    final int d;

    static final class a<T, R> implements f {
        final R a;
        final c<T, R> b;
        boolean c;

        public a(R r, c<T, R> cVar) {
            this.a = r;
            this.b = cVar;
        }

        public void a(long j) {
            if (!this.c) {
                this.c = true;
                c cVar = this.b;
                cVar.b(this.a);
                cVar.c(1);
            }
        }
    }

    static final class b<T, R> extends k<R> {
        final c<T, R> a;
        long b;

        public b(c<T, R> cVar) {
            this.a = cVar;
        }

        public void a(f fVar) {
            this.a.d.a(fVar);
        }

        public void a_(R r) {
            this.b++;
            this.a.b((Object) r);
        }

        public void a(Throwable th) {
            this.a.a(th, this.b);
        }

        public void o_() {
            this.a.c(this.b);
        }
    }

    static final class c<T, R> extends k<T> {
        final k<? super R> a;
        final o<? super T, ? extends d<? extends R>> b;
        final int c;
        final dji.thirdparty.f.e.b.a d = new dji.thirdparty.f.e.b.a();
        final Queue<Object> e;
        final AtomicInteger f = new AtomicInteger();
        final AtomicReference<Throwable> g = new AtomicReference();
        final e h;
        volatile boolean i;
        volatile boolean j;

        public c(k<? super R> kVar, o<? super T, ? extends d<? extends R>> oVar, int i, int i2) {
            Queue zVar;
            this.a = kVar;
            this.b = oVar;
            this.c = i2;
            if (an.a()) {
                zVar = new z(i);
            } else {
                zVar = new dji.thirdparty.f.e.d.a.e(i);
            }
            this.e = zVar;
            this.h = new e();
            a((long) i);
        }

        public void a_(T t) {
            if (this.e.offer(r.a().a((Object) t))) {
                d();
                return;
            }
            n_();
            a(new dji.thirdparty.f.c.c());
        }

        public void a(Throwable th) {
            if (dji.thirdparty.f.e.d.c.addThrowable(this.g, th)) {
                this.i = true;
                if (this.c == 0) {
                    Throwable terminate = dji.thirdparty.f.e.d.c.terminate(this.g);
                    if (!dji.thirdparty.f.e.d.c.isTerminated(terminate)) {
                        this.a.a(terminate);
                    }
                    this.h.n_();
                    return;
                }
                d();
                return;
            }
            b(th);
        }

        public void o_() {
            this.i = true;
            d();
        }

        void b(long j) {
            if (j > 0) {
                this.d.a(j);
            } else if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            }
        }

        void b(R r) {
            this.a.a_(r);
        }

        void a(Throwable th, long j) {
            if (!dji.thirdparty.f.e.d.c.addThrowable(this.g, th)) {
                b(th);
            } else if (this.c == 0) {
                Throwable terminate = dji.thirdparty.f.e.d.c.terminate(this.g);
                if (!dji.thirdparty.f.e.d.c.isTerminated(terminate)) {
                    this.a.a(terminate);
                }
                n_();
            } else {
                if (j != 0) {
                    this.d.b(j);
                }
                this.j = false;
                d();
            }
        }

        void c(long j) {
            if (j != 0) {
                this.d.b(j);
            }
            this.j = false;
            d();
        }

        void b(Throwable th) {
            dji.thirdparty.f.i.d.getInstance().b().a(th);
        }

        void d() {
            if (this.f.getAndIncrement() == 0) {
                int i = this.c;
                while (!this.a.b()) {
                    if (!this.j) {
                        Throwable terminate;
                        if (i != 1 || this.g.get() == null) {
                            boolean z = this.i;
                            Object poll = this.e.poll();
                            boolean z2 = poll == null;
                            if (z && z2) {
                                terminate = dji.thirdparty.f.e.d.c.terminate(this.g);
                                if (terminate == null) {
                                    this.a.o_();
                                    return;
                                } else if (!dji.thirdparty.f.e.d.c.isTerminated(terminate)) {
                                    this.a.a(terminate);
                                    return;
                                } else {
                                    return;
                                }
                            } else if (!z2) {
                                try {
                                    d dVar = (d) this.b.a(r.a().g(poll));
                                    if (dVar == null) {
                                        c(new NullPointerException("The source returned by the mapper was null"));
                                        return;
                                    }
                                    if (dVar != d.d()) {
                                        if (dVar instanceof l) {
                                            this.d.a(new a(((l) dVar).a(), this));
                                        } else {
                                            k bVar = new b(this);
                                            this.h.a(bVar);
                                            if (!bVar.b()) {
                                                this.j = true;
                                                dVar.a(bVar);
                                            } else {
                                                return;
                                            }
                                        }
                                    }
                                    a(1);
                                } catch (Throwable terminate2) {
                                    dji.thirdparty.f.c.b.b(terminate2);
                                    c(terminate2);
                                    return;
                                }
                            }
                        }
                        terminate2 = dji.thirdparty.f.e.d.c.terminate(this.g);
                        if (!dji.thirdparty.f.e.d.c.isTerminated(terminate2)) {
                            this.a.a(terminate2);
                            return;
                        }
                        return;
                    }
                    if (this.f.decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }

        void c(Throwable th) {
            n_();
            if (dji.thirdparty.f.e.d.c.addThrowable(this.g, th)) {
                Throwable terminate = dji.thirdparty.f.e.d.c.terminate(this.g);
                if (!dji.thirdparty.f.e.d.c.isTerminated(terminate)) {
                    this.a.a(terminate);
                    return;
                }
                return;
            }
            b(th);
        }
    }

    public v(d<? extends T> dVar, o<? super T, ? extends d<? extends R>> oVar, int i, int i2) {
        this.a = dVar;
        this.b = oVar;
        this.c = i;
        this.d = i2;
    }

    public void a(k<? super R> kVar) {
        k dVar;
        if (this.d == 0) {
            dVar = new dji.thirdparty.f.g.d(kVar);
        } else {
            k<? super R> kVar2 = kVar;
        }
        final dji.thirdparty.f.l cVar = new c(dVar, this.b, this.c, this.d);
        kVar.a(cVar);
        kVar.a(cVar.h);
        kVar.a(new f(this) {
            final /* synthetic */ v b;

            public void a(long j) {
                cVar.b(j);
            }
        });
        if (!kVar.b()) {
            this.a.a(cVar);
        }
    }
}
