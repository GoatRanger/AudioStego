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

public class DataCameraSetVideoCaption extends n implements e {
    private static DataCameraSetVideoCaption b = null;
    private byte a = (byte) 0;

    public static synchronized DataCameraSetVideoCaption getInstance() {
        DataCameraSetVideoCaption dataCameraSetVideoCaption;
        synchronized (DataCameraSetVideoCaption.class) {
            if (b == null) {
                b = new DataCameraSetVideoCaption();
            }
            dataCameraSetVideoCaption = b;
        }
        return dataCameraSetVideoCaption;
    }

    public DataCameraSetVideoCaption a() {
        this.a = (byte) 0;
        return this;
    }

    public boolean b() {
        return (this.a & 128) != 0;
    }

    public DataCameraSetVideoCaption a(boolean z) {
        if (z) {
            this.a = (byte) (this.a | 128);
        } else {
            this.a = (byte) (this.a & -129);
        }
        return this;
    }

    public void b(boolean z) {
        if (z) {
            this.a = (byte) (this.a | 1);
        } else {
            this.a = (byte) (this.a & -2);
        }
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = this.a;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.as.a();
        cVar.p = getSendData();
        cVar.v = 2000;
        start(cVar, dVar);
    }
}
