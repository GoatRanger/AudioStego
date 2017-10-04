package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraGetPhotoMode extends n implements e {
    private static DataCameraGetPhotoMode instance = null;

    public static synchronized DataCameraGetPhotoMode getInstance() {
        DataCameraGetPhotoMode dataCameraGetPhotoMode;
        synchronized (DataCameraGetPhotoMode.class) {
            if (instance == null) {
                instance = new DataCameraGetPhotoMode();
            }
            dataCameraGetPhotoMode = instance;
        }
        return dataCameraGetPhotoMode;
    }

    public TYPE getType() {
        return TYPE.find(this._recData[0]);
    }

    public int getContinueNum() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int getTimeType() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    public int getTimeNum() {
        return ((Integer) get(3, 1, Integer.class)).intValue();
    }

    public int getTimeInterval() {
        return ((Integer) get(4, 2, Integer.class)).intValue();
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.aC.a();
        start(cVar, dVar);
    }
}
