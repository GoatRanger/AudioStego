package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.model.b.a;
import dji.midware.e.d;

public class DataGimbalActiveStatus extends a {
    private static DataGimbalActiveStatus a = null;

    public static synchronized DataGimbalActiveStatus getInstance() {
        DataGimbalActiveStatus dataGimbalActiveStatus;
        synchronized (DataGimbalActiveStatus.class) {
            if (a == null) {
                a = new DataGimbalActiveStatus();
                a.setVersion(a.a.b);
            }
            dataGimbalActiveStatus = a;
        }
        return dataGimbalActiveStatus;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = q.a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.a.a();
        cVar.n = dji.midware.data.config.P3.d.a.s.a();
        start(cVar, dVar);
    }
}
