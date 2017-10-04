package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataRcSetSlavePermission extends n implements e {
    private static DataRcSetSlavePermission a = null;
    private RcSlavePermission b = new RcSlavePermission();

    public static class RcSlavePermission {
        public int a;
        public boolean b;
        public boolean c;
        public boolean d;
        public boolean e;
        public boolean f;
        public boolean g;
    }

    public static synchronized DataRcSetSlavePermission getInstance() {
        DataRcSetSlavePermission dataRcSetSlavePermission;
        synchronized (DataRcSetSlavePermission.class) {
            if (a == null) {
                a = new DataRcSetSlavePermission();
            }
            dataRcSetSlavePermission = a;
        }
        return dataRcSetSlavePermission;
    }

    public DataRcSetSlavePermission a(int i) {
        this.b.a = i;
        return this;
    }

    public DataRcSetSlavePermission a(boolean z) {
        this.b.b = z;
        return this;
    }

    public DataRcSetSlavePermission b(boolean z) {
        this.b.c = z;
        return this;
    }

    public DataRcSetSlavePermission c(boolean z) {
        this.b.d = z;
        return this;
    }

    public DataRcSetSlavePermission d(boolean z) {
        this.b.e = z;
        return this;
    }

    public DataRcSetSlavePermission e(boolean z) {
        this.b.f = z;
        return this;
    }

    public DataRcSetSlavePermission f(boolean z) {
        this.b.g = z;
        return this;
    }

    private int g(boolean z) {
        return z ? 1 : 0;
    }

    protected void doPack() {
        this._sendData = new byte[5];
        System.arraycopy(c.a(this.b.a), 0, this._sendData, 0, 4);
        this._sendData[4] = (byte) ((((((g(this.b.b) << 7) | (g(this.b.c) << 6)) | (g(this.b.d) << 5)) | (g(this.b.e) << 4)) | (g(this.b.f) << 3)) | (g(this.b.g) << 2));
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.SetSlavePermission.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
