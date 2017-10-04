package dji.thirdparty.f;

import dji.thirdparty.f.d.n;
import dji.thirdparty.f.f.c;
import java.util.concurrent.TimeUnit;

class d$14 implements n<c<T>> {
    final /* synthetic */ long a;
    final /* synthetic */ TimeUnit b;
    final /* synthetic */ g c;
    final /* synthetic */ d d;

    d$14(d dVar, long j, TimeUnit timeUnit, g gVar) {
        this.d = dVar;
        this.a = j;
        this.b = timeUnit;
        this.c = gVar;
    }

    public /* synthetic */ Object call() {
        return a();
    }

    public c<T> a() {
        return this.d.g(this.a, this.b, this.c);
    }
}
