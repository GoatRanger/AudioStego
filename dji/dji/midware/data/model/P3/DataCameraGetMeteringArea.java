package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.manager.P3.n.a;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraGetMeteringArea extends n implements e {
    private static DataCameraGetMeteringArea instance = null;

    public static synchronized DataCameraGetMeteringArea getInstance() {
        DataCameraGetMeteringArea dataCameraGetMeteringArea;
        synchronized (DataCameraGetMeteringArea.class) {
            if (instance == null) {
                instance = new DataCameraGetMeteringArea();
            }
            dataCameraGetMeteringArea = instance;
        }
        return dataCameraGetMeteringArea;
    }

    protected a getDataType() {
        return a.b;
    }

    public int getIndex() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getHnum() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int getVnum() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = q.a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.O.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
