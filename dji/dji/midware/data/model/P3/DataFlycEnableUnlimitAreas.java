package dji.midware.data.model.P3;

import com.google.android.gms.common.api.CommonStatusCodes;
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

public class DataFlycEnableUnlimitAreas extends n implements e {
    private static DataFlycEnableUnlimitAreas a = null;
    private boolean b = false;

    public static synchronized DataFlycEnableUnlimitAreas getInstance() {
        DataFlycEnableUnlimitAreas dataFlycEnableUnlimitAreas;
        synchronized (DataFlycEnableUnlimitAreas.class) {
            if (a == null) {
                a = new DataFlycEnableUnlimitAreas();
            }
            dataFlycEnableUnlimitAreas = a;
        }
        return dataFlycEnableUnlimitAreas;
    }

    public DataFlycEnableUnlimitAreas a(boolean z) {
        this.b = z;
        return this;
    }

    public int a() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.F.a();
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        start(cVar, dVar);
    }

    protected void doPack() {
        int i = 1;
        this._sendData = new byte[1];
        byte[] bArr = this._sendData;
        if (!this.b) {
            i = 0;
        }
        bArr[0] = (byte) i;
    }
}
