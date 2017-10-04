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

public class DataWifiSetWifiFrequency extends n implements e {
    private static DataWifiSetWifiFrequency a;
    private boolean b = false;
    private int c = 0;

    public DataWifiSetWifiFrequency a(int i) {
        this.c = i;
        return this;
    }

    public static synchronized DataWifiSetWifiFrequency getInstance() {
        DataWifiSetWifiFrequency dataWifiSetWifiFrequency;
        synchronized (DataWifiSetWifiFrequency.class) {
            if (a == null) {
                a = new DataWifiSetWifiFrequency();
            }
            dataWifiSetWifiFrequency = a;
        }
        return dataWifiSetWifiFrequency;
    }

    public DataWifiSetWifiFrequency a(boolean z) {
        this.b = z;
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        if (this.b) {
            cVar.h = DeviceType.WIFI_G.value();
        } else {
            cVar.h = DeviceType.WIFI.value();
        }
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.h.a();
        cVar.n = o.a.SetWifiFrequency.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.c;
    }
}
