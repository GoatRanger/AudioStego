package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.e.d.a.e;
import dji.thirdparty.f.e.d.b.an;
import dji.thirdparty.f.e.d.b.z;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class bi<T, R> implements d$g<R, T> {
    final o<? super T, ? extends d<? extends R>> a;
    final int b;

    static final class a<T> extends k<T> {
        final c<?, T> a;
        final Queue<Object> b;
        final r<T> c;
        volatile boolean d;
        Throwable e;

        public a(c<?, T> cVar, int i) {
            Queue zVar;
            this.a = cVar;
            if (an.a()) {
                zVar = new z(i);
            } else {
                zVar = new e(i);
            }
            this.b = zVar;
            this.c = r.a();
            a((long) i);
        }

        public void a_(T t) {
            this.b.offer(this.c.a((Object) t));
            this.a.f();
        }

        public void a(Throwable th) {
            this.e = th;
            this.d = true;
            this.a.f();
        }

        public void o_() {
            this.d = true;
            this.a.f();
        }

        void b(long j) {
            a(j);
        }
    }

    static final class b extends AtomicLong implements f {
        private static final long b = -657299606803478389L;
        final c<?, ?> a;

        public b(c<?, ?> cVar) {
            this.a = cVar;
        }

        public void a(long j) {
            if (j < 0) {
                throw new IllegalStateException("n >= 0 required but it was " + j);
            } else if (j > 0) {
                a.a((AtomicLong) this, j);
                this.a.f();
            }
        }
    }

    static final class c<T, R> extends k<T> {
        final o<? super T, ? extends d<? extends R>> a;
        final int b;
        final k<? super R> c;
        final LinkedList<a<R>> d = new LinkedList();
        volatile boolean e;
        Throwable f;
        volatile boolean g;
        final AtomicInteger h = new AtomicInteger();
        private b i;

        public c(o<? super T, ? extends d<? extends R>> oVar, int i, k<? super R> kVar) {
            this.a = oVar;
            this.b = i;
            this.c = kVar;
        }

        void d() {
            this.i = new b(this);
            a(dji.thirdparty.f.m.f.a(new dji.thirdparty.f.d.b(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.g = true;
                    if (this.a.h.getAndIncrement() == 0) {
                        this.a.e();
                    }
                }
            }));
            this.c.a((l) this);
            this.c.a(this.i);
        }

        void e() {
            synchronized (this.d) {
                List<l> arrayList = new ArrayList(this.d);
                this.d.clear();
            }
            for (l n_ : arrayList) {
                n_.n_();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a_(T r5) {
            /*
            r4 = this;
            r0 = r4.a;	 Catch:{ Throwable -> 0x0014 }
            r0 = r0.a(r5);	 Catch:{ Throwable -> 0x0014 }
            r0 = (dji.thirdparty.f.d) r0;	 Catch:{ Throwable -> 0x0014 }
            r1 = new dji.thirdparty.f.e.a.bi$a;
            r2 = r4.b;
            r1.<init>(r4, r2);
            r2 = r4.g;
            if (r2 == 0) goto L_0x001b;
        L_0x0013:
            return;
        L_0x0014:
            r0 = move-exception;
            r1 = r4.c;
            dji.thirdparty.f.c.b.a(r0, r1, r5);
            goto L_0x0013;
        L_0x001b:
            r2 = r4.d;
            monitor-enter(r2);
            r3 = r4.g;	 Catch:{ all -> 0x0024 }
            if (r3 == 0) goto L_0x0027;
        L_0x0022:
            monitor-exit(r2);	 Catch:{ all -> 0x0024 }
            goto L_0x0013;
        L_0x0024:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x0024 }
            throw r0;
        L_0x0027:
            r3 = r4.d;	 Catch:{ all -> 0x0024 }
            r3.add(r1);	 Catch:{ all -> 0x0024 }
            monitor-exit(r2);	 Catch:{ all -> 0x0024 }
            r2 = r4.g;
            if (r2 != 0) goto L_0x0013;
        L_0x0031:
            r0.a(r1);
            r4.f();
            goto L_0x0013;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.bi.c.a_(java.lang.Object):void");
        }

        public void a(Throwable th) {
            this.f = th;
            this.e = true;
            f();
        }

        public void o_() {
            this.e = true;
            f();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void f() {
            /*
            r20 = this;
            r0 = r20;
            r2 = r0.h;
            r2 = r2.getAndIncrement();
            if (r2 == 0) goto L_0x000b;
        L_0x000a:
            return;
        L_0x000b:
            r2 = 1;
            r0 = r20;
            r11 = r0.i;
            r0 = r20;
            r12 = r0.c;
            r13 = dji.thirdparty.f.e.a.r.a();
            r3 = r2;
        L_0x0019:
            r0 = r20;
            r2 = r0.g;
            if (r2 == 0) goto L_0x0023;
        L_0x001f:
            r20.e();
            goto L_0x000a;
        L_0x0023:
            r0 = r20;
            r5 = r0.e;
            r0 = r20;
            r4 = r0.d;
            monitor-enter(r4);
            r0 = r20;
            r2 = r0.d;	 Catch:{ all -> 0x0049 }
            r2 = r2.peek();	 Catch:{ all -> 0x0049 }
            r2 = (dji.thirdparty.f.e.a.bi.a) r2;	 Catch:{ all -> 0x0049 }
            monitor-exit(r4);	 Catch:{ all -> 0x0049 }
            if (r2 != 0) goto L_0x004c;
        L_0x0039:
            r4 = 1;
        L_0x003a:
            if (r5 == 0) goto L_0x0054;
        L_0x003c:
            r0 = r20;
            r5 = r0.f;
            if (r5 == 0) goto L_0x004e;
        L_0x0042:
            r20.e();
            r12.a(r5);
            goto L_0x000a;
        L_0x0049:
            r2 = move-exception;
            monitor-exit(r4);	 Catch:{ all -> 0x0049 }
            throw r2;
        L_0x004c:
            r4 = 0;
            goto L_0x003a;
        L_0x004e:
            if (r4 == 0) goto L_0x0054;
        L_0x0050:
            r12.o_();
            goto L_0x000a;
        L_0x0054:
            if (r4 != 0) goto L_0x00a9;
        L_0x0056:
            r8 = r11.get();
            r6 = 0;
            r4 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r4 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1));
            if (r4 != 0) goto L_0x007f;
        L_0x0065:
            r4 = 1;
        L_0x0066:
            r14 = r2.b;
            r5 = 0;
        L_0x0069:
            r15 = r2.d;
            r16 = r14.peek();
            if (r16 != 0) goto L_0x0081;
        L_0x0071:
            r10 = 1;
        L_0x0072:
            if (r15 == 0) goto L_0x00ba;
        L_0x0074:
            r15 = r2.e;
            if (r15 == 0) goto L_0x0083;
        L_0x0078:
            r20.e();
            r12.a(r15);
            goto L_0x000a;
        L_0x007f:
            r4 = 0;
            goto L_0x0066;
        L_0x0081:
            r10 = 0;
            goto L_0x0072;
        L_0x0083:
            if (r10 == 0) goto L_0x00ba;
        L_0x0085:
            r0 = r20;
            r5 = r0.d;
            monitor-enter(r5);
            r0 = r20;
            r8 = r0.d;	 Catch:{ all -> 0x00b7 }
            r8.poll();	 Catch:{ all -> 0x00b7 }
            monitor-exit(r5);	 Catch:{ all -> 0x00b7 }
            r2.n_();
            r5 = 1;
        L_0x0096:
            r8 = 0;
            r8 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
            if (r8 == 0) goto L_0x00a7;
        L_0x009c:
            if (r4 != 0) goto L_0x00a1;
        L_0x009e:
            r11.addAndGet(r6);
        L_0x00a1:
            if (r5 != 0) goto L_0x00a7;
        L_0x00a3:
            r6 = -r6;
            r2.b(r6);
        L_0x00a7:
            if (r5 != 0) goto L_0x0019;
        L_0x00a9:
            r0 = r20;
            r2 = r0.h;
            r3 = -r3;
            r2 = r2.addAndGet(r3);
            if (r2 == 0) goto L_0x000a;
        L_0x00b4:
            r3 = r2;
            goto L_0x0019;
        L_0x00b7:
            r2 = move-exception;
            monitor-exit(r5);	 Catch:{ all -> 0x00b7 }
            throw r2;
        L_0x00ba:
            if (r10 != 0) goto L_0x0096;
        L_0x00bc:
            r18 = 0;
            r10 = (r8 > r18 ? 1 : (r8 == r18 ? 0 : -1));
            if (r10 == 0) goto L_0x0096;
        L_0x00c2:
            r14.poll();
            r0 = r16;
            r10 = r13.g(r0);	 Catch:{ Throwable -> 0x00d7 }
            r12.a_(r10);	 Catch:{ Throwable -> 0x00d7 }
            r16 = 1;
            r8 = r8 - r16;
            r16 = 1;
            r6 = r6 - r16;
            goto L_0x0069;
        L_0x00d7:
            r2 = move-exception;
            r0 = r16;
            dji.thirdparty.f.c.b.a(r2, r12, r0);
            goto L_0x000a;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.bi.c.f():void");
        }
    }

    public bi(o<? super T, ? extends d<? extends R>> oVar, int i) {
        this.a = oVar;
        this.b = i;
    }

    public k<? super T> a(k<? super R> kVar) {
        k cVar = new c(this.a, this.b, kVar);
        cVar.d();
        return cVar;
    }
}
