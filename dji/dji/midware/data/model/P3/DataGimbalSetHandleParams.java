package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.h;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataGimbalSetHandleParams extends n implements e {
    int a = 255;
    int b = 255;
    int c = 255;
    int d = 255;
    int e = 255;
    int f = 255;
    int g = 255;
    int h = 255;

    public DataGimbalSetHandleParams a(int i) {
        this.a = i;
        return this;
    }

    public DataGimbalSetHandleParams b(int i) {
        this.b = i;
        return this;
    }

    public DataGimbalSetHandleParams c(int i) {
        this.c = i;
        return this;
    }

    public DataGimbalSetHandleParams d(int i) {
        this.d = i;
        return this;
    }

    public DataGimbalSetHandleParams e(int i) {
        this.e = i;
        return this;
    }

    public DataGimbalSetHandleParams f(int i) {
        this.f = i;
        return this;
    }

    public DataGimbalSetHandleParams g(int i) {
        this.g = i;
        return this;
    }

    public DataGimbalSetHandleParams h(int i) {
        this.h = i;
        return this;
    }

    private void a() {
        this.a = 255;
        this.b = 255;
        this.c = 255;
        this.d = 255;
        this.e = 255;
        this.f = 255;
    }

    protected void doPack() {
        this._sendData = new byte[8];
        this._sendData[0] = (byte) this.a;
        this._sendData[1] = (byte) this.b;
        this._sendData[2] = (byte) this.c;
        this._sendData[3] = (byte) this.d;
        this._sendData[4] = (byte) this.e;
        this._sendData[5] = (byte) this.f;
        this._sendData[6] = (byte) this.g;
        this._sendData[7] = (byte) this.h;
        a();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.e.a();
        cVar.n = h.a.SetHandleParams.a();
        start(cVar, dVar);
    }
}
