package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraGetIso extends n implements e {
    private static DataCameraGetIso instance = null;

    public enum TYPE {
        AUTO(0),
        AUTOHIGH(1),
        ISO50(2),
        ISO100(3),
        ISO200(4),
        ISO400(5),
        ISO800(6),
        ISO1600(7),
        ISO3200(8),
        ISO6400(9),
        ISO12800(10),
        ISO25600(11),
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

    public static synchronized DataCameraGetIso getInstance() {
        DataCameraGetIso dataCameraGetIso;
        synchronized (DataCameraGetIso.class) {
            if (instance == null) {
                instance = new DataCameraGetIso();
            }
            dataCameraGetIso = instance;
        }
        return dataCameraGetIso;
    }

    public TYPE getType() {
        return TYPE.find(((Short) get(0, 1, Short.class)).shortValue());
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.H.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
