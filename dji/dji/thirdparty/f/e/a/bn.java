package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.k;

public final class bn<T, R> implements d$g<R, T> {
    final o<? super T, ? extends R> a;

    public bn(o<? super T, ? extends R> oVar) {
        this.a = oVar;
    }

    public k<? super T> a(final k<? super R> kVar) {
        return new k<T>(this, kVar) {
            final /* synthetic */ bn b;

            public void o_() {
                kVar.o_();
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void a_(T t) {
                try {
                    kVar.a_(this.b.a.a(t));
                } catch (Throwable th) {
                    b.a(th, this, t);
                }
            }
        };
    }
}
