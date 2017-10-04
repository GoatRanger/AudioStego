package dji.thirdparty.f.f;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.c;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.d.p;
import dji.thirdparty.f.f;
import dji.thirdparty.f.i.d;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicLong;

@dji.thirdparty.f.b.b
public abstract class e<S, T> implements d$f<T> {

    private static class a<S, T> extends AtomicLong implements dji.thirdparty.f.e<T>, f, l {
        private static final long a = -3736864024352728072L;
        private final k<? super T> b;
        private final e<S, T> c;
        private boolean d;
        private boolean e;
        private S f;

        a(k<? super T> kVar, e<S, T> eVar, S s) {
            this.b = kVar;
            this.c = eVar;
            this.f = s;
        }

        public boolean b() {
            return get() < 0;
        }

        public void n_() {
            long j;
            do {
                j = get();
                if (compareAndSet(0, -1)) {
                    d();
                    return;
                }
            } while (!compareAndSet(j, -2));
        }

        private boolean c() {
            if (!this.e && get() >= -1) {
                return false;
            }
            set(-1);
            d();
            return true;
        }

        private void d() {
            try {
                this.c.b(this.f);
            } catch (Throwable th) {
                dji.thirdparty.f.c.b.b(th);
                d.getInstance().b().a(th);
            }
        }

        public void a(long j) {
            if (j > 0 && dji.thirdparty.f.e.a.a.a((AtomicLong) this, j) == 0) {
                if (j == IPositioningSession.NotSet) {
                    e();
                } else {
                    b(j);
                }
            }
        }

        private void e() {
            e eVar = this.c;
            k kVar = this.b;
            do {
                try {
                    this.d = false;
                    a(eVar);
                } catch (Throwable th) {
                    a(kVar, th);
                    return;
                }
            } while (!c());
        }

        private void a(k<? super T> kVar, Throwable th) {
            if (this.e) {
                d.getInstance().b().a(th);
                return;
            }
            this.e = true;
            kVar.a(th);
            n_();
        }

