package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.g;
import dji.thirdparty.f.k;
import java.util.concurrent.TimeUnit;

public final class cy<T> implements d$g<T, T> {
    final long a;
    final g b;

    public cy(long j, TimeUnit timeUnit, g gVar) {
        this.a = timeUnit.toMillis(j);
        this.b = gVar;
    }

    public k<? super T> a(final k<? super T> kVar) {
        return new k<T>(this, kVar) {
            final /* synthetic */ cy b;
            private long c = 0;

            public void c() {
                a((long) IPositioningSession.NotSet);
            }

            public void a_(T t) {
                long b = this.b.b.b();
                if (this.c == 0 || b - this.c >= this.b.a) {
                    this.c = b;
                    kVar.a_(t);
                }
            }

            public void o_() {
                kVar.o_();
            }

            public void a(Throwable th) {
                kVar.a(th);
            }
        };
    }
}
