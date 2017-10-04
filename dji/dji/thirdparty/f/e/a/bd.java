package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.k;

public final class bd<T> implements d$g<T, T> {
    final b a;

    public bd(b bVar) {
        if (bVar == null) {
            throw new NullPointerException("Action can not be null");
        }
        this.a = bVar;
    }

    public k<? super T> a(final k<? super T> kVar) {
        return new k<T>(this, kVar) {
            final /* synthetic */ bd b;

            public void a_(T t) {
                kVar.a_(t);
            }

            public void a(Throwable th) {
                try {
                    kVar.a(th);
                } finally {
                    this.b.a.a();
                }
            }

            public void o_() {
                try {
                    kVar.o_();
                } finally {
                    this.b.a.a();
                }
            }
        };
    }
}
