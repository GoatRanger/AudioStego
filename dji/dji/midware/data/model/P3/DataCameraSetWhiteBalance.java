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

public class DataCameraSetWhiteBalance extends n implements e {
    private static DataCameraSetWhiteBalance c = null;
    protected int a = 1000;
    protected int b = 2;
    private int d;
    private int e;

    public static synchronized DataCameraSetWhiteBalance getInstance() {
        DataCameraSetWhiteBalance dataCameraSetWhiteBalance;
        synchronized (DataCameraSetWhiteBalance.class) {
            if (c == null) {
                c = new DataCameraSetWhiteBalance();
            }
            dataCameraSetWhiteBalance = c;
        }
        return dataCameraSetWhiteBalance;
    }

    public DataCameraSetWhiteBalance a() {
        a(DataCameraGetPushShotParams.getInstance().getWhiteBalance());
        b(DataCameraGetPushShotParams.getInstance().getColorTemp());
        return this;
    }

    public DataCameraSetWhiteBalance a(int i) {
        this.d = i;
        return this;
    }

    public DataCameraSetWhiteBalance b(int i) {
        this.e = i;
        return this;
    }

    public void a(int i, int i2) {
        if (i > 0) {
            this.a = i;
        }
        if (i2 > 0 && i2 <= 3) {
            this.b = i2;
        }
    }

    protected void doPack() {
        this._sendData = new byte[2];
        this._sendData[0] = (byte) this.d;
        this._sendData[1] = (byte) this.e;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.I.a();
        cVar.v = this.a;
        cVar.w = this.b;
        start(cVar, dVar);
    }
}
