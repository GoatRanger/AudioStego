package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.manager.P3.n;
import dji.midware.e.b;
import dji.midware.util.c;

public class DataCameraScaleGesture extends n implements b {
    private static DataCameraScaleGesture instance = null;
    private int scale;

    public static synchronized DataCameraScaleGesture getInstance() {
        DataCameraScaleGesture dataCameraScaleGesture;
        synchronized (DataCameraScaleGesture.class) {
            if (instance == null) {
                instance = new DataCameraScaleGesture();
            }
            dataCameraScaleGesture = instance;
        }
        return dataCameraScaleGesture;
    }

    public DataCameraScaleGesture setScale(int i) {
        this.scale = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[2];
        c.b(c.b(this.scale), this._sendData);
    }

    public void start() {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.b.a();
        cVar.l = q.b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.aM.a();
        cVar.p = getSendData();
        start(cVar);
    }
}
