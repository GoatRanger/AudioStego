package dji.thirdparty.f.j;

import dji.thirdparty.f.g;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.f;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class j extends g {
    private static final j b = new j();

    private static class a extends dji.thirdparty.f.g.a implements l {
        final AtomicInteger a = new AtomicInteger();
        final PriorityBlockingQueue<b> b = new PriorityBlockingQueue();
        private final dji.thirdparty.f.m.a c = new dji.thirdparty.f.m.a();
        private final AtomicInteger d = new AtomicInteger();

        a() {
        }

        public l a(dji.thirdparty.f.d.b bVar) {
            return a(bVar, a());
        }

        public l a(dji.thirdparty.f.d.b bVar, long j, TimeUnit timeUnit) {
            long a = a() + timeUnit.toMillis(j);
            return a(new f(bVar, this, a), a);
        }

        private l a(dji.thirdparty.f.d.b bVar, long j) {
            if (this.c.b()) {
                return f.b();
            }
            final b bVar2 = new b(bVar, Long.valueOf(j), this.a.incrementAndGet());
            this.b.add(bVar2);
            if (this.d.getAndIncrement() != 0) {
                return f.a(new dji.thirdparty.f.d.b(this) {
                    final /* synthetic */ a b;

                    public void a() {
                        this.b.b.remove(bVar2);
                    }
                });
            }
            do {
                bVar2 = (b) this.b.poll();
                if (bVar2 != null) {
                    bVar2.a.a();
                }
            } while (this.d.decrementAndGet() > 0);
            return f.b();
        }

        public void n_() {
            this.c.n_();
        }

        public boolean b() {
            return this.c.b();
        }
    }

    private static final class b implements Comparable<b> {
        final dji.thirdparty.f.d.b a;
        final Long b;
        final int c;

        public /* synthetic */ int compareTo(Object obj) {
            return a((b) obj);
        }

        b(dji.thirdparty.f.d.b bVar, Long l, int i) {
            this.a = bVar;
            this.b = l;
            this.c = i;
        }

        public int a(b bVar) {
            int compareTo = this.b.compareTo(bVar.b);
            if (compareTo == 0) {
                return j.a(this.c, bVar.c);
            }
            return compareTo;
        }
    }

    static j c() {
        return b;
    }

    public dji.thirdparty.f.g.a a() {
        return new a();
    }

    j() {
    }

    static int a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }
}
