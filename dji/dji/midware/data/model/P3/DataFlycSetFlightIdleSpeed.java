package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataFlycSetFlightIdleSpeed extends n implements e {
    private static DataFlycSetFlightIdleSpeed a = null;
    private float b;

    public static synchronized DataFlycSetFlightIdleSpeed getInstance() {
        DataFlycSetFlightIdleSpeed dataFlycSetFlightIdleSpeed;
        synchronized (DataFlycSetFlightIdleSpeed.class) {
            if (a == null) {
                a = new DataFlycSetFlightIdleSpeed();
            }
            dataFlycSetFlightIdleSpeed = a;
        }
        return dataFlycSetFlightIdleSpeed;
    }

    public int a() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public DataFlycSetFlightIdleSpeed a(float f) {
        this.b = f;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[4];
        System.arraycopy(c.a(this.b), 0, this._sendData, 0, 4);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.aD.a();
        start(cVar, dVar);
    }
}
