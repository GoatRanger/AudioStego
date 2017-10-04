package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataRcSetPassword extends n implements e {
    private static DataRcSetPassword a = null;
    private int b;

    public static synchronized DataRcSetPassword getInstance() {
        DataRcSetPassword dataRcSetPassword;
        synchronized (DataRcSetPassword.class) {
            if (a == null) {
                a = new DataRcSetPassword();
            }
            dataRcSetPassword = a;
        }
        return dataRcSetPassword;
    }

    public DataRcSetPassword a(int i) {
        this.b = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[2];
        System.arraycopy(c.b(this.b), 0, this._sendData, 0, 2);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.SetPassword.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
