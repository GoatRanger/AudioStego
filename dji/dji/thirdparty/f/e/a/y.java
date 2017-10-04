package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.g.e;
import dji.thirdparty.f.k;

public final class y<T, U> implements d$f<T> {
    final d<? extends T> a;
    final d<U> b;

    public y(d<? extends T> dVar, d<U> dVar2) {
        this.a = dVar;
        this.b = dVar2;
    }

    public void a(k<? super T> kVar) {
        final k a = e.a((k) kVar);
        final dji.thirdparty.f.m.e eVar = new dji.thirdparty.f.m.e();
        Object anonymousClass1 = new k<U>(this) {
            boolean a;
            final /* synthetic */ y d;

            public void a_(U u) {
                o_();
            }

            public void a(Throwable th) {
                if (this.a) {
                    dji.thirdparty.f.i.d.getInstance().b().a(th);
                    return;
                }
                this.a = true;
                a.a(th);
            }

            public void o_() {
                if (!this.a) {
                    this.a = true;
                    eVar.a(a);
                    this.d.a.a(a);
                }
            }
        };
        eVar.a(anonymousClass1);
        this.b.a(anonymousClass1);
    }
}
