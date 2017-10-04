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

public class DataFlycSetTimeZone extends n implements e {
    private static DataFlycSetTimeZone b = null;
    public int a = -4;

    public static synchronized DataFlycSetTimeZone getInstance() {
        DataFlycSetTimeZone dataFlycSetTimeZone;
        synchronized (DataFlycSetTimeZone.class) {
            if (b == null) {
                b = new DataFlycSetTimeZone();
            }
            dataFlycSetTimeZone = b;
        }
        return dataFlycSetTimeZone;
    }

    public DataFlycSetTimeZone a(int i) {
        this.a = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[2];
        System.arraycopy(c.b((short) this.a), 0, this._sendData, 0, 2);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.FLYC.a();
        cVar.n = g.a.SetTimeZone.a();
        start(cVar, dVar);
    }
}
