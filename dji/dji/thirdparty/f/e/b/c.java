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

public final class c<T> extends AtomicLong implements e<T>, f {
    static final Object f = new Object();
    private static final long g = 7277121710709137047L;
    final k<? super T> a;
    final Queue<Object> b;
    final AtomicInteger c;
    Throwable d;
    volatile boolean e;

    public c(k<? super T> kVar) {
        this(kVar, an.a() ? new ag() : new h());
    }

    public c(k<? super T> kVar, Queue<Object> queue) {
        this.a = kVar;
        this.b = queue;
        this.c = new AtomicInteger();
    }

    public void a(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        } else if (j > 0) {
            a.a((AtomicLong) this, j);
            b();
        }
    }

    public boolean b(T t) {
        if (t == null) {
            if (!this.b.offer(f)) {
                return false;
            }
        } else if (!this.b.offer(t)) {
            return false;
        }
        b();
        return true;
    }

    public void a_(T t) {
        if (!b(t)) {
            a(new dji.thirdparty.f.c.c());
        }
    }

    public void a(Throwable th) {
        this.d = th;
        this.e = true;
        b();
    }

    public void o_() {
        this.e = true;
        b();
    }

    private boolean a(boolean z, boolean z2) {
        if (this.a.b()) {
            return true;
        }
        if (z) {
            Throwable th = this.d;
            if (th != null) {
                this.b.clear();
                this.a.a(th);
                return true;
            } else if (z2) {
                this.a.o_();
                return true;
            }
        }
        return false;
    }

    private void b() {
        if (this.c.getAndIncrement() == 0) {
            e eVar = this.a;
            Queue queue = this.b;
            while (!a(this.e, queue.isEmpty())) {
                this.c.lazySet(1);
                long j = get();
                long j2 = 0;
                while (j != 0) {
                    boolean z = this.e;
                    Object poll = queue.poll();
                    if (!a(z, poll == null)) {
                        if (poll == null) {
                            break;
                        }
                        try {
                            if (poll == f) {
                                eVar.a_(null);
                            } else {
                                eVar.a_(poll);
                            }
                            j--;
                            j2 = 1 + j2;
                        } catch (Throwable th) {
                            b.a(th, eVar, poll != f ? poll : null);
                            return;
                        }
                    }
                    return;
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
