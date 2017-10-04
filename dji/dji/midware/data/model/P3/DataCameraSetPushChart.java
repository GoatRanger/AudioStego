package dji.midware.data.model.P3;

import com.google.android.gms.common.api.CommonStatusCodes;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraSetPushChart extends n implements e {
    private static DataCameraSetPushChart a = null;
    private boolean b = false;

    public static synchronized DataCameraSetPushChart getInstance() {
        DataCameraSetPushChart dataCameraSetPushChart;
        synchronized (DataCameraSetPushChart.class) {
            if (a == null) {
                a = new DataCameraSetPushChart();
            }
            dataCameraSetPushChart = a;
        }
        return dataCameraSetPushChart;
    }

    public DataCameraSetPushChart a(boolean z) {
        this.b = z;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        if (this.b) {
            this._sendData[0] = (byte) 1;
        } else {
            this._sendData[0] = (byte) 0;
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
        cVar.n = dji.midware.data.config.P3.b.a.aq.a();
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        cVar.w = 2;
        start(cVar, dVar);
    }
}
