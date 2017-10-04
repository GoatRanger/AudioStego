package dji.thirdparty.f.l;

import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.e.a.r;
import dji.thirdparty.f.e.d.q;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class d<T> extends f<T, T> {
    private static final Object[] e = new Object[0];
    final i<T, ?> c;
    final g<T> d;

    static final class a implements o<Object, Object> {
        final dji.thirdparty.f.g a;

        public a(dji.thirdparty.f.g gVar) {
            this.a = gVar;
        }

        public Object a(Object obj) {
            return new dji.thirdparty.f.j.i(this.a.b(), obj);
        }
    }

    interface i<T, I> {
        I a(I i, b<? super T> bVar);

        I a(I i, b<? super T> bVar, long j);

        void a();

        void a(T t);

        void a(Throwable th);

        boolean a(b<? super T> bVar);

        T[] a(T[] tArr);

        boolean d();

        int e();

        boolean f();

        T g();
    }

    static final class b<T> implements i<T, a<Object>> {
        final f<Object> a = new f();
        final e b;
        final o<Object, Object> c;
        final o<Object, Object> d;
        final r<T> e = r.a();
        volatile boolean f;
        volatile a<Object> g = this.a.b;

        public b(e eVar, o<Object, Object> oVar, o<Object, Object> oVar2) {
            this.b = eVar;
            this.c = oVar;
            this.d = oVar2;
        }

        public void a(T t) {
            if (!this.f) {
                this.a.a(this.c.a(this.e.a((Object) t)));
                this.b.a(this.a);
                this.g = this.a.b;
            }
        }

        public void a() {
            if (!this.f) {
                this.f = true;
                this.a.a(this.c.a(this.e.b()));
                this.b.b(this.a);
                this.g = this.a.b;
            }
        }

        public void a(Throwable th) {
            if (!this.f) {
                this.f = true;
                this.a.a(this.c.a(this.e.a(th)));
                this.b.b(this.a);
                this.g = this.a.b;
            }
        }

        public void a(dji.thirdparty.f.e<? super T> eVar, a<Object> aVar) {
            this.e.a(eVar, this.d.a(aVar.a));
        }

        public void a(dji.thirdparty.f.e<? super T> eVar, a<Object> aVar, long j) {
            Object obj = aVar.a;
            if (!this.b.a(obj, j)) {
                this.e.a(eVar, this.d.a(obj));
            }
        }

        public a<Object> b() {
            return this.a.a;
        }

        public a<Object> c() {
            return this.g;
        }

        public boolean a(b<? super T> bVar) {
            synchronized (bVar) {
                bVar.b = false;
                if (bVar.c) {
                    return false;
                }
                bVar.b(a((a) bVar.c(), (b) bVar));
                return true;
            }
        }

        public a<Object> a(a<Object> aVar, b<? super T> bVar) {
            while (aVar != c()) {
                a((dji.thirdparty.f.e) bVar, aVar.b);
                aVar = aVar.b;
            }
            return aVar;
        }

        public a<Object> a(a<Object> aVar, b<? super T> bVar, long j) {
            while (aVar != c()) {
                a((dji.thirdparty.f.e) bVar, aVar.b, j);
                aVar = aVar.b;
            }
            return aVar;
        }

        public boolean d() {
            return this.f;
        }

        public int e() {
            a b = b();
            int i = 0;
            a aVar = b;
            for (b = b.b; b != null; b = b.b) {
                int i2 = i + 1;
                i = i2;
                aVar = b;
            }
            if (aVar.a == null) {
                return i;
            }
            Object a = this.d.a(aVar.a);
            if (a == null) {
                return i;
            }
            if (this.e.c(a) || this.e.b(a)) {
                return i - 1;
            }
            return i;
        }

        public boolean f() {
            a aVar = b().b;
            if (aVar == null) {
                return true;
            }
            Object a = this.d.a(aVar.a);
            if (this.e.c(a) || this.e.b(a)) {
                return true;
            }
            return false;
        }

        public T[] a(T[] tArr) {
            List arrayList = new ArrayList();
            for (a aVar = b().b; aVar != null; aVar = aVar.b) {
                Object a = this.d.a(aVar.a);
                if (aVar.b == null && (this.e.c(a) || this.e.b(a))) {
                    break;
                }
                arrayList.add(a);
            }
            return arrayList.toArray(tArr);
        }

        public T g() {
            a aVar = b().b;
            if (aVar == null) {
                return null;
            }
            a aVar2 = null;
            while (aVar != c()) {
                a aVar3 = aVar;
                aVar = aVar.b;
                aVar2 = aVar3;
            }
            Object a = this.d.a(aVar.a);
            if (!this.e.c(a) && !this.e.b(a)) {
                return this.e.g(a);
            }
            if (aVar2 == null) {
                return null;
            }
            return this.e.g(this.d.a(aVar2.a));
        }
    }

    static final class c<T> implements dji.thirdparty.f.d.c<b<T>> {
        final b<T> a;

        public c(b<T> bVar) {
            this.a = bVar;
        }

        public void a(b<T> bVar) {
            bVar.b(this.a.a(this.a.b(), (b) bVar));
        }
    }

    interface e {
        void a(f<Object> fVar);

        boolean a(Object obj, long j);

        void b(f<Object> fVar);
    }

    static final class d implements e {
        d() {
        }

        public boolean a(Object obj, long j) {
            return true;
        }

        public void a(f<Object> fVar) {
        }

        public void b(f<Object> fVar) {
        }
    }

    static final class f<T> {
        final a<T> a = new a(null);
        a<T> b = this.a;
        int c;

        static final class a<T> {
            final T a;
            volatile a<T> b;

            a(T t) {
                this.a = t;
            }
        }

        f() {
        }

        public void a(T t) {
            a aVar = this.b;
            a aVar2 = new a(t);
            aVar.b = aVar2;
            this.b = aVar2;
            this.c++;
        }

        public T a() {
            if (this.a.b == null) {
                throw new IllegalStateException("Empty!");
            }
            a aVar = this.a.b;
            this.a.b = aVar.b;
            if (this.a.b == null) {
                this.b = this.a;
            }
            this.c--;
            return aVar.a;
        }

        public boolean b() {
            return this.c == 0;
        }

        public int c() {
            return this.c;
        }

        public void d() {
            this.b = this.a;
            this.c = 0;
        }
    }

    static final class g implements e {
        final e a;
        final e b;

        public g(e eVar, e eVar2) {
            this.a = eVar;
            this.b = eVar2;
        }

        public void a(f<Object> fVar) {
            this.a.a(fVar);
            this.b.a(fVar);
        }

        public void b(f<Object> fVar) {
            this.a.b(fVar);
            this.b.b(fVar);
        }

        public boolean a(Object obj, long j) {
            return this.a.a(obj, j) || this.b.a(obj, j);
        }
    }

    static final class h implements o<Object, Object> {
        h() {
        }

        public Object a(Object obj) {
            return ((dji.thirdparty.f.j.i) obj).b();
        }
    }

    static final class j implements e {
        final int a;

        public j(int i) {
            this.a = i;
        }

        public void a(f<Object> fVar) {
            while (fVar.c() > this.a) {
                fVar.a();
            }
        }

        public boolean a(Object obj, long j) {
            return false;
        }

        public void b(f<Object> fVar) {
            while (fVar.c() > this.a + 1) {
                fVar.a();
            }
        }
    }

    static final class k implements e {
        final long a;
        final dji.thirdparty.f.g b;

        public k(long j, dji.thirdparty.f.g gVar) {
            this.a = j;
            this.b = gVar;
        }

        public void a(f<Object> fVar) {
            long b = this.b.b();
            while (!fVar.b() && a(fVar.a.b.a, b)) {
                fVar.a();
            }
        }

        public void b(f<Object> fVar) {
            long b = this.b.b();
            while (fVar.c > 1 && a(fVar.a.b.a, b)) {
                fVar.a();
            }
        }

        public boolean a(Object obj, long j) {
            return ((dji.thirdparty.f.j.i) obj).a() <= j - this.a;
        }
    }

    static final class l<T> implements dji.thirdparty.f.d.c<b<T>> {
        final b<T> a;
        final dji.thirdparty.f.g b;

        public l(b<T> bVar, dji.thirdparty.f.g gVar) {
            this.a = bVar;
            this.b = gVar;
        }

        public void a(b<T> bVar) {
            Object a;
            if (this.a.f) {
                a = this.a.a(this.a.b(), (b) bVar);
            } else {
                a = this.a.a(this.a.b(), (b) bVar, this.b.b());
            }
            bVar.b(a);
        }
    }

    static final class m<T> extends AtomicInteger implements i<T, Integer> {
        private final r<T> a = r.a();
        private final ArrayList<Object> b;
        private volatile boolean c;

        public m(int i) {
            this.b = new ArrayList(i);
        }

        public void a(T t) {
            if (!this.c) {
                this.b.add(this.a.a((Object) t));
                getAndIncrement();
            }
        }

        public void a(dji.thirdparty.f.e<? super T> eVar, int i) {
            this.a.a(eVar, this.b.get(i));
        }

        public void a() {
            if (!this.c) {
                this.c = true;
                this.b.add(this.a.b());
                getAndIncrement();
            }
        }

        public void a(Throwable th) {
            if (!this.c) {
                this.c = true;
                this.b.add(this.a.a(th));
                getAndIncrement();
            }
        }

        public boolean d() {
            return this.c;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a(dji.thirdparty.f.l.g.b<? super T> r4) {
            /*
            r3 = this;
            r0 = 0;
            monitor-enter(r4);
            r1 = 0;
            r4.b = r1;	 Catch:{ all -> 0x0025 }
            r1 = r4.c;	 Catch:{ all -> 0x0025 }
            if (r1 == 0) goto L_0x000b;
        L_0x0009:
            monitor-exit(r4);	 Catch:{ all -> 0x0025 }
        L_0x000a:
            return r0;
        L_0x000b:
            monitor-exit(r4);	 Catch:{ all -> 0x0025 }
            r0 = r4.c();
            r0 = (java.lang.Integer) r0;
            if (r0 == 0) goto L_0x0028;
        L_0x0014:
            r0 = r3.a(r0, r4);
            r0 = r0.intValue();
            r0 = java.lang.Integer.valueOf(r0);
            r4.b(r0);
            r0 = 1;
            goto L_0x000a;
        L_0x0025:
            r0 = move-exception;
            monitor-exit(r4);	 Catch:{ all -> 0x0025 }
            throw r0;
        L_0x0028:
            r0 = new java.lang.IllegalStateException;
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = "failed to find lastEmittedLink for: ";
            r1 = r1.append(r2);
            r1 = r1.append(r4);
            r1 = r1.toString();
            r0.<init>(r1);
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.l.d.m.a(dji.thirdparty.f.l.g$b):boolean");
        }

        public Integer a(Integer num, b<? super T> bVar) {
            int intValue = num.intValue();
            while (intValue < get()) {
                a((dji.thirdparty.f.e) bVar, intValue);
                intValue++;
            }
            return Integer.valueOf(intValue);
        }

        public Integer a(Integer num, b<? super T> bVar, long j) {
            return a(num, (b) bVar);
        }

        public int e() {
            int i = get();
            if (i <= 0) {
                return i;
            }
            Object obj = this.b.get(i - 1);
            if (this.a.b(obj) || this.a.c(obj)) {
                return i - 1;
            }
            return i;
        }

        public boolean f() {
            return e() == 0;
        }

        public T[] a(T[] tArr) {
            int i = 0;
            int e = e();
            if (e > 0) {
                T[] tArr2;
                if (e > tArr.length) {
                    tArr2 = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), e);
                } else {
                    tArr2 = tArr;
                }
                while (i < e) {
                    tArr2[i] = this.b.get(i);
                    i++;
                }
                if (tArr2.length <= e) {
                    return tArr2;
                }
                tArr2[e] = null;
                return tArr2;
            }
            if (tArr.length > 0) {
                tArr[0] = null;
            }
            return tArr;
        }

        public T g() {
            int i = get();
            if (i <= 0) {
                return null;
            }
            Object obj = this.b.get(i - 1);
            if (!this.a.b(obj) && !this.a.c(obj)) {
                return this.a.g(obj);
            }
            if (i > 1) {
                return this.a.g(this.b.get(i - 2));
            }
            return null;
        }
    }

    public static <T> d<T> I() {
        return n(16);
    }

    public static <T> d<T> n(int i) {
        final i mVar = new m(i);
        Object gVar = new g();
        gVar.c = new dji.thirdparty.f.d.c<b<T>>() {
            public void a(b<T> bVar) {
                bVar.b(Integer.valueOf(mVar.a(Integer.valueOf(0), (b) bVar).intValue()));
            }
        };
        gVar.d = new dji.thirdparty.f.d.c<b<T>>() {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void a(dji.thirdparty.f.l.g.b<T> r6) {
                /*
                r5 = this;
                r1 = 1;
                r2 = 0;
                monitor-enter(r6);
                r0 = r6.b;	 Catch:{ all -> 0x004a }
                if (r0 == 0) goto L_0x000b;
            L_0x0007:
                r0 = r6.c;	 Catch:{ all -> 0x004a }
                if (r0 == 0) goto L_0x000d;
            L_0x000b:
                monitor-exit(r6);	 Catch:{ all -> 0x004a }
            L_0x000c:
                return;
            L_0x000d:
                r0 = 0;
                r6.b = r0;	 Catch:{ all -> 0x004a }
                r0 = 1;
                r6.c = r0;	 Catch:{ all -> 0x004a }
                monitor-exit(r6);	 Catch:{ all -> 0x004a }
                r3 = r0;	 Catch:{ all -> 0x0055 }
            L_0x0016:
                r0 = r6.c();	 Catch:{ all -> 0x0055 }
                r0 = (java.lang.Integer) r0;	 Catch:{ all -> 0x0055 }
                r0 = r0.intValue();	 Catch:{ all -> 0x0055 }
                r4 = r3.get();	 Catch:{ all -> 0x0055 }
                if (r0 == r4) goto L_0x0031;
            L_0x0026:
                r0 = java.lang.Integer.valueOf(r0);	 Catch:{ all -> 0x0055 }
                r0 = r3.a(r0, r6);	 Catch:{ all -> 0x0055 }
                r6.b(r0);	 Catch:{ all -> 0x0055 }
            L_0x0031:
                monitor-enter(r6);	 Catch:{ all -> 0x0055 }
                r0 = r3.get();	 Catch:{ all -> 0x004f }
                if (r4 != r0) goto L_0x004d;
            L_0x0038:
                r0 = 0;
                r6.c = r0;	 Catch:{ all -> 0x004f }
                monitor-exit(r6);	 Catch:{ all -> 0x003d }
                goto L_0x000c;
            L_0x003d:
                r0 = move-exception;
            L_0x003e:
                monitor-exit(r6);	 Catch:{ all -> 0x003d }
                throw r0;	 Catch:{ all -> 0x0040 }
            L_0x0040:
                r0 = move-exception;
                r2 = r1;
            L_0x0042:
                if (r2 != 0) goto L_0x0049;
            L_0x0044:
                monitor-enter(r6);
                r1 = 0;
                r6.c = r1;	 Catch:{ all -> 0x0052 }
                monitor-exit(r6);	 Catch:{ all -> 0x0052 }
            L_0x0049:
                throw r0;
            L_0x004a:
                r0 = move-exception;
                monitor-exit(r6);	 Catch:{ all -> 0x004a }
                throw r0;
            L_0x004d:
                monitor-exit(r6);	 Catch:{ all -> 0x004f }
                goto L_0x0016;
            L_0x004f:
                r0 = move-exception;
                r1 = r2;
                goto L_0x003e;
            L_0x0052:
                r0 = move-exception;
                monitor-exit(r6);	 Catch:{ all -> 0x0052 }
                throw r0;
            L_0x0055:
                r0 = move-exception;
                goto L_0x0042;
                */
                throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.l.d.2.a(dji.thirdparty.f.l.g$b):void");
            }
        };
        gVar.e = new dji.thirdparty.f.d.c<b<T>>() {
            public void a(b<T> bVar) {
                Integer num = (Integer) bVar.c();
                if (num == null) {
                    num = Integer.valueOf(0);
                }
                mVar.a(num, (b) bVar);
            }
        };
        return new d(gVar, gVar, mVar);
    }

    static <T> d<T> K() {
        b bVar = new b(new d(), q.c(), q.c());
        return a(bVar, new c(bVar));
    }

    public static <T> d<T> o(int i) {
        b bVar = new b(new j(i), q.c(), q.c());
        return a(bVar, new c(bVar));
    }

    public static <T> d<T> s(long j, TimeUnit timeUnit, dji.thirdparty.f.g gVar) {
        b bVar = new b(new k(timeUnit.toMillis(j), gVar), new a(gVar), new h());
        return a(bVar, new l(bVar, gVar));
    }

    public static <T> d<T> c(long j, TimeUnit timeUnit, int i, dji.thirdparty.f.g gVar) {
        b bVar = new b(new g(new j(i), new k(timeUnit.toMillis(j), gVar)), new a(gVar), new h());
        return a(bVar, new l(bVar, gVar));
    }

    static <T> d<T> a(final b<T> bVar, dji.thirdparty.f.d.c<b<T>> cVar) {
        Object gVar = new g();
        gVar.c = cVar;
        gVar.d = new dji.thirdparty.f.d.c<b<T>>() {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void a(dji.thirdparty.f.l.g.b<T> r6) {
                /*
                r5 = this;
                r1 = 1;
                r2 = 0;
                monitor-enter(r6);
                r0 = r6.b;	 Catch:{ all -> 0x0046 }
                if (r0 == 0) goto L_0x000b;
            L_0x0007:
                r0 = r6.c;	 Catch:{ all -> 0x0046 }
                if (r0 == 0) goto L_0x000d;
            L_0x000b:
                monitor-exit(r6);	 Catch:{ all -> 0x0046 }
            L_0x000c:
                return;
            L_0x000d:
                r0 = 0;
                r6.b = r0;	 Catch:{ all -> 0x0046 }
                r0 = 1;
                r6.c = r0;	 Catch:{ all -> 0x0046 }
                monitor-exit(r6);	 Catch:{ all -> 0x0046 }
            L_0x0014:
                r0 = r6.c();	 Catch:{ all -> 0x0051 }
                r0 = (dji.thirdparty.f.l.d.f.a) r0;	 Catch:{ all -> 0x0051 }
                r3 = r2;	 Catch:{ all -> 0x0051 }
                r3 = r3.c();	 Catch:{ all -> 0x0051 }
                if (r0 == r3) goto L_0x002b;
            L_0x0022:
                r4 = r2;	 Catch:{ all -> 0x0051 }
                r0 = r4.a(r0, r6);	 Catch:{ all -> 0x0051 }
                r6.b(r0);	 Catch:{ all -> 0x0051 }
            L_0x002b:
                monitor-enter(r6);	 Catch:{ all -> 0x0051 }
                r0 = r2;	 Catch:{ all -> 0x004b }
                r0 = r0.c();	 Catch:{ all -> 0x004b }
                if (r3 != r0) goto L_0x0049;
            L_0x0034:
                r0 = 0;
                r6.c = r0;	 Catch:{ all -> 0x004b }
                monitor-exit(r6);	 Catch:{ all -> 0x0039 }
                goto L_0x000c;
            L_0x0039:
                r0 = move-exception;
            L_0x003a:
                monitor-exit(r6);	 Catch:{ all -> 0x0039 }
                throw r0;	 Catch:{ all -> 0x003c }
            L_0x003c:
                r0 = move-exception;
                r2 = r1;
            L_0x003e:
                if (r2 != 0) goto L_0x0045;
            L_0x0040:
                monitor-enter(r6);
                r1 = 0;
                r6.c = r1;	 Catch:{ all -> 0x004e }
                monitor-exit(r6);	 Catch:{ all -> 0x004e }
            L_0x0045:
                throw r0;
            L_0x0046:
                r0 = move-exception;
                monitor-exit(r6);	 Catch:{ all -> 0x0046 }
                throw r0;
            L_0x0049:
                monitor-exit(r6);	 Catch:{ all -> 0x004b }
                goto L_0x0014;
            L_0x004b:
                r0 = move-exception;
                r1 = r2;
                goto L_0x003a;
            L_0x004e:
                r0 = move-exception;
                monitor-exit(r6);	 Catch:{ all -> 0x004e }
                throw r0;
            L_0x0051:
                r0 = move-exception;
                goto L_0x003e;
                */
                throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.l.d.4.a(dji.thirdparty.f.l.g$b):void");
            }
        };
        gVar.e = new dji.thirdparty.f.d.c<b<T>>() {
            public void a(b<T> bVar) {
                a aVar = (a) bVar.c();
                if (aVar == null) {
                    aVar = bVar.b();
                }
                bVar.a(aVar, (b) bVar);
            }
        };
        return new d(gVar, gVar, bVar);
    }

    d(d$f<T> dji_thirdparty_f_d_f_T, g<T> gVar, i<T, ?> iVar) {
        super(dji_thirdparty_f_d_f_T);
        this.d = gVar;
        this.c = iVar;
    }

    public void a_(T t) {
        if (this.d.b) {
            this.c.a((Object) t);
            for (b bVar : this.d.b()) {
                if (a(bVar)) {
                    bVar.a_(t);
                }
            }
        }
    }

    public void a(Throwable th) {
        if (this.d.b) {
            this.c.a(th);
            List list = null;
            for (b bVar : this.d.d(r.a().a(th))) {
                try {
                    if (a(bVar)) {
                        bVar.a(th);
                    }
                } catch (Throwable th2) {
                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(th2);
                }
            }
            dji.thirdparty.f.c.b.a(list);
        }
    }

    public void o_() {
        if (this.d.b) {
            this.c.a();
            for (b bVar : this.d.d(r.a().b())) {
                if (a(bVar)) {
                    bVar.o_();
                }
            }
        }
    }

    int L() {
        return ((a) this.d.get()).b.length;
    }

    public boolean J() {
        return this.d.b().length > 0;
    }

    private boolean a(b<? super T> bVar) {
        if (bVar.f) {
            return true;
        }
        if (this.c.a((b) bVar)) {
            bVar.f = true;
            bVar.b(null);
        }
        return false;
    }

    @dji.thirdparty.f.b.a
    public boolean M() {
        return this.d.f.c(this.d.a());
    }

    @dji.thirdparty.f.b.a
    public boolean N() {
        r rVar = this.d.f;
        Object a = this.d.a();
        return (a == null || rVar.c(a)) ? false : true;
    }

    @dji.thirdparty.f.b.a
    public Throwable O() {
        r rVar = this.d.f;
        Object a = this.d.a();
        if (rVar.c(a)) {
            return rVar.h(a);
        }
        return null;
    }

    @dji.thirdparty.f.b.a
    public int P() {
        return this.c.e();
    }

    @dji.thirdparty.f.b.a
    public boolean Q() {
        return !this.c.f();
    }

    @dji.thirdparty.f.b.a
    public boolean R() {
        return Q();
    }

    @dji.thirdparty.f.b.a
    public T[] b(T[] tArr) {
        return this.c.a((Object[]) tArr);
    }

    @dji.thirdparty.f.b.a
    public Object[] S() {
        Object[] b = b(e);
        if (b == e) {
            return new Object[0];
        }
        return b;
    }

    @dji.thirdparty.f.b.a
    public T T() {
        return this.c.g();
    }
}
