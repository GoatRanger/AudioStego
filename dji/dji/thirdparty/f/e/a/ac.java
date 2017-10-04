package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

public final class ac<T> implements d$f<T> {
    final Iterable<? extends T> a;

    private static final class a<T> extends AtomicLong implements f {
        private static final long a = -8730475647105475802L;
        private final k<? super T> b;
        private final Iterator<? extends T> c;

        a(k<? super T> kVar, Iterator<? extends T> it) {
            this.b = kVar;
            this.c = it;
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
            k kVar = this.b;
            Iterator it = this.c;
            do {
                long j2 = j;
                while (!kVar.b()) {
                    if (it.hasNext()) {
                        j2--;
                        if (j2 >= 0) {
                            kVar.a_(it.next());
                        } else {
                            j = addAndGet(-j);
                        }
                    } else if (!kVar.b()) {
                        kVar.o_();
                        return;
                    } else {
                        return;
                    }
                }
                return;
            } while (j != 0);
        }

        void a() {
            k kVar = this.b;
            Iterator it = this.c;
            while (!kVar.b()) {
                if (it.hasNext()) {
                    kVar.a_(it.next());
                } else if (!kVar.b()) {
                    kVar.o_();
                    return;
                } else {
                    return;
                }
            }
        }
    }

    public ac(Iterable<? extends T> iterable) {
        if (iterable == null) {
            throw new NullPointerException("iterable must not be null");
        }
        this.a = iterable;
    }

    public void a(k<? super T> kVar) {
        Iterator it = this.a.iterator();
        if (it.hasNext() || kVar.b()) {
            kVar.a(new a(kVar, it));
        } else {
            kVar.o_();
        }
    }
}
