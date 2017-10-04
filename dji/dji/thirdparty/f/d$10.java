package dji.thirdparty.f;

import dji.thirdparty.f.d.n;
import dji.thirdparty.f.f.c;
import java.util.concurrent.TimeUnit;

class d$10 implements n<c<T>> {
    final /* synthetic */ int a;
    final /* synthetic */ long b;
    final /* synthetic */ TimeUnit c;
    final /* synthetic */ g d;
    final /* synthetic */ d e;

    d$10(d dVar, int i, long j, TimeUnit timeUnit, g gVar) {
        this.e = dVar;
        this.a = i;
        this.b = j;
        this.c = timeUnit;
        this.d = gVar;
    }

    public /* synthetic */ Object call() {
        return a();
    }

    public c<T> a() {
        return this.e.a(this.a, this.b, this.c, this.d);
    }
}
