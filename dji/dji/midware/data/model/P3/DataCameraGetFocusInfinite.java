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

public class DataCameraGetFocusInfinite extends n implements e {
    private static DataCameraGetFocusInfinite instance = null;

    public static synchronized DataCameraGetFocusInfinite getInstance() {
        DataCameraGetFocusInfinite dataCameraGetFocusInfinite;
        synchronized (DataCameraGetFocusInfinite.class) {
            if (instance == null) {
                instance = new DataCameraGetFocusInfinite();
            }
            dataCameraGetFocusInfinite = instance;
        }
        return dataCameraGetFocusInfinite;
    }

    public boolean isFocusInfinite() {
        return this._recData[0] == (byte) 1;
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bW.a();
        start(cVar, dVar);
    }
}
