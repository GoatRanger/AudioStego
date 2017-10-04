package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycSetPushGpsSnr extends n implements e {
    private static DataFlycSetPushGpsSnr a = null;
    private boolean b = false;

    public static synchronized DataFlycSetPushGpsSnr getInstance() {
        DataFlycSetPushGpsSnr dataFlycSetPushGpsSnr;
        synchronized (DataFlycSetPushGpsSnr.class) {
            if (a == null) {
                a = new DataFlycSetPushGpsSnr();
            }
            dataFlycSetPushGpsSnr = a;
        }
        return dataFlycSetPushGpsSnr;
    }

    public void a(boolean z) {
        this.b = z;
    }

    protected void doPack() {
        byte b = (byte) 1;
        this._sendData = new byte[1];
        byte[] bArr = this._sendData;
        if (!this.b) {
            b = (byte) 0;
        }
        bArr[0] = b;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.K.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
