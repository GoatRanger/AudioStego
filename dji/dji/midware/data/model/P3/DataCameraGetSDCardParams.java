package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.e.d;
import dji.midware.e.e;

class DataCameraGetSDCardParams extends n implements e {
    private static DataCameraGetSDCardParams instance = null;

    DataCameraGetSDCardParams() {
    }

    public static synchronized DataCameraGetSDCardParams getInstance() {
        DataCameraGetSDCardParams dataCameraGetSDCardParams;
        synchronized (DataCameraGetSDCardParams.class) {
            if (instance == null) {
                instance = new DataCameraGetSDCardParams();
            }
            dataCameraGetSDCardParams = instance;
        }
        return dataCameraGetSDCardParams;
    }

    public boolean getSDCardInsertState() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 1) == 1;
    }

    public SDCardState getSDCardState() {
        return SDCardState.find(((Integer) get(0, 1, Integer.class)).intValue() & 30);
    }

    public int getSDCardTotalSize() {
        return ((Integer) get(1, 4, Integer.class)).intValue();
    }

    public int getSDCardFreeSize() {
        return ((Integer) get(5, 4, Integer.class)).intValue();
    }

    public int getRemainedShots() {
        return ((Integer) get(9, 4, Integer.class)).intValue();
    }

    public int getRemainedTime() {
        return ((Integer) get(13, 4, Integer.class)).intValue();
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
        cVar.n = dji.midware.data.config.P3.b.a.aE.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
