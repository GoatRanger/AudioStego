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

public class DataFlycNavigationSwitch extends n implements e {
    private static DataFlycNavigationSwitch instance = null;
    private int mRetryTimes = 2;
    private GS_COMMAND type;

    public enum GS_COMMAND {
        OPEN_GROUND_STATION(1),
        CLOSE_GROUND_STATION(2),
        OTHER(100);
        
        private int data;

        private GS_COMMAND(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static GS_COMMAND find(int i) {
            GS_COMMAND gs_command = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return gs_command;
        }
    }

    public static synchronized DataFlycNavigationSwitch getInstance() {
        DataFlycNavigationSwitch dataFlycNavigationSwitch;
        synchronized (DataFlycNavigationSwitch.class) {
            if (instance == null) {
                instance = new DataFlycNavigationSwitch();
            }
            dataFlycNavigationSwitch = instance;
        }
        return dataFlycNavigationSwitch;
    }

    public DataFlycNavigationSwitch setCommand(GS_COMMAND gs_command) {
        this.type = gs_command;
        return this;
    }

    public DataFlycNavigationSwitch setRetryTimes(int i) {
        this.mRetryTimes = i;
        return this;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.type.value();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.aa.a();
        cVar.p = getSendData();
        cVar.w = this.mRetryTimes;
        this.mRetryTimes = 2;
        start(cVar, dVar);
    }
}
