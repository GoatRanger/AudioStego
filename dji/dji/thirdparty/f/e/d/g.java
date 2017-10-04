package dji.thirdparty.f.e.d;

import dji.thirdparty.f.d.b;
import dji.thirdparty.f.e.c.e;
import dji.thirdparty.f.e.d.b.an;
import dji.thirdparty.f.e.d.b.j;
import dji.thirdparty.f.g.a;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public abstract class g<T> implements e {
    Queue<T> a;
    final int b;
    final int c;
    private final long d;
    private final AtomicReference<a> e;

    protected abstract T b();

    public g() {
        this(0, 0, 67);
    }

    private g(int i, int i2, long j) {
        this.b = i;
        this.c = i2;
        this.d = j;
        this.e = new AtomicReference();
        a(i);
        c();
    }

    public T e() {
        T poll = this.a.poll();
        if (poll == null) {
            return b();
        }
        return poll;
    }

    public void a(T t) {
        if (t != null) {
            this.a.offer(t);
        }
    }

    public void d() {
        a aVar = (a) this.e.getAndSet(null);
        if (aVar != null) {
            aVar.n_();
        }
    }

    public void c() {
        a a = dji.thirdparty.f.j.e.d().a();
        if (this.e.compareAndSet(null, a)) {
            a.a(new b(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void a() {
                    int i = 0;
                    int size = this.a.a.size();
                    if (size < this.a.b) {
                        size = this.a.c - size;
                        while (i < size) {
                            this.a.a.add(this.a.b());
                            i++;
                        }
                    } else if (size > this.a.c) {
                        size -= this.a.c;
                        while (i < size) {
                            this.a.a.poll();
                            i++;
                        }
                    }
                }
            }, this.d, this.d, TimeUnit.SECONDS);
        } else {
            a.n_();
        }
    }

    private void a(int i) {
        if (an.a()) {
            this.a = new j(Math.max(this.c, 1024));
        } else {
            this.a = new ConcurrentLinkedQueue();
        }
        for (int i2 = 0; i2 < i; i2++) {
            this.a.add(b());
        }
    }
}
