package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.g;
import dji.thirdparty.f.j.h;
import dji.thirdparty.f.k;

public final class cz<T> implements d$g<h<T>, T> {
    final g a;

    public cz(g gVar) {
        this.a = gVar;
    }

    public k<? super T> a(final k<? super h<T>> kVar) {
        return new k<T>(this, kVar) {
            final /* synthetic */ cz b;
            private long c = this.b.a.b();

            public void a_(T t) {
                long b = this.b.a.b();
                kVar.a_(new h(b - this.c, t));
                this.c = b;
            }

            public void o_() {
                kVar.o_();
            }

            public void a(Throwable th) {
                kVar.a(th);
            }
        };
    }
}
