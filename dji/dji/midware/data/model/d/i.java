package dji.midware.data.model.d;

import dji.log.DJILogHelper;
import dji.midware.data.config.a.a.a;
import dji.midware.data.config.a.a.b;
import dji.midware.data.config.a.a.c;

public class i extends a {
    private static i a = null;
    private int b;
    private int c;
    private c d;
    private int e;
    private long f;
    private long g;

    public static synchronized i getInstance() {
        i iVar;
        synchronized (i.class) {
            if (a == null) {
                a = new i();
            }
            iVar = a;
        }
        return iVar;
    }

    public i a(int i) {
        this.b = i;
        return this;
    }

    public i b(int i) {
        this.c = i;
        return this;
    }

    public i a(c cVar) {
        this.d = cVar;
        return this;
    }

    public i c(int i) {
        this.e = i;
        return this;
    }

    public i a(long j) {
        this.f = j;
        return this;
    }

    public i b(long j) {
        this.g = j;
        return this;
    }

    protected void doPack() {
        dji.midware.data.a.b.c cVar = new dji.midware.data.a.b.c();
        cVar.i = new byte[16];
        Object a = dji.midware.util.c.a(this.b);
        Object b = dji.midware.util.c.b(this.c);
        Object b2 = dji.midware.util.c.b(this.f);
        Object b3 = dji.midware.util.c.b(this.g);
        System.arraycopy(a, 0, cVar.i, 0, a.length);
        System.arraycopy(b, 0, cVar.i, 4, b.length);
        cVar.i[6] = (byte) this.d.a();
        cVar.i[7] = (byte) this.e;
        System.arraycopy(b2, 0, cVar.i, 8, b2.length);
        System.arraycopy(b3, 0, cVar.i, 12, b3.length);
        cVar.c = a.File.a();
        cVar.d = b.REQUEST.a();
        cVar.a();
        DJILogHelper.getInstance().LOGD("", "sendPack.cmdId=" + cVar.c + " time=" + System.currentTimeMillis(), true, false);
        this._sendData = cVar.j;
    }
}
