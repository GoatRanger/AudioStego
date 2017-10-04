package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.h;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataGimbalSaveUserParams extends n implements e {
    private static DataGimbalSaveUserParams a = null;

    public static synchronized DataGimbalSaveUserParams getInstance() {
        DataGimbalSaveUserParams dataGimbalSaveUserParams;
        synchronized (DataGimbalSaveUserParams.class) {
            if (a == null) {
                a = new DataGimbalSaveUserParams();
            }
            dataGimbalSaveUserParams = a;
        }
        return dataGimbalSaveUserParams;
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.e.a();
        cVar.n = h.a.SaveUserParams.a();
        start(cVar, dVar);
    }
}
