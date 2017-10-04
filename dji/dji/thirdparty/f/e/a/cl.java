package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicBoolean;

public final class cl<T, U> implements d$g<T, T> {
    final d<U> a;

    public cl(d<U> dVar) {
        this.a = dVar;
    }

    public k<? super T> a(k<? super T> kVar) {
        final dji.thirdparty.f.g.d dVar = new dji.thirdparty.f.g.d(kVar);
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        l anonymousClass1 = new k<U>(this) {
            final /* synthetic */ cl c;

            public void a_(U u) {
                atomicBoolean.set(true);
                n_();
            }

            public void a(Throwable th) {
                dVar.a(th);
                dVar.n_();
            }

            public void o_() {
                n_();
            }
        };
        kVar.a(anonymousClass1);
        this.a.a(anonymousClass1);
        return new k<T>(this, kVar) {
            final /* synthetic */ cl c;

            public void a_(T t) {
                if (atomicBoolean.get()) {
                    dVar.a_(t);
                } else {
                    a(1);
                }
            }

            public void a(Throwable th) {
                dVar.a(th);
                n_();
            }

            public void o_() {
                dVar.o_();
                n_();
            }
        };
    }
}
