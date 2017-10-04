package dji.thirdparty.f.h;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.k;
import java.util.Map;

public final class b<K, R> implements d$f<R> {
    final n<? extends K> a;
    final Map<? super K, ? extends d<? extends R>> b;
    final d<? extends R> c;

    public b(n<? extends K> nVar, Map<? super K, ? extends d<? extends R>> map, d<? extends R> dVar) {
        this.a = nVar;
        this.b = map;
        this.c = dVar;
    }

    public void a(k<? super R> kVar) {
        try {
            d dVar;
            Object call = this.a.call();
            if (this.b.containsKey(call)) {
                dVar = (d) this.b.get(call);
            } else {
                dVar = this.c;
            }
            dVar.b(kVar);
        } catch (Throwable th) {
            kVar.a(th);
        }
    }
}
