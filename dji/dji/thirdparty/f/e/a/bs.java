package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.f.c;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import dji.thirdparty.f.l.f;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public final class bs<T, R> extends c<R> {
    final d<? extends T> c;
    final Object d;
    final n<? extends f<? super T, ? extends R>> e;
    final AtomicReference<f<? super T, ? extends R>> f;
    final List<k<? super R>> g;
    k<T> h;
    l i;

    class AnonymousClass1 implements d$f<R> {
        final /* synthetic */ Object a;
        final /* synthetic */ AtomicReference b;
        final /* synthetic */ List c;

        AnonymousClass1(Object obj, AtomicReference atomicReference, List list) {
            this.a = obj;
            this.b = atomicReference;
            this.c = list;
        }

        public void a(k<? super R> kVar) {
            synchronized (this.a) {
                if (this.b.get() == null) {
                    this.c.add(kVar);
                } else {
                    ((f) this.b.get()).a(kVar);
                }
            }
        }
    }

    public bs(d<? extends T> dVar, n<? extends f<? super T, ? extends R>> nVar) {
        this(new Object(), new AtomicReference(), new ArrayList(), dVar, nVar);
    }

    private bs(Object obj, AtomicReference<f<? super T, ? extends R>> atomicReference, List<k<? super R>> list, d<? extends T> dVar, n<? extends f<? super T, ? extends R>> nVar) {
        super(new AnonymousClass1(obj, atomicReference, list));
        this.d = obj;
        this.f = atomicReference;
        this.g = list;
        this.c = dVar;
        this.e = nVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void h(dji.thirdparty.f.d.c<? super dji.thirdparty.f.l> r6) {
        /*
        r5 = this;
        r2 = r5.d;
        monitor-enter(r2);
        r0 = r5.h;	 Catch:{ all -> 0x0050 }
        if (r0 == 0) goto L_0x000e;
    L_0x0007:
        r0 = r5.i;	 Catch:{ all -> 0x0050 }
        r6.a(r0);	 Catch:{ all -> 0x0050 }
        monitor-exit(r2);	 Catch:{ all -> 0x0050 }
    L_0x000d:
        return;
    L_0x000e:
        r0 = r5.e;	 Catch:{ all -> 0x0050 }
        r0 = r0.call();	 Catch:{ all -> 0x0050 }
        r0 = (dji.thirdparty.f.l.f) r0;	 Catch:{ all -> 0x0050 }
        r1 = dji.thirdparty.f.g.e.a(r0);	 Catch:{ all -> 0x0050 }
        r5.h = r1;	 Catch:{ all -> 0x0050 }
        r1 = new java.util.concurrent.atomic.AtomicReference;	 Catch:{ all -> 0x0050 }
        r1.<init>();	 Catch:{ all -> 0x0050 }
        r3 = new dji.thirdparty.f.e.a.bs$2;	 Catch:{ all -> 0x0050 }
        r3.<init>(r5, r1);	 Catch:{ all -> 0x0050 }
        r3 = dji.thirdparty.f.m.f.a(r3);	 Catch:{ all -> 0x0050 }
        r1.set(r3);	 Catch:{ all -> 0x0050 }
        r1 = r1.get();	 Catch:{ all -> 0x0050 }
        r1 = (dji.thirdparty.f.l) r1;	 Catch:{ all -> 0x0050 }
        r5.i = r1;	 Catch:{ all -> 0x0050 }
        r1 = r5.g;	 Catch:{ all -> 0x0050 }
        r3 = r1.iterator();	 Catch:{ all -> 0x0050 }
    L_0x003b:
        r1 = r3.hasNext();	 Catch:{ all -> 0x0050 }
        if (r1 == 0) goto L_0x0053;
    L_0x0041:
        r1 = r3.next();	 Catch:{ all -> 0x0050 }
        r1 = (dji.thirdparty.f.k) r1;	 Catch:{ all -> 0x0050 }
        r4 = new dji.thirdparty.f.e.a.bs$3;	 Catch:{ all -> 0x0050 }
        r4.<init>(r5, r1, r1);	 Catch:{ all -> 0x0050 }
        r0.a(r4);	 Catch:{ all -> 0x0050 }
        goto L_0x003b;
    L_0x0050:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0050 }
        throw r0;
    L_0x0053:
        r1 = r5.g;	 Catch:{ all -> 0x0050 }
        r1.clear();	 Catch:{ all -> 0x0050 }
        r1 = r5.f;	 Catch:{ all -> 0x0050 }
        r1.set(r0);	 Catch:{ all -> 0x0050 }
        monitor-exit(r2);	 Catch:{ all -> 0x0050 }
        r0 = r5.i;
        r6.a(r0);
        r1 = r5.d;
        monitor-enter(r1);
        r0 = r5.h;	 Catch:{ all -> 0x0071 }
        monitor-exit(r1);	 Catch:{ all -> 0x0071 }
        if (r0 == 0) goto L_0x000d;
    L_0x006b:
        r1 = r5.c;
        r1.b(r0);
        goto L_0x000d;
    L_0x0071:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0071 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.bs.h(dji.thirdparty.f.d.c):void");
    }
}
