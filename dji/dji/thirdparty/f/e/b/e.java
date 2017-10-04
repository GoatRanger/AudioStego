package dji.thirdparty.f.e.b;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import java.util.concurrent.atomic.AtomicInteger;

public final class e<T> extends AtomicInteger implements f {
    static final int c = 0;
    static final int d = 1;
    static final int e = 2;
    static final int f = 3;
    private static final long g = -2873467947112093874L;
    final k<? super T> a;
    T b;

    public e(k<? super T> kVar) {
        this.a = kVar;
    }

    public void a(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        } else if (j != 0) {
            int i;
            while (true) {
                i = get();
                if (i != 0) {
                    break;
                } else if (compareAndSet(0, 2)) {
                    return;
                }
            }
            if (i == 1 && compareAndSet(1, 3)) {
                a(this.a, this.b);
            }
        }
    }

    public void a(T t) {
        do {
            int i = get();
            if (i == 0) {
                this.b = t;
            } else if (i == 2 && compareAndSet(2, 3)) {
                a(this.a, t);
                return;
            } else {
                return;
            }
        } while (!compareAndSet(0, 1));
    }

    private static <T> void a(k<? super T> kVar, T t) {
        if (!kVar.b()) {
            try {
                kVar.a_(t);
                if (!kVar.b()) {
                    kVar.o_();
                }
            } catch (Throwable th) {
                b.a(th, kVar, t);
            }
        }
    }
}
