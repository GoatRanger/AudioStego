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

public class DataRcSetMaster extends n implements e {
    private static DataRcSetMaster a = null;
    private MODE b;

    public enum MODE {
        Master(0),
        Slave(1),
        Monitor(2),
        OTHER(6);
        
        private int e;

        private MODE(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        public boolean a(int i) {
            return this.e == i;
        }

        public static MODE find(int i) {
            MODE mode = Master;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return mode;
        }
    }

    public static synchronized DataRcSetMaster getInstance() {
        DataRcSetMaster dataRcSetMaster;
        synchronized (DataRcSetMaster.class) {
            if (a == null) {
                a = new DataRcSetMaster();
            }
            dataRcSetMaster = a;
        }
        return dataRcSetMaster;
    }

    public DataRcSetMaster a(MODE mode) {
        this.b = mode;
        return this;
    }

    public MODE a() {
        return MODE.find(this._recData[0]);
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) (this.b.a() << 6);
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.RC.a();
        cVar.n = k.a.f.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
