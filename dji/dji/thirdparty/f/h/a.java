package dji.thirdparty.f.h;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.k;

public final class a<R> implements d$f<R> {
    final n<Boolean> a;
    final d<? extends R> b;
    final d<? extends R> c;

    public a(n<Boolean> nVar, d<? extends R> dVar, d<? extends R> dVar2) {
        this.a = nVar;
        this.b = dVar;
        this.c = dVar2;
    }

    public void a(k<? super R> kVar) {
        try {
            d dVar;
            if (((Boolean) this.a.call()).booleanValue()) {
                dVar = this.b;
            } else {
                dVar = this.c;
            }
            dVar.b(kVar);
        } catch (Throwable th) {
            kVar.a(th);
        }
    }
}
