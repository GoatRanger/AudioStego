package dji.thirdparty.f.g;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.e;
import dji.thirdparty.f.e.a.r;

public class c<T> implements e<T> {
    private static final int f = 1024;
    private final e<? super T> a;
    private boolean b;
    private volatile boolean c;
    private a d;
    private final r<T> e = r.a();

    static final class a {
        Object[] a;
        int b;

        a() {
        }

        public void a(Object obj) {
            Object[] objArr;
            int i = this.b;
            Object obj2 = this.a;
            if (obj2 == null) {
                objArr = new Object[16];
                this.a = objArr;
            } else if (i == obj2.length) {
                objArr = new Object[((i >> 2) + i)];
                System.arraycopy(obj2, 0, objArr, 0, i);
                this.a = objArr;
            } else {
                Object obj3 = obj2;
            }
            objArr[i] = obj;
            this.b = i + 1;
        }
    }

    public c(e<? super T> eVar) {
        this.a = eVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a_(T r10) {
        /*
        r9 = this;
        r1 = 0;
        r8 = 1;
        r0 = r9.c;
        if (r0 == 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        monitor-enter(r9);
        r0 = r9.c;	 Catch:{ all -> 0x000e }
        if (r0 == 0) goto L_0x0011;
    L_0x000c:
        monitor-exit(r9);	 Catch:{ all -> 0x000e }
        goto L_0x0006;
    L_0x000e:
        r0 = move-exception;
        monitor-exit(r9);	 Catch:{ all -> 0x000e }
        throw r0;
    L_0x0011:
        r0 = r9.b;	 Catch:{ all -> 0x000e }
        if (r0 == 0) goto L_0x002b;
    L_0x0015:
        r0 = r9.d;	 Catch:{ all -> 0x000e }
        if (r0 != 0) goto L_0x0020;
    L_0x0019:
        r0 = new dji.thirdparty.f.g.c$a;	 Catch:{ all -> 0x000e }
        r0.<init>();	 Catch:{ all -> 0x000e }
        r9.d = r0;	 Catch:{ all -> 0x000e }
    L_0x0020:
        r1 = r9.e;	 Catch:{ all -> 0x000e }
        r1 = r1.a(r10);	 Catch:{ all -> 0x000e }
        r0.a(r1);	 Catch:{ all -> 0x000e }
        monitor-exit(r9);	 Catch:{ all -> 0x000e }
        goto L_0x0006;
    L_0x002b:
        r0 = 1;
        r9.b = r0;	 Catch:{ all -> 0x000e }
        monitor-exit(r9);	 Catch:{ all -> 0x000e }
        r0 = r9.a;	 Catch:{ Throwable -> 0x0046 }
        r0.a_(r10);	 Catch:{ Throwable -> 0x0046 }
    L_0x0034:
        r2 = r1;
    L_0x0035:
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        if (r2 >= r0) goto L_0x0034;
    L_0x0039:
        monitor-enter(r9);
        r0 = r9.d;	 Catch:{ all -> 0x0043 }
        if (r0 != 0) goto L_0x004f;
    L_0x003e:
        r0 = 0;
        r9.b = r0;	 Catch:{ all -> 0x0043 }
        monitor-exit(r9);	 Catch:{ all -> 0x0043 }
        goto L_0x0006;
    L_0x0043:
        r0 = move-exception;
        monitor-exit(r9);	 Catch:{ all -> 0x0043 }
        throw r0;
    L_0x0046:
        r0 = move-exception;
        r9.c = r8;
        r1 = r9.a;
        dji.thirdparty.f.c.b.a(r0, r1, r10);
        goto L_0x0006;
    L_0x004f:
        r3 = 0;
        r9.d = r3;	 Catch:{ all -> 0x0043 }
        monitor-exit(r9);	 Catch:{ all -> 0x0043 }
        r3 = r0.a;
        r4 = r3.length;
        r0 = r1;
    L_0x0057:
        if (r0 >= r4) goto L_0x005d;
    L_0x0059:
        r5 = r3[r0];
        if (r5 != 0) goto L_0x0061;
    L_0x005d:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0035;
    L_0x0061:
        r6 = r9.e;	 Catch:{ Throwable -> 0x006f }
        r7 = r9.a;	 Catch:{ Throwable -> 0x006f }
        r5 = r6.a(r7, r5);	 Catch:{ Throwable -> 0x006f }
        if (r5 == 0) goto L_0x007f;
    L_0x006b:
        r0 = 1;
        r9.c = r0;	 Catch:{ Throwable -> 0x006f }
        goto L_0x0006;
    L_0x006f:
        r0 = move-exception;
        r9.c = r8;
        dji.thirdparty.f.c.b.b(r0);
        r1 = r9.a;
        r0 = dji.thirdparty.f.c.g.a(r0, r10);
        r1.a(r0);
        goto L_0x0006;
    L_0x007f:
        r0 = r0 + 1;
        goto L_0x0057;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.g.c.a_(java.lang.Object):void");
    }

    public void a(Throwable th) {
        b.b(th);
        if (!this.c) {
            synchronized (this) {
                if (this.c) {
                    return;
                }
                this.c = true;
                if (this.b) {
                    a aVar = this.d;
                    if (aVar == null) {
                        aVar = new a();
                        this.d = aVar;
                    }
                    aVar.a(this.e.a(th));
                    return;
                }
                this.b = true;
                this.a.a(th);
            }
        }
    }

    public void o_() {
        if (!this.c) {
            synchronized (this) {
                if (this.c) {
                    return;
                }
                this.c = true;
                if (this.b) {
                    a aVar = this.d;
                    if (aVar == null) {
                        aVar = new a();
                        this.d = aVar;
                    }
                    aVar.a(this.e.b());
                    return;
                }
                this.b = true;
                this.a.o_();
            }
        }
    }
}
