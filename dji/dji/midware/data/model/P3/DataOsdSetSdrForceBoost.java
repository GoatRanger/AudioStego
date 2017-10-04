package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.i;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataOsdSetSdrForceBoost extends n implements e {
    private static DataOsdSetSdrForceBoost a = null;

    public static synchronized DataOsdSetSdrForceBoost getInstance() {
        DataOsdSetSdrForceBoost dataOsdSetSdrForceBoost;
        synchronized (DataOsdSetSdrForceBoost.class) {
            if (a == null) {
                a = new DataOsdSetSdrForceBoost();
            }
            dataOsdSetSdrForceBoost = a;
        }
        return dataOsdSetSdrForceBoost;
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OFDM.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.OSD.a();
        cVar.n = i.a.P.a();
        start(cVar, dVar);
    }
}
