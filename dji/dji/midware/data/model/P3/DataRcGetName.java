package dji.midware.data.model.P3;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataRcGetName extends n implements e {
    private static DataRcGetName instance = null;

    public static synchronized DataRcGetName getInstance() {
        DataRcGetName dataRcGetName;
        synchronized (DataRcGetName.class) {
            if (instance == null) {
                instance = new DataRcGetName();
            }
            dataRcGetName = instance;
        }
        return dataRcGetName;
    }

    public String getName() {
        DJILogHelper.getInstance().LOGD("", "getrcname=" + c.i(this._recData), false, true);
        return c.g(this._recData);
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.GetName.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
