package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.i;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.manager.P3.n;
import dji.midware.e.b;

public class DataOsdGetPushMaxMcs extends n implements b {
    private static DataOsdGetPushMaxMcs instance = null;

    public static synchronized DataOsdGetPushMaxMcs getInstance() {
        DataOsdGetPushMaxMcs dataOsdGetPushMaxMcs;
        synchronized (DataOsdGetPushMaxMcs.class) {
            if (instance == null) {
                instance = new DataOsdGetPushMaxMcs();
            }
            dataOsdGetPushMaxMcs = instance;
        }
        return dataOsdGetPushMaxMcs;
    }

    public int getMaxMcs() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    protected void doPack() {
        this._sendData = new byte[1];
    }

    public void start() {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OFDM.value();
        cVar.j = a.b.a();
        cVar.k = q.c.b.a();
        cVar.l = q.b.a.a();
        cVar.m = p.j.a();
        cVar.n = i.a.GetPushMaxMcs.a();
        start(cVar);
    }
}