        private void b(long j) {
            e eVar = this.c;
            k kVar = this.b;
            do {
                long j2 = j;
                do {
                    try {
                        this.d = false;
                        a(eVar);
                        if (!c()) {
                            if (this.d) {
                                j2--;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        a(kVar, th);
                        return;
                    }
                } while (j2 != 0);
                j = addAndGet(-j);
            } while (j > 0);
            c();
        }

        private void a(e<S, T> eVar) {
            this.f = eVar.a(this.f, (dji.thirdparty.f.e) this);
        }

        public void o_() {
            if (this.e) {
                throw new IllegalStateException("Terminal event already emitted.");
            }
            this.e = true;
            if (!this.b.b()) {
                this.b.o_();
            }
        }

        public void a(Throwable th) {
            if (this.e) {
                throw new IllegalStateException("Terminal event already emitted.");
            }
            this.e = true;
            if (!this.b.b()) {
                this.b.a(th);
            }
        }

        public void a_(T t) {
            if (this.d) {
                throw new IllegalStateException("onNext called multiple times!");
            }
            this.d = true;
            this.b.a_(t);
        }
    }

    private static final class b<S, T> extends e<S, T> {
        private final n<? extends S> a;
        private final p<? super S, ? super dji.thirdparty.f.e<? super T>, ? extends S> b;
        private final c<? super S> c;

        public /* bridge */ /* synthetic */ void a(Object obj) {
            super.a((k) obj);
        }

        b(n<? extends S> nVar, p<? super S, ? super dji.thirdparty.f.e<? super T>, ? extends S> pVar, c<? super S> cVar) {
            this.a = nVar;
            this.b = pVar;
            this.c = cVar;
        }

        public b(n<? extends S> nVar, p<? super S, ? super dji.thirdparty.f.e<? super T>, ? extends S> pVar) {
            this(nVar, pVar, null);
        }

        public b(p<S, dji.thirdparty.f.e<? super T>, S> pVar, c<? super S> cVar) {
            this(null, pVar, cVar);
        }

        public b(p<S, dji.thirdparty.f.e<? super T>, S> pVar) {
            this(null, pVar, null);
        }

        protected S a() {
            return this.a == null ? null : this.a.call();
        }

        protected S a(S s, dji.thirdparty.f.e<? super T> eVar) {
            return this.b.b(s, eVar);
        }

        protected void b(S s) {
            if (this.c != null) {
                this.c.a(s);
            }
        }
    }

    protected abstract S a();

    protected abstract S a(S s, dji.thirdparty.f.e<? super T> eVar);

    public final void a(k<? super T> kVar) {
        try {
            f aVar = new a(kVar, this, a());
            kVar.a((l) aVar);
            kVar.a(aVar);
        } catch (Throwable th) {
            dji.thirdparty.f.c.b.b(th);
            kVar.a(th);
        }
    }

    protected void b(S s) {
    }

    @dji.thirdparty.f.b.b
    public static <S, T> e<S, T> a(n<? extends S> nVar, final dji.thirdparty.f.d.d<? super S, ? super dji.thirdparty.f.e<? super T>> dVar) {
        return new b((n) nVar, new p<S, dji.thirdparty.f.e<? super T>, S>() {
            public /* synthetic */ Object b(Object obj, Object obj2) {
                return a(obj, (dji.thirdparty.f.e) obj2);
            }

            public S a(S s, dji.thirdparty.f.e<? super T> eVar) {
                dVar.a(s, eVar);
                return s;
            }
        });
    }

    @dji.thirdparty.f.b.b
    public static <S, T> e<S, T> a(n<? extends S> nVar, final dji.thirdparty.f.d.d<? super S, ? super dji.thirdparty.f.e<? super T>> dVar, c<? super S> cVar) {
        return new b(nVar, new p<S, dji.thirdparty.f.e<? super T>, S>() {
            public /* synthetic */ Object b(Object obj, Object obj2) {
                return a(obj, (dji.thirdparty.f.e) obj2);
            }

            public S a(S s, dji.thirdparty.f.e<? super T> eVar) {
                dVar.a(s, eVar);
                return s;
            }
        }, cVar);
    }

    @dji.thirdparty.f.b.b
    public static <S, T> e<S, T> a(n<? extends S> nVar, p<? super S, ? super dji.thirdparty.f.e<? super T>, ? extends S> pVar, c<? super S> cVar) {
        return new b(nVar, pVar, cVar);
    }

    @dji.thirdparty.f.b.b
    public static <S, T> e<S, T> a(n<? extends S> nVar, p<? super S, ? super dji.thirdparty.f.e<? super T>, ? extends S> pVar) {
        return new b((n) nVar, (p) pVar);
    }

    @dji.thirdparty.f.b.b
    public static <T> e<Void, T> a(final c<? super dji.thirdparty.f.e<? super T>> cVar) {
        return new b(new p<Void, dji.thirdparty.f.e<? super T>, Void>() {
            public /* synthetic */ Object b(Object obj, Object obj2) {
                return a((Void) obj, (dji.thirdparty.f.e) obj2);
            }

            public Void a(Void voidR, dji.thirdparty.f.e<? super T> eVar) {
                cVar.a(eVar);
                return voidR;
            }
        });
    }

    @dji.thirdparty.f.b.b
    public static <T> e<Void, T> a(final c<? super dji.thirdparty.f.e<? super T>> cVar, final dji.thirdparty.f.d.b bVar) {
        return new b(new p<Void, dji.thirdparty.f.e<? super T>, Void>() {
            public /* synthetic */ Object b(Object obj, Object obj2) {
                return a((Void) obj, (dji.thirdparty.f.e) obj2);
            }

            public Void a(Void voidR, dji.thirdparty.f.e<? super T> eVar) {
                cVar.a(eVar);
                return null;
            }
        }, new c<Void>() {
            public void a(Void voidR) {
                bVar.a();
            }
        });
    }
}
