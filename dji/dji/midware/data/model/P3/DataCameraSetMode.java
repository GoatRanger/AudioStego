package dji.midware.data.model.P3;

import com.google.android.gms.common.api.CommonStatusCodes;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraSetMode extends n implements e {
    private static DataCameraSetMode a = null;
    private MODE b;

    public static synchronized DataCameraSetMode getInstance() {
        DataCameraSetMode dataCameraSetMode;
        synchronized (DataCameraSetMode.class) {
            if (a == null) {
                a = new DataCameraSetMode();
            }
            dataCameraSetMode = a;
        }
        return dataCameraSetMode;
    }

    public DataCameraSetMode a(MODE mode) {
        this.b = mode;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.b.value();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.g.a();
        cVar.w = 4;
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        start(cVar, dVar);
    }
}
