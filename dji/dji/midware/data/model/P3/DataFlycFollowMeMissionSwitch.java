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

public class DataFlycFollowMeMissionSwitch extends n implements e {
    private static DataFlycFollowMeMissionSwitch instance = null;
    private FOLLOWMEMISSIONSWITCH missionSwitch;

    public enum FOLLOWMEMISSIONSWITCH {
        PAUSE(0),
        RESUME(1);
        
        private int data;

        private FOLLOWMEMISSIONSWITCH(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static FOLLOWMEMISSIONSWITCH find(int i) {
            FOLLOWMEMISSIONSWITCH followmemissionswitch = PAUSE;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return followmemissionswitch;
        }
    }

    public static synchronized DataFlycFollowMeMissionSwitch getInstance() {
        DataFlycFollowMeMissionSwitch dataFlycFollowMeMissionSwitch;
        synchronized (DataFlycFollowMeMissionSwitch.class) {
            if (instance == null) {
                instance = new DataFlycFollowMeMissionSwitch();
            }
            dataFlycFollowMeMissionSwitch = instance;
        }
        return dataFlycFollowMeMissionSwitch;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public DataFlycFollowMeMissionSwitch missionSwitch(FOLLOWMEMISSIONSWITCH followmemissionswitch) {
        this.missionSwitch = followmemissionswitch;
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.aw.a();
        super.start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.missionSwitch.value();
    }
}
