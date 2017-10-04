package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.e;
import dji.thirdparty.f.g.c;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.LinkedList;
import java.util.List;

public final class dm<T, U, V> implements d$g<d<T>, T> {
    final d<? extends U> a;
    final o<? super U, ? extends d<? extends V>> b;

    static final class a<T> {
        final e<T> a;
        final d<T> b;

        public a(e<T> eVar, d<T> dVar) {
            this.a = new c(eVar);
            this.b = dVar;
        }
    }

    final class b extends k<T> {
        final k<? super d<T>> a;
        final dji.thirdparty.f.m.b b;
        final Object c = new Object();
        final List<a<T>> d = new LinkedList();
        boolean e;
        final /* synthetic */ dm f;

        public b(dm dmVar, k<? super d<T>> kVar, dji.thirdparty.f.m.b bVar) {
            this.f = dmVar;
            this.a = new dji.thirdparty.f.g.d(kVar);
            this.b = bVar;
        }

        public void c() {
            a((long) IPositioningSession.NotSet);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a_(T r4) {
            /*
            r3 = this;
            r1 = r3.c;
            monitor-enter(r1);
            r0 = r3.e;	 Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x0009;
        L_0x0007:
            monitor-exit(r1);	 Catch:{ all -> 0x0027 }
        L_0x0008:
            return;
        L_0x0009:
            r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0027 }
            r2 = r3.d;	 Catch:{ all -> 0x0027 }
            r0.<init>(r2);	 Catch:{ all -> 0x0027 }
            monitor-exit(r1);	 Catch:{ all -> 0x0027 }
            r1 = r0.iterator();
        L_0x0015:
            r0 = r1.hasNext();
            if (r0 == 0) goto L_0x0008;
        L_0x001b:
            r0 = r1.next();
            r0 = (dji.thirdparty.f.e.a.dm.a) r0;
            r0 = r0.a;
            r0.a_(r4);
            goto L_0x0015;
        L_0x0027:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0027 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.dm.b.a_(java.lang.Object):void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(java.lang.Throwable r4) {
            /*
            r3 = this;
            r1 = r3.c;	 Catch:{ all -> 0x0034 }
            monitor-enter(r1);	 Catch:{ all -> 0x0034 }
            r0 = r3.e;	 Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x000e;
        L_0x0007:
            monitor-exit(r1);	 Catch:{ all -> 0x003b }
            r0 = r3.b;
            r0.n_();
        L_0x000d:
            return;
        L_0x000e:
            r0 = 1;
            r3.e = r0;	 Catch:{ all -> 0x003b }
            r0 = new java.util.ArrayList;	 Catch:{ all -> 0x003b }
            r2 = r3.d;	 Catch:{ all -> 0x003b }
            r0.<init>(r2);	 Catch:{ all -> 0x003b }
            r2 = r3.d;	 Catch:{ all -> 0x003b }
            r2.clear();	 Catch:{ all -> 0x003b }
            monitor-exit(r1);	 Catch:{ all -> 0x003b }
            r1 = r0.iterator();	 Catch:{ all -> 0x0034 }
        L_0x0022:
            r0 = r1.hasNext();	 Catch:{ all -> 0x0034 }
            if (r0 == 0) goto L_0x003e;
        L_0x0028:
            r0 = r1.next();	 Catch:{ all -> 0x0034 }
            r0 = (dji.thirdparty.f.e.a.dm.a) r0;	 Catch:{ all -> 0x0034 }
            r0 = r0.a;	 Catch:{ all -> 0x0034 }
            r0.a(r4);	 Catch:{ all -> 0x0034 }
            goto L_0x0022;
        L_0x0034:
            r0 = move-exception;
            r1 = r3.b;
            r1.n_();
            throw r0;
        L_0x003b:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x003b }
            throw r0;	 Catch:{ all -> 0x0034 }
        L_0x003e:
            r0 = r3.a;	 Catch:{ all -> 0x0034 }
            r0.a(r4);	 Catch:{ all -> 0x0034 }
            r0 = r3.b;
            r0.n_();
            goto L_0x000d;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.dm.b.a(java.lang.Throwable):void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void o_() {
            /*
            r3 = this;
            r1 = r3.c;	 Catch:{ all -> 0x0034 }
            monitor-enter(r1);	 Catch:{ all -> 0x0034 }
            r0 = r3.e;	 Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x000e;
        L_0x0007:
            monitor-exit(r1);	 Catch:{ all -> 0x003b }
            r0 = r3.b;
            r0.n_();
        L_0x000d:
            return;
        L_0x000e:
            r0 = 1;
            r3.e = r0;	 Catch:{ all -> 0x003b }
            r0 = new java.util.ArrayList;	 Catch:{ all -> 0x003b }
            r2 = r3.d;	 Catch:{ all -> 0x003b }
            r0.<init>(r2);	 Catch:{ all -> 0x003b }
            r2 = r3.d;	 Catch:{ all -> 0x003b }
            r2.clear();	 Catch:{ all -> 0x003b }
            monitor-exit(r1);	 Catch:{ all -> 0x003b }
            r1 = r0.iterator();	 Catch:{ all -> 0x0034 }
        L_0x0022:
            r0 = r1.hasNext();	 Catch:{ all -> 0x0034 }
            if (r0 == 0) goto L_0x003e;
        L_0x0028:
            r0 = r1.next();	 Catch:{ all -> 0x0034 }
            r0 = (dji.thirdparty.f.e.a.dm.a) r0;	 Catch:{ all -> 0x0034 }
            r0 = r0.a;	 Catch:{ all -> 0x0034 }
            r0.o_();	 Catch:{ all -> 0x0034 }
            goto L_0x0022;
        L_0x0034:
            r0 = move-exception;
            r1 = r3.b;
            r1.n_();
            throw r0;
        L_0x003b:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x003b }
            throw r0;	 Catch:{ all -> 0x0034 }
        L_0x003e:
            r0 = r3.a;	 Catch:{ all -> 0x0034 }
            r0.o_();	 Catch:{ all -> 0x0034 }
            r0 = r3.b;
            r0.n_();
            goto L_0x000d;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.dm.b.o_():void");
        }

        void b(U u) {
            final a d = d();
            synchronized (this.c) {
                if (this.e) {
                    return;
                }
                this.d.add(d);
                this.a.a_(d.b);
                try {
                    d dVar = (d) this.f.b.a(u);
                    l anonymousClass1 = new k<V>(this) {
                        boolean a = true;
                        final /* synthetic */ b c;

                        public void a_(V v) {
                            o_();
                        }

                        public void a(Throwable th) {
                        }

                        public void o_() {
                            if (this.a) {
                                this.a = false;
                                this.c.a(d);
                                this.c.b.b(this);
                            }
                        }
                    };
                    this.b.a(anonymousClass1);
                    dVar.a(anonymousClass1);
                } catch (Throwable th) {
                    a(th);
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void a(dji.thirdparty.f.e.a.dm.a<T> r5) {
            /*
            r4 = this;
            r1 = 0;
            r2 = r4.c;
            monitor-enter(r2);
            r0 = r4.e;	 Catch:{ all -> 0x002b }
            if (r0 == 0) goto L_0x000a;
        L_0x0008:
            monitor-exit(r2);	 Catch:{ all -> 0x002b }
        L_0x0009:
            return;
        L_0x000a:
            r0 = r4.d;	 Catch:{ all -> 0x002b }
            r3 = r0.iterator();	 Catch:{ all -> 0x002b }
        L_0x0010:
            r0 = r3.hasNext();	 Catch:{ all -> 0x002b }
            if (r0 == 0) goto L_0x002e;
        L_0x0016:
            r0 = r3.next();	 Catch:{ all -> 0x002b }
            r0 = (dji.thirdparty.f.e.a.dm.a) r0;	 Catch:{ all -> 0x002b }
            if (r0 != r5) goto L_0x0010;
        L_0x001e:
            r0 = 1;
            r3.remove();	 Catch:{ all -> 0x002b }
        L_0x0022:
            monitor-exit(r2);	 Catch:{ all -> 0x002b }
            if (r0 == 0) goto L_0x0009;
        L_0x0025:
            r0 = r5.a;
            r0.o_();
            goto L_0x0009;
        L_0x002b:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x002b }
            throw r0;
        L_0x002e:
            r0 = r1;
            goto L_0x0022;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.dm.b.a(dji.thirdparty.f.e.a.dm$a):void");
        }

        a<T> d() {
            Object I = dv.I();
            return new a(I, I);
        }
    }

    public dm(d<? extends U> dVar, o<? super U, ? extends d<? extends V>> oVar) {
        this.a = dVar;
        this.b = oVar;
    }

    public k<? super T> a(k<? super d<T>> kVar) {
        l bVar = new dji.thirdparty.f.m.b();
        kVar.a(bVar);
        final l bVar2 = new b(this, kVar, bVar);
        l anonymousClass1 = new k<U>(this) {
            final /* synthetic */ dm b;

            public void c() {
                a((long) IPositioningSession.NotSet);
            }

            public void a_(U u) {
                bVar2.b(u);
            }

            public void a(Throwable th) {
                bVar2.a(th);
            }

            public void o_() {
                bVar2.o_();
            }
        };
        bVar.a(bVar2);
        bVar.a(anonymousClass1);
        this.a.a(anonymousClass1);
        return bVar2;
    }
}
