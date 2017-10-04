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

public class DataCommonSetMultiParam extends n implements e {
    private static DataCommonSetMultiParam a = null;
    private byte[] b = null;
    private DeviceType c = DeviceType.CAMERA;

    public static synchronized DataCommonSetMultiParam getInstance() {
        DataCommonSetMultiParam dataCommonSetMultiParam;
        synchronized (DataCommonSetMultiParam.class) {
            if (a == null) {
                a = new DataCommonSetMultiParam();
            }
            dataCommonSetMultiParam = a;
        }
        return dataCommonSetMultiParam;
    }

    public int a() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public DataCommonSetMultiParam a(byte[] bArr) {
        this.b = bArr;
        return this;
    }

    public DataCommonSetMultiParam a(DeviceType deviceType) {
        this.c = deviceType;
        return this;
    }

    protected void doPack() {
        this._sendData = this.b;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = this.c.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.a.a();
        cVar.n = dji.midware.data.config.P3.d.a.b.a();
        cVar.p = getSendData();
        cVar.v = this.b[0] * 1000;
        start(cVar, dVar);
    }
}
