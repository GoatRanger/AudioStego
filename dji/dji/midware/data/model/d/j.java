package dji.midware.data.model.d;

import dji.midware.data.config.a.a.a;
import dji.midware.data.config.a.a.b;
import dji.midware.data.config.a.a.c;

public class j extends a {
    private static j a = null;
    private int b;
    private int c;
    private c d;

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

    public j a(int i) {
        this.b = i;
        return this;
    }

    public j b(int i) {
        this.c = i;
        return this;
    }

    public j a(c cVar) {
        this.d = cVar;
        return this;
    }

    protected void doPack() {
        dji.midware.data.a.b.c cVar = new dji.midware.data.a.b.c();
        cVar.i = new byte[7];
        Object a = dji.midware.util.c.a(this.b);
        Object b = dji.midware.util.c.b(this.c);
        System.arraycopy(a, 0, cVar.i, 0, a.length);
        System.arraycopy(b, 0, cVar.i, 4, b.length);
        cVar.i[6] = (byte) this.d.a();
        cVar.c = a.a.a();
        cVar.d = b.REQUEST.a();
        cVar.a();
        this._sendData = cVar.j;
    }
}
