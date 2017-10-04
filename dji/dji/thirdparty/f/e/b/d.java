package dji.thirdparty.f.e.b;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.c.b;
import dji.thirdparty.f.e;
import dji.thirdparty.f.e.a.a;
import dji.thirdparty.f.e.d.a.h;
import dji.thirdparty.f.e.d.b.ag;
import dji.thirdparty.f.e.d.b.an;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class d<T> extends AtomicLong implements f {
    static final Object d = new Object();
    private static final long e = 7277121710709137047L;
    final k<? super T> a;
    final Queue<Object> b;
    final AtomicInteger c;

    public d(k<? super T> kVar) {
        this(kVar, an.a() ? new ag() : new h());
    }

    public d(k<? super T> kVar, Queue<Object> queue) {
        this.a = kVar;
        this.b = queue;
        this.c = new AtomicInteger();
    }

    public void a(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        } else if (j > 0) {
            a.a((AtomicLong) this, j);
            a();
        }
    }

    public boolean a(T t) {
        if (t == null) {
            if (!this.b.offer(d)) {
                return false;
            }
        } else if (!this.b.offer(t)) {
            return false;
        }
        a();
        return true;
    }

    private void a() {
        if (this.c.getAndIncrement() == 0) {
            e eVar = this.a;
            Queue queue = this.b;
            while (!eVar.b()) {
                this.c.lazySet(1);
                long j = get();
                long j2 = 0;
                while (j != 0) {
                    Object poll = queue.poll();
                    if (poll == null) {
                        break;
                    }
                    try {
                        if (poll == d) {
                            eVar.a_(null);
                        } else {
                            eVar.a_(poll);
                        }
                        if (!eVar.b()) {
                            j--;
                            j2++;
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        if (poll == d) {
                            poll = null;
                        }
                        b.a(th, eVar, poll);
                        return;
                    }
                }
                if (!(j2 == 0 || get() == IPositioningSession.NotSet)) {
                    addAndGet(-j2);
                }
                if (this.c.decrementAndGet() == 0) {
                    return;
                }
            }
        }
    }
}
