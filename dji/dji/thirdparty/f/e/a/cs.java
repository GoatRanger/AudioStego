package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicInteger;

public class cs<T> implements d$g<T, T> {

    private static class a {
        static final cs<Object> a = new cs();

        private a() {
        }
    }

    private static class b<T> extends k<T> {
        private static final int a = 0;
        private static final int b = 1;
        private static final int c = 2;
        private static final int d = 3;
        private static final Object e = new Object();
        private final k<? super T> f;
        private T g = e;
        private final AtomicInteger h = new AtomicInteger(0);

        b(k<? super T> kVar) {
            this.f = kVar;
        }

        void b(long j) {
            if (j > 0) {
                while (true) {
                    int i = this.h.get();
                    if (i == 0) {
                        if (this.h.compareAndSet(0, 2)) {
                            return;
                        }
                    } else if (i != 1) {
                        return;
                    } else {
                        if (this.h.compareAndSet(1, 3)) {
                            d();
                            return;
                        }
                    }
                }
            }
        }

        public void o_() {
            if (this.g == e) {
                this.f.o_();
                return;
            }
            while (true) {
                int i = this.h.get();
                if (i == 0) {
                    if (this.h.compareAndSet(0, 1)) {
                        return;
                    }
                } else if (i != 2) {
                    return;
                } else {
                    if (this.h.compareAndSet(2, 3)) {
                        d();
                        return;
                    }
                }
            }
        }

        private void d() {
            if (b()) {
                this.g = null;
                return;
            }
            Object obj = this.g;
            this.g = null;
            if (obj != e) {
                try {
                    this.f.a_(obj);
                } catch (Throwable th) {
                    dji.thirdparty.f.c.b.a(th, this.f);
                    return;
                }
            }
            if (!b()) {
                this.f.o_();
            }
        }

        public void a(Throwable th) {
            this.f.a(th);
        }

        public void a_(T t) {
            this.g = t;
        }
    }

    public static <T> cs<T> a() {
        return a.a;
    }

    cs() {
    }

    public k<? super T> a(k<? super T> kVar) {
        final l bVar = new b(kVar);
        kVar.a(new f(this) {
            final /* synthetic */ cs b;

            public void a(long j) {
                bVar.b(j);
            }
        });
        kVar.a(bVar);
        return bVar;
    }
}
