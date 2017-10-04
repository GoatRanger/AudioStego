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

public class DataSimulatorMomentum extends n implements e {
    private static DataSimulatorMomentum a;
    private int b;
    private int c;
    private int d;
    private int e;

    public static synchronized DataSimulatorMomentum getInstance() {
        DataSimulatorMomentum dataSimulatorMomentum;
        synchronized (DataSimulatorMomentum.class) {
            if (a == null) {
                a = new DataSimulatorMomentum();
            }
            dataSimulatorMomentum = a;
        }
        return dataSimulatorMomentum;
    }

    public DataSimulatorMomentum a(int i) {
        this.b = i;
        return this;
    }

    public DataSimulatorMomentum b(int i) {
        this.c = i;
        return this;
    }

    public DataSimulatorMomentum c(int i) {
        this.d = i;
        return this;
    }

    public DataSimulatorMomentum d(int i) {
        this.e = i;
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
        cVar.n = l.a.Momentum.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[8];
        System.arraycopy(dji.midware.util.c.b(this.b), 0, this._sendData, 0, 4);
        System.arraycopy(dji.midware.util.c.b(this.c), 0, this._sendData, 4, 4);
        System.arraycopy(dji.midware.util.c.b(this.d), 0, this._sendData, 8, 4);
        System.arraycopy(dji.midware.util.c.b(this.e), 0, this._sendData, 12, 4);
    }
}
