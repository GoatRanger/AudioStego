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

public class DataOsdGetSdrLBT extends n implements e {
    private static DataOsdGetSdrLBT instance = null;

    public static synchronized DataOsdGetSdrLBT getInstance() {
        DataOsdGetSdrLBT dataOsdGetSdrLBT;
        synchronized (DataOsdGetSdrLBT.class) {
            if (instance == null) {
                instance = new DataOsdGetSdrLBT();
            }
            dataOsdGetSdrLBT = instance;
        }
        return dataOsdGetSdrLBT;
    }

    public boolean getLBTStatue() {
        return ((Integer) get(0, 1, Integer.class)).intValue() == 1;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OFDM.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.j.a();
        cVar.n = i.a.GetSdrLBT.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
    }
}
