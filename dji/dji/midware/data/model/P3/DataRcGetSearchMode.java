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

public class DataRcGetSearchMode extends n implements e {
    private static DataRcGetSearchMode instance = null;

    public static synchronized DataRcGetSearchMode getInstance() {
        DataRcGetSearchMode dataRcGetSearchMode;
        synchronized (DataRcGetSearchMode.class) {
            if (instance == null) {
                instance = new DataRcGetSearchMode();
            }
            dataRcGetSearchMode = instance;
        }
        return dataRcGetSearchMode;
    }

    public boolean getIsOpen() {
        return ((Integer) get(0, 1, Integer.class)).intValue() == 1;
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
        cVar.n = k.a.GetSearchMode.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
