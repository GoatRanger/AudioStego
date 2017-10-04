package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.b.a;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataBaseCameraSetting extends n implements e {
    protected p a = p.CAMERA;
    protected int b;
    protected String c;
    protected a d;
    protected int e = 1000;
    protected int f = 2;

    public DataBaseCameraSetting a(String str) {
        this.c = str;
        return this;
    }

    public DataBaseCameraSetting a(a aVar) {
        this.d = aVar;
        return this;
    }

    public DataBaseCameraSetting a(int i) {
        this.b = i;
        return this;
    }

    public void a(int i, int i2) {
        if (i > 0) {
            this.e = i;
        }
        if (i2 > 0 && i2 <= 3) {
            this.f = i2;
        }
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.b;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = q.a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = this.a.a();
        if (this.d != null) {
            cVar.n = this.d.a();
        } else {
            cVar.n = a.valueOf("Set" + this.c).a();
        }
        cVar.v = this.e;
        cVar.w = this.f;
        start(cVar, dVar);
    }
}
