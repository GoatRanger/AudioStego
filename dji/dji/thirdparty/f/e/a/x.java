package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.g;
import dji.thirdparty.f.g.e;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.TimeUnit;

public final class x<T> implements d$f<T> {
    final d<? extends T> a;
    final long b;
    final TimeUnit c;
    final g d;

    public x(d<? extends T> dVar, long j, TimeUnit timeUnit, g gVar) {
        this.a = dVar;
        this.b = j;
        this.c = timeUnit;
        this.d = gVar;
    }

    public void a(final k<? super T> kVar) {
        l a = this.d.a();
        kVar.a(a);
        a.a(new b(this) {
            final /* synthetic */ x b;

            public void a() {
                if (!kVar.b()) {
                    this.b.a.a(e.a(kVar));
                }
            }
        }, this.b, this.c);
    }
}
