package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.o;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataWifiSetModeChannel extends n implements e {
    private static DataWifiSetModeChannel a;
    private int b = 1;
    private int c;

    public static synchronized DataWifiSetModeChannel getInstance() {
        DataWifiSetModeChannel dataWifiSetModeChannel;
        synchronized (DataWifiSetModeChannel.class) {
            if (a == null) {
                a = new DataWifiSetModeChannel();
            }
            dataWifiSetModeChannel = a;
        }
        return dataWifiSetModeChannel;
    }

    public DataWifiSetModeChannel a(int i) {
        if (i < 0 || i > 1) {
            i = 1;
        }
        this.b = i;
        return this;
    }

    public DataWifiSetModeChannel b(int i) {
        this.c = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[5];
        this._sendData[0] = (byte) this.b;
        c.a(c.a(this.c), this._sendData, 1);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.WIFI.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.h.a();
        cVar.n = o.a.SetWifiModeChannel.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
