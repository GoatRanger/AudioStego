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

public class DataFlycRTKSwitch extends n implements e {
    private static DataFlycRTKSwitch a = null;
    private boolean b;

    public static synchronized DataFlycRTKSwitch getInstance() {
        DataFlycRTKSwitch dataFlycRTKSwitch;
        synchronized (DataFlycRTKSwitch.class) {
            if (a == null) {
                a = new DataFlycRTKSwitch();
            }
            dataFlycRTKSwitch = a;
        }
        return dataFlycRTKSwitch;
    }

    public DataFlycRTKSwitch a(boolean z) {
        this.b = z;
        return this;
    }

    private DataFlycRTKSwitch() {
    }

    protected void doPack() {
        int i = 0;
        byte[] bArr = new byte[2];
        bArr[0] = (byte) 3;
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
        cVar.n = g.a.U.a();
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
        cVar.n = g.a.U.a();
        super.start(cVar, dVar);
    }
}
