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

public class DataFlycWayPointMissionPauseOrResume extends n implements e {
    private static DataFlycWayPointMissionPauseOrResume instance = null;
    private CMD cmd;

    public enum CMD {
        PAUSE(0),
        RESUME(1);
        
        private int data;

        private CMD(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static CMD find(int i) {
            CMD cmd = RESUME;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cmd;
        }
    }

    public static synchronized DataFlycWayPointMissionPauseOrResume getInstance() {
        DataFlycWayPointMissionPauseOrResume dataFlycWayPointMissionPauseOrResume;
        synchronized (DataFlycWayPointMissionPauseOrResume.class) {
            if (instance == null) {
                instance = new DataFlycWayPointMissionPauseOrResume();
            }
            dataFlycWayPointMissionPauseOrResume = instance;
        }
        return dataFlycWayPointMissionPauseOrResume;
    }

    public DataFlycWayPointMissionPauseOrResume setCMD(CMD cmd) {
        this.cmd = cmd;
        return this;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.ah.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.cmd.value();
    }
}
