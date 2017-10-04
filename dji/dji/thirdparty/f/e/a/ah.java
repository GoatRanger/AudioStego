package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.c;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.d.p;
import dji.thirdparty.f.f;
import dji.thirdparty.f.g;
import dji.thirdparty.f.j.e;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public final class ah<T> implements d$f<T> {
    static final o<d<? extends c<?>>, d<?>> a = new o<d<? extends c<?>>, d<?>>() {
        public d<?> a(d<? extends c<?>> dVar) {
            return dVar.r(new o<c<?>, c<?>>(this) {
                final /* synthetic */ AnonymousClass1 a;

                {
                    this.a = r1;
                }

                public c<?> a(c<?> cVar) {
                    return c.a(null);
                }
            });
        }
    };
    final d<T> b;
    final boolean c;
    final boolean d;
    private final o<? super d<? extends c<?>>, ? extends d<?>> e;
    private final g f;

    public static final class a implements o<d<? extends c<?>>, d<?>> {
        final long a;

        public a(long j) {
            this.a = j;
        }

        public d<?> a(d<? extends c<?>> dVar) {
            return dVar.r(new o<c<?>, c<?>>(this) {
                int a = 0;
                final /* synthetic */ a b;

                {
                    this.b = r2;
                }

                public c<?> a(c<?> cVar) {
                    if (this.b.a == 0) {
                        return cVar;
                    }
                    this.a++;
                    if (((long) this.a) <= this.b.a) {
                        return c.a(Integer.valueOf(this.a));
                    }
                    return cVar;
                }
            }).k();
        }
    }

    public static final class b implements o<d<? extends c<?>>, d<? extends c<?>>> {
        final p<Integer, Throwable, Boolean> a;

        public b(p<Integer, Throwable, Boolean> pVar) {
            this.a = pVar;
        }

        public d<? extends c<?>> a(d<? extends c<?>> dVar) {
            return dVar.b(c.a(Integer.valueOf(0)), new p<c<Integer>, c<?>, c<Integer>>(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public /* synthetic */ Object b(Object obj, Object obj2) {
                    return a((c) obj, (c) obj2);
                }

                public c<Integer> a(c<Integer> cVar, c<?> cVar2) {
                    int intValue = ((Integer) cVar.c()).intValue();
                    if (((Boolean) this.a.a.b(Integer.valueOf(intValue), cVar2.b())).booleanValue()) {
                        return c.a(Integer.valueOf(intValue + 1));
                    }
                    return cVar2;
                }
            });
        }
    }

    public static <T> d<T> a(d<T> dVar) {
        return a((d) dVar, a);
    }

    public static <T> d<T> a(d<T> dVar, long j) {
        if (j >= 0) {
            return j == 0 ? dVar : a((d) dVar, new a(j));
        } else {
            throw new IllegalArgumentException("count >= 0 expected");
        }
    }

    public static <T> d<T> a(d<T> dVar, o<? super d<? extends c<?>>, ? extends d<?>> oVar) {
        return d.a(new ah(dVar, oVar, true, false, e.b()));
    }

    public static <T> d<T> a(d<T> dVar, o<? super d<? extends c<?>>, ? extends d<?>> oVar, g gVar) {
        return d.a(new ah(dVar, oVar, true, false, gVar));
    }

    public static <T> d<T> b(d<T> dVar) {
        return a((d) dVar, e.b());
    }

    public static <T> d<T> a(d<T> dVar, g gVar) {
        return b(dVar, a, gVar);
    }

    public static <T> d<T> b(d<T> dVar, long j) {
        return a((d) dVar, j, e.b());
    }

    public static <T> d<T> a(d<T> dVar, long j, g gVar) {
        if (j == 0) {
            return d.d();
        }
        if (j >= 0) {
            return b(dVar, new a(j - 1), gVar);
        }
        throw new IllegalArgumentException("count >= 0 expected");
    }

    public static <T> d<T> b(d<T> dVar, o<? super d<? extends c<?>>, ? extends d<?>> oVar) {
        return d.a(new ah(dVar, oVar, false, true, e.b()));
    }

    public static <T> d<T> b(d<T> dVar, o<? super d<? extends c<?>>, ? extends d<?>> oVar, g gVar) {
        return d.a(new ah(dVar, oVar, false, true, gVar));
    }

    public static <T> d<T> c(d<T> dVar, o<? super d<? extends c<?>>, ? extends d<?>> oVar, g gVar) {
        return d.a(new ah(dVar, oVar, false, false, gVar));
    }

    private ah(d<T> dVar, o<? super d<? extends c<?>>, ? extends d<?>> oVar, boolean z, boolean z2, g gVar) {
        this.b = dVar;
        this.e = oVar;
        this.c = z;
        this.d = z2;
        this.f = gVar;
    }

    public void a(k<? super T> kVar) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        final AtomicLong atomicLong = new AtomicLong();
        final l a = this.f.a();
        kVar.a(a);
        final l eVar = new dji.thirdparty.f.m.e();
        kVar.a(eVar);
        final dji.thirdparty.f.l.b I = dji.thirdparty.f.l.b.I();
        I.b(dji.thirdparty.f.g.e.a());
        final dji.thirdparty.f.e.b.a aVar = new dji.thirdparty.f.e.b.a();
        final k<? super T> kVar2 = kVar;
        AnonymousClass2 anonymousClass2 = new dji.thirdparty.f.d.b(this) {
            final /* synthetic */ ah f;

            public void a() {
                if (!kVar2.b()) {
                    Object anonymousClass1 = new k<T>(this) {
                        boolean a;
                        final /* synthetic */ AnonymousClass2 b;

                        {
                            this.b = r1;
                        }

                        public void o_() {
                            if (!this.a) {
                                this.a = true;
                                n_();
                                I.a_(c.a());
                            }
                        }

                        public void a(Throwable th) {
                            if (!this.a) {
                                this.a = true;
                                n_();
                                I.a_(c.a(th));
                            }
                        }

                        public void a_(T t) {
                            if (!this.a) {
                                kVar2.a_(t);
                                d();
                                aVar.b(1);
                            }
                        }

                        private void d() {
                            long j;
                            do {
                                j = atomicLong.get();
                                if (j == IPositioningSession.NotSet) {
                                    return;
                                }
                            } while (!atomicLong.compareAndSet(j, j - 1));
                        }

                        public void a(f fVar) {
                            aVar.a(fVar);
                        }
                    };
                    eVar.a(anonymousClass1);
                    this.f.b.a(anonymousClass1);
                }
            }
        };
        final d dVar = (d) this.e.a(I.a(new d$g<c<?>, c<?>>(this) {
            final /* synthetic */ ah a;

            {
                this.a = r1;
            }

            public k<? super c<?>> a(final k<? super c<?>> kVar) {
                return new k<c<?>>(this, kVar) {
                    final /* synthetic */ AnonymousClass3 b;

                    public /* synthetic */ void a_(Object obj) {
                        a((c) obj);
                    }

                    public void o_() {
                        kVar.o_();
                    }

                    public void a(Throwable th) {
                        kVar.a(th);
                    }

                    public void a(c<?> cVar) {
                        if (cVar.h() && this.b.a.c) {
                            kVar.o_();
                        } else if (cVar.g() && this.b.a.d) {
                            kVar.a(cVar.b());
                        } else {
                            kVar.a_(cVar);
                        }
                    }

                    public void a(f fVar) {
                        fVar.a(IPositioningSession.NotSet);
                    }
                };
            }
        }));
        final k<? super T> kVar3 = kVar;
        final AtomicLong atomicLong2 = atomicLong;
        final AnonymousClass2 anonymousClass22 = anonymousClass2;
        a.a(new dji.thirdparty.f.d.b(this) {
            final /* synthetic */ ah g;

            public void a() {
                dVar.a(new k<Object>(this, kVar3) {
                    final /* synthetic */ AnonymousClass4 a;

                    public void o_() {
                        kVar3.o_();
                    }

                    public void a(Throwable th) {
                        kVar3.a(th);
                    }

                    public void a_(Object obj) {
                        if (!kVar3.b()) {
                            if (atomicLong2.get() > 0) {
                                a.a(anonymousClass22);
                            } else {
                                atomicBoolean.compareAndSet(false, true);
                            }
                        }
                    }

                    public void a(f fVar) {
                        fVar.a(IPositioningSession.NotSet);
                    }
                });
            }
        });
        final AtomicLong atomicLong3 = atomicLong;
        final dji.thirdparty.f.e.b.a aVar2 = aVar;
        final AtomicBoolean atomicBoolean2 = atomicBoolean;
        anonymousClass22 = anonymousClass2;
        kVar.a(new f(this) {
            final /* synthetic */ ah f;

            public void a(long j) {
                if (j > 0) {
                    a.a(atomicLong3, j);
                    aVar2.a(j);
                    if (atomicBoolean2.compareAndSet(true, false)) {
                        a.a(anonymousClass22);
                    }
                }
            }
        });
    }
}
