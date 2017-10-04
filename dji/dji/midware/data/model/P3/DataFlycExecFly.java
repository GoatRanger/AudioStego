package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataFlycExecFly extends n implements e {
    private TYPE type;

    public enum TYPE {
        PAUSE_FLY(0),
        RESUME_FLY(1),
        AUTO_LANDING(3),
        ENTER_SINGAL(4),
        OUT_SINGAL(5),
        START_FLY(7),
        START_TURN(8),
        OTHER(100);
        
        private int data;

        private TYPE(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static TYPE find(int i) {
            TYPE type = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return type;
        }
    }

    public static DataFlycExecFly factory() {
        return new DataFlycExecFly();
    }

    public DataFlycExecFly setType(TYPE type) {
        this.type = type;
        return this;
    }

    public int getAckStatus() {
        return c.b(this._recData);
    }

    protected void doPack() {
        this._sendData = c.a(this.type.value());
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.l.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
