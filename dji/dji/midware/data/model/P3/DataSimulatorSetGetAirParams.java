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

public class DataSimulatorSetGetAirParams extends n implements e {
    private static DataSimulatorSetGetAirParams instance;
    private float mDensity;
    private int mFlag;
    private float mResistanceCoef;

    public static synchronized DataSimulatorSetGetAirParams getInstance() {
        DataSimulatorSetGetAirParams dataSimulatorSetGetAirParams;
        synchronized (DataSimulatorSetGetAirParams.class) {
            if (instance == null) {
                instance = new DataSimulatorSetGetAirParams();
            }
            dataSimulatorSetGetAirParams = instance;
        }
        return dataSimulatorSetGetAirParams;
    }

    public DataSimulatorSetGetAirParams setDensity(int i) {
        this.mDensity = (float) i;
        return this;
    }

    public DataSimulatorSetGetAirParams setResistanceCoef(int i) {
        this.mResistanceCoef = (float) i;
        return this;
    }

    public DataSimulatorSetGetAirParams setAckFlag(boolean z) {
        if (z) {
            this.mFlag |= 1;
        } else {
            this.mFlag |= 0;
        }
        return this;
    }

    public DataSimulatorSetGetAirParams setInitFlag(boolean z) {
        if (z) {
            this.mFlag |= 2;
        } else {
            this.mFlag |= 0;
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
        cVar.n = l.a.SetGetAirParams.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[9];
        System.arraycopy(dji.midware.util.c.b(this.mDensity), 0, this._sendData, 0, 4);
        System.arraycopy(dji.midware.util.c.b(this.mResistanceCoef), 0, this._sendData, 4, 4);
        this._sendData[8] = (byte) this.mFlag;
    }
}
