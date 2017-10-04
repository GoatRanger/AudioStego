package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.g.e;
import dji.thirdparty.f.k;

public final class w<T> implements d$f<T> {
    final n<? extends d<? extends T>> a;

    public w(n<? extends d<? extends T>> nVar) {
        this.a = nVar;
    }

    public void a(k<? super T> kVar) {
        try {
            ((d) this.a.call()).a(e.a((k) kVar));
        } catch (Throwable th) {
            b.a(th, (dji.thirdparty.f.e) kVar);
        }
    }
}
