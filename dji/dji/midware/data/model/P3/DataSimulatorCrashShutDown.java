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

public class DataSimulatorCrashShutDown extends n implements e {
    private static DataSimulatorCrashShutDown a;
    private int b;

    public static synchronized DataSimulatorCrashShutDown getInstance() {
        DataSimulatorCrashShutDown dataSimulatorCrashShutDown;
        synchronized (DataSimulatorCrashShutDown.class) {
            if (a == null) {
                a = new DataSimulatorCrashShutDown();
            }
            dataSimulatorCrashShutDown = a;
        }
        return dataSimulatorCrashShutDown;
    }

    public DataSimulatorCrashShutDown a(boolean z) {
        if (z) {
            this.b = 1;
        } else {
            this.b = 0;
        }
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.l.a();
        cVar.n = l.a.CrashShutDown.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.b;
    }
}
