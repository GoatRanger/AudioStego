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

public class DataSmartBatteryGetHistory extends n implements e {
    private static DataSmartBatteryGetHistory mInstance = null;
    private int index = 0;

    public static DataSmartBatteryGetHistory getInstance() {
        if (mInstance == null) {
            mInstance = new DataSmartBatteryGetHistory();
        }
        return mInstance;
    }

    public DataSmartBatteryGetHistory setIndex(int i) {
        this.index = i;
        return this;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getIndex() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public long[] getHistory() {
        long[] jArr = new long[16];
        for (int i = 0; i < jArr.length; i++) {
            jArr[i] = ((Long) get((i * 4) + 2, 4, Long.class)).longValue();
        }
        return jArr;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.BATTERY.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.n.a();
        cVar.n = m.a.GetHistory.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[9];
        this._sendData[0] = (byte) this.index;
    }
}
