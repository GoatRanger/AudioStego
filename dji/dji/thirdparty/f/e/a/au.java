package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.e;
import dji.thirdparty.f.g;
import dji.thirdparty.f.g.d;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class au<T> implements d$g<List<T>, T> {
    final long a;
    final long b;
    final TimeUnit c;
    final int d;
    final g e;

    final class a extends k<T> {
        final k<? super List<T>> a;
        final dji.thirdparty.f.g.a b;
        List<T> c = new ArrayList();
        boolean d;
        final /* synthetic */ au e;

        public a(au auVar, k<? super List<T>> kVar, dji.thirdparty.f.g.a aVar) {
            this.e = auVar;
            this.a = kVar;
            this.b = aVar;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a_(T r4) {
            /*
            r3 = this;
            r0 = 0;
            monitor-enter(r3);
            r1 = r3.d;	 Catch:{ all -> 0x002b }
            if (r1 == 0) goto L_0x0008;
        L_0x0006:
            monitor-exit(r3);	 Catch:{ all -> 0x002b }
        L_0x0007:
            return;
        L_0x0008:
            r1 = r3.c;	 Catch:{ all -> 0x002b }
            r1.add(r4);	 Catch:{ all -> 0x002b }
            r1 = r3.c;	 Catch:{ all -> 0x002b }
            r1 = r1.size();	 Catch:{ all -> 0x002b }
            r2 = r3.e;	 Catch:{ all -> 0x002b }
            r2 = r2.d;	 Catch:{ all -> 0x002b }
            if (r1 != r2) goto L_0x0022;
        L_0x0019:
            r0 = r3.c;	 Catch:{ all -> 0x002b }
            r1 = new java.util.ArrayList;	 Catch:{ all -> 0x002b }
            r1.<init>();	 Catch:{ all -> 0x002b }
            r3.c = r1;	 Catch:{ all -> 0x002b }
        L_0x0022:
            monitor-exit(r3);	 Catch:{ all -> 0x002b }
            if (r0 == 0) goto L_0x0007;
        L_0x0025:
            r1 = r3.a;
            r1.a_(r0);
            goto L_0x0007;
        L_0x002b:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x002b }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.au.a.a_(java.lang.Object):void");
        }

        public void a(Throwable th) {
            synchronized (this) {
                if (this.d) {
                    return;
                }
                this.d = true;
                this.c = null;
                this.a.a(th);
                n_();
            }
        }

        public void o_() {
            try {
                this.b.n_();
                synchronized (this) {
                    if (this.d) {
                        return;
                    }
                    this.d = true;
                    List list = this.c;
                    this.c = null;
                    this.a.a_(list);
                    this.a.o_();
                    n_();
                }
            } catch (Throwable th) {
                dji.thirdparty.f.c.b.a(th, this.a);
            }
        }

        void d() {
            this.b.a(new dji.thirdparty.f.d.b(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.e();
                }
            }, this.e.a, this.e.a, this.e.c);
        }

        void e() {
            synchronized (this) {
                if (this.d) {
                    return;
                }
                List list = this.c;
                this.c = new ArrayList();
                try {
                    this.a.a_(list);
                } catch (Throwable th) {
                    dji.thirdparty.f.c.b.a(th, (e) this);
                }
            }
        }
    }

    final class b extends k<T> {
        final k<? super List<T>> a;
        final dji.thirdparty.f.g.a b;
        final List<List<T>> c = new LinkedList();
        boolean d;
        final /* synthetic */ au e;

        public b(au auVar, k<? super List<T>> kVar, dji.thirdparty.f.g.a aVar) {
            this.e = auVar;
            this.a = kVar;
            this.b = aVar;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a_(T r6) {
            /*
            r5 = this;
            r1 = 0;
            monitor-enter(r5);
            r0 = r5.d;	 Catch:{ all -> 0x0050 }
            if (r0 == 0) goto L_0x0008;
        L_0x0006:
            monitor-exit(r5);	 Catch:{ all -> 0x0050 }
        L_0x0007:
            return;
        L_0x0008:
            r0 = r5.c;	 Catch:{ all -> 0x0050 }
            r2 = r0.iterator();	 Catch:{ all -> 0x0050 }
        L_0x000e:
            r0 = r2.hasNext();	 Catch:{ all -> 0x0050 }
            if (r0 == 0) goto L_0x0037;
        L_0x0014:
            r0 = r2.next();	 Catch:{ all -> 0x0050 }
            r0 = (java.util.List) r0;	 Catch:{ all -> 0x0050 }
            r0.add(r6);	 Catch:{ all -> 0x0050 }
            r3 = r0.size();	 Catch:{ all -> 0x0050 }
            r4 = r5.e;	 Catch:{ all -> 0x0050 }
            r4 = r4.d;	 Catch:{ all -> 0x0050 }
            if (r3 != r4) goto L_0x0034;
        L_0x0027:
            r2.remove();	 Catch:{ all -> 0x0050 }
            if (r1 != 0) goto L_0x0031;
        L_0x002c:
            r1 = new java.util.LinkedList;	 Catch:{ all -> 0x0050 }
            r1.<init>();	 Catch:{ all -> 0x0050 }
        L_0x0031:
            r1.add(r0);	 Catch:{ all -> 0x0050 }
        L_0x0034:
            r0 = r1;
            r1 = r0;
            goto L_0x000e;
        L_0x0037:
            monitor-exit(r5);	 Catch:{ all -> 0x0050 }
            if (r1 == 0) goto L_0x0007;
        L_0x003a:
            r1 = r1.iterator();
        L_0x003e:
            r0 = r1.hasNext();
            if (r0 == 0) goto L_0x0007;
        L_0x0044:
            r0 = r1.next();
            r0 = (java.util.List) r0;
            r2 = r5.a;
            r2.a_(r0);
            goto L_0x003e;
        L_0x0050:
            r0 = move-exception;
            monitor-exit(r5);	 Catch:{ all -> 0x0050 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.au.b.a_(java.lang.Object):void");
        }

        public void a(Throwable th) {
            synchronized (this) {
                if (this.d) {
                    return;
                }
                this.d = true;
                this.c.clear();
                this.a.a(th);
                n_();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void o_() {
            /*
            r3 = this;
            monitor-enter(r3);	 Catch:{ Throwable -> 0x002d }
            r0 = r3.d;	 Catch:{ all -> 0x0034 }
            if (r0 == 0) goto L_0x0007;
        L_0x0005:
            monitor-exit(r3);	 Catch:{ all -> 0x0034 }
        L_0x0006:
            return;
        L_0x0007:
            r0 = 1;
            r3.d = r0;	 Catch:{ all -> 0x0034 }
            r0 = new java.util.LinkedList;	 Catch:{ all -> 0x0034 }
            r1 = r3.c;	 Catch:{ all -> 0x0034 }
            r0.<init>(r1);	 Catch:{ all -> 0x0034 }
            r1 = r3.c;	 Catch:{ all -> 0x0034 }
            r1.clear();	 Catch:{ all -> 0x0034 }
            monitor-exit(r3);	 Catch:{ all -> 0x0034 }
            r1 = r0.iterator();	 Catch:{ Throwable -> 0x002d }
        L_0x001b:
            r0 = r1.hasNext();	 Catch:{ Throwable -> 0x002d }
            if (r0 == 0) goto L_0x0037;
        L_0x0021:
            r0 = r1.next();	 Catch:{ Throwable -> 0x002d }
            r0 = (java.util.List) r0;	 Catch:{ Throwable -> 0x002d }
            r2 = r3.a;	 Catch:{ Throwable -> 0x002d }
            r2.a_(r0);	 Catch:{ Throwable -> 0x002d }
            goto L_0x001b;
        L_0x002d:
            r0 = move-exception;
            r1 = r3.a;
            dji.thirdparty.f.c.b.a(r0, r1);
            goto L_0x0006;
        L_0x0034:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0034 }
            throw r0;	 Catch:{ Throwable -> 0x002d }
        L_0x0037:
            r0 = r3.a;
            r0.o_();
            r3.n_();
            goto L_0x0006;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.au.b.o_():void");
        }

        void d() {
            this.b.a(new dji.thirdparty.f.d.b(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.e();
                }
            }, this.e.b, this.e.b, this.e.c);
        }

        void e() {
            final List arrayList = new ArrayList();
            synchronized (this) {
                if (this.d) {
                    return;
                }
                this.c.add(arrayList);
                this.b.a(new dji.thirdparty.f.d.b(this) {
                    final /* synthetic */ b b;

                    public void a() {
                        this.b.a(arrayList);
                    }
                }, this.e.a, this.e.c);
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void a(java.util.List<T> r4) {
            /*
            r3 = this;
            r1 = 0;
            monitor-enter(r3);
            r0 = r3.d;	 Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x0008;
        L_0x0006:
            monitor-exit(r3);	 Catch:{ all -> 0x002e }
        L_0x0007:
            return;
        L_0x0008:
            r0 = r3.c;	 Catch:{ all -> 0x002e }
            r2 = r0.iterator();	 Catch:{ all -> 0x002e }
        L_0x000e:
            r0 = r2.hasNext();	 Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x0031;
        L_0x0014:
            r0 = r2.next();	 Catch:{ all -> 0x002e }
            r0 = (java.util.List) r0;	 Catch:{ all -> 0x002e }
            if (r0 != r4) goto L_0x000e;
        L_0x001c:
            r2.remove();	 Catch:{ all -> 0x002e }
            r0 = 1;
        L_0x0020:
            monitor-exit(r3);	 Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x0007;
        L_0x0023:
            r0 = r3.a;	 Catch:{ Throwable -> 0x0029 }
            r0.a_(r4);	 Catch:{ Throwable -> 0x0029 }
            goto L_0x0007;
        L_0x0029:
            r0 = move-exception;
            dji.thirdparty.f.c.b.a(r0, r3);
            goto L_0x0007;
        L_0x002e:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x002e }
            throw r0;
        L_0x0031:
            r0 = r1;
            goto L_0x0020;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.au.b.a(java.util.List):void");
        }
    }

    public au(long j, long j2, TimeUnit timeUnit, int i, g gVar) {
        this.a = j;
        this.b = j2;
        this.c = timeUnit;
        this.d = i;
        this.e = gVar;
    }

    public k<? super T> a(k<? super List<T>> kVar) {
        l a = this.e.a();
        k dVar = new d(kVar);
        if (this.a == this.b) {
            l aVar = new a(this, dVar, a);
            aVar.a(a);
            kVar.a(aVar);
            aVar.d();
            return aVar;
        }
        aVar = new b(this, dVar, a);
        aVar.a(a);
        kVar.a(aVar);
        aVar.e();
        aVar.d();
        return aVar;
    }
}
