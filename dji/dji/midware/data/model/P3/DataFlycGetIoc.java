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

public class DataFlycGetIoc extends n implements e {
    private static DataFlycGetIoc instance = null;

    public enum MODE {
        CourseLock(1),
        HomeLock(2),
        HotspotSurround(3),
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

    public static synchronized DataFlycGetIoc getInstance() {
        DataFlycGetIoc dataFlycGetIoc;
        synchronized (DataFlycGetIoc.class) {
            if (instance == null) {
                instance = new DataFlycGetIoc();
            }
            dataFlycGetIoc = instance;
        }
        return dataFlycGetIoc;
    }

    public MODE getMode() {
        return MODE.find(this._recData[0]);
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.FLYC.a();
        cVar.n = g.a.GetIoc.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
