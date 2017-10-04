package dji.thirdparty.f;

import dji.thirdparty.f.d.c;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.d.p;
import dji.thirdparty.f.d.r;
import dji.thirdparty.f.d.s;
import dji.thirdparty.f.d.t;
import dji.thirdparty.f.d.u;
import dji.thirdparty.f.d.v;
import dji.thirdparty.f.d.w;
import dji.thirdparty.f.d.x;
import dji.thirdparty.f.e.a.am;
import dji.thirdparty.f.e.a.ay;
import dji.thirdparty.f.e.a.bd;
import dji.thirdparty.f.e.a.be;
import dji.thirdparty.f.e.a.bg;
import dji.thirdparty.f.e.a.bh;
import dji.thirdparty.f.e.a.bn;
import dji.thirdparty.f.e.a.bt;
import dji.thirdparty.f.e.a.bx;
import dji.thirdparty.f.e.a.da;
import dji.thirdparty.f.e.a.dr;
import dji.thirdparty.f.e.a.ds;
import dji.thirdparty.f.e.a.dt;
import dji.thirdparty.f.e.b.e;
import dji.thirdparty.f.e.d.m;
import dji.thirdparty.f.e.d.q;
import dji.thirdparty.f.i.d;
import dji.thirdparty.f.i.f;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@dji.thirdparty.f.b.a
public class h<T> {
    static f b = d.getInstance().d();
    final d$f<T> a;

    public interface a<T> extends c<i<? super T>> {
    }

    public interface b<T, R> extends o<h<T>, h<R>> {
    }

    protected h(final a<T> aVar) {
        this.a = new d$f<T>(this) {
            final /* synthetic */ h b;

            public void a(final k<? super T> kVar) {
                final f eVar = new e(kVar);
                kVar.a(eVar);
                l anonymousClass1 = new i<T>(this) {
                    final /* synthetic */ AnonymousClass1 c;

                    public void a(T t) {
                        eVar.a((Object) t);
                    }

                    public void a(Throwable th) {
                        kVar.a(th);
                    }
                };
                kVar.a(anonymousClass1);
                aVar.a(anonymousClass1);
            }
        };
    }

    private h(d$f<T> dji_thirdparty_f_d_f_T) {
        this.a = dji_thirdparty_f_d_f_T;
    }

    public static <T> h<T> a(a<T> aVar) {
        return new h(b.a((a) aVar));
    }

    private <R> h<R> a(final d$g<? extends R, ? super T> dji_thirdparty_f_d_g__extends_R___super_T) {
        return new h(new d$f<R>(this) {
            final /* synthetic */ h b;

            public void a(k<? super R> kVar) {
                e eVar;
                try {
                    eVar = (k) h.b.a(dji_thirdparty_f_d_g__extends_R___super_T).a(kVar);
                    eVar.c();
                    this.b.a.a(eVar);
                } catch (Throwable th) {
                    dji.thirdparty.f.c.b.a(th, (e) kVar);
                }
            }
        });
    }

    public <R> h<R> a(b<? super T, ? extends R> bVar) {
        return (h) bVar.a(this);
    }

    private static <T> d<T> f(h<T> hVar) {
        return d.a(hVar.a);
    }

    private h<d<T>> e() {
        return a(f(this));
    }

    public static <T> d<T> a(h<? extends T> hVar, h<? extends T> hVar2) {
        return d.b(f((h) hVar), f((h) hVar2));
    }

    public static <T> d<T> a(h<? extends T> hVar, h<? extends T> hVar2, h<? extends T> hVar3) {
        return d.b(f((h) hVar), f((h) hVar2), f((h) hVar3));
    }

    public static <T> d<T> a(h<? extends T> hVar, h<? extends T> hVar2, h<? extends T> hVar3, h<? extends T> hVar4) {
        return d.b(f((h) hVar), f((h) hVar2), f((h) hVar3), f((h) hVar4));
    }

