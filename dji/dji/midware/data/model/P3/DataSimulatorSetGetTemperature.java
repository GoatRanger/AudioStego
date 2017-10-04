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

public class DataSimulatorSetGetTemperature extends n implements e {
    private static DataSimulatorSetGetTemperature instance;
    private int mFlag;
    private float mTemperature;

    public static synchronized DataSimulatorSetGetTemperature getInstance() {
        DataSimulatorSetGetTemperature dataSimulatorSetGetTemperature;
        synchronized (DataSimulatorSetGetTemperature.class) {
            if (instance == null) {
                instance = new DataSimulatorSetGetTemperature();
            }
            dataSimulatorSetGetTemperature = instance;
        }
        return dataSimulatorSetGetTemperature;
    }

    public DataSimulatorSetGetTemperature setTemperature(float f) {
        this.mTemperature = f;
        return this;
    }

    public DataSimulatorSetGetTemperature setAckFlag(boolean z) {
        if (z) {
            this.mFlag |= 1;
        } else {
            this.mFlag |= 0;
        }
        return this;
    }

    public DataSimulatorSetGetTemperature setInitFlag(boolean z) {
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
        cVar.n = l.a.SetGetTemperature.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[5];
        System.arraycopy(dji.midware.util.c.b(this.mTemperature), 0, this._sendData, 0, 4);
        this._sendData[4] = (byte) this.mFlag;
    }
}
