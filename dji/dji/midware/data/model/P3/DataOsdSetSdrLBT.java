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

public class DataOsdSetSdrLBT extends n implements e {
    private static DataOsdSetSdrLBT a = null;
    private int b = 0;

    public static synchronized DataOsdSetSdrLBT getInstance() {
        DataOsdSetSdrLBT dataOsdSetSdrLBT;
        synchronized (DataOsdSetSdrLBT.class) {
            if (a == null) {
                a = new DataOsdSetSdrLBT();
            }
            dataOsdSetSdrLBT = a;
        }
        return dataOsdSetSdrLBT;
    }

    public DataOsdSetSdrLBT a(boolean z) {
        this.b = z ? 1 : 0;
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
        cVar.n = i.a.SetSdrLBT.a();
        start(cVar, dVar);
    }
}
