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

public class DataOsdSetMicGain extends n implements e {
    private static DataOsdSetMicGain a = null;
    private int b = 0;

    public static synchronized DataOsdSetMicGain getInstance() {
        DataOsdSetMicGain dataOsdSetMicGain;
        synchronized (DataOsdSetMicGain.class) {
            if (a == null) {
                a = new DataOsdSetMicGain();
            }
            dataOsdSetMicGain = a;
        }
        return dataOsdSetMicGain;
    }

    public DataOsdSetMicGain a(int i) {
        this.b = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.b;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OFDM.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.j.a();
        cVar.n = i.a.SetMicGain.a();
        start(cVar, dVar);
    }
}
