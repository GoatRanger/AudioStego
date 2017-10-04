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

public class DataSmartBatteryGetMultBatteryInfo extends n implements e {
    public static int DATA_TYPE_IOC = 0;
    public static int DATA_TYPE_VOLTAGE = 1;
    private static DataSmartBatteryGetMultBatteryInfo mInstance = null;

    public static DataSmartBatteryGetMultBatteryInfo getInstance() {
        if (mInstance == null) {
            mInstance = new DataSmartBatteryGetMultBatteryInfo();
        }
        return mInstance;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getType() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int getNum() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    public int[] getValues() {
        int num = getNum();
        if (num <= 0) {
            return null;
        }
        int[] iArr = new int[num];
        for (int i = 0; i < num; i++) {
            iArr[i] = ((Integer) get((i * 4) + 3, 4, Integer.class)).intValue();
        }
        return iArr;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.BATTERY.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.n.a();
        cVar.n = m.a.GetMultBatteryInfo.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
    }
}
