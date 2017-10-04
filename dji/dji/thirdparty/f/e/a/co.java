package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.e;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class co<T> implements d$g<T, dji.thirdparty.f.d<? extends T>> {
    final boolean a;

    private static final class a {
        static final co<Object> a = new co(false);

        private a() {
        }
    }

    private static final class b {
        static final co<Object> a = new co(true);

        private b() {
        }
    }

    private static final class c<T> extends k<T> {
        private final long a;
        private final d<T> b;

        c(long j, d<T> dVar) {
            this.a = j;
            this.b = dVar;
        }

        public void a(f fVar) {
            this.b.c.a(fVar);
        }

        public void a_(T t) {
            this.b.a((Object) t, this.a);
        }

        public void a(Throwable th) {
            this.b.a(th, this.a);
        }

        public void o_() {
            this.b.b(this.a);
        }
    }

    private static final class d<T> extends k<dji.thirdparty.f.d<? extends T>> {
        final k<? super T> a;
        final e b = new e();
        final dji.thirdparty.f.e.b.a c = new dji.thirdparty.f.e.b.a();
        final boolean d;
        long e;
        Throwable f;
        boolean g;
        List<T> h;
        boolean i;
        boolean j;
        boolean k;

        public /* synthetic */ void a_(Object obj) {
            a((dji.thirdparty.f.d) obj);
        }

        d(k<? super T> kVar, boolean z) {
            this.a = kVar;
            this.d = z;
        }

        void d() {
            this.a.a(this.b);
            this.a.a(new f(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void a(long j) {
                    if (j > 0) {
                        this.a.c.a(j);
                    }
                }
            });
        }

        public void a(dji.thirdparty.f.d<? extends T> dVar) {
            Object cVar;
            synchronized (this) {
                long j = this.e + 1;
                this.e = j;
                cVar = new c(j, this);
                this.i = true;
            }
            this.b.a(cVar);
            dVar.a(cVar);
        }

        public void a(Throwable th) {
            synchronized (this) {
                Throwable b = b(th);
                this.g = true;
                if (this.j) {
                    this.k = true;
                } else if (this.d && this.i) {
                } else {
                    this.j = true;
                    this.a.a(b);
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void o_() {
            /*
            r2 = this;
            monitor-enter(r2);
            r0 = 1;
            r2.g = r0;	 Catch:{ all -> 0x0013 }
            r0 = r2.j;	 Catch:{ all -> 0x0013 }
            if (r0 == 0) goto L_0x000d;
        L_0x0008:
            r0 = 1;
            r2.k = r0;	 Catch:{ all -> 0x0013 }
            monitor-exit(r2);	 Catch:{ all -> 0x0013 }
        L_0x000c:
            return;
        L_0x000d:
            r0 = r2.i;	 Catch:{ all -> 0x0013 }
            if (r0 == 0) goto L_0x0016;
        L_0x0011:
            monitor-exit(r2);	 Catch:{ all -> 0x0013 }
            goto L_0x000c;
        L_0x0013:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x0013 }
            throw r0;
        L_0x0016:
            r0 = 1;
            r2.j = r0;	 Catch:{ all -> 0x0013 }
            r0 = r2.f;	 Catch:{ all -> 0x0013 }
            monitor-exit(r2);	 Catch:{ all -> 0x0013 }
            if (r0 != 0) goto L_0x0024;
        L_0x001e:
            r0 = r2.a;
            r0.o_();
            goto L_0x000c;
        L_0x0024:
            r1 = r2.a;
            r1.a(r0);
            goto L_0x000c;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.co.d.o_():void");
        }

        Throwable b(Throwable th) {
            Throwable th2 = this.f;
            if (th2 == null) {
                this.f = th;
                return th;
            } else if (th2 instanceof dji.thirdparty.f.c.a) {
                Collection arrayList = new ArrayList(((dji.thirdparty.f.c.a) th2).a());
                arrayList.add(th);
                th = new dji.thirdparty.f.c.a(arrayList);
                this.f = th;
                return th;
            } else {
                Throwable aVar = new dji.thirdparty.f.c.a(Arrays.asList(new Throwable[]{th2, th}));
                this.f = aVar;
                return aVar;
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void a(T r7, long r8) {
            /*
            r6 = this;
            r1 = 0;
            monitor-enter(r6);
            r2 = r6.e;	 Catch:{ all -> 0x0022 }
            r0 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1));
            if (r0 == 0) goto L_0x000a;
        L_0x0008:
            monitor-exit(r6);	 Catch:{ all -> 0x0022 }
        L_0x0009:
            return;
        L_0x000a:
            r0 = r6.j;	 Catch:{ all -> 0x0022 }
            if (r0 == 0) goto L_0x0025;
        L_0x000e:
            r0 = r6.h;	 Catch:{ all -> 0x0022 }
            if (r0 != 0) goto L_0x001a;
        L_0x0012:
            r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0022 }
            r1 = 4;
            r0.<init>(r1);	 Catch:{ all -> 0x0022 }
            r6.h = r0;	 Catch:{ all -> 0x0022 }
        L_0x001a:
            r0.add(r7);	 Catch:{ all -> 0x0022 }
            r0 = 1;
            r6.k = r0;	 Catch:{ all -> 0x0022 }
            monitor-exit(r6);	 Catch:{ all -> 0x0022 }
            goto L_0x0009;
        L_0x0022:
            r0 = move-exception;
            monitor-exit(r6);	 Catch:{ all -> 0x0022 }
            throw r0;
        L_0x0025:
            r0 = 1;
            r6.j = r0;	 Catch:{ all -> 0x0022 }
            monitor-exit(r6);	 Catch:{ all -> 0x0022 }
            r0 = r6.a;
            r0.a_(r7);
            r0 = r6.c;
            r2 = 1;
            r0.b(r2);
        L_0x0035:
            r0 = r6.a;
            r0 = r0.b();
            if (r0 != 0) goto L_0x0009;
        L_0x003d:
            monitor-enter(r6);
            r0 = r6.k;	 Catch:{ all -> 0x0047 }
            if (r0 != 0) goto L_0x004a;
        L_0x0042:
            r0 = 0;
            r6.j = r0;	 Catch:{ all -> 0x0047 }
            monitor-exit(r6);	 Catch:{ all -> 0x0047 }
            goto L_0x0009;
        L_0x0047:
            r0 = move-exception;
            monitor-exit(r6);	 Catch:{ all -> 0x0047 }
            throw r0;
        L_0x004a:
            r0 = r6.f;	 Catch:{ all -> 0x0047 }
            r2 = r6.g;	 Catch:{ all -> 0x0047 }
            r3 = r6.h;	 Catch:{ all -> 0x0047 }
            r4 = r6.i;	 Catch:{ all -> 0x0047 }
            monitor-exit(r6);	 Catch:{ all -> 0x0047 }
            r5 = r6.d;
            if (r5 != 0) goto L_0x005f;
        L_0x0057:
            if (r0 == 0) goto L_0x005f;
        L_0x0059:
            r1 = r6.a;
            r1.a(r0);
            goto L_0x0009;
        L_0x005f:
            if (r3 != 0) goto L_0x0073;
        L_0x0061:
            if (r4 != 0) goto L_0x0073;
        L_0x0063:
            if (r2 == 0) goto L_0x0073;
        L_0x0065:
            if (r0 == 0) goto L_0x006d;
        L_0x0067:
            r1 = r6.a;
            r1.a(r0);
            goto L_0x0009;
        L_0x006d:
            r0 = r6.a;
            r0.o_();
            goto L_0x0009;
        L_0x0073:
            if (r3 == 0) goto L_0x0035;
        L_0x0075:
            r2 = r3.iterator();
            r0 = r1;
        L_0x007a:
            r3 = r2.hasNext();
            if (r3 == 0) goto L_0x0094;
        L_0x0080:
            r3 = r2.next();
            r4 = r6.a;
            r4 = r4.b();
            if (r4 != 0) goto L_0x0009;
        L_0x008c:
            r4 = r6.a;
            r4.a_(r3);
            r0 = r0 + 1;
            goto L_0x007a;
        L_0x0094:
            r2 = r6.c;
            r4 = (long) r0;
            r2.b(r4);
            goto L_0x0035;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.co.d.a(java.lang.Object, long):void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void a(java.lang.Throwable r5, long r6) {
            /*
            r4 = this;
            r0 = 0;
            r1 = 1;
            monitor-enter(r4);
            r2 = r4.e;	 Catch:{ all -> 0x0023 }
            r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
            if (r2 != 0) goto L_0x0030;
        L_0x0009:
            r1 = 0;
            r4.i = r1;	 Catch:{ all -> 0x0023 }
            r5 = r4.b(r5);	 Catch:{ all -> 0x0023 }
            r1 = r4.j;	 Catch:{ all -> 0x0023 }
            if (r1 == 0) goto L_0x0019;
        L_0x0014:
            r0 = 1;
            r4.k = r0;	 Catch:{ all -> 0x0023 }
            monitor-exit(r4);	 Catch:{ all -> 0x0023 }
        L_0x0018:
            return;
        L_0x0019:
            r1 = r4.d;	 Catch:{ all -> 0x0023 }
            if (r1 == 0) goto L_0x0026;
        L_0x001d:
            r1 = r4.g;	 Catch:{ all -> 0x0023 }
            if (r1 != 0) goto L_0x0026;
        L_0x0021:
            monitor-exit(r4);	 Catch:{ all -> 0x0023 }
            goto L_0x0018;
        L_0x0023:
            r0 = move-exception;
            monitor-exit(r4);	 Catch:{ all -> 0x0023 }
            throw r0;
        L_0x0026:
            r1 = 1;
            r4.j = r1;	 Catch:{ all -> 0x0023 }
        L_0x0029:
            monitor-exit(r4);	 Catch:{ all -> 0x0023 }
            if (r0 == 0) goto L_0x0032;
        L_0x002c:
            r4.c(r5);
            goto L_0x0018;
        L_0x0030:
            r0 = r1;
            goto L_0x0029;
        L_0x0032:
            r0 = r4.a;
            r0.a(r5);
            goto L_0x0018;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.co.d.a(java.lang.Throwable, long):void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void b(long r4) {
            /*
            r3 = this;
            monitor-enter(r3);
            r0 = r3.e;	 Catch:{ all -> 0x0015 }
            r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
            if (r0 == 0) goto L_0x0009;
        L_0x0007:
            monitor-exit(r3);	 Catch:{ all -> 0x0015 }
        L_0x0008:
            return;
        L_0x0009:
            r0 = 0;
            r3.i = r0;	 Catch:{ all -> 0x0015 }
            r0 = r3.j;	 Catch:{ all -> 0x0015 }
            if (r0 == 0) goto L_0x0018;
        L_0x0010:
            r0 = 1;
            r3.k = r0;	 Catch:{ all -> 0x0015 }
            monitor-exit(r3);	 Catch:{ all -> 0x0015 }
            goto L_0x0008;
        L_0x0015:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0015 }
            throw r0;
        L_0x0018:
            r0 = r3.f;	 Catch:{ all -> 0x0015 }
            r1 = r3.g;	 Catch:{ all -> 0x0015 }
            if (r1 != 0) goto L_0x0020;
        L_0x001e:
            monitor-exit(r3);	 Catch:{ all -> 0x0015 }
            goto L_0x0008;
        L_0x0020:
            monitor-exit(r3);	 Catch:{ all -> 0x0015 }
            if (r0 == 0) goto L_0x0029;
        L_0x0023:
            r1 = r3.a;
            r1.a(r0);
            goto L_0x0008;
        L_0x0029:
            r0 = r3.a;
            r0.o_();
            goto L_0x0008;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.co.d.b(long):void");
        }

        void c(Throwable th) {
            dji.thirdparty.f.i.d.getInstance().b().a(th);
        }
    }

    public static <T> co<T> a(boolean z) {
        if (z) {
            return b.a;
        }
        return a.a;
    }

    co(boolean z) {
        this.a = z;
    }

    public k<? super dji.thirdparty.f.d<? extends T>> a(k<? super T> kVar) {
        l dVar = new d(kVar, this.a);
        kVar.a(dVar);
        dVar.d();
        return dVar;
    }
}
