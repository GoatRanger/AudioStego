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

public class DataSimulatorForceMoment extends n implements e {
    private static DataSimulatorForceMoment a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;

    public static synchronized DataSimulatorForceMoment getInstance() {
        DataSimulatorForceMoment dataSimulatorForceMoment;
        synchronized (DataSimulatorForceMoment.class) {
            if (a == null) {
                a = new DataSimulatorForceMoment();
            }
            dataSimulatorForceMoment = a;
        }
        return dataSimulatorForceMoment;
    }

    public DataSimulatorForceMoment a(int i) {
        this.b = i;
        return this;
    }

    public DataSimulatorForceMoment b(int i) {
        this.c = i;
        return this;
    }

    public DataSimulatorForceMoment c(int i) {
        this.d = i;
        return this;
    }

    public DataSimulatorForceMoment d(int i) {
        this.e = i;
        return this;
    }

    public DataSimulatorForceMoment e(int i) {
        this.f = i;
        return this;
    }

    public DataSimulatorForceMoment f(int i) {
        this.g = i;
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
        cVar.n = l.a.ForceMoment.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[12];
        System.arraycopy(dji.midware.util.c.b(this.b), 0, this._sendData, 0, 2);
        System.arraycopy(dji.midware.util.c.b(this.c), 0, this._sendData, 2, 2);
        System.arraycopy(dji.midware.util.c.b(this.d), 0, this._sendData, 4, 2);
        System.arraycopy(dji.midware.util.c.b(this.e), 0, this._sendData, 6, 2);
        System.arraycopy(dji.midware.util.c.b(this.f), 0, this._sendData, 8, 2);
        System.arraycopy(dji.midware.util.c.b(this.g), 0, this._sendData, 10, 2);
    }
}
