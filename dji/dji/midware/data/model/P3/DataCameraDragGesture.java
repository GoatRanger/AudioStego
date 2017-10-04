package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.manager.P3.n;
import dji.midware.e.b;
import dji.midware.util.c;

public class DataCameraDragGesture extends n implements b {
    private static DataCameraDragGesture instance = null;
    private int x = 0;
    private int y = 0;

    public static synchronized DataCameraDragGesture getInstance() {
        DataCameraDragGesture dataCameraDragGesture;
        synchronized (DataCameraDragGesture.class) {
            if (instance == null) {
                instance = new DataCameraDragGesture();
            }
            dataCameraDragGesture = instance;
        }
        return dataCameraDragGesture;
    }

    public DataCameraDragGesture setX(int i) {
        this.x = i;
        return this;
    }

    public DataCameraDragGesture setY(int i) {
        this.y = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[8];
        c.b(c.a(this.x), this._sendData);
        System.arraycopy(c.a(this.y), 0, this._sendData, 4, 4);
    }

    public void start() {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.b.a();
        cVar.l = q.b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.aN.a();
        cVar.p = getSendData();
        start(cVar);
    }
}
