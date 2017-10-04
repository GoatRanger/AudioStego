package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraSetAELock extends n implements e {
    private static DataCameraSetAELock a = null;
    private boolean b;

    public static synchronized DataCameraSetAELock getInstance() {
        DataCameraSetAELock dataCameraSetAELock;
        synchronized (DataCameraSetAELock.class) {
            if (a == null) {
                a = new DataCameraSetAELock();
            }
            dataCameraSetAELock = a;
        }
        return dataCameraSetAELock;
    }

    public DataCameraSetAELock a(boolean z) {
        this.b = z;
        return this;
    }

    protected void doPack() {
        int i = 1;
        this._sendData = new byte[1];
        byte[] bArr = this._sendData;
        if (!this.b) {
            i = 0;
        }
        bArr[0] = (byte) i;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.az.a();
        start(cVar, dVar);
    }
}
