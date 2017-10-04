package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCenterGetSmartBatteryCurrentStatus extends n implements e {
    private static DataCenterGetSmartBatteryCurrentStatus instance = null;

    public static synchronized DataCenterGetSmartBatteryCurrentStatus getInstance() {
        DataCenterGetSmartBatteryCurrentStatus dataCenterGetSmartBatteryCurrentStatus;
        synchronized (DataCenterGetSmartBatteryCurrentStatus.class) {
            if (instance == null) {
                instance = new DataCenterGetSmartBatteryCurrentStatus();
            }
            dataCenterGetSmartBatteryCurrentStatus = instance;
        }
        return dataCenterGetSmartBatteryCurrentStatus;
    }

    public int getCurrentStatus() {
        return ((Integer) get(1, 4, Integer.class)).intValue();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        ProductType c = i.getInstance().c();
        if (c == ProductType.litchiC || c == ProductType.litchiS || c == ProductType.litchiX) {
            cVar.h = DeviceType.BATTERY.value();
        } else {
            cVar.h = DeviceType.CENTER.value();
        }
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.f.a();
        cVar.n = dji.midware.data.config.P3.c.a.GetSmartBatteryCurrentStatus.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) 1;
    }
}
