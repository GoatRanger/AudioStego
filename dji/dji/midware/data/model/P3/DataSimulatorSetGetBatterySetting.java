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

public class DataSimulatorSetGetBatterySetting extends n implements e {
    private static DataSimulatorSetGetBatterySetting instance;
    private int mCellNum;
    private int mCellVoltage;
    private int mCycleCnt;
    private int mDesignCapacity;
    private int mErrorCnt;
    private int mFlag;
    private int mInitialCapPer;
    private float mInitialTemperature;
    private float mInternalResistance;
    private int mManufactureDate;
    private int mSequenceNum;
    private float mStandbyCurrent;

    public static synchronized DataSimulatorSetGetBatterySetting getInstance() {
        DataSimulatorSetGetBatterySetting dataSimulatorSetGetBatterySetting;
        synchronized (DataSimulatorSetGetBatterySetting.class) {
            if (instance == null) {
                instance = new DataSimulatorSetGetBatterySetting();
            }
            dataSimulatorSetGetBatterySetting = instance;
        }
        return dataSimulatorSetGetBatterySetting;
    }

    public DataSimulatorSetGetBatterySetting setAckFlag(boolean z) {
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
        cVar.n = l.a.SetGetBatterySetting.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[26];
        System.arraycopy(dji.midware.util.c.b(this.mCellVoltage), 0, this._sendData, 0, 2);
        System.arraycopy(dji.midware.util.c.b(this.mDesignCapacity), 0, this._sendData, 2, 2);
        System.arraycopy(dji.midware.util.c.b(this.mInternalResistance), 0, this._sendData, 4, 4);
        System.arraycopy(dji.midware.util.c.b(this.mStandbyCurrent), 0, this._sendData, 8, 4);
        System.arraycopy(dji.midware.util.c.b(this.mInitialTemperature), 0, this._sendData, 12, 4);
        System.arraycopy(dji.midware.util.c.b(this.mSequenceNum), 0, this._sendData, 16, 2);
        System.arraycopy(dji.midware.util.c.b(this.mErrorCnt), 0, this._sendData, 18, 2);
        System.arraycopy(dji.midware.util.c.b(this.mManufactureDate), 0, this._sendData, 20, 2);
        System.arraycopy(dji.midware.util.c.b(this.mCellNum), 0, this._sendData, 22, 1);
        System.arraycopy(dji.midware.util.c.b(this.mInitialCapPer), 0, this._sendData, 23, 1);
        System.arraycopy(dji.midware.util.c.b(this.mCycleCnt), 0, this._sendData, 24, 1);
        this._sendData[25] = (byte) this.mFlag;
    }

    public void setmCellVoltage(int i) {
        this.mCellVoltage = i;
    }

    public void setmDesignCapacity(int i) {
        this.mDesignCapacity = i;
    }

    public void setmInternalResistance(float f) {
        this.mInternalResistance = f;
    }

    public void setmStandbyCurrent(float f) {
        this.mStandbyCurrent = f;
    }

    public void setmInitialTemperature(float f) {
        this.mInitialTemperature = f;
    }

    public void setmSequenceNum(int i) {
        this.mSequenceNum = i;
    }

    public void setmErrorCnt(int i) {
        this.mErrorCnt = i;
    }

    public void setmManufactureDate(int i) {
        this.mManufactureDate = i;
    }

    public void setmCellNum(int i) {
        this.mCellNum = i;
    }

    public void setmInitialCapPer(int i) {
        this.mInitialCapPer = i;
    }

    public void setmCycleCnt(int i) {
        this.mCycleCnt = i;
    }
}
