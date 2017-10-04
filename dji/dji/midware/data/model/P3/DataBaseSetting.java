package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataBaseSetting extends n implements e {
    protected p a;
    protected int b;
    protected int c;
    protected DeviceType d;
    protected byte[] e = null;
    protected int f = 1000;
    protected int g = 2;

    public DataBaseSetting a(p pVar) {
        this.a = pVar;
        return this;
    }

    public DataBaseSetting a(int i) {
        this.c = i;
        return this;
    }

    public DataBaseSetting b(int i) {
        this.b = i;
        return this;
    }

    public DataBaseSetting a(DeviceType deviceType) {
        this.d = deviceType;
        return this;
    }

    public DataBaseSetting a(int i, int i2) {
        if (i > 0) {
            this.f = i;
        }
        if (i2 > 0 && i2 <= 3) {
            this.g = i2;
        }
        return this;
    }

    public DataBaseSetting a(byte[] bArr) {
        this.e = bArr;
        return this;
    }

    protected void doPack() {
        if (this.e == null) {
            this._sendData = new byte[1];
            this._sendData[0] = (byte) this.b;
            return;
        }
        this._sendData = this.e;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = this.d.value();
        cVar.j = a.b.a();
        cVar.k = q.c.b.a();
        cVar.l = b.a.a();
        cVar.m = this.a.a();
        cVar.n = this.c;
        cVar.v = 200;
        cVar.w = 1;
        start(cVar, dVar);
    }
}