    public static <T> d<T> a(h<? extends T> hVar, h<? extends T> hVar2, h<? extends T> hVar3, h<? extends T> hVar4, h<? extends T> hVar5) {
        return d.b(f((h) hVar), f((h) hVar2), f((h) hVar3), f((h) hVar4), f((h) hVar5));
    }

    public static <T> d<T> a(h<? extends T> hVar, h<? extends T> hVar2, h<? extends T> hVar3, h<? extends T> hVar4, h<? extends T> hVar5, h<? extends T> hVar6) {
        return d.b(f((h) hVar), f((h) hVar2), f((h) hVar3), f((h) hVar4), f((h) hVar5), f((h) hVar6));
    }

    public static <T> d<T> a(h<? extends T> hVar, h<? extends T> hVar2, h<? extends T> hVar3, h<? extends T> hVar4, h<? extends T> hVar5, h<? extends T> hVar6, h<? extends T> hVar7) {
        return d.b(f((h) hVar), f((h) hVar2), f((h) hVar3), f((h) hVar4), f((h) hVar5), f((h) hVar6), f((h) hVar7));
    }

    public static <T> d<T> a(h<? extends T> hVar, h<? extends T> hVar2, h<? extends T> hVar3, h<? extends T> hVar4, h<? extends T> hVar5, h<? extends T> hVar6, h<? extends T> hVar7, h<? extends T> hVar8) {
        return d.b(f((h) hVar), f((h) hVar2), f((h) hVar3), f((h) hVar4), f((h) hVar5), f((h) hVar6), f((h) hVar7), f((h) hVar8));
    }

    public static <T> d<T> a(h<? extends T> hVar, h<? extends T> hVar2, h<? extends T> hVar3, h<? extends T> hVar4, h<? extends T> hVar5, h<? extends T> hVar6, h<? extends T> hVar7, h<? extends T> hVar8, h<? extends T> hVar9) {
        return d.b(f((h) hVar), f((h) hVar2), f((h) hVar3), f((h) hVar4), f((h) hVar5), f((h) hVar6), f((h) hVar7), f((h) hVar8), f((h) hVar9));
    }

    public static <T> h<T> a(final Throwable th) {
        return a(new a<T>() {
            public void a(i<? super T> iVar) {
                iVar.a(th);
            }
        });
    }

    public static <T> h<T> a(Future<? extends T> future) {
        return new h(am.a(future));
    }

    public static <T> h<T> a(Future<? extends T> future, long j, TimeUnit timeUnit) {
        return new h(am.a(future, j, timeUnit));
    }

    public static <T> h<T> a(Future<? extends T> future, g gVar) {
        return new h(am.a(future)).b(gVar);
    }

    @dji.thirdparty.f.b.b
    public static <T> h<T> a(final Callable<? extends T> callable) {
        return a(new a<T>() {
            public void a(i<? super T> iVar) {
                try {
                    iVar.a(callable.call());
                } catch (Throwable th) {
                    dji.thirdparty.f.c.b.b(th);
                    iVar.a(th);
                }
            }
        });
    }

    public static <T> h<T> a(T t) {
        return m.b(t);
    }

    public static <T> h<T> a(final h<? extends h<? extends T>> hVar) {
        if (hVar instanceof m) {
            return ((m) hVar).g(q.c());
        }
        return a(new a<T>() {
            public void a(final i<? super T> iVar) {
                hVar.a(new i<h<? extends T>>(this) {
                    final /* synthetic */ AnonymousClass21 b;

                    public void a(h<? extends T> hVar) {
                        hVar.a(iVar);
                    }

                    public void a(Throwable th) {
                        iVar.a(th);
                    }
                });
            }
        });
    }

    public static <T> d<T> b(h<? extends T> hVar, h<? extends T> hVar2) {
        return d.c(f((h) hVar), f((h) hVar2));
    }

    public static <T> d<T> b(h<? extends T> hVar, h<? extends T> hVar2, h<? extends T> hVar3) {
        return d.c(f((h) hVar), f((h) hVar2), f((h) hVar3));
    }

    public static <T> d<T> b(h<? extends T> hVar, h<? extends T> hVar2, h<? extends T> hVar3, h<? extends T> hVar4) {
        return d.c(f((h) hVar), f((h) hVar2), f((h) hVar3), f((h) hVar4));
    }

