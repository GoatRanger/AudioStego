package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.e.b.e;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import java.util.concurrent.Callable;

public final class ab<T> implements d$f<T> {
    private final Callable<? extends T> a;

    public ab(Callable<? extends T> callable) {
        this.a = callable;
    }

    public void a(k<? super T> kVar) {
        f eVar = new e(kVar);
        kVar.a(eVar);
        try {
            eVar.a(this.a.call());
        } catch (Throwable th) {
            b.a(th, (dji.thirdparty.f.e) kVar);
        }
    }
}
