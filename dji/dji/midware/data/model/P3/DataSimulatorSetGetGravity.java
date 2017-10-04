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

public class DataSimulatorSetGetGravity extends n implements e {
    private static DataSimulatorSetGetGravity instance;
    private int mFlag;
    private float mGravity;

    public static synchronized DataSimulatorSetGetGravity getInstance() {
        DataSimulatorSetGetGravity dataSimulatorSetGetGravity;
        synchronized (DataSimulatorSetGetGravity.class) {
            if (instance == null) {
                instance = new DataSimulatorSetGetGravity();
            }
            dataSimulatorSetGetGravity = instance;
        }
        return dataSimulatorSetGetGravity;
    }

    public DataSimulatorSetGetGravity setGravity(float f) {
        this.mGravity = f;
        return this;
    }

    public DataSimulatorSetGetGravity setAckFlag(boolean z) {
        if (z) {
            this.mFlag |= 1;
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
        cVar.n = l.a.SetGetGravity.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[5];
        System.arraycopy(dji.midware.util.c.b(this.mGravity), 0, this._sendData, 0, 4);
        this._sendData[4] = (byte) this.mFlag;
    }
}
