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

public class DataCommonControlUpgrade extends n implements e {
    private static DataCommonControlUpgrade a = null;
    private ControlCmd b;
    private DeviceType c = DeviceType.DM368;
    private int d = 0;

    public enum ControlCmd {
        Cancel(0),
        Start(1),
        Pause(2),
        Stop(3),
        StopPush(4),
        Restart(5),
        OTHER(7);
        
        private int h;

        private ControlCmd(int i) {
            this.h = i;
        }

        public int a() {
            return this.h;
        }

        public boolean a(int i) {
            return this.h == i;
        }

        public static ControlCmd find(int i) {
            ControlCmd controlCmd = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return controlCmd;
        }
    }

    public static synchronized DataCommonControlUpgrade getInstance() {
        DataCommonControlUpgrade dataCommonControlUpgrade;
        synchronized (DataCommonControlUpgrade.class) {
            if (a == null) {
                a = new DataCommonControlUpgrade();
            }
            dataCommonControlUpgrade = a;
        }
        return dataCommonControlUpgrade;
    }

    public DataCommonControlUpgrade a(ControlCmd controlCmd) {
        this.b = controlCmd;
        return this;
    }

    public DataCommonControlUpgrade a(DeviceType deviceType) {
        this.c = deviceType;
        return this;
    }

    public DataCommonControlUpgrade a(int i) {
        this.d = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.b.a();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = this.c.value();
        cVar.g = this.d;
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.a.a();
        cVar.n = dji.midware.data.config.P3.d.a.u.a();
        start(cVar, dVar);
    }
}