    public static <T> d<T> b(h<? extends T> hVar, h<? extends T> hVar2, h<? extends T> hVar3, h<? extends T> hVar4, h<? extends T> hVar5) {
        return d.c(f((h) hVar), f((h) hVar2), f((h) hVar3), f((h) hVar4), f((h) hVar5));
    }

    public static <T> d<T> b(h<? extends T> hVar, h<? extends T> hVar2, h<? extends T> hVar3, h<? extends T> hVar4, h<? extends T> hVar5, h<? extends T> hVar6) {
        return d.c(f((h) hVar), f((h) hVar2), f((h) hVar3), f((h) hVar4), f((h) hVar5), f((h) hVar6));
    }

    public static <T> d<T> b(h<? extends T> hVar, h<? extends T> hVar2, h<? extends T> hVar3, h<? extends T> hVar4, h<? extends T> hVar5, h<? extends T> hVar6, h<? extends T> hVar7) {
        return d.c(f((h) hVar), f((h) hVar2), f((h) hVar3), f((h) hVar4), f((h) hVar5), f((h) hVar6), f((h) hVar7));
    }

    public static <T> d<T> b(h<? extends T> hVar, h<? extends T> hVar2, h<? extends T> hVar3, h<? extends T> hVar4, h<? extends T> hVar5, h<? extends T> hVar6, h<? extends T> hVar7, h<? extends T> hVar8) {
        return d.c(f((h) hVar), f((h) hVar2), f((h) hVar3), f((h) hVar4), f((h) hVar5), f((h) hVar6), f((h) hVar7), f((h) hVar8));
    }

    public static <T> d<T> b(h<? extends T> hVar, h<? extends T> hVar2, h<? extends T> hVar3, h<? extends T> hVar4, h<? extends T> hVar5, h<? extends T> hVar6, h<? extends T> hVar7, h<? extends T> hVar8, h<? extends T> hVar9) {
        return d.c(f((h) hVar), f((h) hVar2), f((h) hVar3), f((h) hVar4), f((h) hVar5), f((h) hVar6), f((h) hVar7), f((h) hVar8), f((h) hVar9));
    }

    public static <T1, T2, R> h<R> a(h<? extends T1> hVar, h<? extends T2> hVar2, final p<? super T1, ? super T2, ? extends R> pVar) {
        return dt.a(new h[]{hVar, hVar2}, new x<R>() {
            public R a(Object... objArr) {
                return pVar.b(objArr[0], objArr[1]);
            }
        });
    }

    public static <T1, T2, T3, R> h<R> a(h<? extends T1> hVar, h<? extends T2> hVar2, h<? extends T3> hVar3, final dji.thirdparty.f.d.q<? super T1, ? super T2, ? super T3, ? extends R> qVar) {
        return dt.a(new h[]{hVar, hVar2, hVar3}, new x<R>() {
            public R a(Object... objArr) {
                return qVar.a(objArr[0], objArr[1], objArr[2]);
            }
        });
    }

    public static <T1, T2, T3, T4, R> h<R> a(h<? extends T1> hVar, h<? extends T2> hVar2, h<? extends T3> hVar3, h<? extends T4> hVar4, final r<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> rVar) {
        return dt.a(new h[]{hVar, hVar2, hVar3, hVar4}, new x<R>() {
            public R a(Object... objArr) {
                return rVar.a(objArr[0], objArr[1], objArr[2], objArr[3]);
            }
        });
    }

    public static <T1, T2, T3, T4, T5, R> h<R> a(h<? extends T1> hVar, h<? extends T2> hVar2, h<? extends T3> hVar3, h<? extends T4> hVar4, h<? extends T5> hVar5, final s<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> sVar) {
        return dt.a(new h[]{hVar, hVar2, hVar3, hVar4, hVar5}, new x<R>() {
            public R a(Object... objArr) {
                return sVar.a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            }
        });
    }

