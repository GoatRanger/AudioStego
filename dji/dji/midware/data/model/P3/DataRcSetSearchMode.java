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

public class DataRcSetSearchMode extends n implements e {
    private static DataRcSetSearchMode a = null;
    private boolean b;

    public static synchronized DataRcSetSearchMode getInstance() {
        DataRcSetSearchMode dataRcSetSearchMode;
        synchronized (DataRcSetSearchMode.class) {
            if (a == null) {
                a = new DataRcSetSearchMode();
            }
            dataRcSetSearchMode = a;
        }
        return dataRcSetSearchMode;
    }

    public DataRcSetSearchMode a(boolean z) {
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
        cVar.n = k.a.SetSearchMode.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
