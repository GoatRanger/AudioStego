package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.l;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataSimulatorGetFrequency extends n implements e {
    private static DataSimulatorGetFrequency instance;

    public static synchronized DataSimulatorGetFrequency getInstance() {
        DataSimulatorGetFrequency dataSimulatorGetFrequency;
        synchronized (DataSimulatorGetFrequency.class) {
            if (instance == null) {
                instance = new DataSimulatorGetFrequency();
            }
            dataSimulatorGetFrequency = instance;
        }
        return dataSimulatorGetFrequency;
    }

    public int getFrequency() {
        return ((Integer) get(0, 2, Integer.class)).intValue();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.l.a();
        cVar.n = l.a.GetFrequency.a();
        start(cVar, dVar);
    }

    protected void doPack() {
    }
}
