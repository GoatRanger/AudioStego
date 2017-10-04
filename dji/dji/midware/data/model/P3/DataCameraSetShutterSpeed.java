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

public class DataCameraSetShutterSpeed extends n implements e {
    private static DataCameraSetShutterSpeed a = null;
    private TYPE b;
    private int c;
    private int d;
    private int e;

    public enum TYPE {
        AUTO(0),
        Manual(1),
        OTHER(100);
        
        private int d;

        private TYPE(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public boolean a(int i) {
            return this.d == i;
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

    public static synchronized DataCameraSetShutterSpeed getInstance() {
        DataCameraSetShutterSpeed dataCameraSetShutterSpeed;
        synchronized (DataCameraSetShutterSpeed.class) {
            if (a == null) {
                a = new DataCameraSetShutterSpeed();
            }
            dataCameraSetShutterSpeed = a;
        }
        return dataCameraSetShutterSpeed;
    }

    public DataCameraSetShutterSpeed a() {
        this.b = TYPE.AUTO;
        return this;
    }

    public DataCameraSetShutterSpeed a(boolean z, int i, int i2) {
        this.b = TYPE.Manual;
        this.c = z ? 1 : 0;
        this.d = i;
        this.e = i2;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[4];
        this._sendData[0] = (byte) this.b.a();
        switch (this.b) {
            case AUTO:
                this.d = 0;
                this.e = 0;
                break;
            case Manual:
                this.d = (this.c << 15) | this.d;
                break;
        }
        System.arraycopy(c.b(this.d), 0, this._sendData, 1, 2);
        this._sendData[3] = (byte) this.e;
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.E.a();
        start(cVar, dVar);
    }
}
