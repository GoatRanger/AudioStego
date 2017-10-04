package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.g;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.TimeUnit;

public final class ak implements d$f<Long> {
    final long a;
    final TimeUnit b;
    final g c;

    public ak(long j, TimeUnit timeUnit, g gVar) {
        this.a = j;
        this.b = timeUnit;
        this.c = gVar;
    }

    public void a(final k<? super Long> kVar) {
        l a = this.c.a();
        kVar.a(a);
        a.a(new b(this) {
            final /* synthetic */ ak b;

            public void a() {
                try {
                    kVar.a_(Long.valueOf(0));
                    kVar.o_();
                } catch (Throwable th) {
                    dji.thirdparty.f.c.b.a(th, kVar);
                }
            }
        }, this.a, this.b);
    }
}
