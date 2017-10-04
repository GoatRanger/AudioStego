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

public class DataCameraSetFocusWindow extends n implements e {
    private static DataCameraSetFocusWindow a = null;
    private int b = 1;
    private int c = 1;
    private int d = 0;

    public static synchronized DataCameraSetFocusWindow getInstance() {
        DataCameraSetFocusWindow dataCameraSetFocusWindow;
        synchronized (DataCameraSetFocusWindow.class) {
            if (a == null) {
                a = new DataCameraSetFocusWindow();
            }
            dataCameraSetFocusWindow = a;
        }
        return dataCameraSetFocusWindow;
    }

    public DataCameraSetFocusWindow a(int i) {
        this.b = i;
        return this;
    }

    public DataCameraSetFocusWindow b(int i) {
        this.c = i;
        return this;
    }

    public DataCameraSetFocusWindow c(int i) {
        this.d = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[3];
        this._sendData[0] = (byte) this.b;
        this._sendData[1] = (byte) this.c;
        this._sendData[2] = (byte) this.d;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bx.a();
        start(cVar, dVar);
    }
}
