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

public class DataFlycGetVoltageWarnning extends n implements e {
    private static DataFlycGetVoltageWarnning instance = null;
    private WarnningLevel level;

    public enum WarnningLevel {
        First(1),
        Second(2),
        OTHER(100);
        
        private int data;

        private WarnningLevel(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static WarnningLevel find(int i) {
            WarnningLevel warnningLevel = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return warnningLevel;
        }
    }

    public static synchronized DataFlycGetVoltageWarnning getInstance() {
        DataFlycGetVoltageWarnning dataFlycGetVoltageWarnning;
        synchronized (DataFlycGetVoltageWarnning.class) {
            if (instance == null) {
                instance = new DataFlycGetVoltageWarnning();
            }
            dataFlycGetVoltageWarnning = instance;
        }
        return dataFlycGetVoltageWarnning;
    }

    public void setWarnningLevel(WarnningLevel warnningLevel) {
        this.level = warnningLevel;
    }

    public WarnningLevel getWarnningLevel() {
        return WarnningLevel.find(this._recData[0]);
    }

    public int getValue() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public boolean isNeedGoHome() {
        return this._recData[2] == (byte) 1;
    }

    public boolean isNeedLanding() {
        return this._recData[2] == (byte) 2;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.level.value();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.s.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
