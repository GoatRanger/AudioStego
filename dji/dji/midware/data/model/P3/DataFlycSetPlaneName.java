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

public class DataFlycSetPlaneName extends n implements e {
    private static DataFlycSetPlaneName a = null;
    private String b;

    public static synchronized DataFlycSetPlaneName getInstance() {
        DataFlycSetPlaneName dataFlycSetPlaneName;
        synchronized (DataFlycSetPlaneName.class) {
            if (a == null) {
                a = new DataFlycSetPlaneName();
            }
            dataFlycSetPlaneName = a;
        }
        return dataFlycSetPlaneName;
    }

    public DataFlycSetPlaneName a(String str) {
        this.b = str;
        return this;
    }

    protected void doPack() {
        int i = 32;
        this._sendData = new byte[32];
        Object b = c.b(this.b);
        Object obj = this._sendData;
        if (b.length < 32) {
            i = b.length;
        }
        System.arraycopy(b, 0, obj, 0, i);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.w.a();
        cVar.v = 1000;
        start(cVar, dVar);
    }
}
