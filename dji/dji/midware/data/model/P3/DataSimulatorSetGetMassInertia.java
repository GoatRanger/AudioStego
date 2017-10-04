package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataSimulatorSetGetMassInertia extends n implements e {
    private static DataSimulatorSetGetMassInertia instance;
    private int mFlag;
    private float mInertiaX;
    private float mInertiaY;
    private float mInertiaZ;
    private float mMass;

    public static synchronized DataSimulatorSetGetMassInertia getInstance() {
        DataSimulatorSetGetMassInertia dataSimulatorSetGetMassInertia;
        synchronized (DataSimulatorSetGetMassInertia.class) {
            if (instance == null) {
                instance = new DataSimulatorSetGetMassInertia();
            }
            dataSimulatorSetGetMassInertia = instance;
        }
        return dataSimulatorSetGetMassInertia;
    }

    public DataSimulatorSetGetMassInertia setMass(float f) {
        this.mMass = f;
        return this;
    }

    public DataSimulatorSetGetMassInertia setInertiaY(float f) {
        this.mInertiaY = f;
        return this;
    }

    public DataSimulatorSetGetMassInertia setInertiaX(float f) {
        this.mInertiaX = f;
        return this;
    }

    public DataSimulatorSetGetMassInertia setInertiaZ(float f) {
        this.mInertiaZ = f;
        return this;
    }

    public DataSimulatorSetGetMassInertia setAckFlag(boolean z) {
        if (z) {
            this.mFlag |= 1;
        } else {
            this.mFlag |= 0;
        }
        return this;
    }

    public void start(d dVar) {
    }

    protected void doPack() {
        this._sendData = new byte[17];
        System.arraycopy(c.b(this.mMass), 0, this._sendData, 0, 4);
        System.arraycopy(c.b(this.mInertiaX), 0, this._sendData, 4, 4);
        System.arraycopy(c.b(this.mInertiaY), 0, this._sendData, 8, 4);
        System.arraycopy(c.b(this.mInertiaZ), 0, this._sendData, 12, 4);
        this._sendData[16] = (byte) this.mFlag;
    }
}
