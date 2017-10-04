package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.o;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataWifiRestart extends n implements e {
    private static DataWifiRestart a = null;
    private boolean b = false;

    public DataWifiRestart a(boolean z) {
        this.b = z;
        return this;
    }

    public static synchronized DataWifiRestart getInstance() {
        DataWifiRestart dataWifiRestart;
        synchronized (DataWifiRestart.class) {
            if (a == null) {
                a = new DataWifiRestart();
            }
            dataWifiRestart = a;
        }
        return dataWifiRestart;
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        if (this.b) {
            cVar.h = DeviceType.WIFI.value();
        } else {
            cVar.h = DeviceType.WIFI_G.value();
        }
        ProductType c = i.getInstance().c();
        if (c == ProductType.KumquatX || c == ProductType.KumquatS) {
            cVar.h = DeviceType.WIFI.value();
        }
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.WIFI.a();
        cVar.n = o.a.j.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
