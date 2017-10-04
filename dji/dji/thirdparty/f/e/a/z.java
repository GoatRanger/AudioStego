package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.g.e;
import dji.thirdparty.f.k;

public final class z<T, U> implements d$f<T> {
    final d<? extends T> a;
    final n<? extends d<U>> b;

    public z(d<? extends T> dVar, n<? extends d<U>> nVar) {
        this.a = dVar;
        this.b = nVar;
    }

    public void a(final k<? super T> kVar) {
        try {
            ((d) this.b.call()).i(1).a(new k<U>(this) {
                final /* synthetic */ z b;

                public void o_() {
                    this.b.a.a(e.a(kVar));
                }

                public void a(Throwable th) {
                    kVar.a(th);
                }

                public void a_(U u) {
                }
            });
        } catch (Throwable th) {
            b.a(th, (dji.thirdparty.f.e) kVar);
        }
    }
}
