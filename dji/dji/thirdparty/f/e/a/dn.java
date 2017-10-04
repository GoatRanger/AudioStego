package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.e;
import dji.thirdparty.f.g;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class dn<T> implements d$g<dji.thirdparty.f.d<T>, T> {
    static final Object f = new Object();
    static final r<Object> g = r.a();
    final long a;
    final long b;
    final TimeUnit c;
    final g d;
    final int e;

    static final class a<T> {
        final e<T> a;
        final dji.thirdparty.f.d<T> b;
        int c;

        public a(e<T> eVar, dji.thirdparty.f.d<T> dVar) {
            this.a = new dji.thirdparty.f.g.c(eVar);
            this.b = dVar;
        }
    }

    final class b extends k<T> {
        final k<? super dji.thirdparty.f.d<T>> a;
        final dji.thirdparty.f.g.a b;
        final Object c = new Object();
        List<Object> d;
        boolean e;
        volatile d<T> f = d.c();
        final /* synthetic */ dn g;

        public b(final dn dnVar, k<? super dji.thirdparty.f.d<T>> kVar, dji.thirdparty.f.g.a aVar) {
            this.g = dnVar;
            this.a = new dji.thirdparty.f.g.d(kVar);
            this.b = aVar;
            kVar.a(f.a(new dji.thirdparty.f.d.b(this) {
                final /* synthetic */ b b;

                public void a() {
                    if (this.b.f.a == null) {
                        this.b.n_();
                    }
                }
            }));
        }

        public void c() {
            a((long) IPositioningSession.NotSet);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a_(T r6) {
            /*
            r5 = this;
            r1 = 1;
            r2 = 0;
            r3 = r5.c;
            monitor-enter(r3);
            r0 = r5.e;	 Catch:{ all -> 0x0030 }
            if (r0 == 0) goto L_0x001b;
        L_0x0009:
            r0 = r5.d;	 Catch:{ all -> 0x0030 }
            if (r0 != 0) goto L_0x0014;
        L_0x000d:
            r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0030 }
            r0.<init>();	 Catch:{ all -> 0x0030 }
            r5.d = r0;	 Catch:{ all -> 0x0030 }
        L_0x0014:
            r0 = r5.d;	 Catch:{ all -> 0x0030 }
            r0.add(r6);	 Catch:{ all -> 0x0030 }
            monitor-exit(r3);	 Catch:{ all -> 0x0030 }
        L_0x001a:
            return;
        L_0x001b:
            r0 = 1;
            r5.e = r0;	 Catch:{ all -> 0x0030 }
            monitor-exit(r3);	 Catch:{ all -> 0x0030 }
            r0 = r5.b(r6);	 Catch:{ all -> 0x0066 }
            if (r0 != 0) goto L_0x0033;
        L_0x0025:
            r1 = r5.c;
            monitor-enter(r1);
            r0 = 0;
            r5.e = r0;	 Catch:{ all -> 0x002d }
            monitor-exit(r1);	 Catch:{ all -> 0x002d }
            goto L_0x001a;
        L_0x002d:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x002d }
            throw r0;
        L_0x0030:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0030 }
            throw r0;
        L_0x0033:
            r3 = r5.c;	 Catch:{ all -> 0x0066 }
            monitor-enter(r3);	 Catch:{ all -> 0x0066 }
            r0 = r5.d;	 Catch:{ all -> 0x0068 }
            if (r0 != 0) goto L_0x004e;
        L_0x003a:
            r0 = 0;
            r5.e = r0;	 Catch:{ all -> 0x0068 }
            monitor-exit(r3);	 Catch:{ all -> 0x003f }
            goto L_0x001a;
        L_0x003f:
            r0 = move-exception;
        L_0x0040:
            monitor-exit(r3);	 Catch:{ all -> 0x003f }
            throw r0;	 Catch:{ all -> 0x0042 }
        L_0x0042:
            r0 = move-exception;
            r2 = r1;
        L_0x0044:
            if (r2 != 0) goto L_0x004d;
        L_0x0046:
            r1 = r5.c;
            monitor-enter(r1);
            r2 = 0;
            r5.e = r2;	 Catch:{ all -> 0x0063 }
            monitor-exit(r1);	 Catch:{ all -> 0x0063 }
        L_0x004d:
            throw r0;
        L_0x004e:
            r4 = 0;
            r5.d = r4;	 Catch:{ all -> 0x0068 }
            monitor-exit(r3);	 Catch:{ all -> 0x0068 }
            r0 = r5.a(r0);	 Catch:{ all -> 0x0066 }
            if (r0 != 0) goto L_0x0033;
        L_0x0058:
            r1 = r5.c;
            monitor-enter(r1);
            r0 = 0;
            r5.e = r0;	 Catch:{ all -> 0x0060 }
            monitor-exit(r1);	 Catch:{ all -> 0x0060 }
            goto L_0x001a;
        L_0x0060:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0060 }
            throw r0;
        L_0x0063:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0063 }
            throw r0;
        L_0x0066:
            r0 = move-exception;
            goto L_0x0044;
        L_0x0068:
            r0 = move-exception;
            r1 = r2;
            goto L_0x0040;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.dn.b.a_(java.lang.Object):void");
        }

        boolean a(List<Object> list) {
            if (list == null) {
                return true;
            }
            for (Object next : list) {
                if (next == dn.f) {
                    if (!d()) {
                        return false;
                    }
                } else if (dn.g.c(next)) {
                    b(dn.g.h(next));
                    return true;
                } else if (dn.g.b(next)) {
                    e();
                    return true;
                } else if (!b(next)) {
                    return false;
                }
            }
            return true;
        }

        boolean d() {
            e eVar = this.f.a;
            if (eVar != null) {
                eVar.o_();
            }
            if (this.a.b()) {
                this.f = this.f.b();
                n_();
                return false;
            }
            Object I = dv.I();
            this.f = this.f.a(I, I);
            this.a.a_(I);
            return true;
        }

        boolean b(T t) {
            d dVar = this.f;
            if (dVar.a == null) {
                if (!d()) {
                    return false;
                }
                dVar = this.f;
            }
            dVar.a.a_(t);
            if (dVar.c == this.g.e - 1) {
                dVar.a.o_();
                dVar = dVar.b();
            } else {
                dVar = dVar.a();
            }
            this.f = dVar;
            return true;
        }

        public void a(Throwable th) {
            synchronized (this.c) {
                if (this.e) {
                    this.d = Collections.singletonList(dn.g.a(th));
                    return;
                }
                this.d = null;
                this.e = true;
                b(th);
            }
        }

        void b(Throwable th) {
            e eVar = this.f.a;
            this.f = this.f.b();
            if (eVar != null) {
                eVar.a(th);
            }
            this.a.a(th);
            n_();
        }

        void e() {
            e eVar = this.f.a;
            this.f = this.f.b();
            if (eVar != null) {
                eVar.o_();
            }
            this.a.o_();
            n_();
        }

        public void o_() {
            synchronized (this.c) {
                if (this.e) {
                    if (this.d == null) {
                        this.d = new ArrayList();
                    }
                    this.d.add(dn.g.b());
                    return;
                }
                List list = this.d;
                this.d = null;
                this.e = true;
                try {
                    a(list);
                    e();
                } catch (Throwable th) {
                    b(th);
                }
            }
        }

        void f() {
            this.b.a(new dji.thirdparty.f.d.b(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.g();
                }
            }, 0, this.g.a, this.g.c);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void g() {
            /*
            r5 = this;
            r1 = 1;
            r2 = 0;
            r3 = r5.c;
            monitor-enter(r3);
            r0 = r5.e;	 Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x001d;
        L_0x0009:
            r0 = r5.d;	 Catch:{ all -> 0x0032 }
            if (r0 != 0) goto L_0x0014;
        L_0x000d:
            r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0032 }
            r0.<init>();	 Catch:{ all -> 0x0032 }
            r5.d = r0;	 Catch:{ all -> 0x0032 }
        L_0x0014:
            r0 = r5.d;	 Catch:{ all -> 0x0032 }
            r1 = dji.thirdparty.f.e.a.dn.f;	 Catch:{ all -> 0x0032 }
            r0.add(r1);	 Catch:{ all -> 0x0032 }
            monitor-exit(r3);	 Catch:{ all -> 0x0032 }
        L_0x001c:
            return;
        L_0x001d:
            r0 = 1;
            r5.e = r0;	 Catch:{ all -> 0x0032 }
            monitor-exit(r3);	 Catch:{ all -> 0x0032 }
            r0 = r5.d();	 Catch:{ all -> 0x0068 }
            if (r0 != 0) goto L_0x0035;
        L_0x0027:
            r1 = r5.c;
            monitor-enter(r1);
            r0 = 0;
            r5.e = r0;	 Catch:{ all -> 0x002f }
            monitor-exit(r1);	 Catch:{ all -> 0x002f }
            goto L_0x001c;
        L_0x002f:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x002f }
            throw r0;
        L_0x0032:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0032 }
            throw r0;
        L_0x0035:
            r3 = r5.c;	 Catch:{ all -> 0x0068 }
            monitor-enter(r3);	 Catch:{ all -> 0x0068 }
            r0 = r5.d;	 Catch:{ all -> 0x006a }
            if (r0 != 0) goto L_0x0050;
        L_0x003c:
            r0 = 0;
            r5.e = r0;	 Catch:{ all -> 0x006a }
            monitor-exit(r3);	 Catch:{ all -> 0x0041 }
            goto L_0x001c;
        L_0x0041:
            r0 = move-exception;
        L_0x0042:
            monitor-exit(r3);	 Catch:{ all -> 0x0041 }
            throw r0;	 Catch:{ all -> 0x0044 }
        L_0x0044:
            r0 = move-exception;
            r2 = r1;
        L_0x0046:
            if (r2 != 0) goto L_0x004f;
        L_0x0048:
            r1 = r5.c;
            monitor-enter(r1);
            r2 = 0;
            r5.e = r2;	 Catch:{ all -> 0x0065 }
            monitor-exit(r1);	 Catch:{ all -> 0x0065 }
        L_0x004f:
            throw r0;
        L_0x0050:
            r4 = 0;
            r5.d = r4;	 Catch:{ all -> 0x006a }
            monitor-exit(r3);	 Catch:{ all -> 0x006a }
            r0 = r5.a(r0);	 Catch:{ all -> 0x0068 }
            if (r0 != 0) goto L_0x0035;
        L_0x005a:
            r1 = r5.c;
            monitor-enter(r1);
            r0 = 0;
            r5.e = r0;	 Catch:{ all -> 0x0062 }
            monitor-exit(r1);	 Catch:{ all -> 0x0062 }
            goto L_0x001c;
        L_0x0062:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0062 }
            throw r0;
        L_0x0065:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0065 }
            throw r0;
        L_0x0068:
            r0 = move-exception;
            goto L_0x0046;
        L_0x006a:
            r0 = move-exception;
            r1 = r2;
            goto L_0x0042;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.dn.b.g():void");
        }
    }

    final class c extends k<T> {
        final k<? super dji.thirdparty.f.d<T>> a;
        final dji.thirdparty.f.g.a b;
        final Object c = new Object();
        final List<a<T>> d = new LinkedList();
        boolean e;
        final /* synthetic */ dn f;

        public c(dn dnVar, k<? super dji.thirdparty.f.d<T>> kVar, dji.thirdparty.f.g.a aVar) {
            this.f = dnVar;
            super(kVar);
            this.a = kVar;
            this.b = aVar;
        }

        public void c() {
            a((long) IPositioningSession.NotSet);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a_(T r6) {
            /*
            r5 = this;
            r1 = r5.c;
            monitor-enter(r1);
            r0 = r5.e;	 Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x0009;
        L_0x0007:
            monitor-exit(r1);	 Catch:{ all -> 0x0032 }
        L_0x0008:
            return;
        L_0x0009:
            r2 = new java.util.ArrayList;	 Catch:{ all -> 0x0032 }
            r0 = r5.d;	 Catch:{ all -> 0x0032 }
            r2.<init>(r0);	 Catch:{ all -> 0x0032 }
            r0 = r5.d;	 Catch:{ all -> 0x0032 }
            r3 = r0.iterator();	 Catch:{ all -> 0x0032 }
        L_0x0016:
            r0 = r3.hasNext();	 Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x0035;
        L_0x001c:
            r0 = r3.next();	 Catch:{ all -> 0x0032 }
            r0 = (dji.thirdparty.f.e.a.dn.a) r0;	 Catch:{ all -> 0x0032 }
            r4 = r0.c;	 Catch:{ all -> 0x0032 }
            r4 = r4 + 1;
            r0.c = r4;	 Catch:{ all -> 0x0032 }
            r0 = r5.f;	 Catch:{ all -> 0x0032 }
            r0 = r0.e;	 Catch:{ all -> 0x0032 }
            if (r4 != r0) goto L_0x0016;
        L_0x002e:
            r3.remove();	 Catch:{ all -> 0x0032 }
            goto L_0x0016;
        L_0x0032:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0032 }
            throw r0;
        L_0x0035:
            monitor-exit(r1);	 Catch:{ all -> 0x0032 }
            r1 = r2.iterator();
        L_0x003a:
            r0 = r1.hasNext();
            if (r0 == 0) goto L_0x0008;
        L_0x0040:
            r0 = r1.next();
            r0 = (dji.thirdparty.f.e.a.dn.a) r0;
            r2 = r0.a;
            r2.a_(r6);
            r2 = r0.c;
            r3 = r5.f;
            r3 = r3.e;
            if (r2 != r3) goto L_0x003a;
        L_0x0053:
            r0 = r0.a;
            r0.o_();
            goto L_0x003a;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.dn.c.a_(java.lang.Object):void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(java.lang.Throwable r4) {
            /*
            r3 = this;
            r1 = r3.c;
            monitor-enter(r1);
            r0 = r3.e;	 Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x0009;
        L_0x0007:
            monitor-exit(r1);	 Catch:{ all -> 0x002f }
        L_0x0008:
            return;
        L_0x0009:
            r0 = 1;
            r3.e = r0;	 Catch:{ all -> 0x002f }
            r0 = new java.util.ArrayList;	 Catch:{ all -> 0x002f }
            r2 = r3.d;	 Catch:{ all -> 0x002f }
            r0.<init>(r2);	 Catch:{ all -> 0x002f }
            r2 = r3.d;	 Catch:{ all -> 0x002f }
            r2.clear();	 Catch:{ all -> 0x002f }
            monitor-exit(r1);	 Catch:{ all -> 0x002f }
            r1 = r0.iterator();
        L_0x001d:
            r0 = r1.hasNext();
            if (r0 == 0) goto L_0x0032;
        L_0x0023:
            r0 = r1.next();
            r0 = (dji.thirdparty.f.e.a.dn.a) r0;
            r0 = r0.a;
            r0.a(r4);
            goto L_0x001d;
        L_0x002f:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x002f }
            throw r0;
        L_0x0032:
            r0 = r3.a;
            r0.a(r4);
            goto L_0x0008;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.dn.c.a(java.lang.Throwable):void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void o_() {
            /*
            r3 = this;
            r1 = r3.c;
            monitor-enter(r1);
            r0 = r3.e;	 Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x0009;
        L_0x0007:
            monitor-exit(r1);	 Catch:{ all -> 0x002f }
        L_0x0008:
            return;
        L_0x0009:
            r0 = 1;
            r3.e = r0;	 Catch:{ all -> 0x002f }
            r0 = new java.util.ArrayList;	 Catch:{ all -> 0x002f }
            r2 = r3.d;	 Catch:{ all -> 0x002f }
            r0.<init>(r2);	 Catch:{ all -> 0x002f }
            r2 = r3.d;	 Catch:{ all -> 0x002f }
            r2.clear();	 Catch:{ all -> 0x002f }
            monitor-exit(r1);	 Catch:{ all -> 0x002f }
            r1 = r0.iterator();
        L_0x001d:
            r0 = r1.hasNext();
            if (r0 == 0) goto L_0x0032;
        L_0x0023:
            r0 = r1.next();
            r0 = (dji.thirdparty.f.e.a.dn.a) r0;
            r0 = r0.a;
            r0.o_();
            goto L_0x001d;
        L_0x002f:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x002f }
            throw r0;
        L_0x0032:
            r0 = r3.a;
            r0.o_();
            goto L_0x0008;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.dn.c.o_():void");
        }

        void d() {
            this.b.a(new dji.thirdparty.f.d.b(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.e();
                }
            }, this.f.b, this.f.b, this.f.c);
        }

        void e() {
            final a f = f();
            synchronized (this.c) {
                if (this.e) {
                    return;
                }
                this.d.add(f);
                try {
                    this.a.a_(f.b);
                    this.b.a(new dji.thirdparty.f.d.b(this) {
                        final /* synthetic */ c b;

                        public void a() {
                            this.b.a(f);
                        }
                    }, this.f.a, this.f.c);
                } catch (Throwable th) {
                    a(th);
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void a(dji.thirdparty.f.e.a.dn.a<T> r5) {
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
            r0 = (dji.thirdparty.f.e.a.dn.a) r0;	 Catch:{ all -> 0x002b }
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
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.dn.c.a(dji.thirdparty.f.e.a.dn$a):void");
        }

        a<T> f() {
            Object I = dv.I();
            return new a(I, I);
        }
    }

    static final class d<T> {
        static final d<Object> d = new d(null, null, 0);
        final e<T> a;
        final dji.thirdparty.f.d<T> b;
        final int c;

        public d(e<T> eVar, dji.thirdparty.f.d<T> dVar, int i) {
            this.a = eVar;
            this.b = dVar;
            this.c = i;
        }

        public d<T> a() {
            return new d(this.a, this.b, this.c + 1);
        }

        public d<T> a(e<T> eVar, dji.thirdparty.f.d<T> dVar) {
            return new d(eVar, dVar, 0);
        }

        public d<T> b() {
            return c();
        }

        public static <T> d<T> c() {
            return d;
        }
    }

    public dn(long j, long j2, TimeUnit timeUnit, int i, g gVar) {
        this.a = j;
        this.b = j2;
        this.c = timeUnit;
        this.e = i;
        this.d = gVar;
    }

    public k<? super T> a(k<? super dji.thirdparty.f.d<T>> kVar) {
        l a = this.d.a();
        if (this.a == this.b) {
            k bVar = new b(this, kVar, a);
            bVar.a(a);
            bVar.f();
            return bVar;
        }
        k<? super T> cVar = new c(this, kVar, a);
        cVar.a(a);
        cVar.e();
        cVar.d();
        return cVar;
    }
}