    public static <T1, T2, T3, T4, T5, T6, R> h<R> a(h<? extends T1> hVar, h<? extends T2> hVar2, h<? extends T3> hVar3, h<? extends T4> hVar4, h<? extends T5> hVar5, h<? extends T6> hVar6, final t<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> tVar) {
        return dt.a(new h[]{hVar, hVar2, hVar3, hVar4, hVar5, hVar6}, new x<R>() {
            public R a(Object... objArr) {
                return tVar.a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5]);
            }
        });
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> h<R> a(h<? extends T1> hVar, h<? extends T2> hVar2, h<? extends T3> hVar3, h<? extends T4> hVar4, h<? extends T5> hVar5, h<? extends T6> hVar6, h<? extends T7> hVar7, final u<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> uVar) {
        return dt.a(new h[]{hVar, hVar2, hVar3, hVar4, hVar5, hVar6, hVar7}, new x<R>() {
            public R a(Object... objArr) {
                return uVar.a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6]);
            }
        });
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> h<R> a(h<? extends T1> hVar, h<? extends T2> hVar2, h<? extends T3> hVar3, h<? extends T4> hVar4, h<? extends T5> hVar5, h<? extends T6> hVar6, h<? extends T7> hVar7, h<? extends T8> hVar8, final v<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> vVar) {
        return dt.a(new h[]{hVar, hVar2, hVar3, hVar4, hVar5, hVar6, hVar7, hVar8}, new x<R>() {
            public R a(Object... objArr) {
                return vVar.a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7]);
            }
        });
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> h<R> a(h<? extends T1> hVar, h<? extends T2> hVar2, h<? extends T3> hVar3, h<? extends T4> hVar4, h<? extends T5> hVar5, h<? extends T6> hVar6, h<? extends T7> hVar7, h<? extends T8> hVar8, h<? extends T9> hVar9, final w<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> wVar) {
        return dt.a(new h[]{hVar, hVar2, hVar3, hVar4, hVar5, hVar6, hVar7, hVar8, hVar9}, new x<R>() {
            public R a(Object... objArr) {
                return wVar.a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7], objArr[8]);
            }
        });
    }

    public static <R> h<R> a(Iterable<? extends h<?>> iterable, x<? extends R> xVar) {
        return dt.a(a((Iterable) iterable), xVar);
    }

    public final d<T> b(h<? extends T> hVar) {
        return a(this, (h) hVar);
    }

    public final <R> h<R> a(o<? super T, ? extends h<? extends R>> oVar) {
        if (this instanceof m) {
            return ((m) this).g(oVar);
        }
        return a(c((o) oVar));
    }

    public final <R> d<R> b(o<? super T, ? extends d<? extends R>> oVar) {
        return d.c(f(c((o) oVar)));
    }

    public final <R> h<R> c(o<? super T, ? extends R> oVar) {
        return a(new bn(oVar));
    }

    public final d<T> c(h<? extends T> hVar) {
        return b(this, (h) hVar);
    }

    public final h<T> a(g gVar) {
        if (this instanceof m) {
            return ((m) this).c(gVar);
        }
        return a(new bt(gVar, false));
    }

    public final h<T> d(o<Throwable, ? extends T> oVar) {
        return a(bx.a((o) oVar));
    }

    @dji.thirdparty.f.b.b
    public final h<T> d(h<? extends T> hVar) {
        return new h(ds.a(this, (h) hVar));
    }

    @dji.thirdparty.f.b.b
    public final h<T> e(o<Throwable, ? extends h<? extends T>> oVar) {
        return new h(ds.a(this, (o) oVar));
    }

    public final l a() {
        return b(new k<T>(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public final void o_() {
            }

            public final void a(Throwable th) {
                throw new dji.thirdparty.f.c.f(th);
            }

            public final void a_(T t) {
            }
        });
    }

    public final l a(final c<? super T> cVar) {
        if (cVar != null) {
            return b(new k<T>(this) {
                final /* synthetic */ h b;

                public final void o_() {
                }

                public final void a(Throwable th) {
                    throw new dji.thirdparty.f.c.f(th);
                }

                public final void a_(T t) {
                    cVar.a(t);
                }
            });
        }
        throw new IllegalArgumentException("onSuccess can not be null");
    }

    public final l a(final c<? super T> cVar, final c<Throwable> cVar2) {
        if (cVar == null) {
            throw new IllegalArgumentException("onSuccess can not be null");
        } else if (cVar2 != null) {
            return b(new k<T>(this) {
                final /* synthetic */ h c;

                public final void o_() {
                }

                public final void a(Throwable th) {
                    cVar2.a(th);
                }

                public final void a_(T t) {
                    cVar.a(t);
                }
            });
        } else {
            throw new IllegalArgumentException("onError can not be null");
        }
    }

    public final l a(k<? super T> kVar) {
        try {
            kVar.c();
            b.a(this, this.a).a(kVar);
            return b.a((l) kVar);
        } catch (Throwable th) {
            dji.thirdparty.f.c.b.b(th);
            b.a(new RuntimeException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th));
        }
    }

    public final l a(final e<? super T> eVar) {
        if (eVar != null) {
            return a(new i<T>(this) {
                final /* synthetic */ h b;

                public void a(T t) {
                    eVar.a_(t);
                    eVar.o_();
                }

                public void a(Throwable th) {
                    eVar.a(th);
                }
            });
        }
        throw new NullPointerException("observer is null");
    }

    public final l b(k<? super T> kVar) {
        if (kVar == null) {
            throw new IllegalArgumentException("observer can not be null");
        } else if (this.a == null) {
            throw new IllegalStateException("onSubscribe function can not be null.");
        } else {
            l bVar;
            kVar.c();
            if (!(kVar instanceof dji.thirdparty.f.g.b)) {
                bVar = new dji.thirdparty.f.g.b(kVar);
            }
            try {
                b.a(this, this.a).a(bVar);
                return b.a(bVar);
            } catch (Throwable th) {
                dji.thirdparty.f.c.b.b(th);
                b.a(new RuntimeException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th));
            }
        }
    }

    public final l a(final i<? super T> iVar) {
        k anonymousClass10 = new k<T>(this) {
            final /* synthetic */ h b;

            public void o_() {
            }

            public void a(Throwable th) {
                iVar.a(th);
            }

            public void a_(T t) {
                iVar.a((Object) t);
            }
        };
        iVar.a((l) anonymousClass10);
        b(anonymousClass10);
        return anonymousClass10;
    }

    public final h<T> b(final g gVar) {
        if (this instanceof m) {
            return ((m) this).c(gVar);
        }
        return a(new a<T>(this) {
            final /* synthetic */ h b;

            public void a(final i<? super T> iVar) {
                final l a = gVar.a();
                iVar.a(a);
                a.a(new dji.thirdparty.f.d.b(this) {
                    final /* synthetic */ AnonymousClass11 c;

                    public void a() {
                        i anonymousClass1 = new i<T>(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void a(T t) {
                                try {
                                    iVar.a((Object) t);
                                } finally {
                                    a.n_();
                                }
                            }

                            public void a(Throwable th) {
                                try {
                                    iVar.a(th);
                                } finally {
                                    a.n_();
                                }
                            }
                        };
                        iVar.a((l) anonymousClass1);
                        this.c.b.a(anonymousClass1);
                    }
                });
            }
        });
    }

    public final h<T> a(final b bVar) {
        return a(new d$g<T, T>(this) {
            final /* synthetic */ h b;

            public k<? super T> a(k<? super T> kVar) {
                final l dVar = new dji.thirdparty.f.g.d(kVar, false);
                final l anonymousClass1 = new k<T>(this, false, dVar) {
                    final /* synthetic */ AnonymousClass13 b;

                    public void a_(T t) {
                        dVar.a_(t);
                    }

                    public void a(Throwable th) {
                        try {
                            dVar.a(th);
                        } finally {
                            dVar.n_();
                        }
                    }

                    public void o_() {
                        try {
                            dVar.o_();
                        } finally {
                            dVar.n_();
                        }
                    }
                };
                b$c anonymousClass2 = new b$c(this) {
                    final /* synthetic */ AnonymousClass13 c;

                    public void b() {
                        a(new CancellationException("Stream was canceled before emitting a terminal event."));
                    }

                    public void a(Throwable th) {
                        anonymousClass1.a(th);
                    }

                    public void a(l lVar) {
                        dVar.a(lVar);
                    }
                };
                dVar.a(anonymousClass1);
                kVar.a(dVar);
                bVar.a(anonymousClass2);
                return anonymousClass1;
            }
        });
    }

    public final <E> h<T> a(final d<? extends E> dVar) {
        return a(new d$g<T, T>(this) {
            final /* synthetic */ h b;

            public k<? super T> a(k<? super T> kVar) {
                final l dVar = new dji.thirdparty.f.g.d(kVar, false);
                final l anonymousClass1 = new k<T>(this, false, dVar) {
                    final /* synthetic */ AnonymousClass14 b;

                    public void a_(T t) {
                        dVar.a_(t);
                    }

                    public void a(Throwable th) {
                        try {
                            dVar.a(th);
                        } finally {
                            dVar.n_();
                        }
                    }

                    public void o_() {
                        try {
                            dVar.o_();
                        } finally {
                            dVar.n_();
                        }
                    }
                };
                l anonymousClass2 = new k<E>(this) {
                    final /* synthetic */ AnonymousClass14 b;

                    public void o_() {
                        a(new CancellationException("Stream was canceled before emitting a terminal event."));
                    }

                    public void a(Throwable th) {
                        anonymousClass1.a(th);
                    }

                    public void a_(E e) {
                        a(new CancellationException("Stream was canceled before emitting a terminal event."));
                    }
                };
                dVar.a(anonymousClass1);
                dVar.a(anonymousClass2);
                kVar.a(dVar);
                dVar.a(anonymousClass2);
                return anonymousClass1;
            }
        });
    }

    public final <E> h<T> e(final h<? extends E> hVar) {
        return a(new d$g<T, T>(this) {
            final /* synthetic */ h b;

            public k<? super T> a(k<? super T> kVar) {
                final l dVar = new dji.thirdparty.f.g.d(kVar, false);
                final l anonymousClass1 = new k<T>(this, false, dVar) {
                    final /* synthetic */ AnonymousClass15 b;

                    public void a_(T t) {
                        dVar.a_(t);
                    }

                    public void a(Throwable th) {
                        try {
                            dVar.a(th);
                        } finally {
                            dVar.n_();
                        }
                    }

                    public void o_() {
                        try {
                            dVar.o_();
                        } finally {
                            dVar.n_();
                        }
                    }
                };
                i anonymousClass2 = new i<E>(this) {
                    final /* synthetic */ AnonymousClass15 b;

                    public void a(E e) {
                        a(new CancellationException("Stream was canceled before emitting a terminal event."));
                    }

                    public void a(Throwable th) {
                        anonymousClass1.a(th);
                    }
                };
                dVar.a(anonymousClass1);
                dVar.a((l) anonymousClass2);
                kVar.a(dVar);
                hVar.a(anonymousClass2);
                return anonymousClass1;
            }
        });
    }

    public final d<T> b() {
        return f(this);
    }

    public final h<T> a(long j, TimeUnit timeUnit) {
        return a(j, timeUnit, null, dji.thirdparty.f.j.e.d());
    }

    public final h<T> a(long j, TimeUnit timeUnit, g gVar) {
        return a(j, timeUnit, null, gVar);
    }

    public final h<T> a(long j, TimeUnit timeUnit, h<? extends T> hVar) {
        return a(j, timeUnit, (h) hVar, dji.thirdparty.f.j.e.d());
    }

    public final h<T> a(long j, TimeUnit timeUnit, h<? extends T> hVar, g gVar) {
        h a;
        if (hVar == null) {
            a = a(new TimeoutException());
        }
        return a(new da(j, timeUnit, f(a), gVar));
    }

    @dji.thirdparty.f.b.b
    public final dji.thirdparty.f.k.a<T> c() {
        return dji.thirdparty.f.k.a.a(this);
    }

    public final <T2, R> h<R> a(h<? extends T2> hVar, p<? super T, ? super T2, ? extends R> pVar) {
        return a(this, (h) hVar, (p) pVar);
    }

    @dji.thirdparty.f.b.b
    public final h<T> b(final c<Throwable> cVar) {
        return a(new be(new e<T>(this) {
            final /* synthetic */ h b;

            public void o_() {
            }

            public void a(Throwable th) {
                cVar.a(th);
            }

            public void a_(T t) {
            }
        }));
    }

    @dji.thirdparty.f.b.b
    public final h<T> c(final c<? super T> cVar) {
        return a(new be(new e<T>(this) {
            final /* synthetic */ h b;

            public void o_() {
            }

            public void a(Throwable th) {
            }

            public void a_(T t) {
                cVar.a(t);
            }
        }));
    }

    @dji.thirdparty.f.b.b
    public final h<T> a(dji.thirdparty.f.d.b bVar) {
        return a(new bg(bVar));
    }

    @dji.thirdparty.f.b.b
    public final h<T> b(long j, TimeUnit timeUnit, g gVar) {
        return a(new ay(j, timeUnit, gVar));
    }

    @dji.thirdparty.f.b.b
    public final h<T> b(long j, TimeUnit timeUnit) {
        return b(j, timeUnit, dji.thirdparty.f.j.e.d());
    }

    @dji.thirdparty.f.b.b
    public static <T> h<T> b(final Callable<h<T>> callable) {
        return a(new a<T>() {
            public void a(i<? super T> iVar) {
                try {
                    ((h) callable.call()).a((i) iVar);
                } catch (Throwable th) {
                    dji.thirdparty.f.c.b.b(th);
                    iVar.a(th);
                }
            }
        });
    }

    @dji.thirdparty.f.b.b
    public final h<T> b(dji.thirdparty.f.d.b bVar) {
        return a(new bh(bVar));
    }

    @dji.thirdparty.f.b.b
    public final h<T> c(dji.thirdparty.f.d.b bVar) {
        return a(new bd(bVar));
    }

    static <T> h<? extends T>[] a(Iterable<? extends h<? extends T>> iterable) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            return (h[]) collection.toArray(new h[collection.size()]);
        }
        Object obj = new h[8];
        int i = 0;
        Object obj2 = obj;
        for (h hVar : iterable) {
            if (i == obj2.length) {
                Object obj3 = new h[((i >> 2) + i)];
                System.arraycopy(obj2, 0, obj3, 0, i);
                obj2 = obj3;
            }
            obj2[i] = hVar;
            i++;
        }
        if (obj2.length == i) {
            return obj2;
        }
        h<? extends T>[] hVarArr = new h[i];
        System.arraycopy(obj2, 0, hVarArr, 0, i);
        return hVarArr;
    }

    public final h<T> d() {
        return b().y().b();
    }

    public final h<T> a(long j) {
        return b().c(j).b();
    }

    public final h<T> a(p<Integer, Throwable, Boolean> pVar) {
        return b().b(pVar).b();
    }

    public final h<T> f(o<d<? extends Throwable>, ? extends d<?>> oVar) {
        return b().x(oVar).b();
    }

    @dji.thirdparty.f.b.b
    public static <T, Resource> h<T> a(n<Resource> nVar, o<? super Resource, ? extends h<? extends T>> oVar, c<? super Resource> cVar) {
        return a((n) nVar, (o) oVar, (c) cVar, false);
    }

    @dji.thirdparty.f.b.b
    public static <T, Resource> h<T> a(n<Resource> nVar, o<? super Resource, ? extends h<? extends T>> oVar, c<? super Resource> cVar, boolean z) {
        if (nVar == null) {
            throw new NullPointerException("resourceFactory is null");
        } else if (oVar == null) {
            throw new NullPointerException("singleFactory is null");
        } else if (cVar != null) {
            return a(new dr(nVar, oVar, cVar, z));
        } else {
            throw new NullPointerException("disposeAction is null");
        }
    }
}
