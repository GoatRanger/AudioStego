package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.model.b.a;
import dji.midware.data.model.b.a.b;
import dji.midware.e.d;

public class DataFlycActiveStatus extends a {
    private static DataFlycActiveStatus instance = null;

    public static synchronized DataFlycActiveStatus getInstance() {
        DataFlycActiveStatus dataFlycActiveStatus;
        synchronized (DataFlycActiveStatus.class) {
            if (instance == null) {
                instance = new DataFlycActiveStatus();
            }
            dataFlycActiveStatus = instance;
        }
        return dataFlycActiveStatus;
    }

    protected void setPushRecPack(dji.midware.data.a.a.a aVar) {
        super.setPushRecPack(aVar);
    }

    public boolean isActive() {
        int intValue = ((Integer) get(0, 1, Integer.class)).intValue();
        if (intValue == b.PUSH.a(a.a.Ver1_0) || intValue == b.PUSH.a(a.a.Ver1_1)) {
            return true;
        }
        return false;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = q.a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = q.b.NO.a();
        cVar.m = p.COMMON.a();
        cVar.n = dji.midware.data.config.P3.d.a.ActiveStatus.a();
        cVar.v = 1000;
        cVar.w = 3;
        start(cVar, dVar);
    }
}
