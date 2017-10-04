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

public class DataSmartBatteryGetStaticData extends n implements e {
    private static DataSmartBatteryGetStaticData mInstance = null;
    private int index = 0;

    public static DataSmartBatteryGetStaticData getInstance() {
        if (mInstance == null) {
            mInstance = new DataSmartBatteryGetStaticData();
        }
        return mInstance;
    }

    public DataSmartBatteryGetStaticData setIndex(int i) {
        this.index = i;
        return this;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getIndex() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public long getDesignCapacity() {
        return ((Long) get(2, 4, Long.class)).longValue();
    }

    public int getCycleTimes() {
        return ((Integer) get(6, 2, Integer.class)).intValue();
    }

    public int getDesignVoltage() {
        return ((Integer) get(8, 4, Integer.class)).intValue();
    }

    public int getProductionDate() {
        return ((Integer) get(12, 2, Integer.class)).intValue();
    }

    public int getSerialNumber() {
        return ((Integer) get(14, 2, Integer.class)).intValue();
    }

    public String getCellProvider() {
        return get(16, 5);
    }

    public String getBoardProvider() {
        return get(21, 5);
    }

    public String getDeviceName() {
        return get(26, 5);
    }

    public long getLoaderVer() {
        return ((Long) get(31, 4, Long.class)).longValue();
    }

    public long getAppVer() {
        return ((Long) get(35, 4, Long.class)).longValue();
    }

    public long getLifePercent() {
        return ((Long) get(39, 1, Long.class)).longValue();
    }

    public long getType() {
        return ((Long) get(40, 1, Long.class)).longValue();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.BATTERY.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.n.a();
        cVar.n = m.a.GetStaticData.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[9];
        this._sendData[0] = (byte) this.index;
    }
}
