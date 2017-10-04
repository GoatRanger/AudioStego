package dji.thirdparty.f.j;

import dji.thirdparty.f.l;
import dji.thirdparty.f.m.f;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class g extends dji.thirdparty.f.g {
    static long c = 0;
    final Queue<c> b = new PriorityQueue(11, new a());
    long d;

    private static class a implements Comparator<c> {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((c) obj, (c) obj2);
        }

        a() {
        }

        public int a(c cVar, c cVar2) {
            if (cVar.a == cVar2.a) {
                if (cVar.d < cVar2.d) {
                    return -1;
                }
                return cVar.d > cVar2.d ? 1 : 0;
            } else if (cVar.a >= cVar2.a) {
                return cVar.a > cVar2.a ? 1 : 0;
            } else {
                return -1;
            }
        }
    }

    private final class b extends dji.thirdparty.f.g.a {
        final /* synthetic */ g a;
        private final dji.thirdparty.f.m.a b = new dji.thirdparty.f.m.a();

        b(g gVar) {
            this.a = gVar;
        }

        public void n_() {
            this.b.n_();
        }

        public boolean b() {
            return this.b.b();
        }

        public l a(dji.thirdparty.f.d.b bVar, long j, TimeUnit timeUnit) {
            final c cVar = new c(this, this.a.d + timeUnit.toNanos(j), bVar);
            this.a.b.add(cVar);
            return f.a(new dji.thirdparty.f.d.b(this) {
                final /* synthetic */ b b;

                public void a() {
                    this.b.a.b.remove(cVar);
                }
            });
        }

        public l a(dji.thirdparty.f.d.b bVar) {
            final c cVar = new c(this, 0, bVar);
            this.a.b.add(cVar);
            return f.a(new dji.thirdparty.f.d.b(this) {
                final /* synthetic */ b b;

                public void a() {
                    this.b.a.b.remove(cVar);
                }
            });
        }

        public long a() {
            return this.a.b();
        }
    }

    private static final class c {
        final long a;
        final dji.thirdparty.f.d.b b;
        final dji.thirdparty.f.g.a c;
        private final long d;

        c(dji.thirdparty.f.g.a aVar, long j, dji.thirdparty.f.d.b bVar) {
            long j2 = g.c;
            g.c = 1 + j2;
            this.d = j2;
            this.a = j;
            this.b = bVar;
            this.c = aVar;
        }

        public String toString() {
            return String.format("TimedAction(time = %d, action = %s)", new Object[]{Long.valueOf(this.a), this.b.toString()});
        }
    }

    public long b() {
        return TimeUnit.NANOSECONDS.toMillis(this.d);
    }

    public void a(long j, TimeUnit timeUnit) {
        b(this.d + timeUnit.toNanos(j), TimeUnit.NANOSECONDS);
    }

    public void b(long j, TimeUnit timeUnit) {
        a(timeUnit.toNanos(j));
    }

    public void c() {
        a(this.d);
    }

    private void a(long j) {
        while (!this.b.isEmpty()) {
            c cVar = (c) this.b.peek();
            if (cVar.a > j) {
                break;
            }
            this.d = cVar.a == 0 ? this.d : cVar.a;
            this.b.remove();
            if (!cVar.c.b()) {
                cVar.b.a();
            }
        }
        this.d = j;
    }

    public dji.thirdparty.f.g.a a() {
        return new b(this);
    }
}
