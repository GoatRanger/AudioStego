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

public class DataOsdSetUpgradeTip extends n implements e {
    private static DataOsdSetUpgradeTip a = null;
    private UPGRADETIP b = UPGRADETIP.START;

    public enum UPGRADETIP {
        START(1),
        SUCCESS(2),
        FAIL(3);
        
        private int d;

        private UPGRADETIP(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public boolean a(int i) {
            return this.d == i;
        }

        public static UPGRADETIP find(int i) {
            UPGRADETIP upgradetip = START;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return upgradetip;
        }
    }

    public static synchronized DataOsdSetUpgradeTip getInstance() {
        DataOsdSetUpgradeTip dataOsdSetUpgradeTip;
        synchronized (DataOsdSetUpgradeTip.class) {
            if (a == null) {
                a = new DataOsdSetUpgradeTip();
            }
            dataOsdSetUpgradeTip = a;
        }
        return dataOsdSetUpgradeTip;
    }

    public DataOsdSetUpgradeTip a(UPGRADETIP upgradetip) {
        this.b = upgradetip;
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
        cVar.m = p.j.a();
        cVar.n = i.a.SetUpgradeTip.a();
        cVar.w = 1;
        cVar.v = 300;
        start(cVar, dVar);
    }
}
