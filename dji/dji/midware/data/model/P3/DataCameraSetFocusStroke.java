package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataCameraSetFocusStroke extends n implements e {
    private static DataCameraSetFocusStroke a = null;
    private int b = 0;

    public static synchronized DataCameraSetFocusStroke getInstance() {
        DataCameraSetFocusStroke dataCameraSetFocusStroke;
        synchronized (DataCameraSetFocusStroke.class) {
            if (a == null) {
                a = new DataCameraSetFocusStroke();
            }
            dataCameraSetFocusStroke = a;
        }
        return dataCameraSetFocusStroke;
    }

    public DataCameraSetFocusStroke a(int i) {
        this.b = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[2];
        System.arraycopy(c.b(this.b), 0, this._sendData, 0, 2);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.CAMERA.a();
        cVar.n = dji.midware.data.config.P3.b.a.SetFocusStroke.a();
        start(cVar, dVar);
    }
}
