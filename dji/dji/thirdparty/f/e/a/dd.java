package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.g;
import dji.thirdparty.f.j.i;
import dji.thirdparty.f.k;

public final class dd<T> implements d$g<i<T>, T> {
    final g a;

    public dd(g gVar) {
        this.a = gVar;
    }

    public k<? super T> a(final k<? super i<T>> kVar) {
        return new k<T>(this, kVar) {
            final /* synthetic */ dd b;

            public void o_() {
                kVar.o_();
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void a_(T t) {
                kVar.a_(new i(this.b.a.b(), t));
            }
        };
    }
}
