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

public class DataCameraGetWhiteBalance extends n implements e {
    private static DataCameraGetWhiteBalance instance = null;

    public enum ColorType {
        T2000K(0),
        T2500K(1),
        T3000K(2),
        T3500K(3),
        T4000K(4),
        T4500K(5),
        T5000K(6),
        T5500K(7),
        T6000K(8),
        T6500K(9),
        T7000K(10),
        T7500K(11),
        T8000K(12),
        T8500K(13),
        T9000K(14),
        T9500K(15),
        T10000K(16),
        OTHER(100);
        
        private int data;

        private ColorType(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static ColorType find(int i) {
            ColorType colorType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return colorType;
        }
    }

    public enum Type {
        AUTO(0),
        FineDay(1),
        Cloudy(2),
        Water(3),
        Filament(4),
        Fluorescent(5),
        Color(6),
        OTHER(100);
        
        private int data;

        private Type(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static Type find(int i) {
            Type type = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return type;
        }
    }

    public static synchronized DataCameraGetWhiteBalance getInstance() {
        DataCameraGetWhiteBalance dataCameraGetWhiteBalance;
        synchronized (DataCameraGetWhiteBalance.class) {
            if (instance == null) {
                instance = new DataCameraGetWhiteBalance();
            }
            dataCameraGetWhiteBalance = instance;
        }
        return dataCameraGetWhiteBalance;
    }

    public Type getType() {
        return Type.find(((Short) get(0, 1, Short.class)).shortValue());
    }

    public ColorType getColorType() {
        return ColorType.find(((Short) get(1, 1, Short.class)).shortValue());
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
        cVar.n = dji.midware.data.config.P3.b.a.J.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
