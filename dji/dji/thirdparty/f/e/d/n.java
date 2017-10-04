package dji.thirdparty.f.e.d;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.l;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public final class n implements l {
    private LinkedList<l> a;
    private volatile boolean b;

    public n(l... lVarArr) {
        this.a = new LinkedList(Arrays.asList(lVarArr));
    }

    public n(l lVar) {
        this.a = new LinkedList();
        this.a.add(lVar);
    }

    public boolean b() {
        return this.b;
    }

    public void a(l lVar) {
        if (!lVar.b()) {
            if (!this.b) {
                synchronized (this) {
                    if (!this.b) {
                        LinkedList linkedList = this.a;
                        if (linkedList == null) {
                            linkedList = new LinkedList();
                            this.a = linkedList;
                        }
                        linkedList.add(lVar);
                        return;
                    }
                }
            }
            lVar.n_();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(dji.thirdparty.f.l r3) {
        /*
        r2 = this;
        r0 = r2.b;
        if (r0 != 0) goto L_0x000e;
    L_0x0004:
        monitor-enter(r2);
        r0 = r2.a;	 Catch:{ all -> 0x001a }
        r1 = r2.b;	 Catch:{ all -> 0x001a }
        if (r1 != 0) goto L_0x000d;
    L_0x000b:
        if (r0 != 0) goto L_0x000f;
    L_0x000d:
        monitor-exit(r2);	 Catch:{ all -> 0x001a }
    L_0x000e:
        return;
    L_0x000f:
        r0 = r0.remove(r3);	 Catch:{ all -> 0x001a }
        monitor-exit(r2);	 Catch:{ all -> 0x001a }
        if (r0 == 0) goto L_0x000e;
    L_0x0016:
        r3.n_();
        goto L_0x000e;
    L_0x001a:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x001a }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.d.n.b(dji.thirdparty.f.l):void");
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
            b.a(list);
        }
    }

    public void c() {
        if (!this.b) {
            Collection collection;
            synchronized (this) {
                collection = this.a;
                this.a = null;
            }
            a(collection);
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
