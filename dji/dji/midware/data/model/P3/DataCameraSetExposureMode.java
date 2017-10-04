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

public class DataCameraSetExposureMode extends n implements e {
    private static DataCameraSetExposureMode a = null;
    private int b;
    private int c;

    public enum ExposureMode {
        AUTO(0),
        P(1),
        S(2),
        d(3),
        M(4),
        B(5),
        SCN(6),
        C(7),
        OTHER(100);
        
        private int j;

        private ExposureMode(int i) {
            this.j = i;
        }

        public int a() {
            return this.j;
        }

        public boolean a(int i) {
            return this.j == i;
        }

        public static ExposureMode find(int i) {
            ExposureMode exposureMode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return exposureMode;
        }
    }

    public static synchronized DataCameraSetExposureMode getInstance() {
        DataCameraSetExposureMode dataCameraSetExposureMode;
        synchronized (DataCameraSetExposureMode.class) {
            if (a == null) {
                a = new DataCameraSetExposureMode();
            }
            dataCameraSetExposureMode = a;
        }
        return dataCameraSetExposureMode;
    }

    public DataCameraSetExposureMode a(int i) {
        this.b = i;
        return this;
    }

    public DataCameraSetExposureMode b(int i) {
        this.c = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[2];
        this._sendData[0] = (byte) this.b;
        if (this.b == 6) {
            this._sendData[1] = (byte) this.c;
        }
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.CAMERA.a();
        cVar.n = dji.midware.data.config.P3.b.a.SetExposureMode.a();
        start(cVar, dVar);
    }
}
