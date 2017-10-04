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

public class DataCameraSetSSDVideoFormat extends n implements e {
    private static DataCameraSetSSDVideoFormat a = null;
    private int b;
    private int c;

    public static synchronized DataCameraSetSSDVideoFormat getInstance() {
        DataCameraSetSSDVideoFormat dataCameraSetSSDVideoFormat;
        synchronized (DataCameraSetSSDVideoFormat.class) {
            if (a == null) {
                a = new DataCameraSetSSDVideoFormat();
            }
            dataCameraSetSSDVideoFormat = a;
        }
        return dataCameraSetSSDVideoFormat;
    }

    public DataCameraSetSSDVideoFormat a(int i) {
        this.b = i;
        return this;
    }

    public DataCameraSetSSDVideoFormat b(int i) {
        this.c = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[2];
        this._sendData[0] = (byte) this.b;
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
        cVar.n = dji.midware.data.config.P3.b.a.bp.a();
        start(cVar, dVar);
    }
}
