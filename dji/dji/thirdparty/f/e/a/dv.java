package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.e;
import dji.thirdparty.f.e.d.a.h;
import dji.thirdparty.f.e.d.a.i;
import dji.thirdparty.f.e.d.b.ag;
import dji.thirdparty.f.e.d.b.ah;
import dji.thirdparty.f.e.d.b.an;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l.f;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class dv<T> extends f<T, T> {
    final a<T> c;

    static final class a<T> extends AtomicLong implements d$f<T>, b, e<T>, dji.thirdparty.f.f {
        private static final long j = -9044104859202255786L;
        final AtomicReference<k<? super T>> a = new AtomicReference();
        final Queue<Object> b;
        final r<T> c = r.a();
        final AtomicReference<b> d;
        Throwable e;
        volatile boolean f;
        boolean g;
        boolean h;
        volatile boolean i;

        public a(int i, b bVar) {
            this.d = bVar != null ? new AtomicReference(bVar) : null;
            Queue ahVar = i > 1 ? an.a() ? new ah(i) : new i(i) : an.a() ? new ag() : new h();
            this.b = ahVar;
        }

        public void a_(T t) {
            if (!this.f) {
                if (!this.i) {
                    Object obj = null;
                    synchronized (this) {
                        if (!this.i) {
                            this.b.offer(this.c.a((Object) t));
                            obj = 1;
                        }
                    }
                    if (obj != null) {
                        b();
                        return;
                    }
                }
                k kVar = (k) this.a.get();
                try {
                    kVar.a_(t);
                } catch (Throwable th) {
                    dji.thirdparty.f.c.b.a(th, kVar, t);
                }
            }
        }

        public void a(Throwable th) {
            boolean z = true;
            if (!this.f) {
                c();
                this.e = th;
                this.f = true;
                if (!this.i) {
                    synchronized (this) {
                        if (this.i) {
                            z = false;
                        }
                    }
                    if (z) {
                        b();
                        return;
                    }
                }
                ((k) this.a.get()).a(th);
            }
        }

        public void o_() {
            boolean z = true;
            if (!this.f) {
                c();
                this.f = true;
                if (!this.i) {
                    synchronized (this) {
                        if (this.i) {
                            z = false;
                        }
                    }
                    if (z) {
                        b();
                        return;
                    }
                }
                ((k) this.a.get()).o_();
            }
        }

        public void a(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            } else if (j > 0) {
                a.a((AtomicLong) this, j);
                b();
            } else if (this.f) {
                b();
            }
        }

        public void a(k<? super T> kVar) {
            if (this.a.compareAndSet(null, kVar)) {
                kVar.a(dji.thirdparty.f.m.f.a((b) this));
                kVar.a((dji.thirdparty.f.f) this);
                return;
            }
            kVar.a(new IllegalStateException("Only a single subscriber is allowed"));
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void b() {
            /*
            r10 = this;
            monitor-enter(r10);
            r0 = r10.g;	 Catch:{ all -> 0x0074 }
            if (r0 == 0) goto L_0x000a;
        L_0x0005:
            r0 = 1;
            r10.h = r0;	 Catch:{ all -> 0x0074 }
            monitor-exit(r10);	 Catch:{ all -> 0x0074 }
        L_0x0009:
            return;
        L_0x000a:
            r0 = 1;
            r10.g = r0;	 Catch:{ all -> 0x0074 }
            monitor-exit(r10);	 Catch:{ all -> 0x0074 }
            r8 = r10.b;
        L_0x0010:
            r0 = r10.a;
            r0 = r0.get();
            r0 = (dji.thirdparty.f.k) r0;
            r1 = 0;
            if (r0 == 0) goto L_0x005c;
        L_0x001b:
            r1 = r10.f;
            r2 = r8.isEmpty();
            r1 = r10.a(r1, r2, r0);
            if (r1 != 0) goto L_0x0009;
        L_0x0027:
            r4 = r10.get();
            r2 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
            if (r1 != 0) goto L_0x0077;
        L_0x0034:
            r1 = 1;
        L_0x0035:
            r2 = 0;
            r6 = r4;
            r4 = r2;
        L_0x0039:
            r2 = 0;
            r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
            if (r2 == 0) goto L_0x0050;
        L_0x003f:
            r3 = r10.f;
            r9 = r8.poll();
            if (r9 != 0) goto L_0x0079;
        L_0x0047:
            r2 = 1;
        L_0x0048:
            r3 = r10.a(r3, r2, r0);
            if (r3 != 0) goto L_0x0009;
        L_0x004e:
            if (r2 == 0) goto L_0x007b;
        L_0x0050:
            if (r1 != 0) goto L_0x005c;
        L_0x0052:
            r2 = 0;
            r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
            if (r0 == 0) goto L_0x005c;
        L_0x0058:
            r2 = -r4;
            r10.addAndGet(r2);
        L_0x005c:
            monitor-enter(r10);
            r0 = r10.h;	 Catch:{ all -> 0x0071 }
            if (r0 != 0) goto L_0x009c;
        L_0x0061:
            if (r1 == 0) goto L_0x006c;
        L_0x0063:
            r0 = r8.isEmpty();	 Catch:{ all -> 0x0071 }
            if (r0 == 0) goto L_0x006c;
        L_0x0069:
            r0 = 1;
            r10.i = r0;	 Catch:{ all -> 0x0071 }
        L_0x006c:
            r0 = 0;
            r10.g = r0;	 Catch:{ all -> 0x0071 }
            monitor-exit(r10);	 Catch:{ all -> 0x0071 }
            goto L_0x0009;
        L_0x0071:
            r0 = move-exception;
            monitor-exit(r10);	 Catch:{ all -> 0x0071 }
            throw r0;
        L_0x0074:
            r0 = move-exception;
            monitor-exit(r10);	 Catch:{ all -> 0x0074 }
            throw r0;
        L_0x0077:
            r1 = 0;
            goto L_0x0035;
        L_0x0079:
            r2 = 0;
            goto L_0x0048;
        L_0x007b:
            r2 = r10.c;
            r2 = r2.g(r9);
            r0.a_(r2);	 Catch:{ Throwable -> 0x008c }
            r2 = 1;
            r6 = r6 - r2;
            r2 = 1;
            r2 = r2 + r4;
            r4 = r2;
            goto L_0x0039;
        L_0x008c:
            r1 = move-exception;
            r8.clear();
            dji.thirdparty.f.c.b.b(r1);
            r1 = dji.thirdparty.f.c.g.a(r1, r2);
            r0.a(r1);
            goto L_0x0009;
        L_0x009c:
            r0 = 0;
            r10.h = r0;	 Catch:{ all -> 0x0071 }
            monitor-exit(r10);	 Catch:{ all -> 0x0071 }
            goto L_0x0010;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.dv.a.b():void");
        }

        public void a() {
            c();
            this.f = true;
            synchronized (this) {
                if (this.g) {
                    return;
                }
                this.g = true;
                this.b.clear();
            }
        }

        boolean a(boolean z, boolean z2, k<? super T> kVar) {
            if (kVar.b()) {
                this.b.clear();
                return true;
            }
            if (z) {
                Throwable th = this.e;
                if (th != null) {
                    this.b.clear();
                    kVar.a(th);
                    return true;
                } else if (z2) {
                    kVar.o_();
                    return true;
                }
            }
            return false;
        }

        void c() {
            AtomicReference atomicReference = this.d;
            if (atomicReference != null) {
                b bVar = (b) atomicReference.get();
                if (bVar != null && atomicReference.compareAndSet(bVar, null)) {
                    bVar.a();
                }
            }
        }
    }

    public static <T> dv<T> I() {
        return n(16);
    }

    public static <T> dv<T> n(int i) {
        return new dv(new a(i, null));
    }

    public static <T> dv<T> a(int i, b bVar) {
        return new dv(new a(i, bVar));
    }

    private dv(a<T> aVar) {
        super(aVar);
        this.c = aVar;
    }

    public void a_(T t) {
        this.c.a_(t);
    }

    public void a(Throwable th) {
        this.c.a(th);
    }

    public void o_() {
        this.c.o_();
    }

    public boolean J() {
        return this.c.a.get() != null;
    }
}
