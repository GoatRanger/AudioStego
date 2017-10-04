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

public class DataWifiGetWifiFrequency extends n implements e {
    private static DataWifiGetWifiFrequency instance;
    private boolean isFromLongan = false;

    public static synchronized DataWifiGetWifiFrequency getInstance() {
        DataWifiGetWifiFrequency dataWifiGetWifiFrequency;
        synchronized (DataWifiGetWifiFrequency.class) {
            if (instance == null) {
                instance = new DataWifiGetWifiFrequency();
            }
            dataWifiGetWifiFrequency = instance;
        }
        return dataWifiGetWifiFrequency;
    }

    public DataWifiGetWifiFrequency setFromLongan(boolean z) {
        this.isFromLongan = z;
        return this;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        if (this.isFromLongan) {
            cVar.h = DeviceType.WIFI_G.value();
        } else {
            cVar.h = DeviceType.WIFI.value();
        }
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.h.a();
        cVar.n = o.a.GetWifiFrequency.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
    }
}
