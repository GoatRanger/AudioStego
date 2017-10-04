package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.o;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataWifiSetPowerMode extends n implements e {
    private static DataWifiSetPowerMode a = null;
    private DJIWifiPowerMode b = null;

    public enum DJIWifiPowerMode {
        FCC(0),
        CE(1),
        OTHER(100);
        
        private int d;

        private DJIWifiPowerMode(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public boolean a(int i) {
            return this.d == i;
        }

        public static DJIWifiPowerMode find(int i) {
            DJIWifiPowerMode dJIWifiPowerMode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return dJIWifiPowerMode;
        }
    }

    public static synchronized DataWifiSetPowerMode getInstance() {
        DataWifiSetPowerMode dataWifiSetPowerMode;
        synchronized (DataWifiSetPowerMode.class) {
            if (a == null) {
                a = new DataWifiSetPowerMode();
            }
            dataWifiSetPowerMode = a;
        }
        return dataWifiSetPowerMode;
    }

    public DataWifiSetPowerMode a(DJIWifiPowerMode dJIWifiPowerMode) {
        this.b = dJIWifiPowerMode;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.b.a();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.WIFI_G.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.h.a();
        cVar.n = o.a.SetPowerMode.a();
        start(cVar, dVar);
    }
}
