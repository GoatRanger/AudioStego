package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.h;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataGimbalSetAngle extends n implements e {
    private static DataGimbalSetAngle g = null;
    int a;
    int b;
    int c;
    int d = 10;
    int e = 0;
    int f = 2;

    public static synchronized DataGimbalSetAngle getInstance() {
        DataGimbalSetAngle dataGimbalSetAngle;
        synchronized (DataGimbalSetAngle.class) {
            if (g == null) {
                g = new DataGimbalSetAngle();
            }
            dataGimbalSetAngle = g;
        }
        return dataGimbalSetAngle;
    }

    public DataGimbalSetAngle a(int i) {
        this.a = i;
        return this;
    }

    public DataGimbalSetAngle b(int i) {
        this.b = i;
        return this;
    }

    public DataGimbalSetAngle c(int i) {
        this.c = i;
        return this;
    }

    public DataGimbalSetAngle d(int i) {
        this.d = i;
        return this;
    }

    public DataGimbalSetAngle e(int i) {
        this.f = i;
        return this;
    }

    public DataGimbalSetAngle f(int i) {
        this.e = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[10];
        System.arraycopy(c.b(this.a), 0, this._sendData, 0, 2);
        System.arraycopy(c.b(this.c), 0, this._sendData, 2, 2);
        System.arraycopy(c.b(this.b), 0, this._sendData, 4, 2);
        System.arraycopy(c.b(this.d), 0, this._sendData, 6, 2);
        System.arraycopy(c.b((this.e << 5) | 192), 0, this._sendData, 8, 1);
        System.arraycopy(c.b(this.f), 0, this._sendData, 9, 1);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.e.a();
        cVar.n = h.a.SetAngle.a();
        start(cVar, dVar);
    }

    public void a(int i, int i2, d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.e.a();
        cVar.n = h.a.SetAngle.a();
        cVar.v = i;
        cVar.w = i2;
        start(cVar, dVar);
    }
}
