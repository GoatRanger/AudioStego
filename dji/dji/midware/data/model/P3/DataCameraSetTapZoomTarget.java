package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataCameraSetTapZoomTarget extends n implements e {
    private static DataCameraSetTapZoomTarget a = null;
    private float b;
    private float c;

    public static synchronized DataCameraSetTapZoomTarget getInstance() {
        DataCameraSetTapZoomTarget dataCameraSetTapZoomTarget;
        synchronized (DataCameraSetTapZoomTarget.class) {
            if (a == null) {
                a = new DataCameraSetTapZoomTarget();
            }
            dataCameraSetTapZoomTarget = a;
        }
        return dataCameraSetTapZoomTarget;
    }

    public DataCameraSetTapZoomTarget a(float f) {
        this.b = f;
        return this;
    }

    public DataCameraSetTapZoomTarget b(float f) {
        this.c = f;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[8];
        System.arraycopy(c.a(this.b), 0, this._sendData, 0, 4);
        System.arraycopy(c.a(this.c), 0, this._sendData, 4, 4);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bQ.a();
        start(cVar, dVar);
    }
}
