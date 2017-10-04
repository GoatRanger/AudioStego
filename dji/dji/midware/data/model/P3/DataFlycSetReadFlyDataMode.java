package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycSetReadFlyDataMode extends n implements e {
    private static DataFlycSetReadFlyDataMode a = null;

    public static synchronized DataFlycSetReadFlyDataMode getInstance() {
        DataFlycSetReadFlyDataMode dataFlycSetReadFlyDataMode;
        synchronized (DataFlycSetReadFlyDataMode.class) {
            if (a == null) {
                a = new DataFlycSetReadFlyDataMode();
            }
            dataFlycSetReadFlyDataMode = a;
        }
        return dataFlycSetReadFlyDataMode;
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.x.a();
        start(cVar, dVar);
    }
}
