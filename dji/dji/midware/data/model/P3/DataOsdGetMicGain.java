package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.i;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataOsdGetMicGain extends n implements e {
    private static DataOsdGetMicGain instance = null;

    public static synchronized DataOsdGetMicGain getInstance() {
        DataOsdGetMicGain dataOsdGetMicGain;
        synchronized (DataOsdGetMicGain.class) {
            if (instance == null) {
                instance = new DataOsdGetMicGain();
            }
            dataOsdGetMicGain = instance;
        }
        return dataOsdGetMicGain;
    }

    public int getMicGain() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OFDM.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.j.a();
        cVar.n = i.a.GetMicGain.a();
        start(cVar, dVar);
    }
}
