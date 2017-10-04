package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.e;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.b;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class at<T, TOpening, TClosing> implements d$g<List<T>, T> {
    final d<? extends TOpening> a;
    final o<? super TOpening, ? extends d<? extends TClosing>> b;

    final class a extends k<T> {
        final k<? super List<T>> a;
        final List<List<T>> b = new LinkedList();
        boolean c;
        final b d = new b();
        final /* synthetic */ at e;

        public a(at atVar, k<? super List<T>> kVar) {
            this.e = atVar;
            this.a = kVar;
            a(this.d);
        }

        public void a_(T t) {
            synchronized (this) {
                for (List add : this.b) {
                    add.add(t);
                }
            }
        }

        public void a(Throwable th) {
            synchronized (this) {
                if (this.c) {
                    return;
                }
                this.c = true;
                this.b.clear();
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
            r0 = r3.c;	 Catch:{ all -> 0x0034 }
            if (r0 == 0) goto L_0x0007;
        L_0x0005:
            monitor-exit(r3);	 Catch:{ all -> 0x0034 }
        L_0x0006:
            return;
        L_0x0007:
            r0 = 1;
            r3.c = r0;	 Catch:{ all -> 0x0034 }
            r0 = new java.util.LinkedList;	 Catch:{ all -> 0x0034 }
            r1 = r3.b;	 Catch:{ all -> 0x0034 }
            r0.<init>(r1);	 Catch:{ all -> 0x0034 }
            r1 = r3.b;	 Catch:{ all -> 0x0034 }
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
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.at.a.o_():void");
        }

        void b(TOpening tOpening) {
            final List arrayList = new ArrayList();
            synchronized (this) {
                if (this.c) {
                    return;
                }
                this.b.add(arrayList);
                try {
                    d dVar = (d) this.e.b.a(tOpening);
                    l anonymousClass1 = new k<TClosing>(this) {
                        final /* synthetic */ a b;

                        public void a_(TClosing tClosing) {
                            this.b.d.b(this);
                            this.b.a(arrayList);
                        }

                        public void a(Throwable th) {
                            this.b.a(th);
                        }

                        public void o_() {
                            this.b.d.b(this);
                            this.b.a(arrayList);
                        }
                    };
                    this.d.a(anonymousClass1);
                    dVar.a(anonymousClass1);
                } catch (Throwable th) {
                    dji.thirdparty.f.c.b.a(th, (e) this);
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void a(java.util.List<T> r4) {
            /*
            r3 = this;
            r1 = 0;
            monitor-enter(r3);
            r0 = r3.c;	 Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0008;
        L_0x0006:
            monitor-exit(r3);	 Catch:{ all -> 0x0029 }
        L_0x0007:
            return;
        L_0x0008:
            r0 = r3.b;	 Catch:{ all -> 0x0029 }
            r2 = r0.iterator();	 Catch:{ all -> 0x0029 }
        L_0x000e:
            r0 = r2.hasNext();	 Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x002c;
        L_0x0014:
            r0 = r2.next();	 Catch:{ all -> 0x0029 }
            r0 = (java.util.List) r0;	 Catch:{ all -> 0x0029 }
            if (r0 != r4) goto L_0x000e;
        L_0x001c:
            r0 = 1;
            r2.remove();	 Catch:{ all -> 0x0029 }
        L_0x0020:
            monitor-exit(r3);	 Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0007;
        L_0x0023:
            r0 = r3.a;
            r0.a_(r4);
            goto L_0x0007;
        L_0x0029:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0029 }
            throw r0;
        L_0x002c:
            r0 = r1;
            goto L_0x0020;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.at.a.a(java.util.List):void");
        }
    }

    public at(d<? extends TOpening> dVar, o<? super TOpening, ? extends d<? extends TClosing>> oVar) {
        this.a = dVar;
        this.b = oVar;
    }

    public k<? super T> a(k<? super List<T>> kVar) {
        final l aVar = new a(this, new dji.thirdparty.f.g.d(kVar));
        l anonymousClass1 = new k<TOpening>(this) {
            final /* synthetic */ at b;

            public void a_(TOpening tOpening) {
                aVar.b(tOpening);
            }

            public void a(Throwable th) {
                aVar.a(th);
            }

            public void o_() {
                aVar.o_();
            }
        };
        kVar.a(anonymousClass1);
        kVar.a(aVar);
        this.a.a(anonymousClass1);
        return aVar;
    }
}
