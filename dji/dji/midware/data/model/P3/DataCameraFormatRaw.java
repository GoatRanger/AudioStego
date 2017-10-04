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

public class DataCameraFormatRaw extends n implements e {
    private static DataCameraFormatRaw a = null;
    private boolean b = false;
    private int c = 0;

    public static synchronized DataCameraFormatRaw getInstance() {
        DataCameraFormatRaw dataCameraFormatRaw;
        synchronized (DataCameraFormatRaw.class) {
            if (a == null) {
                a = new DataCameraFormatRaw();
            }
            dataCameraFormatRaw = a;
        }
        return dataCameraFormatRaw;
    }

    public DataCameraFormatRaw a(boolean z) {
        this.b = z;
        return this;
    }

    public DataCameraFormatRaw a(int i) {
        this.c = i;
        return this;
    }

    protected void doPack() {
        int i = 1;
        this._sendData = new byte[1];
        byte[] bArr = this._sendData;
        byte b = bArr[0];
        if (!this.b) {
            i = 0;
        }
        bArr[0] = (byte) (i | b);
        byte[] bArr2 = this._sendData;
        bArr2[0] = (byte) (bArr2[0] | ((byte) (this.c << 1)));
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bv.a();
        cVar.v = 5000;
        start(cVar, dVar);
    }
}
