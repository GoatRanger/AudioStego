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

public class DataCameraSetLockGimbalWhenShot extends n implements e {
    private static DataCameraSetLockGimbalWhenShot a = null;
    private boolean b = false;

    public static synchronized DataCameraSetLockGimbalWhenShot getInstance() {
        DataCameraSetLockGimbalWhenShot dataCameraSetLockGimbalWhenShot;
        synchronized (DataCameraSetLockGimbalWhenShot.class) {
            if (a == null) {
                a = new DataCameraSetLockGimbalWhenShot();
            }
            dataCameraSetLockGimbalWhenShot = a;
        }
        return dataCameraSetLockGimbalWhenShot;
    }

    public DataCameraSetLockGimbalWhenShot a(boolean z) {
        this.b = z;
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bK.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[1];
        if (this.b) {
            this._sendData[0] = (byte) 1;
        } else {
            this._sendData[0] = (byte) 0;
        }
    }
}
