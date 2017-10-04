package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;

public final class cg<T> implements d$g<T, T> {
    private final boolean a;
    private final T b;

    private static class a {
        static final cg<?> a = new cg();

        private a() {
        }
    }

    private static final class b<T> extends k<T> {
        private final k<? super T> a;
        private final boolean b;
        private final T c;
        private T d;
        private boolean e = false;
        private boolean f = false;

        b(k<? super T> kVar, boolean z, T t) {
            this.a = kVar;
            this.b = z;
            this.c = t;
        }

        void b(long j) {
            a(j);
        }

        public void a_(T t) {
            if (this.e) {
                this.f = true;
                this.a.a(new IllegalArgumentException("Sequence contains too many elements"));
                n_();
                return;
            }
            this.d = t;
            this.e = true;
        }

        public void o_() {
            if (!this.f) {
                if (this.e) {
                    this.a.a_(this.d);
                    this.a.o_();
                } else if (this.b) {
                    this.a.a_(this.c);
                    this.a.o_();
                } else {
                    this.a.a(new NoSuchElementException("Sequence contains no elements"));
                }
            }
        }

        public void a(Throwable th) {
            this.a.a(th);
        }
    }

    public static <T> cg<T> a() {
        return a.a;
    }

    cg() {
        this(false, null);
    }

    public cg(T t) {
        this(true, t);
    }

    private cg(boolean z, T t) {
        this.a = z;
        this.b = t;
    }

    public k<? super T> a(k<? super T> kVar) {
        final l bVar = new b(kVar, this.a, this.b);
        kVar.a(new f(this) {
            final /* synthetic */ cg b;
            private final AtomicBoolean c = new AtomicBoolean(false);

            public void a(long j) {
                if (j > 0 && this.c.compareAndSet(false, true)) {
                    bVar.b(2);
                }
            }
        });
        kVar.a(bVar);
        return bVar;
    }
}
