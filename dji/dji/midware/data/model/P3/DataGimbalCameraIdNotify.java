package dji.midware.data.model.P3;

import android.util.Log;
import dji.midware.data.a.a.b;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.h;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;

public class DataGimbalCameraIdNotify extends n {
    private static final String a = DataGimbalCameraIdNotify.class.getSimpleName();
    private static DataGimbalCameraIdNotify b = null;
    private int c = -1;

    public static synchronized DataGimbalCameraIdNotify getInstance() {
        DataGimbalCameraIdNotify dataGimbalCameraIdNotify;
        synchronized (DataGimbalCameraIdNotify.class) {
            if (b == null) {
                b = new DataGimbalCameraIdNotify();
            }
            dataGimbalCameraIdNotify = b;
        }
        return dataGimbalCameraIdNotify;
    }

    public DataGimbalCameraIdNotify a(int i) {
        this.c = i;
        return this;
    }

    protected void doPack() {
        int i;
        this._sendData = new byte[8];
        byte[] bArr = this._sendData;
        if (this.c >= 0) {
            i = this.c;
        } else {
            i = 0;
        }
        bArr[0] = (byte) i;
    }

    public b a() {
        return this.recvPack;
    }

    public void a(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = q.b.a.a();
        cVar.m = p.e.a();
        cVar.n = h.a.NotiFyCameraId.a();
        super.start(cVar, dVar);
        Log.d(a, "start: send data = " + dji.midware.util.c.i(cVar.r));
    }
}
