package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataFlycGetIoc.MODE;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycSetIoc extends n implements e {
    private static DataFlycSetIoc a = null;
    private MODE b;

    public static synchronized DataFlycSetIoc getInstance() {
        DataFlycSetIoc dataFlycSetIoc;
        synchronized (DataFlycSetIoc.class) {
            if (a == null) {
                a = new DataFlycSetIoc();
            }
            dataFlycSetIoc = a;
        }
        return dataFlycSetIoc;
    }

    public DataFlycSetIoc a(MODE mode) {
        this.b = mode;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.b.value();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.n.a();
        start(cVar, dVar);
    }
}
