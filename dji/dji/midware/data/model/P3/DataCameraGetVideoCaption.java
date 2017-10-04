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

public class DataCameraGetVideoCaption extends n implements e {
    private static DataCameraGetVideoCaption mInstance = null;

    public static synchronized DataCameraGetVideoCaption getInstance() {
        DataCameraGetVideoCaption dataCameraGetVideoCaption;
        synchronized (DataCameraGetVideoCaption.class) {
            if (mInstance == null) {
                mInstance = new DataCameraGetVideoCaption();
            }
            dataCameraGetVideoCaption = mInstance;
        }
        return dataCameraGetVideoCaption;
    }

    public boolean isGenerateVideoCaptionEnable() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 128) != 0;
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
        cVar.n = dji.midware.data.config.P3.b.a.at.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
