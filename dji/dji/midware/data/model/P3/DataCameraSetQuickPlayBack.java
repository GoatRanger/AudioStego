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

public class DataCameraSetQuickPlayBack extends n implements e {
    private static DataCameraSetQuickPlayBack a = null;
    private static final byte b = Byte.MIN_VALUE;
    private byte c = (byte) 0;
    private boolean d = false;

    public static synchronized DataCameraSetQuickPlayBack getInstance() {
        DataCameraSetQuickPlayBack dataCameraSetQuickPlayBack;
        synchronized (DataCameraSetQuickPlayBack.class) {
            if (a == null) {
                a = new DataCameraSetQuickPlayBack();
            }
            dataCameraSetQuickPlayBack = a;
        }
        return dataCameraSetQuickPlayBack;
    }

    public void a(byte b) {
        this.c = b;
    }

    public void a(boolean z) {
        this.d = z;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) 0;
        if (this.c != (byte) 0 && this.d) {
            byte[] bArr = this._sendData;
            bArr[0] = (byte) (bArr[0] | -128);
            bArr = this._sendData;
            bArr[0] = (byte) (bArr[0] | this.c);
        }
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.aj.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
