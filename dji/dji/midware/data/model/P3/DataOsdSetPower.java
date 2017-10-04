package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.i;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataOsdSetPower extends n implements e {
    private static DataOsdSetPower a = null;
    private POWER_TYPE b = POWER_TYPE.OTHER;

    public enum POWER_TYPE {
        NOT_CHANGE(0),
        SLEEP(1),
        AWEAK(2),
        RESET(3),
        POWER_OFF(4),
        OTHER(10);
        
        private int g;

        private POWER_TYPE(int i) {
            this.g = i;
        }

        public int a() {
            return this.g;
        }

        public boolean a(int i) {
            return this.g == i;
        }

        public static POWER_TYPE find(int i) {
            POWER_TYPE power_type = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return power_type;
        }
    }

    public static synchronized DataOsdSetPower getInstance() {
        DataOsdSetPower dataOsdSetPower;
        synchronized (DataOsdSetPower.class) {
            if (a == null) {
                a = new DataOsdSetPower();
            }
            dataOsdSetPower = a;
        }
        return dataOsdSetPower;
    }

    public DataOsdSetPower a(POWER_TYPE power_type) {
        this.b = power_type;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.b.a();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OFDM.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.j.a();
        cVar.n = i.a.SetPower.a();
        start(cVar, dVar);
    }
}
