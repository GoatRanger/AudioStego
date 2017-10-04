package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataCameraSetZoomParams extends n implements e {
    private static DataCameraSetZoomParams a = null;
    private byte b = (byte) 9;
    private int c = 1;

    public static synchronized DataCameraSetZoomParams getInstance() {
        DataCameraSetZoomParams dataCameraSetZoomParams;
        synchronized (DataCameraSetZoomParams.class) {
            if (a == null) {
                a = new DataCameraSetZoomParams();
            }
            dataCameraSetZoomParams = a;
        }
        return dataCameraSetZoomParams;
    }

    public DataCameraSetZoomParams a(int i) {
        this.b = (byte) (this.b | (i & 1));
        return this;
    }

    public DataCameraSetZoomParams b(int i) {
        this.b = (byte) (this.b | ((i & 1) << 3));
        return this;
    }

    public DataCameraSetZoomParams c(int i) {
        this.c = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[5];
        this._sendData[0] = this.b;
        System.arraycopy(c.b(this.c), 0, this._sendData, 3, 2);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.Q.a();
        cVar.g = 0;
        start(cVar, dVar);
    }

    public void a() {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.Q.a();
        cVar.g = 0;
        super.start(cVar);
    }
}
