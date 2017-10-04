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

public class DataCameraGetAEBParams extends n implements e {
    private static DataCameraGetAEBParams instance = null;

    public static synchronized DataCameraGetAEBParams getInstance() {
        DataCameraGetAEBParams dataCameraGetAEBParams;
        synchronized (DataCameraGetAEBParams.class) {
            if (instance == null) {
                instance = new DataCameraGetAEBParams();
            }
            dataCameraGetAEBParams = instance;
        }
        return dataCameraGetAEBParams;
    }

    public int getExposureValue() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getAEBNumber() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
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
        cVar.n = dji.midware.data.config.P3.b.a.an.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
