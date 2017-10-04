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

public class DataSmartBatteryGetSetSelfDischargeDays extends n implements e {
    private static DataSmartBatteryGetSetSelfDischargeDays mInstance = null;
    private int days = 0;
    private int index = 0;
    private boolean set = false;

    public static DataSmartBatteryGetSetSelfDischargeDays getInstance() {
        if (mInstance == null) {
            mInstance = new DataSmartBatteryGetSetSelfDischargeDays();
        }
        return mInstance;
    }

    public DataSmartBatteryGetSetSelfDischargeDays setIndex(int i) {
        this.index = i;
        return this;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public DataSmartBatteryGetSetSelfDischargeDays setType(boolean z) {
        this.set = z;
        return this;
    }

    public DataSmartBatteryGetSetSelfDischargeDays setDays(int i) {
        this.days = i;
        return this;
    }

    public int getIndex() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int getDays() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.BATTERY.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.n.a();
        cVar.n = m.a.GetSetSelfDischargeDays.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        int i = 0;
        this._sendData = new byte[3];
        this._sendData[0] = (byte) this.index;
        byte[] bArr = this._sendData;
        if (this.set) {
            i = 1;
        }
        bArr[1] = (byte) i;
        this._sendData[2] = (byte) this.days;
    }
}
