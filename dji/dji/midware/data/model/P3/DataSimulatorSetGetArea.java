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

public class DataSimulatorSetGetArea extends n implements e {
    private static DataSimulatorSetGetArea instance;
    private float mAreaX;
    private float mAreaY;
    private float mAreaZ;
    private int mFlag;

    public static synchronized DataSimulatorSetGetArea getInstance() {
        DataSimulatorSetGetArea dataSimulatorSetGetArea;
        synchronized (DataSimulatorSetGetArea.class) {
            if (instance == null) {
                instance = new DataSimulatorSetGetArea();
            }
            dataSimulatorSetGetArea = instance;
        }
        return dataSimulatorSetGetArea;
    }

    public DataSimulatorSetGetArea setAreaX(float f) {
        this.mAreaX = f;
        return this;
    }

    public DataSimulatorSetGetArea setAreaY(float f) {
        this.mAreaY = f;
        return this;
    }

    public DataSimulatorSetGetArea setAreaZ(float f) {
        this.mAreaZ = f;
        return this;
    }

    public DataSimulatorSetGetArea setAckFlag(boolean z) {
        if (z) {
            this.mFlag = 1;
        } else {
            this.mFlag = 0;
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
        cVar.n = l.a.SetGetArea.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[13];
        System.arraycopy(dji.midware.util.c.b(this.mAreaX), 0, this._sendData, 0, 4);
        System.arraycopy(dji.midware.util.c.b(this.mAreaY), 0, this._sendData, 4, 4);
        System.arraycopy(dji.midware.util.c.b(this.mAreaZ), 0, this._sendData, 8, 4);
        this._sendData[12] = (byte) this.mFlag;
    }
}
