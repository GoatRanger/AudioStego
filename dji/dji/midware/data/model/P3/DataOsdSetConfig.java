package dji.midware.data.model.P3;

import com.f.a.a.g;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.i;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataOsdSetConfig extends n implements e {
    private static DataOsdSetConfig a = null;
    private int b = 0;
    private int c;
    private int d;
    private int e;
    private boolean f;
    private int g;
    private int h;
    private int i;

    public enum KEY {
        a(1),
        FreqStep(2),
        Mcs(10),
        SingleOrDouble(11),
        BandWidthPercentage(12),
        f(13),
        WorkingFreq(16),
        OTHER(6);
        
        private int i;

        private KEY(int i) {
            this.i = i;
        }

        public int a() {
            return this.i;
        }

        public boolean a(int i) {
            return this.i == i;
        }

        public static KEY find(int i) {
            KEY key = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return key;
        }
    }

    public static synchronized DataOsdSetConfig getInstance() {
        DataOsdSetConfig dataOsdSetConfig;
        synchronized (DataOsdSetConfig.class) {
            if (a == null) {
                a = new DataOsdSetConfig();
            }
            dataOsdSetConfig = a;
        }
        return dataOsdSetConfig;
    }

    private void a() {
        this.c = 0;
        this.e = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
    }

    public DataOsdSetConfig a(int i) {
        a();
        this.e = i;
        this._sendData = new byte[4];
        this._sendData[0] = (byte) 3;
        this._sendData[1] = (byte) 0;
        this._sendData[2] = (byte) 10;
        this._sendData[3] = (byte) this.e;
        return this;
    }

    public DataOsdSetConfig b(int i) {
        a();
        this.g = i;
        this._sendData = new byte[4];
        this._sendData[0] = (byte) 3;
        this._sendData[1] = (byte) 0;
        this._sendData[2] = (byte) 14;
        this._sendData[3] = (byte) this.g;
        return this;
    }

    public DataOsdSetConfig a(int i, int i2, int i3) {
        a();
        this.d = i;
        this.e = i2;
        this.g = i3;
        this._sendData = new byte[8];
        this._sendData[0] = (byte) 3;
        this._sendData[1] = (byte) 0;
        this._sendData[2] = (byte) 1;
        this._sendData[3] = (byte) i;
        this._sendData[4] = (byte) 10;
        this._sendData[5] = (byte) i2;
        this._sendData[6] = (byte) 14;
        this._sendData[7] = (byte) i3;
        return this;
    }

    public DataOsdSetConfig a(int i, int i2) {
        a();
        this._sendData = new byte[2];
        if (i == 1) {
            this._sendData[0] = (byte) 15;
            this.h = i2;
        } else {
            this._sendData[0] = (byte) 2;
            this.i = i2;
        }
        this._sendData[1] = (byte) i2;
        return this;
    }

    public DataOsdSetConfig c(int i) {
        this.b = i;
        return this;
    }

    public DataOsdSetConfig a(boolean z) {
        a();
        this.c = z ? 1 : 0;
        if (z) {
            this._sendData = new byte[4];
            this._sendData[0] = (byte) 3;
            this._sendData[1] = (byte) this.c;
            this._sendData[2] = (byte) 1;
            this._sendData[3] = (byte) this.d;
        } else {
            this._sendData = new byte[2];
            this._sendData[0] = (byte) 3;
            this._sendData[1] = (byte) this.c;
        }
        return this;
    }

    public DataOsdSetConfig d(int i) {
        a();
        this.d = i;
        this._sendData = new byte[4];
        this._sendData[0] = (byte) 3;
        this._sendData[1] = (byte) 0;
        this._sendData[2] = (byte) 1;
        this._sendData[3] = (byte) this.d;
        return this;
    }

    public DataOsdSetConfig e(int i) {
        a();
        this.e = i;
        this._sendData = new byte[2];
        this._sendData[0] = (byte) 10;
        this._sendData[1] = (byte) this.e;
        return this;
    }

    public DataOsdSetConfig b(boolean z) {
        int i = 0;
        a();
        this.f = z;
        this._sendData = new byte[2];
        this._sendData[0] = g.STRUCT_END;
        byte[] bArr = this._sendData;
        if (this.f) {
            i = 1;
        }
        bArr[1] = (byte) i;
        return this;
    }

    public DataOsdSetConfig f(int i) {
        this._sendData = new byte[2];
        this._sendData[0] = g.ZERO_TAG;
        this._sendData[1] = (byte) i;
        return this;
    }

    public DataOsdSetConfig g(int i) {
        this._sendData = new byte[2];
        this._sendData[0] = g.SIMPLE_LIST;
        this._sendData[1] = (byte) i;
        return this;
    }

    public DataOsdSetConfig b(int i, int i2) {
        this._sendData = new byte[4];
        this._sendData[0] = g.ZERO_TAG;
        this._sendData[1] = (byte) i;
        this._sendData[2] = g.SIMPLE_LIST;
        this._sendData[3] = (byte) i2;
        return this;
    }

    public DataOsdSetConfig h(int i) {
        this._sendData = new byte[2];
        this._sendData[0] = (byte) KEY.WorkingFreq.a();
        this._sendData[1] = (byte) i;
        return this;
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        if (this.b == 1) {
            cVar.h = DeviceType.OFDM.value();
        } else {
            cVar.h = DeviceType.OSD.value();
        }
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.j.a();
        cVar.n = i.a.SetConfig.a();
        start(cVar, dVar);
    }
}
