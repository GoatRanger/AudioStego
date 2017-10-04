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

public class DataWifiSetWifiCodeRate extends n implements e {
    private static DataWifiSetWifiCodeRate a = null;
    private int b = 2;

    public static synchronized DataWifiSetWifiCodeRate getInstance() {
        DataWifiSetWifiCodeRate dataWifiSetWifiCodeRate;
        synchronized (DataWifiSetWifiCodeRate.class) {
            if (a == null) {
                a = new DataWifiSetWifiCodeRate();
            }
            dataWifiSetWifiCodeRate = a;
        }
        return dataWifiSetWifiCodeRate;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.DM368.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.i.a();
        cVar.n = dji.midware.data.config.P3.e.a.SetWifiCodeRate.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.b;
    }

    public DataWifiSetWifiCodeRate a(int i) {
        this.b = i;
        return this;
    }
}
