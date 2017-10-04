package dji.midware.data.model.P3;

import android.support.v4.view.MotionEventCompat;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraSetTrackingParms extends n implements e {
    private static DataCameraSetTrackingParms a = null;
    private boolean b;
    private int c;
    private int d;

    public static synchronized DataCameraSetTrackingParms getInstance() {
        DataCameraSetTrackingParms dataCameraSetTrackingParms;
        synchronized (DataCameraSetTrackingParms.class) {
            if (a == null) {
                a = new DataCameraSetTrackingParms();
            }
            dataCameraSetTrackingParms = a;
        }
        return dataCameraSetTrackingParms;
    }

    public DataCameraSetTrackingParms a(boolean z) {
        this.b = z;
        return this;
    }

    public DataCameraSetTrackingParms a(int i) {
        this.c = i;
        return this;
    }

    public DataCameraSetTrackingParms b(int i) {
        this.d = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[5];
        this._sendData[0] = (byte) (this.b ? 1 : 0);
        this._sendData[1] = (byte) (this.c & 255);
        this._sendData[2] = (byte) ((this.c & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
        this._sendData[3] = (byte) (this.d & 255);
        this._sendData[4] = (byte) ((this.d & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bj.a();
        start(cVar, dVar);
    }
}
