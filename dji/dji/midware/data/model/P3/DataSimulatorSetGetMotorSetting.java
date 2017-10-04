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

public class DataSimulatorSetGetMotorSetting extends n implements e {
    private static DataSimulatorSetGetMotorSetting instance;
    private float[] mCl;
    private float[] mCq;
    private int mFlag;
    private int[] mIMax;
    private int[] mIMin;
    private float[] mInertiaMotor;
    private float[] mInertiaProp;
    private int[] mKV;
    private int mMarkBits;
    private int[] mMotorTiltAngle;
    private boolean mReqFlag = false;
    private int[] mRm;
    private int[] mVoltMax;

    public static synchronized DataSimulatorSetGetMotorSetting getInstance() {
        synchronized (DataSimulatorSetGetMotorSetting.class) {
            if (instance == null) {
                instance = new DataSimulatorSetGetMotorSetting();
            }
        }
        return null;
    }

    public DataSimulatorSetGetMotorSetting setMarkBits(int i) {
        this.mMarkBits = i;
        return this;
    }

    public DataSimulatorSetGetMotorSetting setAckFlag(boolean z) {
        if (z) {
            this.mFlag |= 1;
        } else {
            this.mFlag |= 0;
        }
        return this;
    }

    public DataSimulatorSetGetMotorSetting setReqFlag(boolean z) {
        if (z) {
            this.mFlag |= 2;
        } else {
            this.mFlag |= 0;
        }
        this.mReqFlag = true;
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
        cVar.n = l.a.SetGetMotorSetting.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        if (this.mReqFlag) {
            this._sendData = new byte[((this.mMarkBits * 28) + 2)];
            for (int i = 0; i < this.mMarkBits; i++) {
                System.arraycopy(dji.midware.util.c.b(this.mVoltMax[i]), 0, this._sendData, (i * 28) + 2, 2);
                System.arraycopy(dji.midware.util.c.b(this.mKV[i]), 0, this._sendData, (i * 28) + 4, 2);
                System.arraycopy(dji.midware.util.c.b(this.mRm[i]), 0, this._sendData, (i * 28) + 6, 2);
                System.arraycopy(dji.midware.util.c.b(this.mIMax[i]), 0, this._sendData, (i * 28) + 8, 2);
                System.arraycopy(dji.midware.util.c.b(this.mIMin[i]), 0, this._sendData, (i * 28) + 10, 2);
                System.arraycopy(dji.midware.util.c.b(this.mMotorTiltAngle[i]), 0, this._sendData, (i * 28) + 12, 2);
                System.arraycopy(dji.midware.util.c.b(this.mInertiaMotor[i]), 0, this._sendData, (i * 28) + 14, 4);
                System.arraycopy(dji.midware.util.c.b(this.mCl[i]), 0, this._sendData, (i * 28) + 18, 4);
                System.arraycopy(dji.midware.util.c.b(this.mCq[i]), 0, this._sendData, (i * 28) + 22, 4);
                System.arraycopy(dji.midware.util.c.b(this.mInertiaProp[i]), 0, this._sendData, (i * 28) + 26, 2);
            }
            return;
        }
        this._sendData = new byte[2];
        this._sendData[0] = (byte) this.mMarkBits;
        this._sendData[1] = (byte) this.mFlag;
    }

    public int[] getmVoltMax() {
        return this.mVoltMax;
    }

    public void setmVoltMax(int[] iArr) {
        this.mVoltMax = iArr;
    }

    public int[] getmKV() {
        return this.mKV;
    }

    public void setmKV(int[] iArr) {
        this.mKV = iArr;
    }

    public int[] getmRm() {
        return this.mRm;
    }

    public void setmRm(int[] iArr) {
        this.mRm = iArr;
    }

    public int[] getmIMax() {
        return this.mIMax;
    }

    public void setmIMax(int[] iArr) {
        this.mIMax = iArr;
    }

    public int[] getmIMin() {
        return this.mIMin;
    }

    public void setmIMin(int[] iArr) {
        this.mIMin = iArr;
    }

    public int[] getmMotorTiltAngle() {
        return this.mMotorTiltAngle;
    }

    public void setmMotorTiltAngle(int[] iArr) {
        this.mMotorTiltAngle = iArr;
    }

    public float[] getmInertiaMotor() {
        return this.mInertiaMotor;
    }

    public void setmInertiaMotor(float[] fArr) {
        this.mInertiaMotor = fArr;
    }

    public float[] getmCl() {
        return this.mCl;
    }

    public void setmCl(float[] fArr) {
        this.mCl = fArr;
    }

    public float[] getmCq() {
        return this.mCq;
    }

    public void setmCq(float[] fArr) {
        this.mCq = fArr;
    }

    public float[] getmInertiaProp() {
        return this.mInertiaProp;
    }

    public void setmInertiaProp(float[] fArr) {
        this.mInertiaProp = fArr;
    }
}
