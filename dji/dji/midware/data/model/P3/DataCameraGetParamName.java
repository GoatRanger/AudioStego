package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCameraSaveParams.USER;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataCameraGetParamName extends n implements e {
    private static DataCameraGetParamName instance = null;
    private USER mParamIndex = USER.USER1;

    public static synchronized DataCameraGetParamName getInstance() {
        DataCameraGetParamName dataCameraGetParamName;
        synchronized (DataCameraGetParamName.class) {
            if (instance == null) {
                instance = new DataCameraGetParamName(true);
            }
            dataCameraGetParamName = instance;
        }
        return dataCameraGetParamName;
    }

    public DataCameraGetParamName(boolean z) {
        super(z);
    }

    public String getName() {
        if (this._recData == null || this._recData.length <= 0) {
            return null;
        }
        return c.c(this._recData, 0, this._recData.length);
    }

    public DataCameraGetParamName setParamIndex(USER user) {
        this.mParamIndex = user;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.mParamIndex.value();
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bN.a();
        start(cVar, dVar);
    }
}
