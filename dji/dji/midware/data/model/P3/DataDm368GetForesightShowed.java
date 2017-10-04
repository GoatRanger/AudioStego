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

public class DataDm368GetForesightShowed extends n implements e {
    private static DataDm368GetForesightShowed instance = null;

    public static synchronized DataDm368GetForesightShowed getInstance() {
        DataDm368GetForesightShowed dataDm368GetForesightShowed;
        synchronized (DataDm368GetForesightShowed.class) {
            if (instance == null) {
                instance = new DataDm368GetForesightShowed();
            }
            dataDm368GetForesightShowed = instance;
        }
        return dataDm368GetForesightShowed;
    }

    public boolean isOpen() {
        if (this._recData[0] == (byte) 0) {
            return false;
        }
        return true;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.DM368.value();
        cVar.g = 0;
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.i.a();
        cVar.n = dji.midware.data.config.P3.e.a.GetForesightShowed.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
    }
}
