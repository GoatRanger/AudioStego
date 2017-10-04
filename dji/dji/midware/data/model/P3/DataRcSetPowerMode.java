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

public class DataRcSetPowerMode extends n implements e {
    private static DataRcSetPowerMode a = null;
    private DJIRcPowerMode b = null;

    public enum DJIRcPowerMode {
        CE(0),
        FCC(1),
        OTHER(100);
        
        private int d;

        private DJIRcPowerMode(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public boolean a(int i) {
            return this.d == i;
        }

        public static DJIRcPowerMode find(int i) {
            DJIRcPowerMode dJIRcPowerMode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return dJIRcPowerMode;
        }
    }

    public static synchronized DataRcSetPowerMode getInstance() {
        DataRcSetPowerMode dataRcSetPowerMode;
        synchronized (DataRcSetPowerMode.class) {
            if (a == null) {
                a = new DataRcSetPowerMode();
            }
            dataRcSetPowerMode = a;
        }
        return dataRcSetPowerMode;
    }

    public DataRcSetPowerMode a(DJIRcPowerMode dJIRcPowerMode) {
        this.b = dJIRcPowerMode;
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
        cVar.n = k.a.SetPowerMode.b();
        start(cVar, dVar);
    }
}
