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

public class DataCameraSetCameraRotationMode extends n implements e {
    private static DataCameraSetCameraRotationMode a = null;
    private int b = -1;
    private RotationAngleType c;

    public enum RotationAngleType {
        Rotate0(0),
        Rotate90(1),
        Rotate180(2),
        Rotate270(3),
        Unknown(255);
        
        private int f;

        private RotationAngleType(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }

        public boolean a(int i) {
            return this.f == i;
        }

        public static RotationAngleType find(int i) {
            RotationAngleType rotationAngleType = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return rotationAngleType;
        }
    }

    public static synchronized DataCameraSetCameraRotationMode getInstance() {
        DataCameraSetCameraRotationMode dataCameraSetCameraRotationMode;
        synchronized (DataCameraSetCameraRotationMode.class) {
            if (a == null) {
                a = new DataCameraSetCameraRotationMode();
            }
            dataCameraSetCameraRotationMode = a;
        }
        return dataCameraSetCameraRotationMode;
    }

    public DataCameraSetCameraRotationMode a(int i) {
        this.b = i;
        return this;
    }

    public DataCameraSetCameraRotationMode a(RotationAngleType rotationAngleType) {
        this.c = rotationAngleType;
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
        cVar.n = dji.midware.data.config.P3.b.a.bI.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[2];
        this._sendData[0] = (byte) this.b;
        this._sendData[1] = (byte) this.c.a();
    }
}
