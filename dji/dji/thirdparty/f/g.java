package dji.thirdparty.f;

import dji.thirdparty.f.d.b;
import dji.thirdparty.f.m.c;
import java.util.concurrent.TimeUnit;

public abstract class g {
    static final long a = TimeUnit.MINUTES.toNanos(Long.getLong("rx.scheduler.drift-tolerance", 15).longValue());

    public static abstract class a implements l {
        public abstract l a(b bVar);

        public abstract l a(b bVar, long j, TimeUnit timeUnit);

        public l a(b bVar, long j, long j2, TimeUnit timeUnit) {
            final long toNanos = timeUnit.toNanos(j2);
            final long toNanos2 = TimeUnit.MILLISECONDS.toNanos(a());
            final long toNanos3 = toNanos2 + timeUnit.toNanos(j);
            final l cVar = new c();
            final b bVar2 = bVar;
            b anonymousClass1 = new b(this) {
                long a;
                long b = toNanos2;
                long c = toNanos3;
                final /* synthetic */ a i;

                public void a() {
                    if (!cVar.b()) {
                        long j;
                        bVar2.a();
                        long toNanos = TimeUnit.MILLISECONDS.toNanos(this.i.a());
                        long j2;
                        if (g.a + toNanos < this.b || toNanos >= (this.b + toNanos) + g.a) {
                            j = toNanos + toNanos;
                            j2 = toNanos;
                            long j3 = this.a + 1;
                            this.a = j3;
                            this.c = j - (j2 * j3);
                        } else {
                            j = this.c;
                            j2 = this.a + 1;
                            this.a = j2;
                            j += j2 * toNanos;
                        }
                        this.b = toNanos;
                        cVar.a(this.i.a(this, j - toNanos, TimeUnit.NANOSECONDS));
                    }
                }
            };
            Object cVar2 = new c();
            cVar.a(cVar2);
            cVar2.a(a(anonymousClass1, j, timeUnit));
            return cVar;
        }

        public long a() {
            return System.currentTimeMillis();
        }
    }

    public abstract a a();

    public long b() {
        return System.currentTimeMillis();
    }
}
