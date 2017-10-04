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

public class DataWifiRequestPushSnr extends n implements e {
    private static DataWifiRequestPushSnr a = null;
    private DeviceType b = DeviceType.WIFI_G;
    private boolean c = true;

    public static synchronized DataWifiRequestPushSnr getInstance() {
        DataWifiRequestPushSnr dataWifiRequestPushSnr;
        synchronized (DataWifiRequestPushSnr.class) {
            if (a == null) {
                a = new DataWifiRequestPushSnr();
            }
            dataWifiRequestPushSnr = a;
        }
        return dataWifiRequestPushSnr;
    }

    public DataWifiRequestPushSnr a(DeviceType deviceType) {
        this.b = deviceType;
        return this;
    }

    public DataWifiRequestPushSnr a(boolean z) {
        this.c = z;
        return this;
    }

    protected void doPack() {
        byte b = (byte) 1;
        this._sendData = new byte[1];
        byte[] bArr = this._sendData;
        if (!this.c) {
            b = (byte) 0;
        }
        bArr[0] = b;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = this.b.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.h.a();
        cVar.n = o.a.RequestSnrPush.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
