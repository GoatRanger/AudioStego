package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.e;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class dk<T, U> implements d$g<d<T>, T> {
    static final Object b = new Object();
    static final r<Object> c = r.a();
    final n<? extends d<? extends U>> a;

    static final class a<T, U> extends k<U> {
        final b<T, U> a;
        boolean b;

        public a(k<?> kVar, b<T, U> bVar) {
            this.a = bVar;
        }

        public void c() {
            a((long) IPositioningSession.NotSet);
        }

        public void a_(U u) {
            if (!this.b) {
                this.b = true;
                this.a.f();
            }
        }

        public void a(Throwable th) {
            this.a.a(th);
        }

        public void o_() {
            if (!this.b) {
                this.b = true;
                this.a.o_();
            }
        }
    }

    static final class b<T, U> extends k<T> {
        final k<? super d<T>> a;
        final Object b = new Object();
        e<T> c;
        d<T> d;
        boolean e;
        List<Object> f;
        final dji.thirdparty.f.m.e g = new dji.thirdparty.f.m.e();
        final n<? extends d<? extends U>> h;

        public b(k<? super d<T>> kVar, n<? extends d<? extends U>> nVar) {
            this.a = new dji.thirdparty.f.g.d(kVar);
            this.h = nVar;
            a(this.g);
        }

        public void c() {
            a((long) IPositioningSession.NotSet);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a_(T r7) {
            /*
            r6 = this;
            r1 = 1;
            r2 = 0;
            r3 = r6.b;
            monitor-enter(r3);
            r0 = r6.e;	 Catch:{ all -> 0x004d }
            if (r0 == 0) goto L_0x001b;
        L_0x0009:
            r0 = r6.f;	 Catch:{ all -> 0x004d }
            if (r0 != 0) goto L_0x0014;
        L_0x000d:
            r0 = new java.util.ArrayList;	 Catch:{ all -> 0x004d }
            r0.<init>();	 Catch:{ all -> 0x004d }
            r6.f = r0;	 Catch:{ all -> 0x004d }
        L_0x0014:
            r0 = r6.f;	 Catch:{ all -> 0x004d }
            r0.add(r7);	 Catch:{ all -> 0x004d }
            monitor-exit(r3);	 Catch:{ all -> 0x004d }
        L_0x001a:
            return;
        L_0x001b:
            r0 = r6.f;	 Catch:{ all -> 0x004d }
            r4 = 0;
            r6.f = r4;	 Catch:{ all -> 0x004d }
            r4 = 1;
            r6.e = r4;	 Catch:{ all -> 0x004d }
            monitor-exit(r3);	 Catch:{ all -> 0x004d }
            r3 = r0;
            r0 = r1;
        L_0x0026:
            r6.a(r3);	 Catch:{ all -> 0x0067 }
            if (r0 == 0) goto L_0x002f;
        L_0x002b:
            r6.b(r7);	 Catch:{ all -> 0x0067 }
            r0 = r2;
        L_0x002f:
            r4 = r6.b;	 Catch:{ all -> 0x0067 }
            monitor-enter(r4);	 Catch:{ all -> 0x0067 }
            r3 = r6.f;	 Catch:{ all -> 0x0069 }
            r5 = 0;
            r6.f = r5;	 Catch:{ all -> 0x0069 }
            if (r3 != 0) goto L_0x0050;
        L_0x0039:
            r0 = 0;
            r6.e = r0;	 Catch:{ all -> 0x0069 }
            monitor-exit(r4);	 Catch:{ all -> 0x003e }
            goto L_0x001a;
        L_0x003e:
            r0 = move-exception;
        L_0x003f:
            monitor-exit(r4);	 Catch:{ all -> 0x003e }
            throw r0;	 Catch:{ all -> 0x0041 }
        L_0x0041:
            r0 = move-exception;
            r2 = r1;
        L_0x0043:
            if (r2 != 0) goto L_0x004c;
        L_0x0045:
            r1 = r6.b;
            monitor-enter(r1);
            r2 = 0;
            r6.e = r2;	 Catch:{ all -> 0x0064 }
            monitor-exit(r1);	 Catch:{ all -> 0x0064 }
        L_0x004c:
            throw r0;
        L_0x004d:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x004d }
            throw r0;
        L_0x0050:
            monitor-exit(r4);	 Catch:{ all -> 0x0069 }
            r4 = r6.a;	 Catch:{ all -> 0x0067 }
            r4 = r4.b();	 Catch:{ all -> 0x0067 }
            if (r4 == 0) goto L_0x0026;
        L_0x0059:
            r1 = r6.b;
            monitor-enter(r1);
            r0 = 0;
            r6.e = r0;	 Catch:{ all -> 0x0061 }
            monitor-exit(r1);	 Catch:{ all -> 0x0061 }
            goto L_0x001a;
        L_0x0061:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0061 }
            throw r0;
        L_0x0064:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0064 }
            throw r0;
        L_0x0067:
            r0 = move-exception;
            goto L_0x0043;
        L_0x0069:
            r0 = move-exception;
            r1 = r2;
            goto L_0x003f;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.dk.b.a_(java.lang.Object):void");
        }

        void a(List<Object> list) {
            if (list != null) {
                for (Object next : list) {
                    if (next == dk.b) {
                        d();
                    } else if (dk.c.c(next)) {
                        b(dk.c.h(next));
                        return;
                    } else if (dk.c.b(next)) {
                        g();
                        return;
                    } else {
                        b(next);
                    }
                }
            }
        }

        void d() {
            e eVar = this.c;
            if (eVar != null) {
                eVar.o_();
            }
            e();
            this.a.a_(this.d);
        }

        void e() {
            Object I = dv.I();
            this.c = I;
            this.d = I;
            try {
                d dVar = (d) this.h.call();
                Object aVar = new a(this.a, this);
                this.g.a(aVar);
                dVar.a(aVar);
            } catch (Throwable th) {
                this.a.a(th);
                n_();
            }
        }

        void b(T t) {
            e eVar = this.c;
            if (eVar != null) {
                eVar.a_(t);
            }
        }

        public void a(Throwable th) {
            synchronized (this.b) {
                if (this.e) {
                    this.f = Collections.singletonList(dk.c.a(th));
                    return;
                }
                this.f = null;
                this.e = true;
                b(th);
            }
        }

        public void o_() {
            synchronized (this.b) {
                if (this.e) {
                    if (this.f == null) {
                        this.f = new ArrayList();
                    }
                    this.f.add(dk.c.b());
                    return;
                }
                List list = this.f;
                this.f = null;
                this.e = true;
                try {
                    a(list);
                    g();
                } catch (Throwable th) {
                    b(th);
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void f() {
            /*
            r6 = this;
            r1 = 1;
            r2 = 0;
            r3 = r6.b;
            monitor-enter(r3);
            r0 = r6.e;	 Catch:{ all -> 0x004f }
            if (r0 == 0) goto L_0x001d;
        L_0x0009:
            r0 = r6.f;	 Catch:{ all -> 0x004f }
            if (r0 != 0) goto L_0x0014;
        L_0x000d:
            r0 = new java.util.ArrayList;	 Catch:{ all -> 0x004f }
            r0.<init>();	 Catch:{ all -> 0x004f }
            r6.f = r0;	 Catch:{ all -> 0x004f }
        L_0x0014:
            r0 = r6.f;	 Catch:{ all -> 0x004f }
            r1 = dji.thirdparty.f.e.a.dk.b;	 Catch:{ all -> 0x004f }
            r0.add(r1);	 Catch:{ all -> 0x004f }
            monitor-exit(r3);	 Catch:{ all -> 0x004f }
        L_0x001c:
            return;
        L_0x001d:
            r0 = r6.f;	 Catch:{ all -> 0x004f }
            r4 = 0;
            r6.f = r4;	 Catch:{ all -> 0x004f }
            r4 = 1;
            r6.e = r4;	 Catch:{ all -> 0x004f }
            monitor-exit(r3);	 Catch:{ all -> 0x004f }
            r3 = r0;
            r0 = r1;
        L_0x0028:
            r6.a(r3);	 Catch:{ all -> 0x0069 }
            if (r0 == 0) goto L_0x0031;
        L_0x002d:
            r6.d();	 Catch:{ all -> 0x0069 }
            r0 = r2;
        L_0x0031:
            r4 = r6.b;	 Catch:{ all -> 0x0069 }
            monitor-enter(r4);	 Catch:{ all -> 0x0069 }
            r3 = r6.f;	 Catch:{ all -> 0x006b }
            r5 = 0;
            r6.f = r5;	 Catch:{ all -> 0x006b }
            if (r3 != 0) goto L_0x0052;
        L_0x003b:
            r0 = 0;
            r6.e = r0;	 Catch:{ all -> 0x006b }
            monitor-exit(r4);	 Catch:{ all -> 0x0040 }
            goto L_0x001c;
        L_0x0040:
            r0 = move-exception;
        L_0x0041:
            monitor-exit(r4);	 Catch:{ all -> 0x0040 }
            throw r0;	 Catch:{ all -> 0x0043 }
        L_0x0043:
            r0 = move-exception;
            r2 = r1;
        L_0x0045:
            if (r2 != 0) goto L_0x004e;
        L_0x0047:
            r1 = r6.b;
            monitor-enter(r1);
            r2 = 0;
            r6.e = r2;	 Catch:{ all -> 0x0066 }
            monitor-exit(r1);	 Catch:{ all -> 0x0066 }
        L_0x004e:
            throw r0;
        L_0x004f:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x004f }
            throw r0;
        L_0x0052:
            monitor-exit(r4);	 Catch:{ all -> 0x006b }
            r4 = r6.a;	 Catch:{ all -> 0x0069 }
            r4 = r4.b();	 Catch:{ all -> 0x0069 }
            if (r4 == 0) goto L_0x0028;
        L_0x005b:
            r1 = r6.b;
            monitor-enter(r1);
            r0 = 0;
            r6.e = r0;	 Catch:{ all -> 0x0063 }
            monitor-exit(r1);	 Catch:{ all -> 0x0063 }
            goto L_0x001c;
        L_0x0063:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0063 }
            throw r0;
        L_0x0066:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0066 }
            throw r0;
        L_0x0069:
            r0 = move-exception;
            goto L_0x0045;
        L_0x006b:
            r0 = move-exception;
            r1 = r2;
            goto L_0x0041;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.dk.b.f():void");
        }

        void g() {
            e eVar = this.c;
            this.c = null;
            this.d = null;
            if (eVar != null) {
                eVar.o_();
            }
            this.a.o_();
            n_();
        }

        void b(Throwable th) {
            e eVar = this.c;
            this.c = null;
            this.d = null;
            if (eVar != null) {
                eVar.a(th);
            }
            this.a.a(th);
            n_();
        }
    }

    public dk(n<? extends d<? extends U>> nVar) {
        this.a = nVar;
    }

    public k<? super T> a(k<? super d<T>> kVar) {
        l bVar = new b(kVar, this.a);
        kVar.a(bVar);
        bVar.f();
        return bVar;
    }
}
