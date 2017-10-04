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

public class DataRcGetCustomFuction extends n implements e {
    private static DataRcGetCustomFuction instance = null;

    public static synchronized DataRcGetCustomFuction getInstance() {
        DataRcGetCustomFuction dataRcGetCustomFuction;
        synchronized (DataRcGetCustomFuction.class) {
            if (instance == null) {
                instance = new DataRcGetCustomFuction();
            }
            dataRcGetCustomFuction = instance;
        }
        return dataRcGetCustomFuction;
    }

    public int getC2() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int getC1() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.RC.a();
        cVar.n = k.a.O.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
