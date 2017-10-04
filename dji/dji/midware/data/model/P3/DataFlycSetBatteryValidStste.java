package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycSetBatteryValidStste extends n implements e {
    private static DataFlycSetBatteryValidStste a;
    private boolean b = true;

    public static synchronized DataFlycSetBatteryValidStste getInstance() {
        DataFlycSetBatteryValidStste dataFlycSetBatteryValidStste;
        synchronized (DataFlycSetBatteryValidStste.class) {
            if (a == null) {
                a = new DataFlycSetBatteryValidStste();
            }
            dataFlycSetBatteryValidStste = a;
        }
        return dataFlycSetBatteryValidStste;
    }

    public DataFlycSetBatteryValidStste a(boolean z) {
        this.b = z;
        return this;
    }

    private DataFlycSetBatteryValidStste() {
    }

    protected void doPack() {
        int i = 2;
        byte[] bArr = new byte[2];
        bArr[0] = (byte) 0;
        if (this.b) {
            i = 1;
        }
        bArr[1] = (byte) i;
        this._sendData = bArr;
    }

    public void a() {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.b.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.Z.a();
        super.start(cVar);
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.Z.a();
        super.start(cVar, dVar);
    }
}
