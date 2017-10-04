package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;

public final class ch<T> implements d$g<T, T> {
    final int a;

    public ch(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("n >= 0 required but it was " + i);
        }
        this.a = i;
    }

    public k<? super T> a(final k<? super T> kVar) {
        return new k<T>(this, kVar) {
            int a = 0;
            final /* synthetic */ ch c;

            public void o_() {
                kVar.o_();
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void a_(T t) {
                if (this.a >= this.c.a) {
                    kVar.a_(t);
                } else {
                    this.a++;
                }
            }

            public void a(f fVar) {
                kVar.a(fVar);
                fVar.a((long) this.c.a);
            }
        };
    }
}
