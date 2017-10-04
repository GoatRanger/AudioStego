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

public class DataDm368SetForesightShowed extends n implements e {
    private static DataDm368SetForesightShowed a = null;
    private int b = 0;

    public static synchronized DataDm368SetForesightShowed getInstance() {
        DataDm368SetForesightShowed dataDm368SetForesightShowed;
        synchronized (DataDm368SetForesightShowed.class) {
            if (a == null) {
                a = new DataDm368SetForesightShowed();
            }
            dataDm368SetForesightShowed = a;
        }
        return dataDm368SetForesightShowed;
    }

    public DataDm368SetForesightShowed a(boolean z) {
        if (z) {
            this.b = 1;
        } else {
            this.b = 0;
        }
        return this;
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
        cVar.n = dji.midware.data.config.P3.e.a.SetForesightShowed.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.b;
    }
}
