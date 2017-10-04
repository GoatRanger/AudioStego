package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.g;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ck<T> implements d$g<T, T> {
    final long a;
    final TimeUnit b;
    final g c;

    public ck(long j, TimeUnit timeUnit, g gVar) {
        this.a = j;
        this.b = timeUnit;
        this.c = gVar;
    }

    public k<? super T> a(final k<? super T> kVar) {
        l a = this.c.a();
        kVar.a(a);
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        a.a(new b(this) {
            final /* synthetic */ ck b;

            public void a() {
                atomicBoolean.set(true);
            }
        }, this.a, this.b);
        return new k<T>(this, kVar) {
            final /* synthetic */ ck c;

            public void a_(T t) {
                if (atomicBoolean.get()) {
                    kVar.a_(t);
                }
            }

            public void a(Throwable th) {
                try {
                    kVar.a(th);
                } finally {
                    n_();
                }
            }

            public void o_() {
                try {
                    kVar.o_();
                } finally {
                    n_();
                }
            }
        };
    }
}
