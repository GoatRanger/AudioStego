package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCameraGetIso.TYPE;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraSetIso extends n implements e {
    private static DataCameraSetIso a = null;
    private int b;
    private int c;
    private int d;

    public static synchronized DataCameraSetIso getInstance() {
        DataCameraSetIso dataCameraSetIso;
        synchronized (DataCameraSetIso.class) {
            if (a == null) {
                a = new DataCameraSetIso();
            }
            dataCameraSetIso = a;
        }
        return dataCameraSetIso;
    }

    public DataCameraSetIso a(boolean z) {
        this.b = z ? 0 : 1;
        return this;
    }

    public DataCameraSetIso a(TYPE type) {
        this.c = type.value();
        return this;
    }

    public DataCameraSetIso b(boolean z) {
        this.d = z ? 1 : 0;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        if (this.b == 0) {
            this._sendData[0] = (byte) ((this.b << 7) | this.c);
        } else {
            this._sendData[0] = (byte) ((this.b << 7) | this.d);
        }
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.G.a();
        start(cVar, dVar);
    }
}
