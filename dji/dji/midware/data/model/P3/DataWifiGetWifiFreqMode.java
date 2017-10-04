package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.o;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataWifiGetWifiFreqMode extends n implements e {
    private static DataWifiGetWifiFreqMode instance = null;

    public static synchronized DataWifiGetWifiFreqMode getInstance() {
        DataWifiGetWifiFreqMode dataWifiGetWifiFreqMode;
        synchronized (DataWifiGetWifiFreqMode.class) {
            if (instance == null) {
                instance = new DataWifiGetWifiFreqMode();
            }
            dataWifiGetWifiFreqMode = instance;
        }
        return dataWifiGetWifiFreqMode;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.WIFI.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.WIFI.a();
        cVar.n = o.a.u.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
    }

    public int getFreqMode() {
        return dji.midware.util.c.a(this._recData[0]);
    }
}
