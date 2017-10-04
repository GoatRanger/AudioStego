package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.m;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataSmartBatteryGetPushCellVoltage extends n implements e {
    private static DataSmartBatteryGetPushCellVoltage mInstance = null;
    private int dataOffset = 0;
    private int index = 0;
    private boolean isRequestPush = false;
    private boolean isStopPush = true;
    private int pushFreq = 1;

    public static DataSmartBatteryGetPushCellVoltage getInstance() {
        if (mInstance == null) {
            mInstance = new DataSmartBatteryGetPushCellVoltage();
        }
        return mInstance;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public DataSmartBatteryGetPushCellVoltage setIndex(int i) {
        this.index = i;
        return this;
    }

    public DataSmartBatteryGetPushCellVoltage setRequestPush(boolean z) {
        this.isRequestPush = z;
        this.dataOffset = this.isRequestPush ? 0 : 1;
        return this;
    }

    public DataSmartBatteryGetPushCellVoltage setContinuePush(boolean z) {
        this.isStopPush = z;
        return this;
    }

    public DataSmartBatteryGetPushCellVoltage setPushFreq(int i) {
        this.pushFreq = i;
        return this;
    }

    public int getIndex() {
        return ((Integer) get(this.dataOffset + 0, 1, Integer.class)).intValue();
    }

    public int getCells() {
        return ((Integer) get(this.dataOffset + 1, 1, Integer.class)).intValue();
    }

    public int[] getVoltages() {
        int intValue = ((Integer) get(this.dataOffset + 1, 1, Integer.class)).intValue();
        if (intValue <= 0) {
            return null;
        }
        int[] iArr = new int[intValue];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = ((Integer) get(((i * 2) + 2) + this.dataOffset, 2, Integer.class)).intValue();
        }
        return iArr;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.BATTERY.value();
        cVar.j = a.a.a();
        cVar.k = q.c.c.a();
        cVar.l = b.a.a();
        cVar.m = p.n.a();
        cVar.n = m.a.GetPushCellVoltage.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        int i;
        int i2 = 1;
        this._sendData = new byte[4];
        this._sendData[0] = (byte) this.index;
        byte[] bArr = this._sendData;
        if (this.isRequestPush) {
            i = 1;
        } else {
            i = 0;
        }
        bArr[1] = (byte) i;
        byte[] bArr2 = this._sendData;
        if (!this.isStopPush) {
            i2 = 0;
        }
        bArr2[2] = (byte) i2;
        this._sendData[3] = (byte) this.pushFreq;
    }

    protected void setPushRecPack(dji.midware.data.a.a.a aVar) {
        if (aVar != null && aVar.j == 0 && aVar.p != null && aVar.p[0] == (byte) 0) {
            super.setPushRecPack(aVar);
        }
    }
}
