package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.c;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import java.util.concurrent.atomic.AtomicLong;

public class bv<T> implements d$g<T, T> {
    final c<? super T> a;

    private static final class a {
        static final bv<Object> a = new bv();

        private a() {
        }
    }

    public static <T> bv<T> a() {
        return a.a;
    }

    bv() {
        this(null);
    }

    public bv(c<? super T> cVar) {
        this.a = cVar;
    }

    public k<? super T> a(final k<? super T> kVar) {
        final AtomicLong atomicLong = new AtomicLong();
        kVar.a(new f(this) {
            final /* synthetic */ bv b;

            public void a(long j) {
                a.a(atomicLong, j);
            }
        });
        return new k<T>(this, kVar) {
            final /* synthetic */ bv c;

            public void c() {
                a((long) IPositioningSession.NotSet);
            }

            public void o_() {
                kVar.o_();
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void a_(T t) {
                if (atomicLong.get() > 0) {
                    kVar.a_(t);
                    atomicLong.decrementAndGet();
                } else if (this.c.a != null) {
                    try {
                        this.c.a.a(t);
                    } catch (Throwable th) {
                        b.a(th, kVar, t);
                    }
                }
            }
        };
    }
}
