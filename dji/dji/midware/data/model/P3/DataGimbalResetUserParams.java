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

public class DataGimbalResetUserParams extends n implements e {
    private static DataGimbalResetUserParams a = null;

    public static synchronized DataGimbalResetUserParams getInstance() {
        DataGimbalResetUserParams dataGimbalResetUserParams;
        synchronized (DataGimbalResetUserParams.class) {
            if (a == null) {
                a = new DataGimbalResetUserParams();
            }
            dataGimbalResetUserParams = a;
        }
        return dataGimbalResetUserParams;
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
        cVar.n = h.a.ResetUserParams.a();
        start(cVar, dVar);
    }
}
