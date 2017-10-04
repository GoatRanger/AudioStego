package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.q;
import dji.thirdparty.f.d.r;
import dji.thirdparty.f.f;
import dji.thirdparty.f.g;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.e;
import java.util.concurrent.TimeoutException;

class db<T> implements d$g<T, T> {
    final a<T> a;
    final b<T> b;
    final d<? extends T> c;
    final g d;

    interface a<T> extends q<c<T>, Long, dji.thirdparty.f.g.a, l> {
    }

    interface b<T> extends r<c<T>, Long, T, dji.thirdparty.f.g.a, l> {
    }

    static final class c<T> extends k<T> {
        final e a;
        final dji.thirdparty.f.g.d<T> b;
        final b<T> c;
        final d<? extends T> d;
        final dji.thirdparty.f.g.a e;
        final dji.thirdparty.f.e.b.a f = new dji.thirdparty.f.e.b.a();
        boolean g;
        long h;

        c(dji.thirdparty.f.g.d<T> dVar, b<T> bVar, e eVar, d<? extends T> dVar2, dji.thirdparty.f.g.a aVar) {
            this.b = dVar;
            this.c = bVar;
            this.a = eVar;
            this.d = dVar2;
            this.e = aVar;
        }

        public void a(f fVar) {
            this.f.a(fVar);
        }

        public void a_(T t) {
            Object obj = null;
            synchronized (this) {
                long j;
                if (this.g) {
                    j = this.h;
                } else {
                    j = this.h + 1;
                    this.h = j;
                    obj = 1;
                }
            }
            if (obj != null) {
                this.b.a_(t);
                this.a.a((l) this.c.a(this, Long.valueOf(j), t, this.e));
            }
        }

        public void a(Throwable th) {
            Object obj = 1;
            synchronized (this) {
                if (this.g) {
                    obj = null;
                } else {
                    this.g = true;
                }
            }
            if (obj != null) {
                this.a.n_();
                this.b.a(th);
            }
        }

        public void o_() {
            Object obj = 1;
            synchronized (this) {
                if (this.g) {
                    obj = null;
                } else {
                    this.g = true;
                }
            }
            if (obj != null) {
                this.a.n_();
                this.b.o_();
            }
        }

        public void b(long j) {
            Object obj = 1;
            synchronized (this) {
                if (j != this.h || this.g) {
                    obj = null;
                } else {
                    this.g = true;
                }
            }
            if (obj == null) {
                return;
            }
            if (this.d == null) {
                this.b.a(new TimeoutException());
                return;
            }
            l anonymousClass1 = new k<T>(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void a_(T t) {
                    this.a.b.a_(t);
                }

                public void a(Throwable th) {
                    this.a.b.a(th);
                }

                public void o_() {
                    this.a.b.o_();
                }

                public void a(f fVar) {
                    this.a.f.a(fVar);
                }
            };
            this.d.a(anonymousClass1);
            this.a.a(anonymousClass1);
        }
    }

    db(a<T> aVar, b<T> bVar, d<? extends T> dVar, g gVar) {
        this.a = aVar;
        this.b = bVar;
        this.c = dVar;
        this.d = gVar;
    }

    public k<? super T> a(k<? super T> kVar) {
        l a = this.d.a();
        kVar.a(a);
        dji.thirdparty.f.g.d dVar = new dji.thirdparty.f.g.d(kVar);
        e eVar = new e();
        dVar.a((l) eVar);
        k<? super T> cVar = new c(dVar, this.b, eVar, this.c, a);
        dVar.a((l) cVar);
        dVar.a(cVar.f);
        eVar.a((l) this.a.a(cVar, Long.valueOf(0), a));
        return cVar;
    }
}
