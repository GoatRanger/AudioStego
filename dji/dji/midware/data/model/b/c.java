package dji.midware.data.model.b;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.config.P3.q;
import dji.midware.data.manager.P3.n;
import dji.midware.e.b;

public class c extends n implements b {
    private static c a = null;

    public static synchronized c getInstance() {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                a = new c();
            }
            cVar = a;
        }
        return cVar;
    }

    protected void setPushRecData(byte[] bArr) {
        super.setPushRecData(bArr);
        start();
    }

    protected boolean isWantPush() {
        return false;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) a.b.a();
    }

    public void start() {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = this.pack.f;
        cVar.g = this.pack.e;
        cVar.j = q.a.b.a();
        cVar.k = dji.midware.data.config.P3.q.c.b.a();
        cVar.l = q.b.a.a();
        cVar.m = this.pack.m;
        cVar.n = this.pack.n;
        cVar.i = this.pack.i;
        super.start(cVar);
    }
}
