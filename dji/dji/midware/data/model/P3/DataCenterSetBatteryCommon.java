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

public class DataCenterSetBatteryCommon extends n implements e {
    private static DataCenterSetBatteryCommon a = null;
    private int b;
    private int c = 10;

    public static synchronized DataCenterSetBatteryCommon getInstance() {
        DataCenterSetBatteryCommon dataCenterSetBatteryCommon;
        synchronized (DataCenterSetBatteryCommon.class) {
            if (a == null) {
                a = new DataCenterSetBatteryCommon();
            }
            dataCenterSetBatteryCommon = a;
        }
        return dataCenterSetBatteryCommon;
    }

    public DataCenterSetBatteryCommon a(int i) {
        this.b = i;
        return this;
    }

    public DataCenterSetBatteryCommon b(int i) {
        this.c = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[3];
        this._sendData[0] = (byte) this.b;
        System.arraycopy(c.b(this.c), 0, this._sendData, 1, 2);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CENTER.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.f.a();
        cVar.n = dji.midware.data.config.P3.c.a.SetBatteryCommon.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
