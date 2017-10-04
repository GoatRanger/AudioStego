package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.e;
import dji.thirdparty.f.e.d.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public final class h<T> extends d<T> {
    private final a<T> c;

    static final class a<T> extends f implements e<T> {
        static final c<?>[] d = new c[0];
        final d<? extends T> a;
        final dji.thirdparty.f.m.e b = new dji.thirdparty.f.m.e();
        volatile c<?>[] c = d;
        final r<T> e = r.a();
        volatile boolean f;
        boolean g;

        public a(d<? extends T> dVar, int i) {
            super(i);
            this.a = dVar;
        }

        public void a(c<T> cVar) {
            synchronized (this.b) {
                Object obj = this.c;
                int length = obj.length;
                Object obj2 = new c[(length + 1)];
                System.arraycopy(obj, 0, obj2, 0, length);
                obj2[length] = cVar;
                this.c = obj2;
            }
        }

        public void b(c<T> cVar) {
            int i = 0;
            synchronized (this.b) {
                Object obj = this.c;
                int length = obj.length;
                while (i < length) {
                    if (obj[i].equals(cVar)) {
                        break;
                    }
                    i++;
                }
                i = -1;
                if (i < 0) {
                } else if (length == 1) {
                    this.c = d;
                } else {
                    Object obj2 = new c[(length - 1)];
                    System.arraycopy(obj, 0, obj2, 0, i);
                    System.arraycopy(obj, i + 1, obj2, i, (length - i) - 1);
                    this.c = obj2;
                }
            }
        }

        public void b() {
            Object anonymousClass1 = new k<T>(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a_(T t) {
                    this.a.a_(t);
                }

                public void a(Throwable th) {
                    this.a.a(th);
                }

                public void o_() {
                    this.a.o_();
                }
            };
            this.b.a(anonymousClass1);
            this.a.a(anonymousClass1);
            this.f = true;
        }

        public void a_(T t) {
            if (!this.g) {
                b(this.e.a((Object) t));
                c();
            }
        }

        public void a(Throwable th) {
            if (!this.g) {
                this.g = true;
                b(this.e.a(th));
                this.b.n_();
                c();
            }
        }

        public void o_() {
            if (!this.g) {
                this.g = true;
                b(this.e.b());
                this.b.n_();
                c();
            }
        }

        void c() {
            for (c c : this.c) {
                c.c();
            }
        }
    }

    static final class b<T> extends AtomicBoolean implements d$f<T> {
        private static final long b = -2817751667698696782L;
        final a<T> a;

        public b(a<T> aVar) {
            this.a = aVar;
        }

        public void a(k<? super T> kVar) {
            dji.thirdparty.f.f cVar = new c(kVar, this.a);
            this.a.a((c) cVar);
            kVar.a((l) cVar);
            kVar.a(cVar);
            if (!get() && compareAndSet(false, true)) {
                this.a.b();
            }
        }
    }

    static final class c<T> extends AtomicLong implements dji.thirdparty.f.f, l {
        private static final long h = -2557562030197141021L;
        final k<? super T> a;
        final a<T> b;
        Object[] c;
        int d;
        int e;
        boolean f;
        boolean g;

        public c(k<? super T> kVar, a<T> aVar) {
            this.a = kVar;
            this.b = aVar;
        }

        public void a(long j) {
            long j2;
            long j3;
            do {
                j2 = get();
                if (j2 >= 0) {
                    j3 = j2 + j;
                    if (j3 < 0) {
                        j3 = IPositioningSession.NotSet;
                    }
                } else {
                    return;
                }
            } while (!compareAndSet(j2, j3));
            c();
        }

        public long b(long j) {
            return addAndGet(-j);
        }

        public boolean b() {
            return get() < 0;
        }

        public void n_() {
            if (get() >= 0 && getAndSet(-1) >= 0) {
                this.b.b(this);
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void c() {
            /*
            r15 = this;
            monitor-enter(r15);
            r0 = r15.f;	 Catch:{ all -> 0x0059 }
            if (r0 == 0) goto L_0x000a;
        L_0x0005:
            r0 = 1;
            r15.g = r0;	 Catch:{ all -> 0x0059 }
            monitor-exit(r15);	 Catch:{ all -> 0x0059 }
        L_0x0009:
            return;
        L_0x000a:
            r0 = 1;
            r15.f = r0;	 Catch:{ all -> 0x0059 }
            monitor-exit(r15);	 Catch:{ all -> 0x0059 }
            r1 = 0;
            r0 = r15.b;	 Catch:{ all -> 0x0050 }
            r8 = r0.e;	 Catch:{ all -> 0x0050 }
            r9 = r15.a;	 Catch:{ all -> 0x0050 }
        L_0x0015:
            r6 = r15.get();	 Catch:{ all -> 0x0050 }
            r2 = 0;
            r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
            if (r0 < 0) goto L_0x0009;
        L_0x001f:
            r0 = r15.b;	 Catch:{ all -> 0x0050 }
            r10 = r0.f();	 Catch:{ all -> 0x0050 }
            if (r10 == 0) goto L_0x00de;
        L_0x0027:
            r4 = r15.c;	 Catch:{ all -> 0x0050 }
            if (r4 != 0) goto L_0x0033;
        L_0x002b:
            r0 = r15.b;	 Catch:{ all -> 0x0050 }
            r4 = r0.d();	 Catch:{ all -> 0x0050 }
            r15.c = r4;	 Catch:{ all -> 0x0050 }
        L_0x0033:
            r0 = r4.length;	 Catch:{ all -> 0x0050 }
            r11 = r0 + -1;
            r3 = r15.e;	 Catch:{ all -> 0x0050 }
            r2 = r15.d;	 Catch:{ all -> 0x0050 }
            r12 = 0;
            r0 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
            if (r0 != 0) goto L_0x006e;
        L_0x0040:
            r0 = r4[r2];	 Catch:{ all -> 0x0050 }
            r2 = r8.b(r0);	 Catch:{ all -> 0x0050 }
            if (r2 == 0) goto L_0x005c;
        L_0x0048:
            r9.o_();	 Catch:{ all -> 0x0050 }
            r1 = 1;
            r15.n_();	 Catch:{ all -> 0x0050 }
            goto L_0x0009;
        L_0x0050:
            r0 = move-exception;
            if (r1 != 0) goto L_0x0058;
        L_0x0053:
            monitor-enter(r15);
            r1 = 0;
            r15.f = r1;	 Catch:{ all -> 0x00f3 }
            monitor-exit(r15);	 Catch:{ all -> 0x00f3 }
        L_0x0058:
            throw r0;
        L_0x0059:
            r0 = move-exception;
            monitor-exit(r15);	 Catch:{ all -> 0x0059 }
            throw r0;
        L_0x005c:
            r2 = r8.c(r0);	 Catch:{ all -> 0x0050 }
            if (r2 == 0) goto L_0x00de;
        L_0x0062:
            r0 = r8.h(r0);	 Catch:{ all -> 0x0050 }
            r9.a(r0);	 Catch:{ all -> 0x0050 }
            r1 = 1;
            r15.n_();	 Catch:{ all -> 0x0050 }
            goto L_0x0009;
        L_0x006e:
            r12 = 0;
            r0 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
            if (r0 <= 0) goto L_0x00de;
        L_0x0074:
            r0 = 0;
            r5 = r3;
            r3 = r0;
            r0 = r2;
            r2 = r4;
        L_0x0079:
            if (r5 >= r10) goto L_0x00ce;
        L_0x007b:
            r12 = 0;
            r4 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
            if (r4 <= 0) goto L_0x00ce;
        L_0x0081:
            r4 = r9.b();	 Catch:{ all -> 0x0050 }
            if (r4 != 0) goto L_0x0009;
        L_0x0087:
            if (r0 != r11) goto L_0x0093;
        L_0x0089:
            r0 = r2[r11];	 Catch:{ all -> 0x0050 }
            r0 = (java.lang.Object[]) r0;	 Catch:{ all -> 0x0050 }
            r0 = (java.lang.Object[]) r0;	 Catch:{ all -> 0x0050 }
            r2 = 0;
            r14 = r2;
            r2 = r0;
            r0 = r14;
        L_0x0093:
            r4 = r2[r0];	 Catch:{ all -> 0x0050 }
            r12 = r8.a(r9, r4);	 Catch:{ Throwable -> 0x00a1 }
            if (r12 == 0) goto L_0x00c2;
        L_0x009b:
            r1 = 1;
            r15.n_();	 Catch:{ Throwable -> 0x00a1 }
            goto L_0x0009;
        L_0x00a1:
            r0 = move-exception;
            dji.thirdparty.f.c.b.b(r0);	 Catch:{ all -> 0x0050 }
            r1 = 1;
            r15.n_();	 Catch:{ all -> 0x0050 }
            r2 = r8.c(r4);	 Catch:{ all -> 0x0050 }
            if (r2 != 0) goto L_0x0009;
        L_0x00af:
            r2 = r8.b(r4);	 Catch:{ all -> 0x0050 }
            if (r2 != 0) goto L_0x0009;
        L_0x00b5:
            r2 = r8.g(r4);	 Catch:{ all -> 0x0050 }
            r0 = dji.thirdparty.f.c.g.a(r0, r2);	 Catch:{ all -> 0x0050 }
            r9.a(r0);	 Catch:{ all -> 0x0050 }
            goto L_0x0009;
        L_0x00c2:
            r4 = r0 + 1;
            r5 = r5 + 1;
            r12 = 1;
            r6 = r6 - r12;
            r0 = r3 + 1;
            r3 = r0;
            r0 = r4;
            goto L_0x0079;
        L_0x00ce:
            r4 = r9.b();	 Catch:{ all -> 0x0050 }
            if (r4 != 0) goto L_0x0009;
        L_0x00d4:
            r15.e = r5;	 Catch:{ all -> 0x0050 }
            r15.d = r0;	 Catch:{ all -> 0x0050 }
            r15.c = r2;	 Catch:{ all -> 0x0050 }
            r2 = (long) r3;	 Catch:{ all -> 0x0050 }
            r15.b(r2);	 Catch:{ all -> 0x0050 }
        L_0x00de:
            monitor-enter(r15);	 Catch:{ all -> 0x0050 }
            r0 = r15.g;	 Catch:{ all -> 0x00ea }
            if (r0 != 0) goto L_0x00ed;
        L_0x00e3:
            r0 = 0;
            r15.f = r0;	 Catch:{ all -> 0x00ea }
            r1 = 1;
            monitor-exit(r15);	 Catch:{ all -> 0x00ea }
            goto L_0x0009;
        L_0x00ea:
            r0 = move-exception;
            monitor-exit(r15);	 Catch:{ all -> 0x00ea }
            throw r0;	 Catch:{ all -> 0x0050 }
        L_0x00ed:
            r0 = 0;
            r15.g = r0;	 Catch:{ all -> 0x00ea }
            monitor-exit(r15);	 Catch:{ all -> 0x00ea }
            goto L_0x0015;
        L_0x00f3:
            r0 = move-exception;
            monitor-exit(r15);	 Catch:{ all -> 0x00f3 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.h.c.c():void");
        }
    }

    public static <T> h<T> u(d<? extends T> dVar) {
        return e(dVar, 16);
    }

    public static <T> h<T> e(d<? extends T> dVar, int i) {
        if (i < 1) {
            throw new IllegalArgumentException("capacityHint > 0 required");
        }
        a aVar = new a(dVar, i);
        return new h(new b(aVar), aVar);
    }

    private h(d$f<T> dji_thirdparty_f_d_f_T, a<T> aVar) {
        super(dji_thirdparty_f_d_f_T);
        this.c = aVar;
    }

    boolean a() {
        return this.c.f;
    }

    boolean I() {
        return this.c.c.length != 0;
    }
}
