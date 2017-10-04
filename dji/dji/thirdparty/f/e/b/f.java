package dji.thirdparty.f.e.b;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.e;
import dji.thirdparty.f.k;
import java.util.concurrent.atomic.AtomicBoolean;

public final class f<T> extends AtomicBoolean implements dji.thirdparty.f.f {
    private static final long c = -3353584923995471404L;
    final k<? super T> a;
    final T b;

    public f(k<? super T> kVar, T t) {
        this.a = kVar;
        this.b = t;
    }

    public void a(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        } else if (j != 0 && compareAndSet(false, true)) {
            e eVar = this.a;
            Object obj = this.b;
            if (!eVar.b()) {
                try {
                    eVar.a_(obj);
                    if (!eVar.b()) {
                        eVar.o_();
                    }
                } catch (Throwable th) {
                    b.a(th, eVar, obj);
                }
            }
        }
    }
}
