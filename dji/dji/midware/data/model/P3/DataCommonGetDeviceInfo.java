package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCommonGetDeviceInfo extends n implements e {
    private int mReceiveId = 0;
    private DeviceType mReceiveType = DeviceType.DM368;

    public DataCommonGetDeviceInfo setReceiveType(DeviceType deviceType) {
        this.mReceiveType = deviceType;
        return this;
    }

    public DataCommonGetDeviceInfo setReceiveId(int i) {
        this.mReceiveId = i;
        return this;
    }

    public String getInfo() {
        return get(0, this._recData.length);
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = this.mReceiveType.value();
        cVar.g = this.mReceiveId;
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.COMMON.a();
        cVar.n = dji.midware.data.config.P3.d.a.GetDeviceInfo.a();
        cVar.v = 1000;
        cVar.w = 2;
        start(cVar, dVar);
    }
}
