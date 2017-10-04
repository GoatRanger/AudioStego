package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.d.p;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.e;
import java.util.concurrent.atomic.AtomicInteger;

public final class ca<T> implements d$g<T, d<T>> {
    final p<Integer, Throwable, Boolean> a;

    static final class a<T> extends k<d<T>> {
        final k<? super T> a;
        final p<Integer, Throwable, Boolean> b;
        final dji.thirdparty.f.g.a c;
        final e d;
        final dji.thirdparty.f.e.b.a e;
        final AtomicInteger f = new AtomicInteger();

        public /* synthetic */ void a_(Object obj) {
            a((d) obj);
        }

        public a(k<? super T> kVar, p<Integer, Throwable, Boolean> pVar, dji.thirdparty.f.g.a aVar, e eVar, dji.thirdparty.f.e.b.a aVar2) {
            this.a = kVar;
            this.b = pVar;
            this.c = aVar;
            this.d = eVar;
            this.e = aVar2;
        }

        public void o_() {
        }

        public void a(Throwable th) {
            this.a.a(th);
        }

        public void a(final d<T> dVar) {
            this.c.a(new b(this) {
                final /* synthetic */ a b;

                public void a() {
                    this.b.f.incrementAndGet();
                    Object anonymousClass1 = new k<T>(this) {
                        boolean a;
                        final /* synthetic */ AnonymousClass1 c;

                        public void o_() {
                            if (!this.a) {
                                this.a = true;
                                this.c.b.a.o_();
                            }
                        }

                        public void a(Throwable th) {
                            if (!this.a) {
                                this.a = true;
                                if (!((Boolean) this.c.b.b.b(Integer.valueOf(this.c.b.f.get()), th)).booleanValue() || this.c.b.c.b()) {
                                    this.c.b.a.a(th);
                                } else {
                                    this.c.b.c.a(this);
                                }
                            }
                        }

                        public void a_(T t) {
                            if (!this.a) {
                                this.c.b.a.a_(t);
                                this.c.b.e.b(1);
                            }
                        }

                        public void a(f fVar) {
                            this.c.b.e.a(fVar);
                        }
                    };
                    this.b.d.a(anonymousClass1);
                    dVar.a(anonymousClass1);
                }
            });
        }
    }

    public ca(p<Integer, Throwable, Boolean> pVar) {
        this.a = pVar;
    }

    public k<? super d<T>> a(k<? super T> kVar) {
        l a = dji.thirdparty.f.j.e.b().a();
        kVar.a(a);
        l eVar = new e();
        kVar.a(eVar);
        f aVar = new dji.thirdparty.f.e.b.a();
        kVar.a(aVar);
        return new a(kVar, this.a, a, eVar, aVar);
    }
}
