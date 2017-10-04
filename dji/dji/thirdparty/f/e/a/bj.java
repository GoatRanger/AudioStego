package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicBoolean;

public final class bj<T> implements d$g<T, T> {
    final int a;
    final boolean b;
    final T c;

    static class a extends AtomicBoolean implements f {
        private static final long b = 1;
        final f a;

        public a(f fVar) {
            this.a = fVar;
        }

        public void a(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            } else if (j > 0 && compareAndSet(false, true)) {
                this.a.a(IPositioningSession.NotSet);
            }
        }
    }

    public bj(int i) {
        this(i, null, false);
    }

    public bj(int i, T t) {
        this(i, t, true);
    }

    private bj(int i, T t, boolean z) {
        if (i < 0) {
            throw new IndexOutOfBoundsException(i + " is out of bounds");
        }
        this.a = i;
        this.c = t;
        this.b = z;
    }

    public k<? super T> a(final k<? super T> kVar) {
        l anonymousClass1 = new k<T>(this) {
            final /* synthetic */ bj b;
            private int c = 0;

            public void a_(T t) {
                int i = this.c;
                this.c = i + 1;
                if (i == this.b.a) {
                    kVar.a_(t);
                    kVar.o_();
                    n_();
                }
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void o_() {
                if (this.c > this.b.a) {
                    return;
                }
                if (this.b.b) {
                    kVar.a_(this.b.c);
                    kVar.o_();
                    return;
                }
                kVar.a(new IndexOutOfBoundsException(this.b.a + " is out of bounds"));
            }

            public void a(f fVar) {
                kVar.a(new a(fVar));
            }
        };
        kVar.a(anonymousClass1);
        return anonymousClass1;
    }
}
