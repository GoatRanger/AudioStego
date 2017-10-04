package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataRcSetGimbalControlMode extends n implements e {
    private static DataRcSetGimbalControlMode a = null;
    private MODE b;

    public enum MODE {
        Pitch(0),
        Roll(1),
        Yaw(2),
        OTHER(10);
        
        private int e;

        private MODE(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        public boolean a(int i) {
            return this.e == i;
        }

        public static MODE find(int i) {
            MODE mode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return mode;
        }
    }

    public static synchronized DataRcSetGimbalControlMode getInstance() {
        DataRcSetGimbalControlMode dataRcSetGimbalControlMode;
        synchronized (DataRcSetGimbalControlMode.class) {
            if (a == null) {
                a = new DataRcSetGimbalControlMode();
            }
            dataRcSetGimbalControlMode = a;
        }
        return dataRcSetGimbalControlMode;
    }

    public DataRcSetGimbalControlMode a(MODE mode) {
        this.b = mode;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.b.a();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.SetGimbalControlMode.b();
        start(cVar, dVar);
    }
}
