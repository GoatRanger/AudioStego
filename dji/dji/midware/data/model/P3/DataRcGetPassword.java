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

public class DataRcGetPassword extends n implements e {
    private static DataRcGetPassword instance = null;

    public static synchronized DataRcGetPassword getInstance() {
        DataRcGetPassword dataRcGetPassword;
        synchronized (DataRcGetPassword.class) {
            if (instance == null) {
                instance = new DataRcGetPassword();
            }
            dataRcGetPassword = instance;
        }
        return dataRcGetPassword;
    }

    public int getPw() {
        return ((Integer) get(0, 2, Integer.class)).intValue();
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
        cVar.n = k.a.GetPassword.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
