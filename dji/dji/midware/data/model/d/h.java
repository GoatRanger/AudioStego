package dji.midware.data.model.d;

import dji.midware.data.a.b.c;
import dji.midware.data.config.a.a.a;
import dji.midware.data.config.a.a.b;

public class h extends a {
    private static h a = null;
    private a b;
    private int c;
    private int d;

    public static synchronized h getInstance() {
        h hVar;
        synchronized (h.class) {
            if (a == null) {
                a = new h();
            }
            hVar = a;
        }
        return hVar;
    }

    public h a(a aVar) {
        this.b = aVar;
        return this;
    }

    public h a(int i) {
        this.c = i;
        return this;
    }

    public h b(int i) {
        this.d = i;
        return this;
    }

    protected void doPack() {
        c cVar = new c();
        cVar.c = this.b.a();
        cVar.d = b.ACK.a();
        cVar.i = new byte[((this.d * 8) + 5)];
        System.arraycopy(dji.midware.util.c.a(this.c), 0, cVar.i, 0, 4);
        cVar.i[4] = (byte) this.d;
        if (this.d > 0) {
            Object a = dji.midware.util.c.a(this.c);
            System.arraycopy(a, 0, cVar.i, 5, a.length);
            a = dji.midware.util.c.a(-1);
            System.arraycopy(a, 0, cVar.i, 9, a.length);
        }
        cVar.b();
        this._sendData = cVar.j;
    }
}
