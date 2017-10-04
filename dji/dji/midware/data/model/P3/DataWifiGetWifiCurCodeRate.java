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

public class DataWifiGetWifiCurCodeRate extends n implements e {
    private static DataWifiGetWifiCurCodeRate instance = null;

    public static synchronized DataWifiGetWifiCurCodeRate getInstance() {
        DataWifiGetWifiCurCodeRate dataWifiGetWifiCurCodeRate;
        synchronized (DataWifiGetWifiCurCodeRate.class) {
            if (instance == null) {
                instance = new DataWifiGetWifiCurCodeRate();
            }
            dataWifiGetWifiCurCodeRate = instance;
        }
        return dataWifiGetWifiCurCodeRate;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.DM368.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.i.a();
        cVar.n = dji.midware.data.config.P3.e.a.GetWifiCurCodeRate.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
    }

    public int getCurCodeRate() {
        return dji.midware.util.c.a(this._recData[0]);
    }
}
