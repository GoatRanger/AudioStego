package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;

public final class cv<T, E> implements d$g<T, T> {
    private final d<? extends E> a;

    public cv(d<? extends E> dVar) {
        this.a = dVar;
    }

    public k<? super T> a(k<? super T> kVar) {
        final l dVar = new dji.thirdparty.f.g.d(kVar, false);
        final l anonymousClass1 = new k<T>(this, false, dVar) {
            final /* synthetic */ cv b;

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
            final /* synthetic */ cv b;

            public void c() {
                a((long) IPositioningSession.NotSet);
            }

            public void o_() {
                anonymousClass1.o_();
            }

            public void a(Throwable th) {
                anonymousClass1.a(th);
            }

            public void a_(E e) {
                o_();
            }
        };
        dVar.a(anonymousClass1);
        dVar.a(anonymousClass2);
        kVar.a(dVar);
        this.a.a(anonymousClass2);
        return anonymousClass1;
    }
}
