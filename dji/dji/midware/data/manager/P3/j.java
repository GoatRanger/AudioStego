package dji.midware.data.manager.P3;

import android.util.SparseArray;
import dji.midware.data.b.a.b;
import dji.midware.data.config.P3.p;

public class j {
    private static j a = null;
    private SparseArray<b> b = new SparseArray();

    public static synchronized j getInstance() {
        j jVar;
        synchronized (j.class) {
            if (a == null) {
                a = new j();
            }
            jVar = a;
        }
        return jVar;
    }

    public b a(p pVar) {
        if (pVar == null) {
            return null;
        }
        if (this.b == null) {
            this.b = new SparseArray();
        }
        b bVar = (b) this.b.get(pVar.a());
        if (bVar != null) {
            return bVar;
        }
        bVar = new b();
        this.b.put(pVar.a(), bVar);
        return bVar;
    }

    public b a(int i) {
        return a(p.find(i));
    }

    private void a() {
        this.b = null;
    }

    public static synchronized void destroy() {
        synchronized (j.class) {
            if (a != null) {
                a.a();
                a = null;
            }
        }
    }
}
