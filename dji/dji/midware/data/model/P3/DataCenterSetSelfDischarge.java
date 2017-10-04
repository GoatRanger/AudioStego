package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataCenterSetSelfDischarge extends n implements e {
    private static DataCenterSetSelfDischarge a;
    private int b = 0;
    private int c = 7;

    public static synchronized DataCenterSetSelfDischarge getInstance() {
        DataCenterSetSelfDischarge dataCenterSetSelfDischarge;
        synchronized (DataCenterSetSelfDischarge.class) {
            if (a == null) {
                a = new DataCenterSetSelfDischarge();
            }
            dataCenterSetSelfDischarge = a;
        }
        return dataCenterSetSelfDischarge;
    }

    public DataCenterSetSelfDischarge a(int i) {
        this.b = i;
        return this;
    }

    public DataCenterSetSelfDischarge b(int i) {
        this.c = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[9];
        this._sendData[0] = (byte) this.b;
        System.arraycopy(c.a(this.c), 0, this._sendData, 1, 4);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.BATTERY.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.f.a();
        cVar.n = dji.midware.data.config.P3.c.a.SetSelfDischarge.a();
        cVar.v = 1500;
        start(cVar, dVar);
    }
}
