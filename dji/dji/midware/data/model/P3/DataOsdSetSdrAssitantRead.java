package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.i;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataOsdSetSdrAssitantRead extends n implements e {
    private static DataOsdSetSdrAssitantRead a = null;
    private SdrDeviceType b = SdrDeviceType.Sky;
    private SdrCpuType c = SdrCpuType.CP_A7;
    private SdrDataType d = SdrDataType.Int_Data;
    private int e = 0;

    public enum SdrCpuType {
        CP_A7(0),
        CP_X1643(1),
        CP_XC4210(2),
        AP(3),
        OTHER(100);
        
        private int f;

        private SdrCpuType(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }

        public boolean a(int i) {
            return this.f == i;
        }

        public static SdrCpuType find(int i) {
            SdrCpuType sdrCpuType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return sdrCpuType;
        }
    }

    public enum SdrDataType {
        Int_Data(0),
        Short_Data(1),
        Byte_Data(2),
        OTHER(100);
        
        private int e;

        private SdrDataType(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        public boolean a(int i) {
            return this.e == i;
        }

        public static SdrDataType find(int i) {
            SdrDataType sdrDataType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return sdrDataType;
        }
    }

    public enum SdrDeviceType {
        Sky(0),
        Ground(1),
        OTHER(100);
        
        private int d;

        private SdrDeviceType(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public boolean a(int i) {
            return this.d == i;
        }

        public static SdrDeviceType find(int i) {
            SdrDeviceType sdrDeviceType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return sdrDeviceType;
        }
    }

    public static synchronized DataOsdSetSdrAssitantRead getInstance() {
        DataOsdSetSdrAssitantRead dataOsdSetSdrAssitantRead;
        synchronized (DataOsdSetSdrAssitantRead.class) {
            if (a == null) {
                a = new DataOsdSetSdrAssitantRead();
            }
            dataOsdSetSdrAssitantRead = a;
        }
        return dataOsdSetSdrAssitantRead;
    }

    public int a() {
        return ((Integer) get(0, 4, Integer.class)).intValue();
    }

    public DataOsdSetSdrAssitantRead a(SdrDeviceType sdrDeviceType) {
        this.b = sdrDeviceType;
        return this;
    }

    public DataOsdSetSdrAssitantRead a(SdrCpuType sdrCpuType) {
        this.c = sdrCpuType;
        return this;
    }

    public DataOsdSetSdrAssitantRead a(SdrDataType sdrDataType) {
        this.d = sdrDataType;
        return this;
    }

    public DataOsdSetSdrAssitantRead a(int i) {
        this.e = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[6];
        this._sendData[0] = (byte) this.c.a();
        this._sendData[1] = (byte) this.d.a();
        System.arraycopy(c.a(this.e), 0, this._sendData, 2, 4);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        if (this.b == SdrDeviceType.Sky) {
            cVar.h = DeviceType.OFDM.value();
        } else {
            cVar.h = DeviceType.OSD.value();
        }
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.j.a();
        cVar.n = i.a.SetSdrAssitantRead.a();
        start(cVar, dVar);
    }
}
