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

public class DataCenterGetSelfDischarge extends n implements e {
    private static DataCenterGetSelfDischarge instance;
    private int mEncrypt = 0;

    public static synchronized DataCenterGetSelfDischarge getInstance() {
        DataCenterGetSelfDischarge dataCenterGetSelfDischarge;
        synchronized (DataCenterGetSelfDischarge.class) {
            if (instance == null) {
                instance = new DataCenterGetSelfDischarge();
            }
            dataCenterGetSelfDischarge = instance;
        }
        return dataCenterGetSelfDischarge;
    }

    public DataCenterGetSelfDischarge setEncrypt(int i) {
        this.mEncrypt = i;
        return this;
    }

    public int getDay() {
        return ((Integer) get(0, 4, Integer.class)).intValue();
    }

    protected void doPack() {
        this._sendData = new byte[9];
        this._sendData[0] = (byte) this.mEncrypt;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.BATTERY.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.f.a();
        cVar.n = dji.midware.data.config.P3.c.a.GetSelfDischarge.a();
        cVar.v = 1500;
        start(cVar, dVar);
    }
}
