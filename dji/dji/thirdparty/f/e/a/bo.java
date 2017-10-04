package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class bo<T, R> implements d$g<R, T> {
    final o<? super T, ? extends R> a;
    final o<? super Throwable, ? extends R> b;
    final n<? extends R> c;

    static final class a<T, R> extends k<T> {
        static final long j = Long.MIN_VALUE;
        static final long k = Long.MAX_VALUE;
        final k<? super R> a;
        final o<? super T, ? extends R> b;
        final o<? super Throwable, ? extends R> c;
        final n<? extends R> d;
        final AtomicLong e = new AtomicLong();
        final AtomicLong f = new AtomicLong();
        final AtomicReference<f> g = new AtomicReference();
        long h;
        R i;

        public a(k<? super R> kVar, o<? super T, ? extends R> oVar, o<? super Throwable, ? extends R> oVar2, n<? extends R> nVar) {
            this.a = kVar;
            this.b = oVar;
            this.c = oVar2;
            this.d = nVar;
        }

        public void a_(T t) {
            try {
                this.h++;
                this.a.a_(this.b.a(t));
            } catch (Throwable th) {
                b.a(th, this.a, t);
            }
        }

        public void a(Throwable th) {
            d();
            try {
                this.i = this.c.a(th);
            } catch (Throwable th2) {
                b.a(th2, this.a, th);
            }
            e();
        }

        public void o_() {
            d();
            try {
                this.i = this.d.call();
            } catch (Throwable th) {
                b.a(th, this.a);
            }
            e();
        }

        void d() {
            long j = this.h;
            if (j != 0 && this.g.get() != null) {
                a.b(this.e, j);
            }
        }

        public void a(f fVar) {
            if (this.g.compareAndSet(null, fVar)) {
                long andSet = this.f.getAndSet(0);
                if (andSet != 0) {
                    fVar.a(andSet);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Producer already set!");
        }

        void e() {
            long j;
            do {
                j = this.e.get();
                if ((j & Long.MIN_VALUE) != 0) {
                    return;
                }
            } while (!this.e.compareAndSet(j, j | Long.MIN_VALUE));
            if (j != 0 || this.g.get() == null) {
                if (!this.a.b()) {
                    this.a.a_(this.i);
                }
                if (!this.a.b()) {
                    this.a.o_();
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void b(long r14) {
            /*
            r13 = this;
            r10 = -9223372036854775808;
            r8 = 0;
            r0 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1));
            if (r0 >= 0) goto L_0x0021;
        L_0x0008:
            r0 = new java.lang.IllegalArgumentException;
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = "n >= 0 required but it was ";
            r1 = r1.append(r2);
            r1 = r1.append(r14);
            r1 = r1.toString();
            r0.<init>(r1);
            throw r0;
        L_0x0021:
            r0 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1));
            if (r0 != 0) goto L_0x0026;
        L_0x0025:
            return;
        L_0x0026:
            r0 = r13.e;
            r0 = r0.get();
            r2 = r0 & r10;
            r2 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
            if (r2 == 0) goto L_0x0066;
        L_0x0032:
            r2 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r2 = r2 & r0;
            r4 = dji.thirdparty.f.e.a.a.b(r2, r14);
            r4 = r4 | r10;
            r6 = r13.e;
            r0 = r6.compareAndSet(r0, r4);
            if (r0 == 0) goto L_0x0026;
        L_0x0045:
            r0 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
            if (r0 != 0) goto L_0x0025;
        L_0x0049:
            r0 = r13.a;
            r0 = r0.b();
            if (r0 != 0) goto L_0x0058;
        L_0x0051:
            r0 = r13.a;
            r1 = r13.i;
            r0.a_(r1);
        L_0x0058:
            r0 = r13.a;
            r0 = r0.b();
            if (r0 != 0) goto L_0x0025;
        L_0x0060:
            r0 = r13.a;
            r0.o_();
            goto L_0x0025;
        L_0x0066:
            r2 = dji.thirdparty.f.e.a.a.b(r0, r14);
            r4 = r13.e;
            r0 = r4.compareAndSet(r0, r2);
            if (r0 == 0) goto L_0x0026;
        L_0x0072:
            r1 = r13.g;
            r0 = r1.get();
            r0 = (dji.thirdparty.f.f) r0;
            if (r0 == 0) goto L_0x0080;
        L_0x007c:
            r0.a(r14);
            goto L_0x0025;
        L_0x0080:
            r0 = r13.f;
            dji.thirdparty.f.e.a.a.a(r0, r14);
            r0 = r1.get();
            r0 = (dji.thirdparty.f.f) r0;
            if (r0 == 0) goto L_0x0025;
        L_0x008d:
            r1 = r13.f;
            r2 = r1.getAndSet(r8);
            r1 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
            if (r1 == 0) goto L_0x0025;
        L_0x0097:
            r0.a(r2);
            goto L_0x0025;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.bo.a.b(long):void");
        }
    }

    public bo(o<? super T, ? extends R> oVar, o<? super Throwable, ? extends R> oVar2, n<? extends R> nVar) {
        this.a = oVar;
        this.b = oVar2;
        this.c = nVar;
    }

    public k<? super T> a(k<? super R> kVar) {
        final l aVar = new a(kVar, this.a, this.b, this.c);
        kVar.a(aVar);
        kVar.a(new f(this) {
            final /* synthetic */ bo b;

            public void a(long j) {
                aVar.b(j);
            }
        });
        return aVar;
    }
}
