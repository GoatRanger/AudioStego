package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.p;
import dji.thirdparty.f.d.q;
import dji.thirdparty.f.d.r;
import dji.thirdparty.f.d.s;
import dji.thirdparty.f.d.t;
import dji.thirdparty.f.d.u;
import dji.thirdparty.f.d.v;
import dji.thirdparty.f.d.w;
import dji.thirdparty.f.d.x;
import dji.thirdparty.f.d.z;
import dji.thirdparty.f.e;
import dji.thirdparty.f.e.d.j;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicLong;

public final class dp<R> implements d$g<R, d<?>[]> {
    final x<? extends R> a;

    static final class a<R> extends AtomicLong {
        static final int b = ((int) (((double) j.c) * 0.7d));
        private static final long d = 5995274816189928317L;
        final e<? super R> a;
        int c = 0;
        private final x<? extends R> e;
        private final dji.thirdparty.f.m.b f = new dji.thirdparty.f.m.b();
        private volatile Object[] g;
        private AtomicLong h;

        final class a extends k {
            final j a = j.d();
            final /* synthetic */ a b;

            a(a aVar) {
                this.b = aVar;
            }

            public void c() {
                a((long) j.c);
            }

            public void b(long j) {
                a(j);
            }

            public void o_() {
                this.a.f();
                this.b.a();
            }

            public void a(Throwable th) {
                this.b.a.a(th);
            }

            public void a_(Object obj) {
                try {
                    this.a.a(obj);
                } catch (Throwable e) {
                    a(e);
                }
                this.b.a();
            }
        }

        public a(k<? super R> kVar, x<? extends R> xVar) {
            this.a = kVar;
            this.e = xVar;
            kVar.a(this.f);
        }

        public void a(d[] dVarArr, AtomicLong atomicLong) {
            int i = 0;
            Object[] objArr = new Object[dVarArr.length];
            for (int i2 = 0; i2 < dVarArr.length; i2++) {
                l aVar = new a(this);
                objArr[i2] = aVar;
                this.f.a(aVar);
            }
            this.h = atomicLong;
            this.g = objArr;
            while (i < dVarArr.length) {
                dVarArr[i].a((a) objArr[i]);
                i++;
            }
        }

        void a() {
            Object[] objArr = this.g;
            if (objArr != null && getAndIncrement() == 0) {
                int length = objArr.length;
                e eVar = this.a;
                AtomicLong atomicLong = this.h;
                while (true) {
                    j jVar;
                    Object obj = new Object[length];
                    int i = 1;
                    int i2 = 0;
                    while (i2 < length) {
                        int i3;
                        jVar = ((a) objArr[i2]).a;
                        Object l = jVar.l();
                        if (l == null) {
                            i3 = 0;
                        } else if (jVar.b(l)) {
                            eVar.o_();
                            this.f.n_();
                            return;
                        } else {
                            obj[i2] = jVar.d(l);
                            i3 = i;
                        }
                        i2++;
                        i = i3;
                    }
                    if (atomicLong.get() > 0 && i != 0) {
                        try {
                            eVar.a_(this.e.a(obj));
                            atomicLong.decrementAndGet();
                            this.c++;
                            for (Object obj2 : objArr) {
                                jVar = ((a) obj2).a;
                                jVar.k();
                                if (jVar.b(jVar.l())) {
                                    eVar.o_();
                                    this.f.n_();
                                    return;
                                }
                            }
                            if (this.c > b) {
                                for (Object obj22 : objArr) {
                                    ((a) obj22).b((long) this.c);
                                }
                                this.c = 0;
                            }
                        } catch (Throwable th) {
                            dji.thirdparty.f.c.b.a(th, eVar, obj);
                            return;
                        }
                    } else if (decrementAndGet() <= 0) {
                        return;
                    }
                }
            }
        }
    }

    private static final class b<R> extends AtomicLong implements f {
        private static final long a = -1216676403723546796L;
        private a<R> b;

        public b(a<R> aVar) {
            this.b = aVar;
        }

        public void a(long j) {
            a.a((AtomicLong) this, j);
            this.b.a();
        }
    }

    private final class c extends k<d[]> {
        final k<? super R> a;
        final a<R> b;
        final b<R> c;
        boolean d = false;
        final /* synthetic */ dp e;

        public /* synthetic */ void a_(Object obj) {
            a((d[]) obj);
        }

        public c(dp dpVar, k<? super R> kVar, a<R> aVar, b<R> bVar) {
            this.e = dpVar;
            this.a = kVar;
            this.b = aVar;
            this.c = bVar;
        }

        public void o_() {
            if (!this.d) {
                this.a.o_();
            }
        }

        public void a(Throwable th) {
            this.a.a(th);
        }

        public void a(d[] dVarArr) {
            if (dVarArr == null || dVarArr.length == 0) {
                this.a.o_();
                return;
            }
            this.d = true;
            this.b.a(dVarArr, this.c);
        }
    }

    public dp(x<? extends R> xVar) {
        this.a = xVar;
    }

    public dp(p pVar) {
        this.a = z.a(pVar);
    }

    public dp(q qVar) {
        this.a = z.a(qVar);
    }

    public dp(r rVar) {
        this.a = z.a(rVar);
    }

    public dp(s sVar) {
        this.a = z.a(sVar);
    }

    public dp(t tVar) {
        this.a = z.a(tVar);
    }

    public dp(u uVar) {
        this.a = z.a(uVar);
    }

    public dp(v vVar) {
        this.a = z.a(vVar);
    }

    public dp(w wVar) {
        this.a = z.a(wVar);
    }

    public k<? super d[]> a(k<? super R> kVar) {
        a aVar = new a(kVar, this.a);
        f bVar = new b(aVar);
        l cVar = new c(this, kVar, aVar, bVar);
        kVar.a(cVar);
        kVar.a(bVar);
        return cVar;
    }
}
