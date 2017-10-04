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

public class DataCameraGetVideoFormat extends n implements e {
    private static DataCameraGetVideoFormat instance = null;

    public static synchronized DataCameraGetVideoFormat getInstance() {
        DataCameraGetVideoFormat dataCameraGetVideoFormat;
        synchronized (DataCameraGetVideoFormat.class) {
            if (instance == null) {
                instance = new DataCameraGetVideoFormat();
            }
            dataCameraGetVideoFormat = instance;
        }
        return dataCameraGetVideoFormat;
    }

    public int getRatio() {
        return ((Short) get(0, 1, Short.class)).shortValue();
    }

    public int getFps() {
        return ((Short) get(1, 1, Short.class)).shortValue();
    }

    public int getFov() {
        return ((Short) get(2, 1, Short.class)).shortValue();
    }

    public int getSecondOpen() {
        return ((Short) get(3, 1, Short.class)).shortValue();
    }

    public int getSecondRatio() {
        return ((Short) get(4, 1, Short.class)).shortValue();
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
        cVar.n = dji.midware.data.config.P3.b.a.p.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
