package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import java.util.concurrent.atomic.AtomicLong;

public final class aa<T> implements d$f<T> {
    final T[] a;

    static final class a<T> extends AtomicLong implements f {
        private static final long d = 3534218984725836979L;
        final k<? super T> a;
        final T[] b;
        int c;

        public a(k<? super T> kVar, T[] tArr) {
            this.a = kVar;
            this.b = tArr;
        }

        public void a(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            } else if (j == IPositioningSession.NotSet) {
                if (a.a((AtomicLong) this, j) == 0) {
                    a();
                }
            } else if (j != 0 && a.a((AtomicLong) this, j) == 0) {
                b(j);
            }
        }

        void a() {
            k kVar = this.a;
            Object[] objArr = this.b;
            int length = objArr.length;
            int i = 0;
            while (i < length) {
                Object obj = objArr[i];
                if (!kVar.b()) {
                    kVar.a_(obj);
                    i++;
                } else {
                    return;
                }
            }
            if (!kVar.b()) {
                kVar.o_();
            }
        }

        void b(long j) {
            k kVar = this.a;
            Object[] objArr = this.b;
            int length = objArr.length;
            int i = this.c;
            long j2 = 0;
            while (true) {
                if (j == 0 || i == length) {
                    j = get() + j2;
                    if (j == 0) {
                        this.c = i;
                        j = addAndGet(j2);
                        if (j != 0) {
                            j2 = 0;
                        } else {
                            return;
                        }
                    }
                    continue;
                } else if (!kVar.b()) {
                    kVar.a_(objArr[i]);
                    i++;
                    if (i == length) {
                        break;
                    }
                    j--;
                    j2--;
                } else {
                    return;
                }
            }
            if (!kVar.b()) {
                kVar.o_();
            }
        }
    }

    public aa(T[] tArr) {
        this.a = tArr;
    }

    public void a(k<? super T> kVar) {
        kVar.a(new a(kVar, this.a));
    }
}
