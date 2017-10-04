package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.c.c;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.e;
import dji.thirdparty.f.e.d.b.an;
import dji.thirdparty.f.e.d.b.z;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class af<T> extends AtomicInteger implements d$f<T>, e<T>, l {
    static final b<?>[] i = new b[0];
    static final b<?>[] j = new b[0];
    private static final long k = -3741892510772238743L;
    final Queue<T> a;
    final int b;
    final boolean c;
    final a<T> d;
    volatile boolean e;
    Throwable f;
    volatile f g;
    volatile b<T>[] h;

    static final class a<T> extends k<T> {
        final af<T> a;

        public a(af<T> afVar) {
            this.a = afVar;
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

        public void a(f fVar) {
            this.a.a(fVar);
        }
    }

    static final class b<T> extends AtomicLong implements f, l {
        private static final long d = 960704844171597367L;
        final k<? super T> a;
        final af<T> b;
        final AtomicBoolean c = new AtomicBoolean();

        public b(k<? super T> kVar, af<T> afVar) {
            this.a = kVar;
            this.b = afVar;
        }

        public void a(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            } else if (j != 0) {
                a.a((AtomicLong) this, j);
                this.b.c();
            }
        }

        public boolean b() {
            return this.c.get();
        }

        public void n_() {
            if (this.c.compareAndSet(false, true)) {
                this.b.b(this);
            }
        }
    }

    public af(int i, boolean z) {
        if (i <= 0) {
            throw new IllegalArgumentException("prefetch > 0 required but it was " + i);
        }
        this.b = i;
        this.c = z;
        if (an.a()) {
            this.a = new z(i);
        } else {
            this.a = new dji.thirdparty.f.e.d.a.e(i);
        }
        this.h = i;
        this.d = new a(this);
    }

    public void a(k<? super T> kVar) {
        b bVar = new b(kVar, this);
        kVar.a((l) bVar);
        kVar.a((f) bVar);
        if (!a(bVar)) {
            Throwable th = this.f;
            if (th != null) {
                kVar.a(th);
            } else {
                kVar.o_();
            }
        } else if (bVar.b()) {
            b(bVar);
        } else {
            c();
        }
    }

    public void a_(T t) {
        if (!this.a.offer(t)) {
            this.d.n_();
            this.f = new c("Queue full?!");
            this.e = true;
        }
        c();
    }

    public void a(Throwable th) {
        this.f = th;
        this.e = true;
        c();
    }

    public void o_() {
        this.e = true;
        c();
    }

    void a(f fVar) {
        this.g = fVar;
        fVar.a((long) this.b);
    }

    void c() {
        if (getAndIncrement() == 0) {
            Queue queue = this.a;
            int i = 0;
            do {
                b[] bVarArr = this.h;
                int length = bVarArr.length;
                int length2 = bVarArr.length;
                long j = IPositioningSession.NotSet;
                int i2 = 0;
                while (i2 < length2) {
                    i2++;
                    j = Math.min(j, bVarArr[i2].get());
                }
                if (length != 0) {
                    long j2 = 0;
                    while (j2 != j) {
                        boolean z = this.e;
                        Object poll = queue.poll();
                        boolean z2 = poll == null;
                        if (!a(z, z2)) {
                            if (z2) {
                                break;
                            }
                            for (b bVar : bVarArr) {
                                bVar.a.a_(poll);
                            }
                            j2 = 1 + j2;
                        } else {
                            return;
                        }
                    }
                    if (j2 != j || !a(this.e, queue.isEmpty())) {
                        if (j2 != 0) {
                            f fVar = this.g;
                            if (fVar != null) {
                                fVar.a(j2);
                            }
                            for (AtomicLong b : bVarArr) {
                                a.b(b, j2);
                            }
                        }
                    } else {
                        return;
                    }
                }
                i = addAndGet(-i);
            } while (i != 0);
        }
    }

    boolean a(boolean z, boolean z2) {
        int i = 0;
        if (z) {
            int length;
            b[] d;
            int length2;
            if (!this.c) {
                Throwable th = this.f;
                if (th != null) {
                    this.a.clear();
                    b[] d2 = d();
                    length = d2.length;
                    while (i < length) {
                        d2[i].a.a(th);
                        i++;
                    }
                    return true;
                } else if (z2) {
                    d = d();
                    length2 = d.length;
                    while (i < length2) {
                        d[i].a.o_();
                        i++;
                    }
                    return true;
                }
            } else if (z2) {
                d = d();
                Throwable th2 = this.f;
                if (th2 != null) {
                    length = d.length;
                    while (i < length) {
                        d[i].a.a(th2);
                        i++;
                    }
                    return true;
                }
                length2 = d.length;
                while (i < length2) {
                    d[i].a.o_();
                    i++;
                }
                return true;
            }
        }
        return false;
    }

    b<T>[] d() {
        b<T>[] bVarArr = this.h;
        if (bVarArr == j) {
            return bVarArr;
        }
        b<T>[] bVarArr2;
        synchronized (this) {
            bVarArr2 = this.h;
            if (bVarArr2 != j) {
                this.h = j;
            }
        }
        return bVarArr2;
    }

    boolean a(b<T> bVar) {
        boolean z = false;
        if (this.h != j) {
            synchronized (this) {
                Object obj = this.h;
                if (obj == j) {
                } else {
                    int length = obj.length;
                    Object obj2 = new b[(length + 1)];
                    System.arraycopy(obj, 0, obj2, 0, length);
                    obj2[length] = bVar;
                    this.h = obj2;
                    z = true;
                }
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void b(dji.thirdparty.f.e.a.af.b<T> r7) {
        /*
        r6 = this;
        r0 = 0;
        r1 = r6.h;
        r2 = j;
        if (r1 == r2) goto L_0x000b;
    L_0x0007:
        r2 = i;
        if (r1 != r2) goto L_0x000c;
    L_0x000b:
        return;
    L_0x000c:
        monitor-enter(r6);
        r2 = r6.h;	 Catch:{ all -> 0x0019 }
        r1 = j;	 Catch:{ all -> 0x0019 }
        if (r2 == r1) goto L_0x0017;
    L_0x0013:
        r1 = i;	 Catch:{ all -> 0x0019 }
        if (r2 != r1) goto L_0x001c;
    L_0x0017:
        monitor-exit(r6);	 Catch:{ all -> 0x0019 }
        goto L_0x000b;
    L_0x0019:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0019 }
        throw r0;
    L_0x001c:
        r1 = -1;
        r3 = r2.length;	 Catch:{ all -> 0x0019 }
    L_0x001e:
        if (r0 >= r3) goto L_0x0025;
    L_0x0020:
        r4 = r2[r0];	 Catch:{ all -> 0x0019 }
        if (r4 != r7) goto L_0x0029;
    L_0x0024:
        r1 = r0;
    L_0x0025:
        if (r1 >= 0) goto L_0x002c;
    L_0x0027:
        monitor-exit(r6);	 Catch:{ all -> 0x0019 }
        goto L_0x000b;
    L_0x0029:
        r0 = r0 + 1;
        goto L_0x001e;
    L_0x002c:
        r0 = 1;
        if (r3 != r0) goto L_0x0037;
    L_0x002f:
        r0 = i;	 Catch:{ all -> 0x0019 }
        r0 = (dji.thirdparty.f.e.a.af.b[]) r0;	 Catch:{ all -> 0x0019 }
    L_0x0033:
        r6.h = r0;	 Catch:{ all -> 0x0019 }
        monitor-exit(r6);	 Catch:{ all -> 0x0019 }
        goto L_0x000b;
    L_0x0037:
        r0 = r3 + -1;
        r0 = new dji.thirdparty.f.e.a.af.b[r0];	 Catch:{ all -> 0x0019 }
        r4 = 0;
        r5 = 0;
        java.lang.System.arraycopy(r2, r4, r0, r5, r1);	 Catch:{ all -> 0x0019 }
        r4 = r1 + 1;
        r3 = r3 - r1;
        r3 = r3 + -1;
        java.lang.System.arraycopy(r2, r4, r0, r1, r3);	 Catch:{ all -> 0x0019 }
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.af.b(dji.thirdparty.f.e.a.af$b):void");
    }

    public k<T> e() {
        return this.d;
    }

    public void n_() {
        this.d.n_();
    }

    public boolean b() {
        return this.d.b();
    }
}
