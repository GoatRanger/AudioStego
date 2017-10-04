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

public class DataCenterSelfDischarge extends n implements e {
    private static DataCenterSelfDischarge instance;
    private int mDay = 0;
    private boolean mGet = true;

    public static synchronized DataCenterSelfDischarge getInstance() {
        DataCenterSelfDischarge dataCenterSelfDischarge;
        synchronized (DataCenterSelfDischarge.class) {
            if (instance == null) {
                instance = new DataCenterSelfDischarge();
            }
            dataCenterSelfDischarge = instance;
        }
        return dataCenterSelfDischarge;
    }

    public DataCenterSelfDischarge setFlag(boolean z) {
        this.mGet = z;
        return this;
    }

    public DataCenterSelfDischarge setDays(int i) {
        this.mDay = i;
        return this;
    }

    public int getDay() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    protected void doPack() {
        byte b;
        byte b2 = (byte) 0;
        this._sendData = new byte[2];
        byte[] bArr = this._sendData;
        if (this.mGet) {
            b = (byte) 0;
        } else {
            b = (byte) 1;
        }
        bArr[0] = b;
        byte[] bArr2 = this._sendData;
        if (!this.mGet) {
            b2 = (byte) this.mDay;
        }
        bArr2[1] = b2;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CENTER.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.f.a();
        cVar.n = dji.midware.data.config.P3.c.a.SelfDischarge.a();
        cVar.v = 1500;
        start(cVar, dVar);
    }
}
