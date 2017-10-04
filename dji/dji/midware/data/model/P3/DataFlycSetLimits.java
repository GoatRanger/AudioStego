package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataFlycGetLimits.MODE;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataFlycSetLimits extends n implements e {
    private static DataFlycSetLimits a = null;
    private MODE b;
    private int c;

    public static synchronized DataFlycSetLimits getInstance() {
        DataFlycSetLimits dataFlycSetLimits;
        synchronized (DataFlycSetLimits.class) {
            if (a == null) {
                a = new DataFlycSetLimits();
            }
            dataFlycSetLimits = a;
        }
        return dataFlycSetLimits;
    }

    public DataFlycSetLimits a(MODE mode) {
        this.b = mode;
        return this;
    }

    public DataFlycSetLimits a(int i) {
        this.c = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[3];
        this._sendData[0] = (byte) this.b.value();
        System.arraycopy(c.b(this.c), 0, this._sendData, 1, 2);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.p.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
