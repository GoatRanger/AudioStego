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
import lecho.lib.hellocharts.model.h;

public class DataCameraGetImageSize extends n implements e {
    private static DataCameraGetImageSize instance = null;

    public enum RatioType {
        R_4_3(0, h.l),
        R_16_9(1, 0.5625f),
        R_3_2(2, 0.6666667f),
        OTHER(6, 0.5625f);
        
        private int data;
        private float mRatio;

        private RatioType(int i, float f) {
            this.mRatio = 0.5625f;
            this.data = i;
            this.mRatio = f;
        }

        public float getRatioNumber() {
            return this.mRatio;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static RatioType find(int i) {
            RatioType ratioType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return ratioType;
        }
    }

    public enum SizeType {
        DEFAULT(0),
        SMALLEST(1),
        SMALL(2),
        MIDDLE(3),
        LARGE(4),
        LARGEST(5),
        OTHER(6);
        
        private int data;

        private SizeType(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static SizeType find(int i) {
            SizeType sizeType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return sizeType;
        }
    }

    public static synchronized DataCameraGetImageSize getInstance() {
        DataCameraGetImageSize dataCameraGetImageSize;
        synchronized (DataCameraGetImageSize.class) {
            if (instance == null) {
                instance = new DataCameraGetImageSize();
            }
            dataCameraGetImageSize = instance;
        }
        return dataCameraGetImageSize;
    }

    public SizeType getSize() {
        return SizeType.find(this._recData[0]);
    }

    public RatioType getRatio() {
        return RatioType.find(this._recData[1]);
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
        cVar.n = dji.midware.data.config.P3.b.a.j.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
