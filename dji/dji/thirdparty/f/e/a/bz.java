package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.j.i;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class bz<T> extends dji.thirdparty.f.f.c<T> {
    static final n f = new n() {
        public Object call() {
            return new h(16);
        }
    };
    final dji.thirdparty.f.d<? extends T> c;
    final AtomicReference<e<T>> d;
    final n<? extends d<T>> e;

    interface d<T> {
        void a(b<T> bVar);

        void a(T t);

        void a(Throwable th);

        void b();
    }

    static class a<T> extends AtomicReference<c> implements d<T> {
        private static final long e = 2346567790059478686L;
        final r<T> a = r.a();
        c b;
        int c;
        long d;

        public a() {
            c cVar = new c(null, 0);
            this.b = cVar;
            set(cVar);
        }

        final void a(c cVar) {
            this.b.set(cVar);
            this.b = cVar;
            this.c++;
        }

        final void a() {
            c cVar = (c) ((c) get()).get();
            if (cVar == null) {
                throw new IllegalStateException("Empty list!");
            }
            this.c--;
            b(cVar);
        }

        final void a(int i) {
            c cVar = (c) get();
            while (i > 0) {
                cVar = (c) cVar.get();
                i--;
                this.c--;
            }
            b(cVar);
        }

        final void b(c cVar) {
            set(cVar);
        }

        public final void a(T t) {
            Object b = b(this.a.a((Object) t));
            long j = this.d + 1;
            this.d = j;
            a(new c(b, j));
            c();
        }

        public final void a(Throwable th) {
            Object b = b(this.a.a(th));
            long j = this.d + 1;
            this.d = j;
            a(new c(b, j));
            d();
        }

        public final void b() {
            Object b = b(this.a.b());
            long j = this.d + 1;
            this.d = j;
            a(new c(b, j));
            d();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void a(dji.thirdparty.f.e.a.bz.b<T> r11) {
            /*
            r10 = this;
            monitor-enter(r11);
            r0 = r11.e;	 Catch:{ all -> 0x0090 }
            if (r0 == 0) goto L_0x000a;
        L_0x0005:
            r0 = 1;
            r11.f = r0;	 Catch:{ all -> 0x0090 }
            monitor-exit(r11);	 Catch:{ all -> 0x0090 }
        L_0x0009:
            return;
        L_0x000a:
            r0 = 1;
            r11.e = r0;	 Catch:{ all -> 0x0090 }
            monitor-exit(r11);	 Catch:{ all -> 0x0090 }
        L_0x000e:
            r0 = r11.b();
            if (r0 != 0) goto L_0x0009;
        L_0x0014:
            r4 = r11.get();
            r0 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
            if (r0 != 0) goto L_0x0093;
        L_0x0021:
            r0 = 1;
            r1 = r0;
        L_0x0023:
            r2 = 0;
            r0 = r11.c();
            r0 = (dji.thirdparty.f.e.a.bz.c) r0;
            if (r0 != 0) goto L_0x003a;
        L_0x002d:
            r0 = r10.get();
            r0 = (dji.thirdparty.f.e.a.bz.c) r0;
            r11.c = r0;
            r6 = r0.b;
            r11.b(r6);
        L_0x003a:
            r6 = r11.b();
            if (r6 != 0) goto L_0x0009;
        L_0x0040:
            r6 = r4;
            r4 = r2;
            r2 = r0;
        L_0x0043:
            r8 = 0;
            r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
            if (r0 == 0) goto L_0x00a7;
        L_0x0049:
            r0 = r2.get();
            r0 = (dji.thirdparty.f.e.a.bz.c) r0;
            if (r0 == 0) goto L_0x00a7;
        L_0x0051:
            r2 = r0.a;
            r2 = r10.c(r2);
            r3 = r10.a;	 Catch:{ Throwable -> 0x0065 }
            r8 = r11.b;	 Catch:{ Throwable -> 0x0065 }
            r3 = r3.a(r8, r2);	 Catch:{ Throwable -> 0x0065 }
            if (r3 == 0) goto L_0x0096;
        L_0x0061:
            r0 = 0;
            r11.c = r0;	 Catch:{ Throwable -> 0x0065 }
            goto L_0x0009;
        L_0x0065:
            r0 = move-exception;
            r1 = 0;
            r11.c = r1;
            dji.thirdparty.f.c.b.b(r0);
            r11.n_();
            r1 = r10.a;
            r1 = r1.c(r2);
            if (r1 != 0) goto L_0x0009;
        L_0x0077:
            r1 = r10.a;
            r1 = r1.b(r2);
            if (r1 != 0) goto L_0x0009;
        L_0x007f:
            r1 = r11.b;
            r3 = r10.a;
            r2 = r3.g(r2);
            r0 = dji.thirdparty.f.c.g.a(r0, r2);
            r1.a(r0);
            goto L_0x0009;
        L_0x0090:
            r0 = move-exception;
            monitor-exit(r11);	 Catch:{ all -> 0x0090 }
            throw r0;
        L_0x0093:
            r0 = 0;
            r1 = r0;
            goto L_0x0023;
        L_0x0096:
            r2 = 1;
            r2 = r2 + r4;
            r4 = 1;
            r4 = r6 - r4;
            r6 = r11.b();
            if (r6 != 0) goto L_0x0009;
        L_0x00a3:
            r6 = r4;
            r4 = r2;
            r2 = r0;
            goto L_0x0043;
        L_0x00a7:
            r6 = 0;
            r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r0 == 0) goto L_0x00b4;
        L_0x00ad:
            r11.c = r2;
            if (r1 != 0) goto L_0x00b4;
        L_0x00b1:
            r11.c(r4);
        L_0x00b4:
            monitor-enter(r11);
            r0 = r11.f;	 Catch:{ all -> 0x00bf }
            if (r0 != 0) goto L_0x00c2;
        L_0x00b9:
            r0 = 0;
            r11.e = r0;	 Catch:{ all -> 0x00bf }
            monitor-exit(r11);	 Catch:{ all -> 0x00bf }
            goto L_0x0009;
        L_0x00bf:
            r0 = move-exception;
            monitor-exit(r11);	 Catch:{ all -> 0x00bf }
            throw r0;
        L_0x00c2:
            r0 = 0;
            r11.f = r0;	 Catch:{ all -> 0x00bf }
            monitor-exit(r11);	 Catch:{ all -> 0x00bf }
            goto L_0x000e;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.bz.a.a(dji.thirdparty.f.e.a.bz$b):void");
        }

        Object b(Object obj) {
            return obj;
        }

        Object c(Object obj) {
            return obj;
        }

        void c() {
        }

        void d() {
        }

        final void a(Collection<? super T> collection) {
            c cVar = (c) get();
            while (true) {
                cVar = (c) cVar.get();
                if (cVar != null) {
                    Object c = c(cVar.a);
                    if (!this.a.b(c) && !this.a.c(c)) {
                        collection.add(this.a.g(c));
                    } else {
                        return;
                    }
                }
                return;
            }
        }

        boolean e() {
            return this.b.a != null && this.a.c(c(this.b.a));
        }

        boolean f() {
            return this.b.a != null && this.a.b(c(this.b.a));
        }
    }

    static final class b<T> extends AtomicLong implements dji.thirdparty.f.f, l {
        static final long g = Long.MIN_VALUE;
        private static final long h = -4453897557930727610L;
        final e<T> a;
        final k<? super T> b;
        Object c;
        final AtomicLong d = new AtomicLong();
        boolean e;
        boolean f;

        public b(e<T> eVar, k<? super T> kVar) {
            this.a = eVar;
            this.b = kVar;
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
                    if (j2 < 0 || j != 0) {
                        j3 = j2 + j;
                        if (j3 < 0) {
                            j3 = IPositioningSession.NotSet;
                        }
                    } else {
                        return;
                    }
                } while (!compareAndSet(j2, j3));
                b(j);
                this.a.e();
                this.a.a.a(this);
            }
        }

        void b(long j) {
            long j2;
            long j3;
            do {
                j2 = this.d.get();
                j3 = j2 + j;
                if (j3 < 0) {
                    j3 = IPositioningSession.NotSet;
                }
            } while (!this.d.compareAndSet(j2, j3));
        }

        public long c(long j) {
            if (j <= 0) {
                throw new IllegalArgumentException("Cant produce zero or less");
            }
            long j2;
            long j3;
            do {
                j3 = get();
                if (j3 == Long.MIN_VALUE) {
                    return Long.MIN_VALUE;
                }
                j2 = j3 - j;
                if (j2 < 0) {
                    throw new IllegalStateException("More produced (" + j + ") than requested (" + j3 + ")");
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

        <U> U c() {
            return this.c;
        }
    }

    static final class c extends AtomicReference<c> {
        private static final long c = 245354315435971818L;
        final Object a;
        final long b;

        public c(Object obj, long j) {
            this.a = obj;
            this.b = j;
        }
    }

    static final class e<T> extends k<T> implements l {
        static final b[] d = new b[0];
        static final b[] e = new b[0];
        final d<T> a;
        final r<T> b = r.a();
        boolean c;
        final AtomicReference<b[]> f = new AtomicReference(d);
        final AtomicBoolean g = new AtomicBoolean();
        boolean h;
        boolean i;
        long j;
        long k;
        volatile dji.thirdparty.f.f l;

        public e(AtomicReference<e<T>> atomicReference, d<T> dVar) {
            this.a = dVar;
            a(0);
        }

        void d() {
            a(dji.thirdparty.f.m.f.a(new dji.thirdparty.f.d.b(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.f.getAndSet(e.e);
                }
            }));
        }

        boolean a(b<T> bVar) {
            if (bVar == null) {
                throw new NullPointerException();
            }
            b[] bVarArr;
            Object obj;
            do {
                bVarArr = (b[]) this.f.get();
                if (bVarArr == e) {
                    return false;
                }
                int length = bVarArr.length;
                obj = new b[(length + 1)];
                System.arraycopy(bVarArr, 0, obj, 0, length);
                obj[length] = bVar;
            } while (!this.f.compareAndSet(bVarArr, obj));
            return true;
        }

        void b(b<T> bVar) {
            b[] bVarArr;
            Object obj;
            do {
                bVarArr = (b[]) this.f.get();
                if (bVarArr != d && bVarArr != e) {
                    int i = -1;
                    int length = bVarArr.length;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (bVarArr[i2].equals(bVar)) {
                            i = i2;
                            break;
                        }
                    }
                    if (i < 0) {
                        return;
                    }
                    if (length == 1) {
                        obj = d;
                    } else {
                        obj = new b[(length - 1)];
                        System.arraycopy(bVarArr, 0, obj, 0, i);
                        System.arraycopy(bVarArr, i + 1, obj, i, (length - i) - 1);
                    }
                } else {
                    return;
                }
            } while (!this.f.compareAndSet(bVarArr, obj));
        }

        public void a(dji.thirdparty.f.f fVar) {
            if (this.l != null) {
                throw new IllegalStateException("Only a single producer can be set on a Subscriber.");
            }
            this.l = fVar;
            e();
            f();
        }

        public void a_(T t) {
            if (!this.c) {
                this.a.a((Object) t);
                f();
            }
        }

        public void a(Throwable th) {
            if (!this.c) {
                this.c = true;
                try {
                    this.a.a(th);
                    f();
                } finally {
                    n_();
                }
            }
        }

        public void o_() {
            if (!this.c) {
                this.c = true;
                try {
                    this.a.b();
                    f();
                } finally {
                    n_();
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void e() {
            /*
            r12 = this;
            r2 = 0;
            r10 = 0;
            r0 = r12.b();
            if (r0 == 0) goto L_0x000a;
        L_0x0009:
            return;
        L_0x000a:
            monitor-enter(r12);
            r0 = r12.h;	 Catch:{ all -> 0x0014 }
            if (r0 == 0) goto L_0x0017;
        L_0x000f:
            r0 = 1;
            r12.i = r0;	 Catch:{ all -> 0x0014 }
            monitor-exit(r12);	 Catch:{ all -> 0x0014 }
            goto L_0x0009;
        L_0x0014:
            r0 = move-exception;
            monitor-exit(r12);	 Catch:{ all -> 0x0014 }
            throw r0;
        L_0x0017:
            r0 = 1;
            r12.h = r0;	 Catch:{ all -> 0x0014 }
            monitor-exit(r12);	 Catch:{ all -> 0x0014 }
        L_0x001b:
            r0 = r12.b();
            if (r0 != 0) goto L_0x0009;
        L_0x0021:
            r0 = r12.f;
            r0 = r0.get();
            r0 = (dji.thirdparty.f.e.a.bz.b[]) r0;
            r6 = r12.j;
            r3 = r0.length;
            r1 = r2;
            r4 = r6;
        L_0x002e:
            if (r1 >= r3) goto L_0x003f;
        L_0x0030:
            r8 = r0[r1];
            r8 = r8.d;
            r8 = r8.get();
            r4 = java.lang.Math.max(r4, r8);
            r1 = r1 + 1;
            goto L_0x002e;
        L_0x003f:
            r0 = r12.k;
            r3 = r12.l;
            r6 = r4 - r6;
            r8 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1));
            if (r8 == 0) goto L_0x0075;
        L_0x0049:
            r12.j = r4;
            if (r3 == 0) goto L_0x0068;
        L_0x004d:
            r4 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1));
            if (r4 == 0) goto L_0x0064;
        L_0x0051:
            r12.k = r10;
            r0 = r0 + r6;
            r3.a(r0);
        L_0x0057:
            monitor-enter(r12);
            r0 = r12.i;	 Catch:{ all -> 0x0061 }
            if (r0 != 0) goto L_0x0081;
        L_0x005c:
            r0 = 0;
            r12.h = r0;	 Catch:{ all -> 0x0061 }
            monitor-exit(r12);	 Catch:{ all -> 0x0061 }
            goto L_0x0009;
        L_0x0061:
            r0 = move-exception;
            monitor-exit(r12);	 Catch:{ all -> 0x0061 }
            throw r0;
        L_0x0064:
            r3.a(r6);
            goto L_0x0057;
        L_0x0068:
            r0 = r0 + r6;
            r3 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1));
            if (r3 >= 0) goto L_0x0072;
        L_0x006d:
            r0 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        L_0x0072:
            r12.k = r0;
            goto L_0x0057;
        L_0x0075:
            r4 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1));
            if (r4 == 0) goto L_0x0057;
        L_0x0079:
            if (r3 == 0) goto L_0x0057;
        L_0x007b:
            r12.k = r10;
            r3.a(r0);
            goto L_0x0057;
        L_0x0081:
            r0 = 0;
            r12.i = r0;	 Catch:{ all -> 0x0061 }
            monitor-exit(r12);	 Catch:{ all -> 0x0061 }
            goto L_0x001b;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.bz.e.e():void");
        }

        void f() {
            for (b a : (b[]) this.f.get()) {
                this.a.a(a);
            }
        }
    }

    static final class f<T> extends a<T> {
        private static final long h = 3457957419649567404L;
        final dji.thirdparty.f.g e;
        final long f;
        final int g;

        public f(int i, long j, dji.thirdparty.f.g gVar) {
            this.e = gVar;
            this.g = i;
            this.f = j;
        }

        Object b(Object obj) {
            return new i(this.e.b(), obj);
        }

        Object c(Object obj) {
            return ((i) obj).b();
        }

        void c() {
            long b = this.e.b() - this.f;
            c cVar = (c) get();
            c cVar2 = cVar;
            int i = 0;
            c cVar3 = (c) cVar.get();
            while (cVar3 != null) {
                if (this.c <= this.g) {
                    if (((i) cVar3.a).a() > b) {
                        break;
                    }
                    i++;
                    this.c--;
                    cVar2 = cVar3;
                    cVar3 = (c) cVar3.get();
                } else {
                    i++;
                    this.c--;
                    cVar2 = cVar3;
                    cVar3 = (c) cVar3.get();
                }
            }
            if (i != 0) {
                b(cVar2);
            }
        }

        void d() {
            long b = this.e.b() - this.f;
            c cVar = (c) get();
            c cVar2 = cVar;
            int i = 0;
            c cVar3 = (c) cVar.get();
            while (cVar3 != null && this.c > 1 && ((i) cVar3.a).a() <= b) {
                i++;
                this.c--;
                cVar2 = cVar3;
                cVar3 = (c) cVar3.get();
            }
            if (i != 0) {
                b(cVar2);
            }
        }
    }

    static final class g<T> extends a<T> {
        private static final long f = -5898283885385201806L;
        final int e;

        public g(int i) {
            this.e = i;
        }

        void c() {
            if (this.c > this.e) {
                a();
            }
        }
    }

    static final class h<T> extends ArrayList<Object> implements d<T> {
        private static final long c = 7063189396499112664L;
        final r<T> a = r.a();
        volatile int b;

        public h(int i) {
            super(i);
        }

        public void a(T t) {
            add(this.a.a((Object) t));
            this.b++;
        }

        public void a(Throwable th) {
            add(this.a.a(th));
            this.b++;
        }

        public void b() {
            add(this.a.b());
            this.b++;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(dji.thirdparty.f.e.a.bz.b<T> r12) {
            /*
            r11 = this;
            monitor-enter(r12);
            r0 = r12.e;	 Catch:{ all -> 0x0050 }
            if (r0 == 0) goto L_0x000a;
        L_0x0005:
            r0 = 1;
            r12.f = r0;	 Catch:{ all -> 0x0050 }
            monitor-exit(r12);	 Catch:{ all -> 0x0050 }
        L_0x0009:
            return;
        L_0x000a:
            r0 = 1;
            r12.e = r0;	 Catch:{ all -> 0x0050 }
            monitor-exit(r12);	 Catch:{ all -> 0x0050 }
        L_0x000e:
            r0 = r12.b();
            if (r0 != 0) goto L_0x0009;
        L_0x0014:
            r7 = r11.b;
            r0 = r12.c();
            r0 = (java.lang.Integer) r0;
            if (r0 == 0) goto L_0x0053;
        L_0x001e:
            r0 = r0.intValue();
        L_0x0022:
            r4 = r12.get();
            r2 = 0;
            r6 = r0;
            r0 = r2;
            r2 = r4;
        L_0x002b:
            r8 = 0;
            r8 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
            if (r8 == 0) goto L_0x007c;
        L_0x0031:
            if (r6 >= r7) goto L_0x007c;
        L_0x0033:
            r8 = r11.get(r6);
            r9 = r11.a;	 Catch:{ Throwable -> 0x0055 }
            r10 = r12.b;	 Catch:{ Throwable -> 0x0055 }
            r8 = r9.a(r10, r8);	 Catch:{ Throwable -> 0x0055 }
            if (r8 != 0) goto L_0x0009;
        L_0x0041:
            r8 = r12.b();
            if (r8 != 0) goto L_0x0009;
        L_0x0047:
            r6 = r6 + 1;
            r8 = 1;
            r2 = r2 - r8;
            r8 = 1;
            r0 = r0 + r8;
            goto L_0x002b;
        L_0x0050:
            r0 = move-exception;
            monitor-exit(r12);	 Catch:{ all -> 0x0050 }
            throw r0;
        L_0x0053:
            r0 = 0;
            goto L_0x0022;
        L_0x0055:
            r0 = move-exception;
            dji.thirdparty.f.c.b.b(r0);
            r12.n_();
            r1 = r11.a;
            r1 = r1.c(r8);
            if (r1 != 0) goto L_0x0009;
        L_0x0064:
            r1 = r11.a;
            r1 = r1.b(r8);
            if (r1 != 0) goto L_0x0009;
        L_0x006c:
            r1 = r12.b;
            r2 = r11.a;
            r2 = r2.g(r8);
            r0 = dji.thirdparty.f.c.g.a(r0, r2);
            r1.a(r0);
            goto L_0x0009;
        L_0x007c:
            r2 = 0;
            r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r2 == 0) goto L_0x0094;
        L_0x0082:
            r2 = java.lang.Integer.valueOf(r6);
            r12.c = r2;
            r2 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
            if (r2 == 0) goto L_0x0094;
        L_0x0091:
            r12.c(r0);
        L_0x0094:
            monitor-enter(r12);
            r0 = r12.f;	 Catch:{ all -> 0x009f }
            if (r0 != 0) goto L_0x00a2;
        L_0x0099:
            r0 = 0;
            r12.e = r0;	 Catch:{ all -> 0x009f }
            monitor-exit(r12);	 Catch:{ all -> 0x009f }
            goto L_0x0009;
        L_0x009f:
            r0 = move-exception;
            monitor-exit(r12);	 Catch:{ all -> 0x009f }
            throw r0;
        L_0x00a2:
            r0 = 0;
            r12.f = r0;	 Catch:{ all -> 0x009f }
            monitor-exit(r12);	 Catch:{ all -> 0x009f }
            goto L_0x000e;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.bz.h.a(dji.thirdparty.f.e.a.bz$b):void");
        }
    }

    public static <T, U, R> dji.thirdparty.f.d<R> c(final n<? extends dji.thirdparty.f.f.c<U>> nVar, final o<? super dji.thirdparty.f.d<U>, ? extends dji.thirdparty.f.d<R>> oVar) {
        return dji.thirdparty.f.d.a(new d$f<R>() {
            public void a(final k<? super R> kVar) {
                try {
                    dji.thirdparty.f.f.c cVar = (dji.thirdparty.f.f.c) nVar.call();
                    ((dji.thirdparty.f.d) oVar.a(cVar)).b(kVar);
                    cVar.h(new dji.thirdparty.f.d.c<l>(this) {
                        final /* synthetic */ AnonymousClass2 b;

                        public void a(l lVar) {
                            kVar.a(lVar);
                        }
                    });
                } catch (Throwable th) {
                    dji.thirdparty.f.c.b.a(th, (dji.thirdparty.f.e) kVar);
                }
            }
        });
    }

    public static <T> dji.thirdparty.f.f.c<T> a(final dji.thirdparty.f.f.c<T> cVar, dji.thirdparty.f.g gVar) {
        final dji.thirdparty.f.d a = cVar.a(gVar);
        return new dji.thirdparty.f.f.c<T>(new d$f<T>() {
            public void a(final k<? super T> kVar) {
                a.a(new k<T>(this, kVar) {
                    final /* synthetic */ AnonymousClass3 b;

                    public void a_(T t) {
                        kVar.a_(t);
                    }

                    public void a(Throwable th) {
                        kVar.a(th);
                    }

                    public void o_() {
                        kVar.o_();
                    }
                });
            }
        }) {
            public void h(dji.thirdparty.f.d.c<? super l> cVar) {
                cVar.h(cVar);
            }
        };
    }

    public static <T> dji.thirdparty.f.f.c<T> u(dji.thirdparty.f.d<? extends T> dVar) {
        return a((dji.thirdparty.f.d) dVar, f);
    }

    public static <T> dji.thirdparty.f.f.c<T> e(dji.thirdparty.f.d<? extends T> dVar, final int i) {
        if (i == Integer.MAX_VALUE) {
            return u(dVar);
        }
        return a((dji.thirdparty.f.d) dVar, new n<d<T>>() {
            public /* synthetic */ Object call() {
                return a();
            }

            public d<T> a() {
                return new g(i);
            }
        });
    }

    public static <T> dji.thirdparty.f.f.c<T> a(dji.thirdparty.f.d<? extends T> dVar, long j, TimeUnit timeUnit, dji.thirdparty.f.g gVar) {
        return a(dVar, j, timeUnit, gVar, Integer.MAX_VALUE);
    }

    public static <T> dji.thirdparty.f.f.c<T> a(dji.thirdparty.f.d<? extends T> dVar, long j, TimeUnit timeUnit, final dji.thirdparty.f.g gVar, final int i) {
        final long toMillis = timeUnit.toMillis(j);
        return a((dji.thirdparty.f.d) dVar, new n<d<T>>() {
            public /* synthetic */ Object call() {
                return a();
            }

            public d<T> a() {
                return new f(i, toMillis, gVar);
            }
        });
    }

    static <T> dji.thirdparty.f.f.c<T> a(dji.thirdparty.f.d<? extends T> dVar, final n<? extends d<T>> nVar) {
        final AtomicReference atomicReference = new AtomicReference();
        return new bz(new d$f<T>() {
            public void a(k<? super T> kVar) {
                e eVar;
                e eVar2;
                do {
                    eVar = (e) atomicReference.get();
                    if (eVar != null) {
                        break;
                    }
                    eVar2 = new e(atomicReference, (d) nVar.call());
                    eVar2.d();
                } while (!atomicReference.compareAndSet(eVar, eVar2));
                eVar = eVar2;
                dji.thirdparty.f.f bVar = new b(eVar, kVar);
                eVar.a((b) bVar);
                kVar.a((l) bVar);
                eVar.a.a((b) bVar);
                kVar.a(bVar);
            }
        }, dVar, atomicReference, nVar);
    }

    private bz(d$f<T> dji_thirdparty_f_d_f_T, dji.thirdparty.f.d<? extends T> dVar, AtomicReference<e<T>> atomicReference, n<? extends d<T>> nVar) {
        super(dji_thirdparty_f_d_f_T);
        this.c = dVar;
        this.d = atomicReference;
        this.e = nVar;
    }

    public void h(dji.thirdparty.f.d.c<? super l> cVar) {
        k kVar;
        k eVar;
        boolean z;
        do {
            kVar = (e) this.d.get();
            if (kVar != null && !kVar.b()) {
                break;
            }
            eVar = new e(this.d, (d) this.e.call());
            eVar.d();
        } while (!this.d.compareAndSet(kVar, eVar));
        kVar = eVar;
        if (kVar.g.get() || !kVar.g.compareAndSet(false, true)) {
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
