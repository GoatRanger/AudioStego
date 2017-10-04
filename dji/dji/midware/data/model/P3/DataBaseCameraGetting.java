package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.b.a;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataBaseCameraGetting extends n implements e {
    protected String cmdId;
    protected a cmdIdType;
    protected int value;

    public DataBaseCameraGetting setCmdId(a aVar) {
        this.cmdIdType = aVar;
        return this;
    }

    public void setCmdId(String str) {
        this.cmdId = str;
    }

    public int getValue() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = q.a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        if (this.cmdIdType != null) {
            cVar.n = this.cmdIdType.a();
        } else {
            cVar.n = a.valueOf("Get" + this.cmdId).a();
        }
        start(cVar, dVar);
    }
}
