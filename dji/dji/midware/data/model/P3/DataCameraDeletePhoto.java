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

public class DataCameraDeletePhoto extends n implements e {
    private static DataCameraDeletePhoto instance = null;
    private int index;
    private TYPE type;

    public enum TYPE {
        SingleOk(0),
        MultipleOk(1),
        GoMultiple(2),
        OutMultiple(3),
        MultipleIndex(4),
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

    public static synchronized DataCameraDeletePhoto getInstance() {
        DataCameraDeletePhoto dataCameraDeletePhoto;
        synchronized (DataCameraDeletePhoto.class) {
            if (instance == null) {
                instance = new DataCameraDeletePhoto();
            }
            dataCameraDeletePhoto = instance;
        }
        return dataCameraDeletePhoto;
    }

    public DataCameraDeletePhoto setType(TYPE type) {
        this.type = type;
        return this;
    }

    public DataCameraDeletePhoto setIndex(int i) {
        this.index = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[2];
        this._sendData[0] = (byte) this.type.value();
        this._sendData[1] = (byte) this.index;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.aI.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
