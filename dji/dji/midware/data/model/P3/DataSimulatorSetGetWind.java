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

public class DataSimulatorSetGetWind extends n implements e {
    private static DataSimulatorSetGetWind instance;
    private int mFlag;
    private int mWindSpeedX = 0;
    private int mWindSpeedY = 0;
    private int mWindSpeedZ = 0;

    public static synchronized DataSimulatorSetGetWind getInstance() {
        DataSimulatorSetGetWind dataSimulatorSetGetWind;
        synchronized (DataSimulatorSetGetWind.class) {
            if (instance == null) {
                instance = new DataSimulatorSetGetWind();
            }
            dataSimulatorSetGetWind = instance;
        }
        return dataSimulatorSetGetWind;
    }

    public DataSimulatorSetGetWind setWindSpeedX(int i) {
        this.mWindSpeedX = i;
        return this;
    }

    public DataSimulatorSetGetWind setWindSpeedY(int i) {
        this.mWindSpeedY = i;
        return this;
    }

    public DataSimulatorSetGetWind setWindSpeedZ(int i) {
        this.mWindSpeedZ = i;
        return this;
    }

    public DataSimulatorSetGetWind setAckFlag(boolean z) {
        if (z) {
            this.mFlag |= 1;
        } else {
            this.mFlag |= 0;
        }
        return this;
    }

    public DataSimulatorSetGetWind setInitFlag(boolean z) {
        if (z) {
            this.mFlag |= 2;
        } else {
            this.mFlag |= 0;
        }
        return this;
    }

    public int getWindSpeedX() {
        return ((Integer) get(0, 2, Integer.class)).intValue();
    }

    public int getWindSpeedY() {
        return ((Integer) get(2, 2, Integer.class)).intValue();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.l.a();
        cVar.n = l.a.SetGetWind.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[7];
        System.arraycopy(dji.midware.util.c.b(this.mWindSpeedX), 0, this._sendData, 0, 2);
        System.arraycopy(dji.midware.util.c.b(this.mWindSpeedY), 0, this._sendData, 2, 2);
        System.arraycopy(dji.midware.util.c.b(this.mWindSpeedZ), 0, this._sendData, 4, 2);
        this._sendData[6] = (byte) this.mFlag;
    }
}
