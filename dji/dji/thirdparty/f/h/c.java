package dji.thirdparty.f.h;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.e;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;

public final class c<T> implements d$f<T> {
    final n<Boolean> a;
    final n<Boolean> b;
    final d<? extends T> c;

    final class a implements e<T> {
        final k<? super T> a;
        final dji.thirdparty.f.m.e b;
        final /* synthetic */ c c;

        public a(c cVar, k<? super T> kVar, dji.thirdparty.f.m.e eVar) {
            this.c = cVar;
            this.a = kVar;
            this.b = eVar;
        }

        public void a_(T t) {
            this.a.a_(t);
        }

        public void a(Throwable th) {
            this.a.a(th);
        }

        public void o_() {
            try {
                if (((Boolean) this.c.b.call()).booleanValue()) {
                    Object anonymousClass1 = new k<T>(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void o_() {
                            this.a.o_();
                        }

                        public void a(Throwable th) {
                            this.a.a(th);
                        }

                        public void a_(T t) {
                            this.a.a_(t);
                        }
                    };
                    this.b.a(anonymousClass1);
                    this.c.c.a(anonymousClass1);
                    return;
                }
                this.a.o_();
            } catch (Throwable th) {
                this.a.a(th);
            }
        }
    }

    public c(d<? extends T> dVar, n<Boolean> nVar, n<Boolean> nVar2) {
        this.c = dVar;
        this.a = nVar;
        this.b = nVar2;
    }

    public void a(k<? super T> kVar) {
        try {
            if (((Boolean) this.a.call()).booleanValue()) {
                l eVar = new dji.thirdparty.f.m.e();
                kVar.a(eVar);
                final a aVar = new a(this, kVar, eVar);
                Object anonymousClass1 = new k<T>(this) {
                    final /* synthetic */ c b;

                    public void o_() {
                        aVar.o_();
                    }

                    public void a(Throwable th) {
                        aVar.a(th);
                    }

                    public void a_(T t) {
                        aVar.a_(t);
                    }
                };
                eVar.a(anonymousClass1);
                this.c.a(anonymousClass1);
                return;
            }
            kVar.o_();
        } catch (Throwable th) {
            kVar.a(th);
        }
    }
}
