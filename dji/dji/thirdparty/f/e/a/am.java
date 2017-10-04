package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.e;
import dji.thirdparty.f.k;
import dji.thirdparty.f.m.f;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public final class am {

    static class a<T> implements d$f<T> {
        final Future<? extends T> a;
        private final long b;
        private final TimeUnit c;

        public a(Future<? extends T> future) {
            this.a = future;
            this.b = 0;
            this.c = null;
        }

        public a(Future<? extends T> future, long j, TimeUnit timeUnit) {
            this.a = future;
            this.b = j;
            this.c = timeUnit;
        }

        public void a(k<? super T> kVar) {
            kVar.a(f.a(new b(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.a.cancel(true);
                }
            }));
            try {
                if (!kVar.b()) {
                    kVar.a_(this.c == null ? this.a.get() : this.a.get(this.b, this.c));
                    kVar.o_();
                }
            } catch (Throwable th) {
                if (!kVar.b()) {
                    dji.thirdparty.f.c.b.a(th, (e) kVar);
                }
            }
        }
    }

    private am() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> d$f<T> a(Future<? extends T> future) {
        return new a(future);
    }

    public static <T> d$f<T> a(Future<? extends T> future, long j, TimeUnit timeUnit) {
        return new a(future, j, timeUnit);
    }
}
