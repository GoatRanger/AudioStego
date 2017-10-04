package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import java.util.concurrent.atomic.AtomicLong;

public final class ag implements d$f<Integer> {
    private final int a;
    private final int b;

    private static final class a extends AtomicLong implements f {
        private static final long a = 4114392207069098388L;
        private final k<? super Integer> b;
        private final int c;
        private long d;

        a(k<? super Integer> kVar, int i, int i2) {
            this.b = kVar;
            this.d = (long) i;
            this.c = i2;
        }

        public void a(long j) {
            if (get() != IPositioningSession.NotSet) {
                if (j == IPositioningSession.NotSet && compareAndSet(0, IPositioningSession.NotSet)) {
                    a();
                } else if (j > 0 && a.a((AtomicLong) this, j) == 0) {
                    b(j);
                }
            }
        }

        void b(long j) {
            long j2 = ((long) this.c) + 1;
            long j3 = this.d;
            k kVar = this.b;
            long j4 = 0;
            while (true) {
                if (j4 == j || j3 == j2) {
                    if (!kVar.b()) {
                        if (j3 == j2) {
                            kVar.o_();
                            return;
                        }
                        j = get();
                        if (j == j4) {
                            this.d = j3;
                            j = addAndGet(-j4);
                            if (j != 0) {
                                j4 = 0;
                            } else {
                                return;
                            }
                        }
                        continue;
                    } else {
                        return;
                    }
                } else if (!kVar.b()) {
                    kVar.a_(Integer.valueOf((int) j3));
                    j3++;
                    j4++;
                } else {
                    return;
                }
            }
        }

        void a() {
            long j = ((long) this.c) + 1;
            k kVar = this.b;
            long j2 = this.d;
            while (j2 != j) {
                if (!kVar.b()) {
                    kVar.a_(Integer.valueOf((int) j2));
                    j2++;
                } else {
                    return;
                }
            }
            if (!kVar.b()) {
                kVar.o_();
            }
        }
    }

    public ag(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public void a(k<? super Integer> kVar) {
        kVar.a(new a(kVar, this.a, this.b));
    }
}
