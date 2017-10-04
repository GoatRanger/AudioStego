package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.k;

public class av<T, R> implements d$g<R, T> {
    final Class<R> a;

    public av(Class<R> cls) {
        this.a = cls;
    }

    public k<? super T> a(final k<? super R> kVar) {
        return new k<T>(this, kVar) {
            final /* synthetic */ av b;

            public void o_() {
                kVar.o_();
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void a_(T t) {
                try {
                    kVar.a_(this.b.a.cast(t));
                } catch (Throwable th) {
                    b.a(th, this, t);
                }
            }
        };
    }
}
