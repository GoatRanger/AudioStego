package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.m;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataSmartBatteryShutDown extends n implements e {
    private static DataSmartBatteryShutDown a = null;
    private int b = 0;

    public static DataSmartBatteryShutDown getInstance() {
        if (a == null) {
            a = new DataSmartBatteryShutDown();
        }
        return a;
    }

    public DataSmartBatteryShutDown a(int i) {
        this.b = i;
        return this;
    }

    public int a() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int b() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.BATTERY.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.n.a();
        cVar.n = m.a.ShutDown.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[9];
        this._sendData[0] = (byte) this.b;
    }
}
