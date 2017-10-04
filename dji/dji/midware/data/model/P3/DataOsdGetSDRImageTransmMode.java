package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.i;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataOsdSetSDRImageTransmMode.SDRImageTransmMode;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataOsdGetSDRImageTransmMode extends n implements e {
    private static DataOsdGetSDRImageTransmMode instance = null;

    public static synchronized DataOsdGetSDRImageTransmMode getInstance() {
        DataOsdGetSDRImageTransmMode dataOsdGetSDRImageTransmMode;
        synchronized (DataOsdGetSDRImageTransmMode.class) {
            if (instance == null) {
                instance = new DataOsdGetSDRImageTransmMode();
            }
            dataOsdGetSDRImageTransmMode = instance;
        }
        return dataOsdGetSDRImageTransmMode;
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OFDM.value();
        cVar.g = 0;
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.j.a();
        cVar.n = i.a.GetSDRImageTransmissionMode.a();
        start(cVar, dVar);
    }

    public SDRImageTransmMode getMode() {
        return SDRImageTransmMode.find(((Integer) get(0, 1, Integer.class)).intValue());
    }
}
