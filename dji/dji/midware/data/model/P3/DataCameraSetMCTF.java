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

public class DataCameraSetMCTF extends n implements e {
    private static DataCameraSetMCTF a = null;
    private boolean b = true;
    private int c = 50;

    public static synchronized DataCameraSetMCTF getInstance() {
        DataCameraSetMCTF dataCameraSetMCTF;
        synchronized (DataCameraSetMCTF.class) {
            if (a == null) {
                a = new DataCameraSetMCTF();
            }
            dataCameraSetMCTF = a;
        }
        return dataCameraSetMCTF;
    }

    public DataCameraSetMCTF a(boolean z) {
        this.b = z;
        return this;
    }

    public DataCameraSetMCTF a(int i) {
        this.c = i;
        return this;
    }

    protected void doPack() {
        byte b;
        this._sendData = new byte[2];
        byte[] bArr = this._sendData;
        if (this.b) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        bArr[0] = b;
        this._sendData[1] = (byte) this.c;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bz.a();
        start(cVar, dVar);
    }
}
