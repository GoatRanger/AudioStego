package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataRcGetGimbalSpeed extends n implements e {
    private static DataRcGetGimbalSpeed instance = null;

    public static synchronized DataRcGetGimbalSpeed getInstance() {
        DataRcGetGimbalSpeed dataRcGetGimbalSpeed;
        synchronized (DataRcGetGimbalSpeed.class) {
            if (instance == null) {
                instance = new DataRcGetGimbalSpeed();
            }
            dataRcGetGimbalSpeed = instance;
        }
        return dataRcGetGimbalSpeed;
    }

    public int getPitch() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getRoll() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int getYaw() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.GetGimbalSpeed.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
