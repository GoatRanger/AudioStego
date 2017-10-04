package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataRcGetRcUnitNLang extends n implements e {
    private static DataRcGetRcUnitNLang instance = null;

    public static synchronized DataRcGetRcUnitNLang getInstance() {
        DataRcGetRcUnitNLang dataRcGetRcUnitNLang;
        synchronized (DataRcGetRcUnitNLang.class) {
            if (instance == null) {
                instance = new DataRcGetRcUnitNLang();
            }
            dataRcGetRcUnitNLang = instance;
        }
        return dataRcGetRcUnitNLang;
    }

    public int getUnit() {
        if ((((((Integer) get(0, 1, Integer.class)).intValue() & 192) >> 6) & 1) != 0) {
            return 0;
        }
        return 1;
    }

    public int getLang() {
        int intValue = ((Integer) get(0, 1, Integer.class)).intValue() & 63;
        for (int i = 0; i < 6; i++) {
            if ((intValue & 1) != 0) {
                return i;
            }
            intValue >>= 1;
        }
        return 0;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.GetRcUnitNLang.b();
        start(cVar, dVar);
    }

    protected void doPack() {
    }
}
