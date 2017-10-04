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

public class DataCameraSetVideoFormat extends n implements e {
    private static DataCameraSetVideoFormat a = null;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;

    public static synchronized DataCameraSetVideoFormat getInstance() {
        DataCameraSetVideoFormat dataCameraSetVideoFormat;
        synchronized (DataCameraSetVideoFormat.class) {
            if (a == null) {
                a = new DataCameraSetVideoFormat();
            }
            dataCameraSetVideoFormat = a;
        }
        return dataCameraSetVideoFormat;
    }

    public DataCameraSetVideoFormat a() {
        a(DataCameraGetPushShotParams.getInstance().getVideoFormat());
        b(DataCameraGetPushShotParams.getInstance().getVideoFps());
        c(DataCameraGetPushShotParams.getInstance().getVideoFov());
        d(DataCameraGetPushShotParams.getInstance().getVideoSecondOpen());
        e(DataCameraGetPushShotParams.getInstance().getVideoSecondRatio());
        return this;
    }

    public DataCameraSetVideoFormat a(int i) {
        this.b = i;
        return this;
    }

    public DataCameraSetVideoFormat b(int i) {
        this.c = i;
        return this;
    }

    public DataCameraSetVideoFormat c(int i) {
        this.d = i;
        return this;
    }

    public DataCameraSetVideoFormat d(int i) {
        this.e = i;
        return this;
    }

    public DataCameraSetVideoFormat e(int i) {
        this.f = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[5];
        this._sendData[0] = (byte) this.b;
        this._sendData[1] = (byte) this.c;
        this._sendData[2] = (byte) this.d;
        this._sendData[3] = (byte) this.e;
        this._sendData[4] = (byte) this.f;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.o.a();
        start(cVar, dVar);
    }
}
