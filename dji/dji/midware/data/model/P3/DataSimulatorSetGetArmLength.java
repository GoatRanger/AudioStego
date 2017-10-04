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

public class DataSimulatorSetGetArmLength extends n implements e {
    private static DataSimulatorSetGetArmLength instance;
    private float mArmLength;
    private int mFlag;

    public static synchronized DataSimulatorSetGetArmLength getInstance() {
        DataSimulatorSetGetArmLength dataSimulatorSetGetArmLength;
        synchronized (DataSimulatorSetGetArmLength.class) {
            if (instance == null) {
                instance = new DataSimulatorSetGetArmLength();
            }
            dataSimulatorSetGetArmLength = instance;
        }
        return dataSimulatorSetGetArmLength;
    }

    public DataSimulatorSetGetArmLength setArmLength(float f) {
        this.mArmLength = f;
        return this;
    }

    public DataSimulatorSetGetArmLength setAckFlag(boolean z) {
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
        cVar.n = l.a.SetGetArmLength.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[5];
        System.arraycopy(dji.midware.util.c.b(this.mArmLength), 0, this._sendData, 0, 4);
        this._sendData[4] = (byte) this.mFlag;
    }
}
