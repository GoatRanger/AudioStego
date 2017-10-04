package dji.midware.data.model.d;

import dji.midware.data.config.a.a.a;
import dji.midware.data.config.a.a.b;
import dji.midware.data.config.a.a.c;

public class k extends a {
    private static k a = null;
    private int b;
    private long c;
    private long d;
    private c e;
    private byte f;
    private byte g;

    public static synchronized k getInstance() {
        k kVar;
        synchronized (k.class) {
            if (a == null) {
                a = new k();
            }
            kVar = a;
        }
        return kVar;
    }

    public k a(int i) {
        this.b = i;
        return this;
    }

    public k a(long j) {
        this.c = j;
        return this;
    }

    public k b(long j) {
        this.d = j;
        return this;
    }

    protected void doPack() {
        dji.midware.data.a.b.c cVar = new dji.midware.data.a.b.c();
        cVar.i = new byte[16];
        Object a = dji.midware.util.c.a(this.b);
        Object a2 = dji.midware.util.c.a(this.c);
        Object a3 = dji.midware.util.c.a(this.d);
        System.arraycopy(a, 0, cVar.i, 0, 4);
        System.arraycopy(a2, 0, cVar.i, 4, 4);
        System.arraycopy(a3, 0, cVar.i, 8, 4);
        cVar.c = a.Stream.a();
        cVar.d = b.REQUEST.a();
        cVar.a();
        this._sendData = cVar.j;
    }
}
