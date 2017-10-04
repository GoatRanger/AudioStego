package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.c.b;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicLong;

final class du<T> extends AtomicLong implements f {
    private final r<T> a;
    private final Deque<Object> b;
    private final k<? super T> c;
    private volatile boolean d = false;

    public du(r<T> rVar, Deque<Object> deque, k<? super T> kVar) {
        this.a = rVar;
        this.b = deque;
        this.c = kVar;
    }

    void a() {
        if (!this.d) {
            this.d = true;
            b(0);
        }
    }

    public void a(long j) {
        if (get() != IPositioningSession.NotSet) {
            long andSet;
            if (j == IPositioningSession.NotSet) {
                andSet = getAndSet(IPositioningSession.NotSet);
            } else {
                andSet = a.a((AtomicLong) this, j);
            }
            if (this.d) {
                b(andSet);
            }
        }
    }

    void b(long j) {
        if (get() == IPositioningSession.NotSet) {
            if (j == 0) {
                try {
                    for (Object next : this.b) {
                        if (!this.c.b()) {
                            this.a.a(this.c, next);
                        } else {
                            return;
                        }
                    }
                    this.b.clear();
                } catch (Throwable th) {
                    b.a(th, this.c);
                } finally {
                    this.b.clear();
                }
            }
        } else if (j == 0) {
            while (true) {
                long j2;
                long j3 = get();
                int i = 0;
                while (true) {
                    j3--;
                    if (j3 < 0) {
                        break;
                    }
                    Object poll = this.b.poll();
                    if (poll == null) {
                        break;
                    } else if (!this.c.b() && !this.a.a(this.c, poll)) {
                        i++;
                    } else {
                        return;
                    }
                }
                do {
                    j3 = get();
                    j2 = j3 - ((long) i);
                    if (j3 == IPositioningSession.NotSet) {
                        break;
                    }
                } while (!compareAndSet(j3, j2));
                if (j2 == 0) {
                    return;
                }
            }
        }
    }
}
