package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCameraSaveParams.USER;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraSetParamName extends n implements e {
    public static final int a = 63;
    private static DataCameraSetParamName b = null;
    private USER c = USER.USER1;
    private byte[] d = null;

    public static synchronized DataCameraSetParamName getInstance() {
        DataCameraSetParamName dataCameraSetParamName;
        synchronized (DataCameraSetParamName.class) {
            if (b == null) {
                b = new DataCameraSetParamName(true);
            }
            dataCameraSetParamName = b;
        }
        return dataCameraSetParamName;
    }

    public DataCameraSetParamName(boolean z) {
        super(z);
    }

    public DataCameraSetParamName a(USER user) {
        this.c = user;
        return this;
    }

    public DataCameraSetParamName a(byte[] bArr) {
        this.d = bArr;
        return this;
    }

    protected void doPack() {
        int i = 63;
        this._sendData = new byte[65];
        this._sendData[0] = (byte) this.c.value();
        if (this.d != null && this.d.length > 0) {
            if (this.d.length <= 63) {
                i = this.d.length;
            }
            System.arraycopy(this.d, 0, this._sendData, 1, i);
        }
        this._sendData[64] = (byte) 0;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bM.a();
        start(cVar, dVar);
    }
}
