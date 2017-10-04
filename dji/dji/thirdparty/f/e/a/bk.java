package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.k;

public final class bk<T> implements d$g<T, T> {
    final o<? super T, Boolean> a;

    public bk(o<? super T, Boolean> oVar) {
        this.a = oVar;
    }

    public k<? super T> a(final k<? super T> kVar) {
        return new k<T>(this, kVar) {
            final /* synthetic */ bk b;

            public void o_() {
                kVar.o_();
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void a_(T t) {
                try {
                    if (((Boolean) this.b.a.a(t)).booleanValue()) {
                        kVar.a_(t);
                    } else {
                        a(1);
                    }
                } catch (Throwable th) {
                    b.a(th, kVar, t);
                }
            }
        };
    }
}
