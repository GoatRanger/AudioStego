package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.e.d.b.an;
import dji.thirdparty.f.e.d.b.z;
import dji.thirdparty.f.e.d.j;
import dji.thirdparty.f.e.d.o;
import dji.thirdparty.f.f;
import dji.thirdparty.f.f.c;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class by<T> extends c<T> {
    final d<? extends T> c;
    final AtomicReference<b<T>> d;

    static final class a<T> extends AtomicLong implements f, l {
        static final long c = Long.MIN_VALUE;
        static final long d = -4611686018427387904L;
        private static final long e = -4453897557930727610L;
        final b<T> a;
        final k<? super T> b;

        public a(b<T> bVar, k<? super T> kVar) {
            this.a = bVar;
            this.b = kVar;
            lazySet(d);
        }

        public void a(long j) {
            if (j >= 0) {
                long j2;
                long j3;
                do {
                    j2 = get();
                    if (j2 == Long.MIN_VALUE) {
                        return;
                    }
                    if (j2 >= 0 && j == 0) {
                        return;
                    }
                    if (j2 == d) {
                        j3 = j;
                    } else {
                        j3 = j2 + j;
                        if (j3 < 0) {
                            j3 = IPositioningSession.NotSet;
                        }
                    }
                } while (!compareAndSet(j2, j3));
                this.a.e();
            }
        }

        public long b(long j) {
            if (j <= 0) {
                throw new IllegalArgumentException("Cant produce zero or less");
            }
            long j2;
            long j3;
            do {
                j3 = get();
                if (j3 == d) {
                    throw new IllegalStateException("Produced without request");
                } else if (j3 == Long.MIN_VALUE) {
                    return Long.MIN_VALUE;
                } else {
                    j2 = j3 - j;
                    if (j2 < 0) {
                        throw new IllegalStateException("More produced (" + j + ") than requested (" + j3 + ")");
                    }
                }
            } while (!compareAndSet(j3, j2));
            return j2;
        }

        public boolean b() {
            return get() == Long.MIN_VALUE;
        }

        public void n_() {
            if (get() != Long.MIN_VALUE && getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.a.b(this);
                this.a.e();
            }
        }
    }

    static final class b<T> extends k<T> implements l {
        static final a[] e = new a[0];
        static final a[] f = new a[0];
        final Queue<Object> a;
        final r<T> b;
        final AtomicReference<b<T>> c;
        volatile Object d;
        final AtomicReference<a[]> g;
        final AtomicBoolean h;
        boolean i;
        boolean j;

        public b(AtomicReference<b<T>> atomicReference) {
            this.a = an.a() ? new z(j.c) : new o(j.c);
            this.b = r.a();
            this.g = new AtomicReference(e);
            this.c = atomicReference;
            this.h = new AtomicBoolean();
        }

        void d() {
            a(dji.thirdparty.f.m.f.a(new dji.thirdparty.f.d.b(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.g.getAndSet(b.f);
                    this.a.c.compareAndSet(this.a, null);
                }
            }));
        }

        public void c() {
            a((long) j.c);
        }

        public void a_(T t) {
            if (this.a.offer(this.b.a((Object) t))) {
                e();
            } else {
                a(new dji.thirdparty.f.c.c());
            }
        }

        public void a(Throwable th) {
            if (this.d == null) {
                this.d = this.b.a(th);
                e();
            }
        }

        public void o_() {
            if (this.d == null) {
                this.d = this.b.b();
                e();
            }
        }

        boolean a(a<T> aVar) {
            if (aVar == null) {
                throw new NullPointerException();
            }
            a[] aVarArr;
            Object obj;
            do {
                aVarArr = (a[]) this.g.get();
                if (aVarArr == f) {
                    return false;
                }
                int length = aVarArr.length;
                obj = new a[(length + 1)];
                System.arraycopy(aVarArr, 0, obj, 0, length);
                obj[length] = aVar;
            } while (!this.g.compareAndSet(aVarArr, obj));
            return true;
        }

        void b(a<T> aVar) {
            a[] aVarArr;
            Object obj;
            do {
                aVarArr = (a[]) this.g.get();
                if (aVarArr != e && aVarArr != f) {
                    int i = -1;
                    int length = aVarArr.length;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (aVarArr[i2].equals(aVar)) {
                            i = i2;
                            break;
                        }
                    }
                    if (i < 0) {
                        return;
                    }
                    if (length == 1) {
                        obj = e;
                    } else {
                        obj = new a[(length - 1)];
                        System.arraycopy(aVarArr, 0, obj, 0, i);
                        System.arraycopy(aVarArr, i + 1, obj, i, (length - i) - 1);
                    }
                } else {
                    return;
                }
            } while (!this.g.compareAndSet(aVarArr, obj));
        }

        boolean a(Object obj, boolean z) {
            int i = 0;
            if (obj != null) {
                a[] aVarArr;
                if (!this.b.b(obj)) {
                    Throwable h = this.b.h(obj);
                    this.c.compareAndSet(this, null);
                    try {
                        aVarArr = (a[]) this.g.getAndSet(f);
                        int length = aVarArr.length;
                        while (i < length) {
                            aVarArr[i].b.a(h);
                            i++;
                        }
                        return true;
                    } finally {
                        n_();
                    }
                } else if (z) {
                    this.c.compareAndSet(this, null);
                    try {
                        aVarArr = (a[]) this.g.getAndSet(f);
                        int length2 = aVarArr.length;
                        while (i < length2) {
                            aVarArr[i].b.o_();
                            i++;
                        }
                        return true;
                    } finally {
                        n_();
                    }
                }
            }
            return false;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void e() {
            /*
            r19 = this;
            monitor-enter(r19);
            r0 = r19;
            r2 = r0.i;	 Catch:{ all -> 0x005e }
            if (r2 == 0) goto L_0x000e;
        L_0x0007:
            r2 = 1;
            r0 = r19;
            r0.j = r2;	 Catch:{ all -> 0x005e }
            monitor-exit(r19);	 Catch:{ all -> 0x005e }
        L_0x000d:
            return;
        L_0x000e:
            r2 = 1;
            r0 = r19;
            r0.i = r2;	 Catch:{ all -> 0x005e }
            r2 = 0;
            r0 = r19;
            r0.j = r2;	 Catch:{ all -> 0x005e }
            monitor-exit(r19);	 Catch:{ all -> 0x005e }
            r6 = 0;
        L_0x001a:
            r0 = r19;
            r2 = r0.d;	 Catch:{ all -> 0x008b }
            r0 = r19;
            r3 = r0.a;	 Catch:{ all -> 0x008b }
            r7 = r3.isEmpty();	 Catch:{ all -> 0x008b }
            r0 = r19;
            r2 = r0.a(r2, r7);	 Catch:{ all -> 0x008b }
            if (r2 != 0) goto L_0x000d;
        L_0x002e:
            if (r7 != 0) goto L_0x00ca;
        L_0x0030:
            r0 = r19;
            r2 = r0.g;	 Catch:{ all -> 0x008b }
            r2 = r2.get();	 Catch:{ all -> 0x008b }
            r2 = (dji.thirdparty.f.e.a.by.a[]) r2;	 Catch:{ all -> 0x008b }
            r10 = r2.length;	 Catch:{ all -> 0x008b }
            r8 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r4 = 0;
            r11 = r2.length;	 Catch:{ all -> 0x008b }
            r3 = 0;
            r18 = r3;
            r3 = r4;
            r4 = r8;
            r8 = r18;
        L_0x0049:
            if (r8 >= r11) goto L_0x006a;
        L_0x004b:
            r9 = r2[r8];	 Catch:{ all -> 0x008b }
            r12 = r9.get();	 Catch:{ all -> 0x008b }
            r14 = 0;
            r9 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1));
            if (r9 < 0) goto L_0x0061;
        L_0x0057:
            r4 = java.lang.Math.min(r4, r12);	 Catch:{ all -> 0x008b }
        L_0x005b:
            r8 = r8 + 1;
            goto L_0x0049;
        L_0x005e:
            r2 = move-exception;
            monitor-exit(r19);	 Catch:{ all -> 0x005e }
            throw r2;
        L_0x0061:
            r14 = -9223372036854775808;
            r9 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1));
            if (r9 != 0) goto L_0x005b;
        L_0x0067:
            r3 = r3 + 1;
            goto L_0x005b;
        L_0x006a:
            if (r10 != r3) goto L_0x0098;
        L_0x006c:
            r0 = r19;
            r3 = r0.d;	 Catch:{ all -> 0x008b }
            r0 = r19;
            r2 = r0.a;	 Catch:{ all -> 0x008b }
            r2 = r2.poll();	 Catch:{ all -> 0x008b }
            if (r2 != 0) goto L_0x0096;
        L_0x007a:
            r2 = 1;
        L_0x007b:
            r0 = r19;
            r2 = r0.a(r3, r2);	 Catch:{ all -> 0x008b }
            if (r2 != 0) goto L_0x000d;
        L_0x0083:
            r2 = 1;
            r0 = r19;
            r0.a(r2);	 Catch:{ all -> 0x008b }
            goto L_0x001a;
        L_0x008b:
            r2 = move-exception;
        L_0x008c:
            if (r6 != 0) goto L_0x0095;
        L_0x008e:
            monitor-enter(r19);
            r3 = 0;
            r0 = r19;
            r0.i = r3;	 Catch:{ all -> 0x0123 }
            monitor-exit(r19);	 Catch:{ all -> 0x0123 }
        L_0x0095:
            throw r2;
        L_0x0096:
            r2 = 0;
            goto L_0x007b;
        L_0x0098:
            r3 = 0;
            r8 = r3;
            r3 = r7;
        L_0x009b:
            r10 = (long) r8;
            r7 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1));
            if (r7 >= 0) goto L_0x0126;
        L_0x00a0:
            r0 = r19;
            r3 = r0.d;	 Catch:{ all -> 0x008b }
            r0 = r19;
            r7 = r0.a;	 Catch:{ all -> 0x008b }
            r9 = r7.poll();	 Catch:{ all -> 0x008b }
            if (r9 != 0) goto L_0x00e0;
        L_0x00ae:
            r7 = 1;
        L_0x00af:
            r0 = r19;
            r3 = r0.a(r3, r7);	 Catch:{ all -> 0x008b }
            if (r3 != 0) goto L_0x000d;
        L_0x00b7:
            if (r7 == 0) goto L_0x00e2;
        L_0x00b9:
            r2 = r7;
        L_0x00ba:
            if (r8 <= 0) goto L_0x00c2;
        L_0x00bc:
            r8 = (long) r8;	 Catch:{ all -> 0x008b }
            r0 = r19;
            r0.a(r8);	 Catch:{ all -> 0x008b }
        L_0x00c2:
            r8 = 0;
            r3 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
            if (r3 == 0) goto L_0x00ca;
        L_0x00c8:
            if (r2 == 0) goto L_0x001a;
        L_0x00ca:
            monitor-enter(r19);	 Catch:{ all -> 0x008b }
            r0 = r19;
            r2 = r0.j;	 Catch:{ all -> 0x0120 }
            if (r2 != 0) goto L_0x0118;
        L_0x00d1:
            r2 = 0;
            r0 = r19;
            r0.i = r2;	 Catch:{ all -> 0x0120 }
            r3 = 1;
            monitor-exit(r19);	 Catch:{ all -> 0x00da }
            goto L_0x000d;
        L_0x00da:
            r2 = move-exception;
        L_0x00db:
            monitor-exit(r19);	 Catch:{ all -> 0x00da }
            throw r2;	 Catch:{ all -> 0x00dd }
        L_0x00dd:
            r2 = move-exception;
            r6 = r3;
            goto L_0x008c;
        L_0x00e0:
            r7 = 0;
            goto L_0x00af;
        L_0x00e2:
            r0 = r19;
            r3 = r0.b;	 Catch:{ all -> 0x008b }
            r10 = r3.g(r9);	 Catch:{ all -> 0x008b }
            r11 = r2.length;	 Catch:{ all -> 0x008b }
            r3 = 0;
            r9 = r3;
        L_0x00ed:
            if (r9 >= r11) goto L_0x0113;
        L_0x00ef:
            r12 = r2[r9];	 Catch:{ all -> 0x008b }
            r14 = r12.get();	 Catch:{ all -> 0x008b }
            r16 = 0;
            r3 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
            if (r3 <= 0) goto L_0x0105;
        L_0x00fb:
            r3 = r12.b;	 Catch:{ Throwable -> 0x0109 }
            r3.a_(r10);	 Catch:{ Throwable -> 0x0109 }
            r14 = 1;
            r12.b(r14);	 Catch:{ all -> 0x008b }
        L_0x0105:
            r3 = r9 + 1;
            r9 = r3;
            goto L_0x00ed;
        L_0x0109:
            r3 = move-exception;
            r12.n_();	 Catch:{ all -> 0x008b }
            r12 = r12.b;	 Catch:{ all -> 0x008b }
            dji.thirdparty.f.c.b.a(r3, r12, r10);	 Catch:{ all -> 0x008b }
            goto L_0x0105;
        L_0x0113:
            r3 = r8 + 1;
            r8 = r3;
            r3 = r7;
            goto L_0x009b;
        L_0x0118:
            r2 = 0;
            r0 = r19;
            r0.j = r2;	 Catch:{ all -> 0x0120 }
            monitor-exit(r19);	 Catch:{ all -> 0x0120 }
            goto L_0x001a;
        L_0x0120:
            r2 = move-exception;
            r3 = r6;
            goto L_0x00db;
        L_0x0123:
            r2 = move-exception;
            monitor-exit(r19);	 Catch:{ all -> 0x0123 }
            throw r2;
        L_0x0126:
            r2 = r3;
            goto L_0x00ba;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.by.b.e():void");
        }
    }

    public static <T> c<T> u(d<? extends T> dVar) {
        final AtomicReference atomicReference = new AtomicReference();
        return new by(new d$f<T>() {
            public void a(k<? super T> kVar) {
                while (true) {
                    b bVar = (b) atomicReference.get();
                    if (bVar == null || bVar.b()) {
                        b bVar2 = new b(atomicReference);
                        bVar2.d();
                        if (atomicReference.compareAndSet(bVar, bVar2)) {
                            bVar = bVar2;
                        } else {
                            continue;
                        }
                    }
                    f aVar = new a(bVar, kVar);
                    if (bVar.a((a) aVar)) {
                        kVar.a((l) aVar);
                        kVar.a(aVar);
                        return;
                    }
                }
            }
        }, dVar, atomicReference);
    }

    public static <T, R> d<R> c(d<? extends T> dVar, dji.thirdparty.f.d.o<? super d<T>, ? extends d<R>> oVar) {
        return a(dVar, oVar, false);
    }

    public static <T, R> d<R> a(final d<? extends T> dVar, final dji.thirdparty.f.d.o<? super d<T>, ? extends d<R>> oVar, final boolean z) {
        return a(new d$f<R>() {
            public void a(final k<? super R> kVar) {
                final l afVar = new af(j.c, z);
                l anonymousClass1 = new k<R>(this) {
                    final /* synthetic */ AnonymousClass2 c;

                    public void a_(R r) {
                        kVar.a_(r);
                    }

                    public void a(Throwable th) {
                        afVar.n_();
                        kVar.a(th);
                    }

                    public void o_() {
                        afVar.n_();
                        kVar.o_();
                    }

                    public void a(f fVar) {
                        kVar.a(fVar);
                    }
                };
                kVar.a(afVar);
                kVar.a(anonymousClass1);
                ((d) oVar.a(d.a(afVar))).a(anonymousClass1);
                dVar.a(afVar.e());
            }
        });
    }

    private by(d$f<T> dji_thirdparty_f_d_f_T, d<? extends T> dVar, AtomicReference<b<T>> atomicReference) {
        super(dji_thirdparty_f_d_f_T);
        this.c = dVar;
        this.d = atomicReference;
    }

    public void h(dji.thirdparty.f.d.c<? super l> cVar) {
        k kVar;
        k bVar;
        boolean z;
        do {
            kVar = (b) this.d.get();
            if (kVar != null && !kVar.b()) {
                break;
            }
            bVar = new b(this.d);
            bVar.d();
        } while (!this.d.compareAndSet(kVar, bVar));
        kVar = bVar;
        if (kVar.h.get() || !kVar.h.compareAndSet(false, true)) {
            z = false;
        } else {
            z = true;
        }
        cVar.a(kVar);
        if (z) {
            this.c.a(kVar);
        }
    }
}
