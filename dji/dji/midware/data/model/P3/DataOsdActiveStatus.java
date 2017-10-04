package dji.midware.data.model.P3;

import dji.logic.c.b;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.b.a;
import dji.midware.e.d;

public class DataOsdActiveStatus extends a {
    private static DataOsdActiveStatus instance = null;

    public static synchronized DataOsdActiveStatus getInstance() {
        DataOsdActiveStatus dataOsdActiveStatus;
        synchronized (DataOsdActiveStatus.class) {
            if (instance == null) {
                instance = new DataOsdActiveStatus();
            }
            dataOsdActiveStatus = instance;
        }
        return dataOsdActiveStatus;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        ProductType c = i.getInstance().c();
        if (dji.logic.f.d.a(c) || b.getInstance().a(c)) {
            cVar.h = DeviceType.OFDM.value();
        } else {
            cVar.h = DeviceType.OSD.value();
        }
        cVar.j = q.a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = q.b.NO.a();
        cVar.m = p.COMMON.a();
        cVar.n = dji.midware.data.config.P3.d.a.ActiveStatus.a();
        start(cVar, dVar);
    }
}
