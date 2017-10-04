package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataCameraSetPhotoMode extends n implements e {
    private static DataCameraSetPhotoMode a = null;
    private TYPE b;
    private int c;
    private int d;
    private int e;
    private int f;

    public static synchronized DataCameraSetPhotoMode getInstance() {
        DataCameraSetPhotoMode dataCameraSetPhotoMode;
        synchronized (DataCameraSetPhotoMode.class) {
            if (a == null) {
                a = new DataCameraSetPhotoMode();
            }
            dataCameraSetPhotoMode = a;
        }
        return dataCameraSetPhotoMode;
    }

    public DataCameraSetPhotoMode a(TYPE type) {
        this.b = type;
        return this;
    }

    public DataCameraSetPhotoMode a(int i) {
        this.c = i;
        return this;
    }

    public DataCameraSetPhotoMode b(int i) {
        this.d = i;
        return this;
    }

    public DataCameraSetPhotoMode c(int i) {
        this.e = i;
        return this;
    }

    public DataCameraSetPhotoMode d(int i) {
        this.f = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[6];
        this._sendData[0] = (byte) this.b.a();
        this._sendData[1] = (byte) this.c;
        this._sendData[2] = (byte) this.d;
        this._sendData[3] = (byte) this.e;
        System.arraycopy(c.b(this.f), 0, this._sendData, 4, 2);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.aB.a();
        cVar.w = 4;
        start(cVar, dVar);
    }
}
