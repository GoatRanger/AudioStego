package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCameraSetVOutParams.LCDFormat;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraGetVOutParams extends n implements e {
    private static DataCameraGetVOutParams instance = null;

    public static synchronized DataCameraGetVOutParams getInstance() {
        DataCameraGetVOutParams dataCameraGetVOutParams;
        synchronized (DataCameraGetVOutParams.class) {
            if (instance == null) {
                instance = new DataCameraGetVOutParams();
            }
            dataCameraGetVOutParams = instance;
        }
        return dataCameraGetVOutParams;
    }

    public boolean isHDOpen() {
        return ((Integer) get(0, 1, Integer.class)).intValue() == LCDFormat.h.ordinal();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.ai.a();
        start(cVar, dVar);
    }

    protected void doPack() {
    }
}
