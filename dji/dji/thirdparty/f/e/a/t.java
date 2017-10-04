package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$f;
import dji.thirdparty.f.f.c;
import dji.thirdparty.f.g.e;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicInteger;

public final class t<T> implements d$f<T> {
    final c<? extends T> a;
    final int b;
    final dji.thirdparty.f.d.c<? super l> c;
    final AtomicInteger d;

    public t(c<? extends T> cVar, int i, dji.thirdparty.f.d.c<? super l> cVar2) {
        if (i <= 0) {
            throw new IllegalArgumentException("numberOfSubscribers > 0 required");
        }
        this.a = cVar;
        this.b = i;
        this.c = cVar2;
        this.d = new AtomicInteger();
    }

    public void a(k<? super T> kVar) {
        this.a.a(e.a((k) kVar));
        if (this.d.incrementAndGet() == this.b) {
            this.a.h(this.c);
        }
    }
}
