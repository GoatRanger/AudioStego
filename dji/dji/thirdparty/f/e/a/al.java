package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.g;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.TimeUnit;

public final class al implements d$f<Long> {
    final long a;
    final long b;
    final TimeUnit c;
    final g d;

    public al(long j, long j2, TimeUnit timeUnit, g gVar) {
        this.a = j;
        this.b = j2;
        this.c = timeUnit;
        this.d = gVar;
    }

    public void a(final k<? super Long> kVar) {
        final l a = this.d.a();
        kVar.a(a);
        a.a(new b(this) {
            long a;
            final /* synthetic */ al d;

            public void a() {
                try {
                    k kVar = kVar;
                    long j = this.a;
                    this.a = 1 + j;
                    kVar.a_(Long.valueOf(j));
                } catch (Throwable th) {
                    a.n_();
                } finally {
                    dji.thirdparty.f.c.b.a(th, kVar);
                }
            }
        }, this.a, this.b, this.c);
    }
}
