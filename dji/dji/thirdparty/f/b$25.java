package dji.thirdparty.f;

import dji.thirdparty.f.d.n;
import dji.thirdparty.f.h.a;

class b$25 implements a<T> {
    final /* synthetic */ n a;
    final /* synthetic */ b b;

    b$25(b bVar, n nVar) {
        this.b = bVar;
        this.a = nVar;
    }

    public void a(final i<? super T> iVar) {
        this.b.a(new b$c(this) {
            final /* synthetic */ b$25 b;

            public void b() {
                try {
                    Object call = this.b.a.call();
                    if (call == null) {
                        iVar.a(new NullPointerException("The value supplied is null"));
                    } else {
                        iVar.a(call);
                    }
                } catch (Throwable th) {
                    iVar.a(th);
                }
            }

            public void a(Throwable th) {
                iVar.a(th);
            }

            public void a(l lVar) {
                iVar.a(lVar);
            }
        });
    }
}
