package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.h;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;

public class DataGimbalGetPushTimelapseStatus extends n {
    private static final String TAG = "DataGimbalGetPushTimelapseStatus";
    private static final DataGimbalGetPushTimelapseStatus mInstance = new DataGimbalGetPushTimelapseStatus();

    public static DataGimbalGetPushTimelapseStatus getInstance() {
        return mInstance;
    }

    public int getTimelapseStatus() {
        return ((Integer) get(0, 1, Integer.class)).intValue() & 3;
    }

    protected void doPack() {
    }

    protected boolean isChanged(byte[] bArr) {
        start();
        return super.isChanged(bArr);
    }

    protected void start() {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.b.a();
        cVar.k = q.c.b.a();
        cVar.l = b.a.a();
        cVar.m = p.e.a();
        cVar.n = h.a.GetPushTimelapseStatus.a();
        super.start(cVar);
    }
}
