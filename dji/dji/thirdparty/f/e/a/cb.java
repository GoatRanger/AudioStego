package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicReference;

public final class cb<T, U> implements d$g<T, T> {
    static final Object b = new Object();
    final d<U> a;

    public cb(d<U> dVar) {
        this.a = dVar;
    }

    public k<? super T> a(k<? super T> kVar) {
        final dji.thirdparty.f.g.d dVar = new dji.thirdparty.f.g.d(kVar);
        final AtomicReference atomicReference = new AtomicReference(b);
        final AtomicReference atomicReference2 = new AtomicReference();
        final l anonymousClass1 = new k<U>(this) {
            final /* synthetic */ cb d;

            public void a_(U u) {
                Object andSet = atomicReference.getAndSet(cb.b);
                if (andSet != cb.b) {
                    dVar.a_(andSet);
                }
            }

            public void a(Throwable th) {
                dVar.a(th);
                ((l) atomicReference2.get()).n_();
            }

            public void o_() {
                dVar.o_();
                ((l) atomicReference2.get()).n_();
            }
        };
        l anonymousClass2 = new k<T>(this) {
            final /* synthetic */ cb d;

            public void a_(T t) {
                atomicReference.set(t);
            }

            public void a(Throwable th) {
                dVar.a(th);
                anonymousClass1.n_();
            }

            public void o_() {
                dVar.o_();
                anonymousClass1.n_();
            }
        };
        atomicReference2.lazySet(anonymousClass2);
        kVar.a(anonymousClass2);
        kVar.a(anonymousClass1);
        this.a.a(anonymousClass1);
        return anonymousClass2;
    }
}
