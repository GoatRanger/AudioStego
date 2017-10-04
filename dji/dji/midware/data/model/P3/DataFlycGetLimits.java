package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycGetLimits extends n implements e {
    private static DataFlycGetLimits instance = null;
    private MODE mode;

    public enum MODE {
        High(1),
        Far(2),
        Low(3),
        OTHER(100);
        
        private int data;

        private MODE(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static MODE find(int i) {
            MODE mode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return mode;
        }
    }

    public static synchronized DataFlycGetLimits getInstance() {
        DataFlycGetLimits dataFlycGetLimits;
        synchronized (DataFlycGetLimits.class) {
            if (instance == null) {
                instance = new DataFlycGetLimits();
            }
            dataFlycGetLimits = instance;
        }
        return dataFlycGetLimits;
    }

    public DataFlycGetLimits setMode(MODE mode) {
        this.mode = mode;
        return this;
    }

    public MODE getMode() {
        return MODE.find(this._recData[0]);
    }

    public int getValue() {
        return ((Integer) get(1, 2, Integer.class)).intValue();
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.mode.value();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.q.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
