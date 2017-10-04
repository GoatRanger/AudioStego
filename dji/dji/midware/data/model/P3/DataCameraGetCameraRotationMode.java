package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCameraSetCameraRotationMode.RotationAngleType;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraGetCameraRotationMode extends n implements e {
    private static DataCameraGetCameraRotationMode instance;

    public static synchronized DataCameraGetCameraRotationMode getInstance() {
        DataCameraGetCameraRotationMode dataCameraGetCameraRotationMode;
        synchronized (DataCameraGetCameraRotationMode.class) {
            if (instance == null) {
                instance = new DataCameraGetCameraRotationMode();
            }
            dataCameraGetCameraRotationMode = instance;
        }
        return dataCameraGetCameraRotationMode;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bJ.a();
        cVar.p = getSendData();
        cVar.w = 3;
        start(cVar, dVar);
    }

    protected void doPack() {
    }

    public int getRotationMode() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public RotationAngleType getRotationAngleType() {
        return RotationAngleType.find(((Integer) get(1, 1, Integer.class)).intValue());
    }
}
