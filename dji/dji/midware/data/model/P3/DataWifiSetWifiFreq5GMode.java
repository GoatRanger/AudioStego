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

public class DataWifiSetWifiFreq5GMode extends n implements e {
    private static DataWifiSetWifiFreq5GMode a = null;
    private int b = 0;

    public static synchronized DataWifiSetWifiFreq5GMode getInstance() {
        DataWifiSetWifiFreq5GMode dataWifiSetWifiFreq5GMode;
        synchronized (DataWifiSetWifiFreq5GMode.class) {
            if (a == null) {
                a = new DataWifiSetWifiFreq5GMode();
            }
            dataWifiSetWifiFreq5GMode = a;
        }
        return dataWifiSetWifiFreq5GMode;
    }

    public DataWifiSetWifiFreq5GMode a(int i) {
        this.b = i;
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.WIFI.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.WIFI.a();
        cVar.n = o.a.t.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[4];
        this._sendData[0] = (byte) this.b;
    }
}
