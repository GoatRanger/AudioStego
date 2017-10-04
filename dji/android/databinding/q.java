package android.databinding;

import android.support.v4.util.Pools.SynchronizedPool;

public class q extends h<android.databinding.w.a, w, a> {
    private static final SynchronizedPool<a> a = new SynchronizedPool(10);
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static final int f = 4;
    private static final android.databinding.h.a<android.databinding.w.a, w, a> g = new android.databinding.h.a<android.databinding.w.a, w, a>() {
        public void a(android.databinding.w.a aVar, w wVar, int i, a aVar2) {
            switch (i) {
                case 1:
                    aVar.a(wVar, aVar2.a, aVar2.b);
                    return;
                case 2:
                    aVar.b(wVar, aVar2.a, aVar2.b);
                    return;
                case 3:
                    aVar.a(wVar, aVar2.a, aVar2.c, aVar2.b);
                    return;
                case 4:
                    aVar.c(wVar, aVar2.a, aVar2.b);
                    return;
                default:
                    aVar.a(wVar);
                    return;
            }
        }
    };

    static class a {
        public int a;
        public int b;
        public int c;

        a() {
        }
    }

    public void a(w wVar) {
        a(wVar, 0, null);
    }

    public void a(w wVar, int i, int i2) {
        a(wVar, 1, a(i, 0, i2));
    }

    public void b(w wVar, int i, int i2) {
        a(wVar, 2, a(i, 0, i2));
    }

    public void a(w wVar, int i, int i2, int i3) {
        a(wVar, 3, a(i, i2, i3));
    }

    public void c(w wVar, int i, int i2) {
        a(wVar, 4, a(i, 0, i2));
    }

    private static a a(int i, int i2, int i3) {
        a aVar = (a) a.acquire();
        if (aVar == null) {
            aVar = new a();
        }
        aVar.a = i;
        aVar.c = i2;
        aVar.b = i3;
        return aVar;
    }

    public synchronized void a(w wVar, int i, a aVar) {
        super.a(wVar, i, aVar);
        if (aVar != null) {
            a.release(aVar);
        }
    }

    public q() {
        super(g);
    }
}
