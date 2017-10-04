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

public class DataCameraSetFocusAid extends n implements e {
    private static DataCameraSetFocusAid a = null;
    private boolean b = true;
    private boolean c = true;

    public static synchronized DataCameraSetFocusAid getInstance() {
        DataCameraSetFocusAid dataCameraSetFocusAid;
        synchronized (DataCameraSetFocusAid.class) {
            if (a == null) {
                a = new DataCameraSetFocusAid();
            }
            dataCameraSetFocusAid = a;
        }
        return dataCameraSetFocusAid;
    }

    public DataCameraSetFocusAid a(boolean z, boolean z2) {
        this.b = z;
        this.c = z2;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        if (this.c) {
            byte[] bArr = this._sendData;
            bArr[0] = (byte) (bArr[0] | 1);
        }
        if (this.b) {
            bArr = this._sendData;
            bArr[0] = (byte) (bArr[0] | 2);
        }
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bs.a();
        start(cVar, dVar);
    }
}
