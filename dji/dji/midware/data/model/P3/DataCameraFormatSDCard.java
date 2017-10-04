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

public class DataCameraFormatSDCard extends n implements e {
    private static DataCameraFormatSDCard a = null;

    public static synchronized DataCameraFormatSDCard getInstance() {
        DataCameraFormatSDCard dataCameraFormatSDCard;
        synchronized (DataCameraFormatSDCard.class) {
            if (a == null) {
                a = new DataCameraFormatSDCard();
            }
            dataCameraFormatSDCard = a;
        }
        return dataCameraFormatSDCard;
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
        cVar.n = dji.midware.data.config.P3.b.a.aF.a();
        cVar.v = 20000;
        start(cVar, dVar);
    }
}
