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

public class DataWifiSetNoisyCheckAdapt extends n implements e {
    private static DataWifiSetNoisyCheckAdapt a = null;
    private DeviceType b = DeviceType.WIFI_G;
    private boolean c = false;

    public static synchronized DataWifiSetNoisyCheckAdapt getInstance() {
        DataWifiSetNoisyCheckAdapt dataWifiSetNoisyCheckAdapt;
        synchronized (DataWifiSetNoisyCheckAdapt.class) {
            if (a == null) {
                a = new DataWifiSetNoisyCheckAdapt();
            }
            dataWifiSetNoisyCheckAdapt = a;
        }
        return dataWifiSetNoisyCheckAdapt;
    }

    public DataWifiSetNoisyCheckAdapt a(boolean z) {
        this.c = z;
        return this;
    }

    public DataWifiSetNoisyCheckAdapt a(DeviceType deviceType) {
        this.b = deviceType;
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
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.WIFI.a();
        cVar.n = o.a.l.a();
        cVar.p = getSendData();
        cVar.w = 5;
        start(cVar, dVar);
    }
}
