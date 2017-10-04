package dji.thirdparty.f.e.d;

import dji.pilot.phonecamera.h;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.e;
import dji.thirdparty.f.f;
import dji.thirdparty.f.g;
import dji.thirdparty.f.k;
import java.util.concurrent.atomic.AtomicBoolean;

public final class l<T> extends d<T> {
    static final boolean c = Boolean.valueOf(System.getProperty("rx.just.strong-mode", h.e)).booleanValue();
    final T d;

    class AnonymousClass1 implements d$f<T> {
        final /* synthetic */ Object a;

        AnonymousClass1(Object obj) {
            this.a = obj;
        }

        public void a(k<? super T> kVar) {
            kVar.a(l.a(kVar, this.a));
        }
    }

    static final class a<T> implements d$f<T> {
        final T a;
        final o<dji.thirdparty.f.d.b, dji.thirdparty.f.l> b;

        a(T t, o<dji.thirdparty.f.d.b, dji.thirdparty.f.l> oVar) {
            this.a = t;
            this.b = oVar;
        }

        public void a(k<? super T> kVar) {
            kVar.a(new b(kVar, this.a, this.b));
        }
    }

    static final class b<T> extends AtomicBoolean implements dji.thirdparty.f.d.b, f {
        private static final long d = -2466317989629281651L;
        final k<? super T> a;
        final T b;
        final o<dji.thirdparty.f.d.b, dji.thirdparty.f.l> c;

        public b(k<? super T> kVar, T t, o<dji.thirdparty.f.d.b, dji.thirdparty.f.l> oVar) {
            this.a = kVar;
            this.b = t;
            this.c = oVar;
        }

        public void a(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            } else if (j != 0 && compareAndSet(false, true)) {
                this.a.a((dji.thirdparty.f.l) this.c.a(this));
            }
        }

        public void a() {
            e eVar = this.a;
            if (!eVar.b()) {
                Object obj = this.b;
                try {
                    eVar.a_(obj);
                    if (!eVar.b()) {
                        eVar.o_();
                    }
                } catch (Throwable th) {
                    dji.thirdparty.f.c.b.a(th, eVar, obj);
                }
            }
        }

        public String toString() {
            return "ScalarAsyncProducer[" + this.b + ", " + get() + dji.pilot.usercenter.protocol.d.H;
        }
    }

    static final class c<T> implements f {
        final k<? super T> a;
        final T b;
        boolean c;

        public c(k<? super T> kVar, T t) {
            this.a = kVar;
            this.b = t;
        }

        public void a(long j) {
            if (!this.c) {
                if (j < 0) {
                    throw new IllegalStateException("n >= required but it was " + j);
                } else if (j != 0) {
                    this.c = true;
                    e eVar = this.a;
                    if (!eVar.b()) {
                        Object obj = this.b;
                        try {
                            eVar.a_(obj);
                            if (!eVar.b()) {
                                eVar.o_();
                            }
                        } catch (Throwable th) {
                            dji.thirdparty.f.c.b.a(th, eVar, obj);
                        }
                    }
                }
            }
        }
    }

    static <T> f a(k<? super T> kVar, T t) {
        if (c) {
            return new dji.thirdparty.f.e.b.f(kVar, t);
        }
        return new c(kVar, t);
    }

    public static <T> l<T> a(T t) {
        return new l(t);
    }

    protected l(T t) {
        super(new AnonymousClass1(t));
        this.d = t;
    }

    public T a() {
        return this.d;
    }

    public d<T> h(final g gVar) {
        o anonymousClass2;
        if (gVar instanceof dji.thirdparty.f.e.c.a) {
            final dji.thirdparty.f.e.c.a aVar = (dji.thirdparty.f.e.c.a) gVar;
            anonymousClass2 = new o<dji.thirdparty.f.d.b, dji.thirdparty.f.l>(this) {
                final /* synthetic */ l b;

                public dji.thirdparty.f.l a(dji.thirdparty.f.d.b bVar) {
                    return aVar.a(bVar);
                }
            };
        } else {
            anonymousClass2 = new o<dji.thirdparty.f.d.b, dji.thirdparty.f.l>(this) {
                final /* synthetic */ l b;

                public dji.thirdparty.f.l a(final dji.thirdparty.f.d.b bVar) {
                    final dji.thirdparty.f.l a = gVar.a();
                    a.a(new dji.thirdparty.f.d.b(this) {
                        final /* synthetic */ AnonymousClass3 c;

                        public void a() {
                            try {
                                bVar.a();
                            } finally {
                                a.n_();
                            }
                        }
                    });
                    return a;
                }
            };
        }
        return a(new a(this.d, anonymousClass2));
    }

    public <R> d<R> I(final o<? super T, ? extends d<? extends R>> oVar) {
        return a(new d$f<R>(this) {
            final /* synthetic */ l b;

            public void a(k<? super R> kVar) {
                d dVar = (d) oVar.a(this.b.d);
                if (dVar instanceof l) {
                    kVar.a(l.a(kVar, ((l) dVar).d));
                } else {
                    dVar.a(dji.thirdparty.f.g.e.a((k) kVar));
                }
            }
        });
    }
}
