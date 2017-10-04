package dji.thirdparty.f.m;

import dji.thirdparty.f.l;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class b implements l {
    private Set<l> a;
    private volatile boolean b;

    public b(l... lVarArr) {
        this.a = new HashSet(Arrays.asList(lVarArr));
    }

    public boolean b() {
        return this.b;
    }

    public void a(l lVar) {
        if (!lVar.b()) {
            if (!this.b) {
                synchronized (this) {
                    if (!this.b) {
                        if (this.a == null) {
                            this.a = new HashSet(4);
                        }
                        this.a.add(lVar);
                        return;
                    }
                }
            }
            lVar.n_();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(dji.thirdparty.f.l r2) {
        /*
        r1 = this;
        r0 = r1.b;
        if (r0 != 0) goto L_0x000e;
    L_0x0004:
        monitor-enter(r1);
        r0 = r1.b;	 Catch:{ all -> 0x001c }
        if (r0 != 0) goto L_0x000d;
    L_0x0009:
        r0 = r1.a;	 Catch:{ all -> 0x001c }
        if (r0 != 0) goto L_0x000f;
    L_0x000d:
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
    L_0x000e:
        return;
    L_0x000f:
        r0 = r1.a;	 Catch:{ all -> 0x001c }
        r0 = r0.remove(r2);	 Catch:{ all -> 0x001c }
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
        if (r0 == 0) goto L_0x000e;
    L_0x0018:
        r2.n_();
        goto L_0x000e;
    L_0x001c:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.m.b.b(dji.thirdparty.f.l):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c() {
        /*
        r2 = this;
        r0 = r2.b;
        if (r0 != 0) goto L_0x000e;
    L_0x0004:
        monitor-enter(r2);
        r0 = r2.b;	 Catch:{ all -> 0x0019 }
        if (r0 != 0) goto L_0x000d;
    L_0x0009:
        r0 = r2.a;	 Catch:{ all -> 0x0019 }
        if (r0 != 0) goto L_0x000f;
    L_0x000d:
        monitor-exit(r2);	 Catch:{ all -> 0x0019 }
    L_0x000e:
        return;
    L_0x000f:
        r0 = r2.a;	 Catch:{ all -> 0x0019 }
        r1 = 0;
        r2.a = r1;	 Catch:{ all -> 0x0019 }
        monitor-exit(r2);	 Catch:{ all -> 0x0019 }
        a(r0);
        goto L_0x000e;
    L_0x0019:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0019 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.m.b.c():void");
    }

    public void n_() {
        if (!this.b) {
            synchronized (this) {
                if (this.b) {
                    return;
                }
                this.b = true;
                Collection collection = this.a;
                this.a = null;
                a(collection);
            }
        }
    }

    private static void a(Collection<l> collection) {
        if (collection != null) {
            List list = null;
            for (l n_ : collection) {
                try {
                    n_.n_();
                } catch (Throwable th) {
                    List arrayList;
                    if (list == null) {
                        arrayList = new ArrayList();
                    } else {
                        arrayList = list;
                    }
                    arrayList.add(th);
                    list = arrayList;
                }
            }
            dji.thirdparty.f.c.b.a(list);
        }
    }

    public boolean d() {
        boolean z = false;
        if (!this.b) {
            synchronized (this) {
                if (!(this.b || this.a == null || this.a.isEmpty())) {
                    z = true;
                }
            }
        }
        return z;
    }
}
