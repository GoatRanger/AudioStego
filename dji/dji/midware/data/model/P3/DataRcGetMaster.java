package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataRcGetMaster extends n implements e {
    private static DataRcGetMaster instance = null;

    public static synchronized DataRcGetMaster getInstance() {
        DataRcGetMaster dataRcGetMaster;
        synchronized (DataRcGetMaster.class) {
            if (instance == null) {
                instance = new DataRcGetMaster();
            }
            dataRcGetMaster = instance;
        }
        return dataRcGetMaster;
    }

    public MODE getMode() {
        return MODE.find((((Integer) get(0, 1, Integer.class)).intValue() >> 6) & 3);
    }

    public boolean isConnected() {
        return ((((Integer) get(0, 1, Integer.class)).intValue() >> 5) & 1) == 1;
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.RC.a();
        cVar.n = k.a.g.b();
        if (i.getInstance().c() == ProductType.litchiC) {
            this._recData = new byte[]{(byte) 32};
            dVar.onSuccess(this);
            return;
        }
        start(cVar, dVar);
    }
}
