package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCameraGetImageSize.RatioType;
import dji.midware.data.model.P3.DataCameraGetImageSize.SizeType;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraSetImageSize extends n implements e {
    private static DataCameraSetImageSize a = null;
    private SizeType b;
    private RatioType c;

    public static synchronized DataCameraSetImageSize getInstance() {
        DataCameraSetImageSize dataCameraSetImageSize;
        synchronized (DataCameraSetImageSize.class) {
            if (a == null) {
                a = new DataCameraSetImageSize();
            }
            dataCameraSetImageSize = a;
        }
        return dataCameraSetImageSize;
    }

    public DataCameraSetImageSize a() {
        a(DataCameraGetPushShotParams.getInstance().getImageSize());
        a(DataCameraGetPushShotParams.getInstance().getImageRatio());
        return this;
    }

    public DataCameraSetImageSize a(SizeType sizeType) {
        this.b = sizeType;
        return this;
    }

    public DataCameraSetImageSize a(RatioType ratioType) {
        this.c = ratioType;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[2];
        this._sendData[0] = (byte) this.b.value();
        this._sendData[1] = (byte) this.c.value();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.i.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
