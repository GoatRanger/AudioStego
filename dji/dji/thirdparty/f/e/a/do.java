package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.p;
import dji.thirdparty.f.e;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicReference;

public final class do<T, U, R> implements d$g<R, T> {
    static final Object c = new Object();
    final p<? super T, ? super U, ? extends R> a;
    final d<? extends U> b;

    public do(d<? extends U> dVar, p<? super T, ? super U, ? extends R> pVar) {
        this.b = dVar;
        this.a = pVar;
    }

    public k<? super T> a(k<? super R> kVar) {
        final l dVar = new dji.thirdparty.f.g.d(kVar, false);
        kVar.a(dVar);
        final AtomicReference atomicReference = new AtomicReference(c);
        final l lVar = dVar;
        Object anonymousClass1 = new k<T>(this, dVar, true) {
            final /* synthetic */ do c;

            public void a_(T t) {
                Object obj = atomicReference.get();
                if (obj != do.c) {
                    try {
                        lVar.a_(this.c.a.b(t, obj));
                    } catch (Throwable th) {
                        b.a(th, (e) this);
                    }
                }
            }

            public void a(Throwable th) {
                lVar.a(th);
                lVar.n_();
            }

            public void o_() {
                lVar.o_();
                lVar.n_();
            }
        };
        Object anonymousClass2 = new k<U>(this) {
            final /* synthetic */ do c;

            public void a_(U u) {
                atomicReference.set(u);
            }

            public void a(Throwable th) {
                dVar.a(th);
                dVar.n_();
            }

            public void o_() {
                if (atomicReference.get() == do.c) {
                    dVar.o_();
                    dVar.n_();
                }
            }
        };
        dVar.a((l) anonymousClass1);
        dVar.a((l) anonymousClass2);
        this.b.a(anonymousClass2);
        return anonymousClass1;
    }
}
