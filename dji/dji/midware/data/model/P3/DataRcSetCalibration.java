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

public class DataRcSetCalibration extends n implements e {
    private static DataRcSetCalibration a = null;
    private MODE b;

    public enum MODE {
        Normal(0),
        Middle(1),
        Limits(2),
        Quit(3),
        TimeOut(4),
        OTHER(6);
        
        private int g;

        private MODE(int i) {
            this.g = i;
        }

        public int a() {
            return this.g;
        }

        public boolean a(int i) {
            return this.g == i;
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

    public static synchronized DataRcSetCalibration getInstance() {
        DataRcSetCalibration dataRcSetCalibration;
        synchronized (DataRcSetCalibration.class) {
            if (a == null) {
                a = new DataRcSetCalibration();
            }
            dataRcSetCalibration = a;
        }
        return dataRcSetCalibration;
    }

    public DataRcSetCalibration a(MODE mode) {
        this.b = mode;
        return this;
    }

    public MODE a() {
        return MODE.find(this._recData[0]);
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
        cVar.n = k.a.SetCalibration.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
