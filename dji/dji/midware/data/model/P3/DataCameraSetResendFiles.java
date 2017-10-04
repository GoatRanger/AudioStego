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

public class DataCameraSetResendFiles extends n implements e {
    private static DataCameraSetResendFiles a = null;
    private int b;

    public static synchronized DataCameraSetResendFiles getInstance() {
        DataCameraSetResendFiles dataCameraSetResendFiles;
        synchronized (DataCameraSetResendFiles.class) {
            if (a == null) {
                a = new DataCameraSetResendFiles();
            }
            dataCameraSetResendFiles = a;
        }
        return dataCameraSetResendFiles;
    }

    public DataCameraSetResendFiles a(int i) {
        this.b = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[4];
        c.b(c.a(this.b), this._sendData);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.a.a();
        cVar.n = dji.midware.data.config.P3.d.a.o.a();
        cVar.v = 1;
        cVar.w = 0;
        start(cVar);
    }
}
