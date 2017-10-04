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

public class DataCameraSetTimeParams extends n implements e {
    private static DataCameraSetTimeParams a = null;
    private TYPE b;
    private int c;
    private int d;

    public enum TYPE {
        Single(0),
        Multiple(1),
        Timelapse(2),
        OTHER(100);
        
        private int e;

        private TYPE(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        public boolean a(int i) {
            return this.e == i;
        }

        public static TYPE find(int i) {
            TYPE type = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return type;
        }
    }

    public static synchronized DataCameraSetTimeParams getInstance() {
        DataCameraSetTimeParams dataCameraSetTimeParams;
        synchronized (DataCameraSetTimeParams.class) {
            if (a == null) {
                a = new DataCameraSetTimeParams();
            }
            dataCameraSetTimeParams = a;
        }
        return dataCameraSetTimeParams;
    }

    public DataCameraSetTimeParams a(TYPE type) {
        this.b = type;
        return this;
    }

    public DataCameraSetTimeParams a(int i) {
        this.c = i;
        return this;
    }

    public DataCameraSetTimeParams b(int i) {
        this.d = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[4];
        this._sendData[0] = (byte) this.b.a();
        this._sendData[1] = (byte) this.c;
        System.arraycopy(c.b(this.d), 0, this._sendData, 2, 2);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.af.a();
        start(cVar, dVar);
    }
}
