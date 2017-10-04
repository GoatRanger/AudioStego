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

public class DataRcSetToggle extends n implements e {
    private static DataRcSetToggle a = null;
    private boolean b;

    public static synchronized DataRcSetToggle getInstance() {
        DataRcSetToggle dataRcSetToggle;
        synchronized (DataRcSetToggle.class) {
            if (a == null) {
                a = new DataRcSetToggle();
            }
            dataRcSetToggle = a;
        }
        return dataRcSetToggle;
    }

    public DataRcSetToggle a(boolean z) {
        this.b = z;
        return this;
    }

    protected void doPack() {
        int i = 1;
        this._sendData = new byte[1];
        byte[] bArr = this._sendData;
        if (!this.b) {
            i = 0;
        }
        bArr[0] = (byte) i;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.SetToggle.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
