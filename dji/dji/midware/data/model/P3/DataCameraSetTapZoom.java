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

public class DataCameraSetTapZoom extends n implements e {
    private static DataCameraSetTapZoom a = null;
    private boolean b;
    private int c;

    public static synchronized DataCameraSetTapZoom getInstance() {
        DataCameraSetTapZoom dataCameraSetTapZoom;
        synchronized (DataCameraSetTapZoom.class) {
            if (a == null) {
                a = new DataCameraSetTapZoom();
            }
            dataCameraSetTapZoom = a;
        }
        return dataCameraSetTapZoom;
    }

    public DataCameraSetTapZoom a(boolean z) {
        this.b = z;
        return this;
    }

    public DataCameraSetTapZoom a(int i) {
        this.c = i;
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bO.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        int i;
        this._sendData = new byte[2];
        byte[] bArr = this._sendData;
        if (this.b) {
            i = 1;
        } else {
            i = 0;
        }
        bArr[0] = (byte) i;
        this._sendData[1] = (byte) this.c;
    }
}
