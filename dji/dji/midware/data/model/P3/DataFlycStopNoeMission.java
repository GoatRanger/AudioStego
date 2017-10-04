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

public class DataFlycStopNoeMission extends n implements e {
    private static DataFlycStopNoeMission a;

    public static synchronized DataFlycStopNoeMission getInstance() {
        DataFlycStopNoeMission dataFlycStopNoeMission;
        synchronized (DataFlycStopNoeMission.class) {
            if (a == null) {
                a = new DataFlycStopNoeMission();
            }
            dataFlycStopNoeMission = a;
        }
        return dataFlycStopNoeMission;
    }

    public int a() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.aj.a();
        super.start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) 0;
    }
}
