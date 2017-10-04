package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.model.a.a;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataCameraGetShotInfo extends a implements e {
    private static DataCameraGetShotInfo instance = null;

    public static synchronized DataCameraGetShotInfo getInstance() {
        DataCameraGetShotInfo dataCameraGetShotInfo;
        synchronized (DataCameraGetShotInfo.class) {
            if (instance == null) {
                instance = new DataCameraGetShotInfo();
            }
            dataCameraGetShotInfo = instance;
        }
        return dataCameraGetShotInfo;
    }

    public int getMemberId() {
        return ((Integer) get(0, 2, Integer.class)).intValue();
    }

    public int getModelId() {
        return ((Integer) get(2, 2, Integer.class)).intValue();
    }

    public int getHardVersion() {
        return ((Integer) get(4, 2, Integer.class)).intValue();
    }

    public String getName() {
        return c.c(this._recData, 6, 31);
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = q.a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.br.a();
        start(cVar, dVar);
    }
}
