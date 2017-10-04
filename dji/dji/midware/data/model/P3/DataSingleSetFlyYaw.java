package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.f;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataSingleSetFlyYaw extends n implements e {
    private short a = (short) 0;
    private short b = (short) 0;
    private short c = (short) 0;
    private short d = (short) 0;
    private short e = (short) 0;

    public DataSingleSetFlyYaw a(short s) {
        this.a = s;
        return this;
    }

    public DataSingleSetFlyYaw b(short s) {
        this.d = s;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[10];
        System.arraycopy(c.b(this.a), 0, this._sendData, 0, 2);
        System.arraycopy(c.b(this.b), 0, this._sendData, 2, 2);
        System.arraycopy(c.b(this.c), 0, this._sendData, 4, 2);
        System.arraycopy(c.b(this.d), 0, this._sendData, 6, 2);
        System.arraycopy(c.b(this.e), 0, this._sendData, 8, 2);
    }

    public void a() {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.SINGLE.value();
        cVar.g = 7;
        cVar.j = a.a.a();
        cVar.k = q.c.b.a();
        cVar.l = b.a.a();
        cVar.m = p.k.a();
        cVar.n = f.a.SetFlyYaw.a();
        start(cVar);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.SINGLE.value();
        cVar.g = 7;
        cVar.j = a.a.a();
        cVar.k = q.c.b.a();
        cVar.l = b.a.a();
        cVar.m = p.k.a();
        cVar.n = f.a.SetFlyYaw.a();
        cVar.v = 1000;
        cVar.w = 1;
        start(cVar, dVar);
    }
}
