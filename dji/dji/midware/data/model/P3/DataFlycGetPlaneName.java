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

public class DataFlycGetPlaneName extends n implements e {
    private static DataFlycGetPlaneName instance = null;

    public static synchronized DataFlycGetPlaneName getInstance() {
        DataFlycGetPlaneName dataFlycGetPlaneName;
        synchronized (DataFlycGetPlaneName.class) {
            if (instance == null) {
                instance = new DataFlycGetPlaneName();
            }
            dataFlycGetPlaneName = instance;
        }
        return dataFlycGetPlaneName;
    }

    public String getName() {
        int i = 32;
        if (this._recData.length <= 32) {
            i = this._recData.length;
        }
        return getUTF8(0, i);
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
        cVar.n = g.a.v.a();
        start(cVar, dVar);
    }
}
