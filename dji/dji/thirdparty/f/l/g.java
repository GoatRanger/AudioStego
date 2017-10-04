package dji.thirdparty.f.l;

import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.c;
import dji.thirdparty.f.d.m;
import dji.thirdparty.f.e;
import dji.thirdparty.f.e.a.r;
import dji.thirdparty.f.k;
import dji.thirdparty.f.m.f;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

final class g<T> extends AtomicReference<a<T>> implements d$f<T> {
    volatile Object a;
    boolean b = true;
    c<b<T>> c = m.a();
    c<b<T>> d = m.a();
    c<b<T>> e = m.a();
    public final r<T> f = r.a();

    protected static final class a<T> {
        static final b[] c = new b[0];
        static final a d = new a(true, c);
        static final a e = new a(false, c);
        final boolean a;
        final b[] b;

        public a(boolean z, b[] bVarArr) {
            this.a = z;
            this.b = bVarArr;
        }

        public a a(b bVar) {
            int length = this.b.length;
            Object obj = new b[(length + 1)];
            System.arraycopy(this.b, 0, obj, 0, length);
            obj[length] = bVar;
            return new a(this.a, obj);
        }

        public a b(b bVar) {
            b[] bVarArr = this.b;
            int length = bVarArr.length;
            if (length == 1 && bVarArr[0] == bVar) {
                return e;
            }
            if (length == 0) {
                return this;
            }
            Object obj = new b[(length - 1)];
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3;
                b bVar2 = bVarArr[i];
                if (bVar2 == bVar) {
                    i3 = i2;
                } else if (i2 == length - 1) {
                    return this;
                } else {
                    i3 = i2 + 1;
                    obj[i2] = bVar2;
                }
                i++;
                i2 = i3;
            }
            if (i2 == 0) {
                return e;
            }
            b[] bVarArr2;
            if (i2 < length - 1) {
                bVarArr2 = new b[i2];
                System.arraycopy(obj, 0, bVarArr2, 0, i2);
            } else {
                Object obj2 = obj;
            }
            return new a(this.a, bVarArr2);
        }
    }

    protected static final class b<T> implements e<T> {
        final e<? super T> a;
        boolean b = true;
        boolean c;
        List<Object> d;
        boolean e;
        protected volatile boolean f;
        private volatile Object g;

        public b(e<? super T> eVar) {
            this.a = eVar;
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

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected void a(java.lang.Object r2, dji.thirdparty.f.e.a.r<T> r3) {
            /*
            r1 = this;
            r0 = r1.e;
            if (r0 != 0) goto L_0x0022;
        L_0x0004:
            monitor-enter(r1);
            r0 = 0;
            r1.b = r0;	 Catch:{ all -> 0x0028 }
            r0 = r1.c;	 Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x001e;
        L_0x000c:
            r0 = r1.d;	 Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x0017;
        L_0x0010:
            r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0028 }
            r0.<init>();	 Catch:{ all -> 0x0028 }
            r1.d = r0;	 Catch:{ all -> 0x0028 }
        L_0x0017:
            r0 = r1.d;	 Catch:{ all -> 0x0028 }
            r0.add(r2);	 Catch:{ all -> 0x0028 }
            monitor-exit(r1);	 Catch:{ all -> 0x0028 }
        L_0x001d:
            return;
        L_0x001e:
            monitor-exit(r1);	 Catch:{ all -> 0x0028 }
            r0 = 1;
            r1.e = r0;
        L_0x0022:
            r0 = r1.a;
            r3.a(r0, r2);
            goto L_0x001d;
        L_0x0028:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0028 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.l.g.b.a(java.lang.Object, dji.thirdparty.f.e.a.r):void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected void b(java.lang.Object r3, dji.thirdparty.f.e.a.r<T> r4) {
            /*
            r2 = this;
            r0 = 0;
            monitor-enter(r2);
            r1 = r2.b;	 Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x000a;
        L_0x0006:
            r1 = r2.c;	 Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x000c;
        L_0x000a:
            monitor-exit(r2);	 Catch:{ all -> 0x001c }
        L_0x000b:
            return;
        L_0x000c:
            r1 = 0;
            r2.b = r1;	 Catch:{ all -> 0x001c }
            if (r3 == 0) goto L_0x0012;
        L_0x0011:
            r0 = 1;
        L_0x0012:
            r2.c = r0;	 Catch:{ all -> 0x001c }
            monitor-exit(r2);	 Catch:{ all -> 0x001c }
            if (r3 == 0) goto L_0x000b;
        L_0x0017:
            r0 = 0;
            r2.a(r0, r3, r4);
            goto L_0x000b;
        L_0x001c:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x001c }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.l.g.b.b(java.lang.Object, dji.thirdparty.f.e.a.r):void");
        }

        protected void a(List<Object> list, Object obj, r<T> rVar) {
            Object obj2 = 1;
            Object obj3 = null;
            Object obj4 = 1;
            while (true) {
                if (list != null) {
                    for (Object c : list) {
                        c(c, rVar);
                    }
                }
                if (obj4 != null) {
                    try {
                        c(obj, rVar);
                        obj4 = null;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                    }
                }
                synchronized (this) {
                    list = this.d;
                    this.d = null;
                    if (list == null) {
                        this.c = false;
                        try {
                            return;
                        } catch (Throwable th3) {
                            th2 = th3;
                            try {
                                throw th2;
                            } catch (Throwable th4) {
                                th2 = th4;
                                obj3 = obj2;
                            }
                        }
                    } else {
                        try {
                        } catch (Throwable th5) {
                            th2 = th5;
                            obj2 = null;
                        }
                    }
                }
            }
            if (obj3 == null) {
                synchronized (this) {
                    this.c = false;
                }
            }
            throw th2;
        }

        protected void c(Object obj, r<T> rVar) {
            if (obj != null) {
                rVar.a(this.a, obj);
            }
        }

        protected e<? super T> b() {
            return this.a;
        }

        public <I> I c() {
            return this.g;
        }

        public void b(Object obj) {
            this.g = obj;
        }
    }

    public g() {
        super(a.e);
    }

    public void a(k<? super T> kVar) {
        b bVar = new b(kVar);
        a(kVar, bVar);
        this.c.a(bVar);
        if (!kVar.b() && a(bVar) && kVar.b()) {
            b(bVar);
        }
    }

    void a(k<? super T> kVar, final b<T> bVar) {
        kVar.a(f.a(new dji.thirdparty.f.d.b(this) {
            final /* synthetic */ g b;

            public void a() {
                this.b.b(bVar);
            }
        }));
    }

    void b(Object obj) {
        this.a = obj;
    }

    Object a() {
        return this.a;
    }

    b<T>[] b() {
        return ((a) get()).b;
    }

    boolean a(b<T> bVar) {
        a aVar;
        do {
            aVar = (a) get();
            if (aVar.a) {
                this.e.a(bVar);
                return false;
            }
        } while (!compareAndSet(aVar, aVar.a(bVar)));
        this.d.a(bVar);
        return true;
    }

    void b(b<T> bVar) {
        a aVar;
        a b;
        do {
            aVar = (a) get();
            if (!aVar.a) {
                b = aVar.b(bVar);
                if (b == aVar) {
                    return;
                }
            } else {
                return;
            }
        } while (!compareAndSet(aVar, b));
    }

    b<T>[] c(Object obj) {
        b(obj);
        return ((a) get()).b;
    }

    b<T>[] d(Object obj) {
        b(obj);
        this.b = false;
        if (((a) get()).a) {
            return a.c;
        }
        return ((a) getAndSet(a.d)).b;
    }
}
