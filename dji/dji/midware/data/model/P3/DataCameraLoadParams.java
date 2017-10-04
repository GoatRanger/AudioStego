package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCameraSaveParams.USER;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraLoadParams extends n implements e {
    private static DataCameraLoadParams instance = null;
    private USER user = USER.DEFAULT;

    public static synchronized DataCameraLoadParams getInstance() {
        DataCameraLoadParams dataCameraLoadParams;
        synchronized (DataCameraLoadParams.class) {
            if (instance == null) {
                instance = new DataCameraLoadParams();
            }
            dataCameraLoadParams = instance;
        }
        return dataCameraLoadParams;
    }

    public DataCameraLoadParams setMode(USER user) {
        this.user = user;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.user.value();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.aH.a();
        start(cVar, dVar);
    }
}
