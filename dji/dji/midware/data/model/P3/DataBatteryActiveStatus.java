package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.model.b.a;
import dji.midware.e.d;

public class DataBatteryActiveStatus extends a {
    private static DataBatteryActiveStatus instance = null;
    private int mIndexMultiBattery = 0;

    public static synchronized DataBatteryActiveStatus getInstance() {
        DataBatteryActiveStatus dataBatteryActiveStatus;
        synchronized (DataBatteryActiveStatus.class) {
            if (instance == null) {
                instance = new DataBatteryActiveStatus();
            }
            dataBatteryActiveStatus = instance;
        }
        return dataBatteryActiveStatus;
    }

    public DataBatteryActiveStatus setIndexMultiBattery(int i) {
        this.mIndexMultiBattery = i;
        return this;
    }

    public int getSenderIndex() {
        return this.pack.e;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.BATTERY.value();
        cVar.g = this.mIndexMultiBattery;
        this.mIndexMultiBattery = 0;
        cVar.j = q.a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.COMMON.a();
        cVar.n = dji.midware.data.config.P3.d.a.ActiveStatus.a();
        cVar.v = 1000;
        cVar.w = 3;
        start(cVar, dVar);
    }
}
