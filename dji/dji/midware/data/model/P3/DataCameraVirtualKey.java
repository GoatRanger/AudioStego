package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.manager.P3.n;
import dji.midware.e.b;
import dji.midware.e.d;

public class DataCameraVirtualKey extends n implements b {
    private static DataCameraVirtualKey instance = null;
    private KEY key;

    public enum KEY {
        S1(1),
        S2(2),
        REC(3),
        DEL(4),
        MODE(5),
        UP(6),
        DOWN(7),
        LEFT(8),
        RIGHT(9),
        OK(10),
        BACK(11),
        ZOOMIN(12),
        ZOOMOUT(13),
        EnterMultiDisplay(14),
        PagePrev(15),
        PageNext(16),
        Cancel(17),
        Download(18),
        OTHER(100);
        
        private int data;

        private KEY(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static KEY find(int i) {
            KEY key = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return key;
        }
    }

    public static synchronized DataCameraVirtualKey getInstance() {
        DataCameraVirtualKey dataCameraVirtualKey;
        synchronized (DataCameraVirtualKey.class) {
            if (instance == null) {
                instance = new DataCameraVirtualKey();
            }
            dataCameraVirtualKey = instance;
        }
        return dataCameraVirtualKey;
    }

    public DataCameraVirtualKey setKey(KEY key) {
        this.key = key;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.key.value();
    }

    public void start() {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = q.b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.d.a();
        cVar.p = getSendData();
        start(cVar);
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = q.b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.d.a();
        cVar.p = getSendData();
        cVar.v = 500;
        start(cVar, dVar);
    }
}
